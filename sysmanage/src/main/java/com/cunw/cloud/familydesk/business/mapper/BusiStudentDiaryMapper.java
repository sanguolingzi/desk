package com.cunw.cloud.familydesk.business.mapper;

import com.cunw.cloud.familydesk.common.model.BusiStudentDiary;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusiStudentDiaryMapper extends IBaseMapper<BusiStudentDiary, String> {
    /**
     * 查询学生日记列表
     * @param diaryStudentId
     * @return
     */
    List<BusiStudentDiary> seleStudentDiaryList(@Param("diaryStudentId") String diaryStudentId);
}