package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.cachedata.data.StageGradeCacheData;
import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysStudentMapper;
import com.cunw.cloud.familydesk.sysmanage.pojo.StudentInfoPOJO;
import com.cunw.cloud.familydesk.sysmanage.service.*;
import com.cunw.cloud.familydesk.utils.PinYinUtil;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.jdbc.datasource.TargetDataSource;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.model.CloudItem;
import com.cunw.cloud.resource.model.KnowledgeName;
import com.cunw.cloud.resource.model.question.KnowledgeRanking;
import com.cunw.cloud.resource.model.question.QueryAccuracyRanking;
import com.cunw.cloud.resource.model.question.QueryKnowledgeRanking;
import com.cunw.cloud.school.common.model.FavorFile;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SysStudent服务实现：
 *
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:59
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysStudent服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysStudentService")
@Slf4j
public class SysStudentServiceImpl extends BaseServiceImpl<SysStudent, String> implements ISysStudentService {

    @Autowired
    private SysStudentMapper sysStudentMapper;

    @Autowired
    private ISysStudentParentService spService;

    @Autowired
    private ISysSchoolService sysSchoolService;

    @Autowired
    private IFavorFileService favorFileService;

    @Autowired
    IStudyTestAnswerService studyTestAnswerService;

    @Autowired
    private IKnowledgeMasterService iKnowledgeMasterService;

    @Autowired
    private ISysStudentParentService sysStudentParentService;

    @Autowired
    private ISysStudentParentUnbindService studentParentUnbindService;

    @Autowired
    private StageGradeCacheData stageGradeCacheData;

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Override
    protected IBaseMapper<SysStudent, String> getMapper() {
        return sysStudentMapper;
    }

    @Override
    public PageList<CloudItem> queryCloudfile(String studentCode, Map<String, Object> params) {
        PageList<CloudItem> pageList = ServerRestTemplate.getPageList(resourceServerUrl + "/resource/newclouditems/list", CloudItem.class, params);
        List<CloudItem> files = pageList.getList();
        List<String> fileIds = files.stream()
                .map(dto -> dto.getItemId())
                .collect(Collectors.toList());
        Map<String, Object> favParams = new HashMap<>();
        favParams.put("userCode", studentCode);
        favParams.put("fileIds", fileIds);
        List<FavorFile> favorResFiles = favorFileService.query(favParams);
        Map<String, String> resIdFavorIdMap = favorResFiles.stream().collect(Collectors.toMap(FavorFile::getFileId, FavorFile::getFavorFileId));
        files.stream().forEach(dto -> dto.setFavorFileId(resIdFavorIdMap.get(dto.getItemId())));
        return pageList;
    }

    @Override
    @Transactional
    public SysStudentVO save(StudentInfoPOJO studentInfo) throws Exception {

        //构造学生信息
        SysStudent sysStudent = new SysStudent();
        String id = genID();
        sysStudent.setId(id);
        String stuName = studentInfo.getStuName();
        sysStudent.setStudentCode(id);
        sysStudent.setStudentName(stuName);
        sysStudent.setAge(studentInfo.getAge());
        sysStudent.setSex(studentInfo.getSex());
        sysStudent.setSchoolName(studentInfo.getSchoolName());
        sysStudent.setGradeCode(studentInfo.getGradeCode());
        sysStudent.setStudentNamePy(PinYinUtil.getPingYin(stuName));
        sysStudent.setHeadImg(studentInfo.getHeadImg());
        this.addSelective(sysStudent);

        //构造学生家长关系表信息
        SysStudentParent sp = new SysStudentParent();
        sp.setParentId(studentInfo.getParentId());
        sp.setStudentId(id);
        spService.addSelective(sp);


        return getStudentInfo(sysStudent.getId());
    }

    @Override
    public SysStudentVO getStudentInfo(String studentId) throws Exception {

        SysStudent sysStudent = this.selectByPrimaryKey(studentId);
        if(sysStudent == null || sysStudent.getStatus() == 0)
            throw new BusinessException("学生信息不存在!");

        SysStudentVO sysStudentVO = new SysStudentVO();
        BeanUtils.copyProperties(sysStudentVO,sysStudent);

        SysStageGradeCache sysStageGradeCache = stageGradeCacheData.getSysStageGradeCacheByGradeCode(sysStudent.getGradeCode());
        sysStudentVO.setGradeName(sysStageGradeCache.getGradeName());
        sysStudentVO.setStageId(sysStageGradeCache.getStageId());
        return sysStudentVO;
    }

    @Override
    @Transactional
    public void updateStatus(String id) {
        if (StringUtils.indexOf(id, ",") == -1) {
            List<String> list = new ArrayList<>();
            list.add(id);
            sysStudentMapper.updateStatus(list);
            spService.updateStatus(list);
        } else {
            sysStudentMapper.updateStatus(Arrays.asList(id.split(",")));
            spService.updateStatus(Arrays.asList(id.split(",")));
        }
    }

    @Override
    public SysStudent updateInfo(StudentInfoPOJO studentInfo) {
        SysStudent sysStudent = this.getById(studentInfo.getId());
        if (null != sysStudent) {
            String stuName = studentInfo.getStuName();
            sysStudent.setStudentName(stuName);
            sysStudent.setAge(studentInfo.getAge());
            sysStudent.setSex(studentInfo.getSex());
            sysStudent.setSchoolName(studentInfo.getSchoolName());
            sysStudent.setGradeCode(studentInfo.getGradeCode());
            sysStudent.setStudentNamePy(PinYinUtil.getPingYin(stuName));
            sysStudent.setHeadImg(studentInfo.getHeadImg());
            this.modify(sysStudent);
        }
        return sysStudent;
    }

    @TargetDataSource("local")
    @Override
    public List<SysStudentVO> getAllSduByParentId(String id) {
        return sysStudentMapper.getAllSduByParentId(id);
    }


    @Override
    public boolean bindStudentInfo(BindStudentInfo bindStudentInfo)  throws BusinessException {

        try{
            SysStudent sysStudent = new SysStudent();

            sysStudent.setId(bindStudentInfo.getStudentId());
            sysStudent.setSyncFlag(1);
            sysStudent.setSyncStudentCode(bindStudentInfo.getSyncStudentCode());
            sysStudent.setSyncSchoolCode(bindStudentInfo.getSyncSchoolCode());
            sysStudent.setSyncClassCode(bindStudentInfo.getSyncClassCode());
            sysStudent.setSyncStudentId(bindStudentInfo.getSyncStudentId());
            int result = sysStudentMapper.updateByPrimaryKeySelective(sysStudent);
            if(result > 0)
                return true;

            log.warn(" bindStudentInfo failed  bindStudentInfo:"+bindStudentInfo +" update result:"+result);
            throw new BusinessException("绑定学生信息失败!");
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new BusinessException("选择的学生信息已存在!");
            }else{
                log.error(" bindStudentInfo error  bindStudentInfo:"+bindStudentInfo,e);
                throw new BusinessException("绑定学生信息失败!");
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean unBindStudentInfo(String parentId,String studentId) throws Exception {

        SysStudent sysStudent = sysStudentMapper.selectByPrimaryKey(studentId);
        if(sysStudent == null)
            throw new BusinessException("学生信息不存在!");


        Map<String,Object> paraMap = Maps.newHashMap();
        paraMap.put("parentId",parentId);
        paraMap.put("studentId",studentId);
        paraMap.put("status",1);
        List<SysStudentParent> list = sysStudentParentService.query(paraMap);
        if(list == null || list.isEmpty())
            throw new BusinessException("学生家长关系不存在!");

        SysStudentParent sysStudentParent = list.get(0);
        SysStudentParentUnbind sysStudentParentUnbind = new SysStudentParentUnbind();

        BeanUtils.copyProperties(sysStudentParentUnbind,sysStudentParent);

        sysStudentParentUnbind.setId(sysStudentParent.getId());
        sysStudentParentUnbind.setCrtDate(null);
        //增加解绑历史记录
        studentParentUnbindService.addSelective(sysStudentParentUnbind);

        //删除原有绑定记录
        sysStudentParentService.delete(sysStudentParent.getId());

        //修改学生信息中的绑定字段
        int result = sysStudentMapper.unBindStudentInfo(studentId);
        return (result>=1?true:false);
    }

    @Override
    public SysStudent selectStudent(Map<String, Object> paraMap) {
        List<SysStudent> list = sysStudentMapper.selectByParameters(paraMap);
        if(list == null || list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public SysStudent selectByPrimaryKey(String id) {
        return sysStudentMapper.selectByPrimaryKey(id);
    }

    @Override
    public String getSchoolUrlByStudentId(String id) {
        SysStudent sysStudent = selectByPrimaryKey(id);
        if(sysStudent == null)
            return null;

        SysSchool sysSchool = sysSchoolService.getSysSchoolBySchoolCode(sysStudent.getSyncSchoolCode());
        if(sysSchool == null)
            return null;
        return sysSchool.getUrl();
    }

    @Override
    public List<KnowledgeRanking> getQueryKnowledgeRanking(String studentCode, String knowledgeId) {
        Map<String, Object> resourceParams = new HashMap<>(1);
        resourceParams.put("ids", knowledgeId);
        List<KnowledgeName> knowledgeList = ServerRestTemplate.postList(resourceServerUrl + "/resource/knowledges/queryKnowledgeNames", KnowledgeName.class, resourceParams);
        List<String> knowledgeNameList = knowledgeList.stream().map(p -> p.getKnowledgeName()).collect(Collectors.toList());
        List<KnowledgeRanking> resultList  = new ArrayList<>();
        KnowledgeRanking knowledgeRanking = new KnowledgeRanking();
        if(!CollectionUtils.isEmpty(knowledgeNameList)){
            knowledgeRanking.setKnowledgeNames(knowledgeNameList.get(0));
            //获取准确率
            float count = getStudentAccacy(studentCode,knowledgeId);
            knowledgeRanking.setAccuracy(count);
            //获取完成度
            Map<String, Object> map = getQueryKnowledgeRankingMax(studentCode,knowledgeId);
            knowledgeRanking.setComplete(map);
            //完成度排名
            float rankCount = getQueryAnswerRanking(studentCode,knowledgeId);
            knowledgeRanking.setCompleteRanking(rankCount);
            //正确率排名
            float rankstar = getQueryAccuracyRanking(studentCode,knowledgeId);
            knowledgeRanking.setAccuracyRanking(rankstar);
            resultList.add(knowledgeRanking);
        }
        return resultList;
    }

    public float getQueryAccuracyRanking(String studentCode, String knowledgeId){
        Map<String, Object> params = new HashMap<>(1);
        params.put("studentCode",studentCode);
        params.put("knowledgeId",knowledgeId);
        Integer runks = iKnowledgeMasterService.selectByStudentRank(params);

        List<QueryAccuracyRanking> list = iKnowledgeMasterService.selectByStudentRankStar(params);
        Map<String, Integer> param = new HashMap<>(1);
        for(int i=0;i<list.size();i++){
            QueryAccuracyRanking queryAccuracyRanking = list.get(i);
            if(param.get(queryAccuracyRanking.getStudentCode()) == null){
                param.put(queryAccuracyRanking.getStudentCode(),queryAccuracyRanking.getStStar());
            }else {
                param.put(queryAccuracyRanking.getStudentCode(),param.get(queryAccuracyRanking.getStudentCode())+queryAccuracyRanking.getStStar());
            }
        }
        int count = 0;
        float runkCount = 0;
        if(param.size() > 0){
            int paramCount = param.get(studentCode);
            for (String key: param.keySet()) {
                if (paramCount > param.get(key)) {
                    count++;
                }
            }
            DecimalFormat df = new DecimalFormat("#.000");
            BigDecimal bigDecimal = new BigDecimal((float)count / param.size());
            runkCount = Float.parseFloat(df.format(bigDecimal));
        }
        return runkCount;
    }

    public float getQueryAnswerRanking(String studentCode, String knowledgeId) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("knowledgeId",knowledgeId);
        //查询出所有学生的知识点排名
        List<QueryKnowledgeRanking> queryKnowledgeRankingList = iKnowledgeMasterService.selectByStudentRanking(params);
        //统计同一个学生的星级
        Map<String,Integer> studentStartMap = queryKnowledgeRankingList.stream().collect(Collectors.groupingBy(QueryKnowledgeRanking::getStudentCode,Collectors.summingInt(QueryKnowledgeRanking::getStar)));

        Integer starCount = studentStartMap.get(studentCode);
        if(starCount == null || starCount == 0){
            return 0f;
        }
        int count = 0;
        for (String key : studentStartMap.keySet()) {  //通过foreach方法来遍历
            if(!key.equals(studentCode) && starCount > studentStartMap.get(key)){
                count++;
            }
        }
        return (float)count/studentStartMap.size()-1;
    }

    /**
     * 获取学生答题准确率
     * @param studentCode
     * @param knowledgeId
     * @return
     */
    public float getStudentAccacy(String studentCode, String knowledgeId){
        Map<String,Object> map = new HashMap<>();
        map.put("studentCode", studentCode);
        map.put("knowledgeId", knowledgeId);
        List<StudyTestAnswer> studyTestAnswerList = studyTestAnswerService.getByKnowledgeAndStudentCode(map);
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(studyTestAnswerList)){
            int size = studyTestAnswerList.size();
            long rightSize = studyTestAnswerList.stream().filter(x -> x.getIsRight() == 1).count();
            return (float) rightSize/size;
        }
        return 0f;
    }

    public Map<String, Object> getQueryKnowledgeRankingMax(String studentCode, String knowledgeId){
        List<QueryKnowledgeRanking> queryKnowledgeRankingList = iKnowledgeMasterService.getByCorrectRateMax(studentCode,knowledgeId);
        Map<String, Object> resourceParams = new HashMap<>(1);
        for (int i=0;i<queryKnowledgeRankingList.size();i++){
            QueryKnowledgeRanking queryKnowledgeRanking = queryKnowledgeRankingList.get(i);
            if(queryKnowledgeRanking.getRank()==1){
                resourceParams.put("Basic",queryKnowledgeRanking.getStar());
            }
            else if (queryKnowledgeRanking.getRank()==2){
                resourceParams.put("Consolidate",queryKnowledgeRanking.getStar());
            }
            else if (queryKnowledgeRanking.getRank()==3){
                resourceParams.put("High",queryKnowledgeRanking.getStar());
            }
        }
        return resourceParams;
    }
}
