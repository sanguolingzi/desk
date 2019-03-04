package com.cunw.cloud.familydesk.business.service;

import com.cunw.cloud.familydesk.common.vo.StudentDiaryVO;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.BusiStudentDiary;
import com.cunw.cloud.framework.service.pagehelper.PageList;

/**
 * BusiStudentDiary服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiStudentDiary服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IBusiStudentDiaryService extends IBaseService<BusiStudentDiary, String>{
    /**
     * 查询日记详情
     * @param diartId
     * @return
     */
    StudentDiaryVO getDiaryInfo(String diartId);

    /**
     * 分页获取学生日记列表
     * @param diaryStudentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageList<BusiStudentDiary> getStudentDiaryList(String diaryStudentId, Integer pageNum, Integer pageSize);

    /**
     * 添加日记
     * @param busiStudentDiary
     * @param fileIds
     */
   void insertStudentDiary(BusiStudentDiary busiStudentDiary, String fileIds);
}