package com.cunw.cloud.familydesk.business.service.impl;

import com.cunw.cloud.familydesk.business.service.IBusiStudentDiaryResourceService;
import com.cunw.cloud.familydesk.common.model.BusiStudentDiaryResource;
import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import com.cunw.cloud.familydesk.common.vo.StudentDiaryVO;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.BusiStudentDiary;
import com.cunw.cloud.familydesk.business.mapper.BusiStudentDiaryMapper;
import com.cunw.cloud.familydesk.business.service.IBusiStudentDiaryService;
import com.cunw.cloud.framework.service.pagehelper.PageHelper;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * BusiStudentDiary服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiStudentDiary服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "busiStudentDiaryService")
public class BusiStudentDiaryServiceImpl extends BaseServiceImpl<BusiStudentDiary, String> implements IBusiStudentDiaryService {

    @Autowired
    private BusiStudentDiaryMapper busiStudentDiaryMapper;

    @Autowired
    private IBusiUploadFileInfoService busiUploadFileInfoService;

    @Autowired
    private IBusiStudentDiaryResourceService busiStudentDiaryResourceService;

    @Autowired
    private IdGenerator IdGenerator;

    @Value("${server.static.url}")
    private String staticUrl;

    @Override
    protected IBaseMapper<BusiStudentDiary, String> getMapper() {
        return busiStudentDiaryMapper;
    }

    @Override
    public StudentDiaryVO getDiaryInfo(String diaryId) {
        StudentDiaryVO studentDiaryVO = new StudentDiaryVO();
        List<String> pathList = new ArrayList<>();
        //日记主键 diaryId
        //根据主键 查询 日记
        BusiStudentDiary busiStudentDiary =  busiStudentDiaryMapper.selectByPrimaryKey(diaryId);
        if (busiStudentDiary == null){
            return studentDiaryVO;
        }
        //根据主键 查询 日记资源
        //调用selectParameter
        Map<String,Object> parameters= new HashMap<>();
        parameters.put("diaryId",diaryId);
        List<BusiStudentDiaryResource> busiStudentDiaryResourceList = busiStudentDiaryResourceService.selectByParameters(parameters);
        if (busiStudentDiaryResourceList != null && !busiStudentDiaryResourceList.isEmpty()){
            for(BusiStudentDiaryResource busiStudentDiaryResource : busiStudentDiaryResourceList ){
                //根据 fileId 查询
                //根据日记资源的fileId 查询 path
                busiStudentDiaryResource.getFileId();
                // 拿到path 拼接 staticUrl
                BusiUploadFileInfo busiUploadFileInfo = busiUploadFileInfoService.selectByPrimaryKey(busiStudentDiaryResource.getFileId());
                //把拼接结果放入 StudentDiaryVO 中的 list
                // 拿到path 拼接 staticUrl
                pathList.add(staticUrl + busiUploadFileInfo.getPath());
            }
        }
        studentDiaryVO.setDiaryMood(busiStudentDiary.getDiaryMood());
        studentDiaryVO.setDiaryTitle(busiStudentDiary.getDiaryTitle());
        studentDiaryVO.setDiaryText(busiStudentDiary.getDiaryText());
        studentDiaryVO.setCrtDate(busiStudentDiary.getCrtDate());
        studentDiaryVO.setDiaryStudentId(busiStudentDiary.getDiaryStudentId());
        studentDiaryVO.setDiaryId(busiStudentDiary.getDiaryId());
        studentDiaryVO.setFileUrlList(pathList);
        return studentDiaryVO;
    }

    @Override
     public PageList<BusiStudentDiary>  getStudentDiaryList(String diaryStudentId, Integer pageNum, Integer pageSize) {
        // 分页设置
        PageHelper.startPage(pageNum, pageSize);
        // 只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select方法）方法会被分页
        List<BusiStudentDiary> list = busiStudentDiaryMapper.seleStudentDiaryList(diaryStudentId);
        // 分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，或者使用PageInfo类
        PageList pageInfo = new PageList(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public void insertStudentDiary(BusiStudentDiary busiStudentDiary, String fileIds) {
        BusiStudentDiaryResource busiStudentDiaryResource = new BusiStudentDiaryResource();
        //组合页面信息
        //标题 表情 内容 + 多个fileId
        String[] fileIdsArr = null;
        String id = IdGenerator.getNextStr();
        busiStudentDiary.setId(id);
        busiStudentDiaryMapper.insertSelective(busiStudentDiary);
        if(fileIds !=null && !"".equals(fileIds.trim())){
            fileIdsArr = fileIds.split(",");
            //遍历多个fileId
            for(String fileId:fileIdsArr){
                busiStudentDiaryResource.setId(IdGenerator.getNextStr());
                busiStudentDiaryResource.setDiaryId(id);
                busiStudentDiaryResource.setFileId(fileId);//
                //插入日记资源
                busiStudentDiaryResourceService.addSelective(busiStudentDiaryResource);
                //标明资源是否有用
                busiUploadFileInfoService.updateFileInfoReferences(fileId);
            }
        }
    }
}
