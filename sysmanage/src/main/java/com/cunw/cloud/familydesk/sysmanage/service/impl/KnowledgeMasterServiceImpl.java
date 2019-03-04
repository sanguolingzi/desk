package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.auth.shiro.realm.UserPayloadUtils;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.model.ResourceKnowledge;
import com.cunw.cloud.resource.model.question.QueryAccuracyRanking;
import com.cunw.cloud.resource.model.question.QueryKnowledgeRanking;
import com.cunw.cloud.familydesk.common.dto.DirStar;
import com.cunw.cloud.familydesk.common.model.KnowledgeMaster;
import com.cunw.cloud.familydesk.sysmanage.mapper.KnowledgeMasterMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IKnowledgeMasterService;
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
 * KnowledgeMaster服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：KnowledgeMaster服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "knowledgeMasterService")
public class KnowledgeMasterServiceImpl extends BaseServiceImpl<KnowledgeMaster, String> implements IKnowledgeMasterService {

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Autowired
    private KnowledgeMasterMapper knowledgeMasterMapper;

    @Override
    protected IBaseMapper<KnowledgeMaster, String> getMapper() {
        return knowledgeMasterMapper;
    }

    @Override
    public List queryKnowledges(String studentCode, String subjectId, String upid) {
        // 查学生年级
        Map<String, Object> params = new HashMap<>();
        params.put("subjectId", subjectId);
        params.put("upid", upid);
        // 根据年级、上级id查知识点
        List<ResourceKnowledge> list = ServerRestTemplate.getList(resourceServerUrl + "/resource/dirs/knowledges", ResourceKnowledge.class, params);
        if(CollectionUtils.isNotEmpty(list)){
            // 查知识点掌握情况
            List<Integer> knowledgeId = list.stream()
                    .map(document -> document.getId())
                    .collect(Collectors.toList());
            List<KnowledgeMaster> knowledgeMasters = knowledgeMasterMapper.selectByDirIds(studentCode, knowledgeId);
            Map<String, KnowledgeMaster> map = knowledgeMasters.stream().collect(Collectors.toMap(KnowledgeMaster::getKnowledgeId, Function.identity()));
            // 合并情况
            List<DirStar> retList = list.stream().map(knowledge -> {
                DirStar star = new DirStar();
                star.setDirId(knowledge.getId().toString());
                star.setName(knowledge.getName());
                star.setPDirId(knowledge.getUpid().toString());
                star.setType(knowledge.getType());
                KnowledgeMaster k = map.get(knowledge.getId().toString());
                if (k != null) {
                    star.setScore(k.getScore());
                    star.setStar(k.getStar());
                    star.setRank(k.getRank());
                    star.setType(0);
                    //根据等级设置当前等级下是否有题目
                    if(k.getRank() == 0){
                        if( knowledge.getBaseQuestCount() != null && knowledge.getBaseQuestCount() >= 5)
                            star.setType(1);
                    }else if(k.getRank() == 1){
                        if(knowledge.getMediumQuestCount() != null && knowledge.getMediumQuestCount() >= 5)
                            star.setType(1);
                    }else if(k.getRank() == 2){
                        if(knowledge.getDifficultQuestCount() != null && knowledge.getDifficultQuestCount() >= 5)
                            star.setType(1);
                    }else{
                        star.setType(0);
                    }
                }else{
                    star.setScore(0);
                    star.setStar(0);
                    star.setRank(0);
                    if(knowledge.getBaseQuestCount() != null && knowledge.getBaseQuestCount() >= 5){
                        star.setType(1);
                    }else{
                        star.setType(0);
                    }
                }
                star.setLeaf(knowledge.getLeaf());
                star.setPathId(knowledge.getPath());
                return star;
            }).collect(Collectors.toList());
            return retList;
        }
        return null;
    }

    @Override
    public KnowledgeMaster getAndInit(String studentCode, String subjectId, String knowledgeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("studentCode", studentCode);
        params.put("knowledgeId", knowledgeId);
        KnowledgeMaster knowledgeMaster = getByParams(params);
        if (subjectId != null && knowledgeMaster == null) {
            knowledgeMaster = new KnowledgeMaster();
            knowledgeMaster.setKnowledgeId(genID());
            knowledgeMaster.setStudentCode(studentCode);
            knowledgeMaster.setSubjectId(subjectId);
            knowledgeMaster.setKnowledgeId(knowledgeId);
            knowledgeMaster.setStar(0);
            knowledgeMaster.setScore(0);
            knowledgeMaster.setCrtDate(new Date());
            knowledgeMaster.setCrtUserCode(UserPayloadUtils.getCurrUser().getLoginName());
            // 层级默认从0开始
            knowledgeMaster.setRank(0);
            add(knowledgeMaster);
            log.info("[getTestQuestion] > impl [getAndInit] > (knowledgeMaster)={} ", JsonMapper.nonEmptyMapper().toJson(knowledgeMaster));
        }
        return knowledgeMaster;
    }

    @Override
    public List<QueryKnowledgeRanking> getByCorrectRate(String studentCode, String knowledgeId) {
        return knowledgeMasterMapper.selectByCorrectRate(studentCode,knowledgeId);
    }

    @Override
    public List<QueryKnowledgeRanking> getByCorrectRateMax(String studentCode, String knowledgeId) {
        return knowledgeMasterMapper.selectByCorrectRateMax(studentCode,knowledgeId);
    }

    @Override
    public Integer selectByRank(String studentCode, String knowledgeId) {
        return knowledgeMasterMapper.selectByRank(studentCode,knowledgeId);
    }

    @Override
    public Integer selectByRankCount(Integer rank) {
        return knowledgeMasterMapper.selectByRankCount(rank);
    }

    @Override
    public List<KnowledgeMaster> selectByStudetArea(String studentCode) {
        return knowledgeMasterMapper.selectByStudetArea(studentCode);
    }

    @Override
    public List<QueryKnowledgeRanking> selectByStudentRanking(Map<String, Object> params) {
        return knowledgeMasterMapper.selectByStudentRanking(params);
    }

    @Override
    public Integer selectByStudentRank(Map<String, Object> params) {
        return knowledgeMasterMapper.selectByStudentRank(params);
    }

    @Override
    public List<QueryAccuracyRanking> selectByStudentRankStar(Map<String, Object> params) {
        return knowledgeMasterMapper.selectByStudentRankStar(params);
    }

    @Override
    public void updateRank(KnowledgeMaster knowledgeMaster) {
        knowledgeMasterMapper.updateRank(knowledgeMaster);
    }


}
