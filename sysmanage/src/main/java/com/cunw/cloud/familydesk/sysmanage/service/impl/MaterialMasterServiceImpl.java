package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.SysStudent;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentService;
import com.cunw.cloud.framework.auth.shiro.realm.UserPayloadUtils;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.model.Dir;
import com.cunw.cloud.familydesk.common.dto.DirStar;
import com.cunw.cloud.familydesk.common.model.MaterialMaster;
import com.cunw.cloud.familydesk.sysmanage.mapper.MaterialMasterMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IMaterialMasterService;
import com.cunw.cloud.sysmanage.general.sysorg.model.SysOrg;
import com.cunw.cloud.sysmanage.general.sysorg.service.ISysOrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * MaterialMaster服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：MaterialMaster服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "materialMasterService")
public class MaterialMasterServiceImpl extends BaseServiceImpl<MaterialMaster, String> implements IMaterialMasterService {

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Autowired
    private MaterialMasterMapper materialMasterMapper;

    @Autowired
    private ISysStudentService sysStudentService;

    @Autowired
    private ISysOrgService sysOrgService;

    @Override
    protected IBaseMapper<MaterialMaster, String> getMapper() {
        return materialMasterMapper;
    }

    public List queryChapters(String studentCode, String pDirId) {
        // 查学生年级
        Map<String, Object> params = new HashMap<>();
        SysStudent student = sysStudentService.getByProperty("studentCode", studentCode);
        if(student != null){
            SysOrg org = sysOrgService.getByProperty("orgCode", student.getGradeCode());
            params.put("grade", org.getGrade());
            params.put("pDirId", pDirId);
            // 根据年级、上级id查章节
            List<Dir> list = ServerRestTemplate.getList(resourceServerUrl + "/resource/dirs/chapters", Dir.class, params);
            if(CollectionUtils.isNotEmpty(list)){
                // 查同步教材掌握情况
                List<String> dirIds = list.stream()
                        .map(document -> document.getId())
                        .collect(Collectors.toList());
                List<MaterialMaster> materialMasters = materialMasterMapper.selectByDirIds(studentCode, dirIds);
                Map<String, MaterialMaster> map = materialMasters.stream().collect(Collectors.toMap(MaterialMaster::getChapterId, Function.identity()));
                // 合并情况
                List<DirStar> retList = list.stream().map(dir -> {
                    DirStar star = new DirStar();
                    star.setDirId(dir.getDirId());
                    star.setName(dir.getDirName());
                    star.setPDirId(dir.getpDirId());
                    MaterialMaster m = map.get(dir.getDirId());
                    if (m != null) {
                        star.setScore(m.getScore());
                        star.setStar(m.getStar());
                        star.setRank(m.getRank());
                        star.setType(0);
                        if(m.getRank() == 0 ){
                            if(dir.getBaseQuestCount() != null && dir.getBaseQuestCount() >= 5)
                                star.setType(1);
                        }else if(m.getRank() == 1){
                            if(dir.getMediumQuestCount() != null && dir.getMediumQuestCount() >= 5)
                                star.setType(1);
                        }else if(m.getRank() == 2){
                            if(dir.getDifficultQuestCount() != null && dir.getDifficultQuestCount() >= 5)
                                star.setType(1);
                        }else{
                            star.setType(0);
                        }
                    }else{
                        star.setScore(0);
                        star.setStar(0);
                        star.setRank(0);
                        if(dir.getBaseQuestCount() != null && dir.getBaseQuestCount() >= 5){
                            star.setType(1);
                        }else{
                            star.setType(0);
                        }
                    }
                    star.setLeaf(dir.getLeaf());
                    star.setPathId(dir.getPathId());
                    star.setPathName(dir.getPathName());
                    star.setLevel(dir.getLevel());
                    return star;
                }).collect(Collectors.toList());
                log.info("call [ queryChapters ] serviceImpl List<DirStar> return={}", JsonMapper.nonEmptyMapper().toJson(retList));
                return retList;
            }
        }
        return null;
    }

    @Override
    public MaterialMaster getAndInit(String studentCode, String subjectId, String chapterId) {
        Map<String, Object> params = new HashMap<>();
        params.put("studentCode", studentCode);
        params.put("chapterId", chapterId);
        MaterialMaster materialMaster = getByParams(params);
        if (subjectId != null && materialMaster == null) {
            materialMaster = new MaterialMaster();
            materialMaster.setMasterId(genID());
            materialMaster.setStudentCode(studentCode);
            materialMaster.setSubjectId(subjectId);
            materialMaster.setChapterId(chapterId);
            materialMaster.setStar(0);
            materialMaster.setScore(0);
            materialMaster.setCrtDate(new Date());
            materialMaster.setCrtUserCode(UserPayloadUtils.getCurrUser().getLoginName());
            // 层级默认从0开始
            materialMaster.setRank(0);
            add(materialMaster);
            log.info("[getTestQuestion] > impl [getAndInit] > (master)={} ", materialMaster.toString());
        }
        return materialMaster;
    }

    @Override
    public void updateRank(MaterialMaster materialMaster) {
        materialMasterMapper.updateRank(materialMaster);
    }
}