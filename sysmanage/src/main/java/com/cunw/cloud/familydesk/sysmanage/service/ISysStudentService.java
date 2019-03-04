package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.BindStudentInfo;
import com.cunw.cloud.familydesk.common.model.SysParent;
import com.cunw.cloud.familydesk.common.model.SysStudentVO;
import com.cunw.cloud.familydesk.sysmanage.pojo.StudentInfoPOJO;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysStudent;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.model.CloudItem;
import com.cunw.cloud.resource.model.question.KnowledgeRanking;

import java.util.List;
import java.util.Map;

/**
 * SysStudent服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:59
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysStudent服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysStudentService extends IBaseService<SysStudent, String>{

    SysStudentVO save(StudentInfoPOJO studentInfo) throws Exception;

    SysStudentVO getStudentInfo(String studentId) throws Exception;

    //逻辑删除，更新status
    void updateStatus(String id);

    //更新学生信息
    SysStudent updateInfo(StudentInfoPOJO studentInfo);

    List<SysStudentVO> getAllSduByParentId(String id);

    boolean bindStudentInfo(BindStudentInfo bindStudentInfo) throws BusinessException;

    boolean unBindStudentInfo(String parentId,String studentId) throws Exception  ;

    SysStudent selectStudent(Map<String,Object> paraMap);

    SysStudent selectByPrimaryKey(String id);

    /**
     * 通过学生id 得到所属学校的服务地址 若不存在 则返回null
     * @param id
     * @return
     */
    String getSchoolUrlByStudentId(String id);

    PageList<CloudItem> queryCloudfile(String studentCode, Map<String, Object> params);

    /**
     * 知识点排名
     * @param studentCode
     * @return
     */
    List<KnowledgeRanking> getQueryKnowledgeRanking(String studentCode, String knowledgeId);

}