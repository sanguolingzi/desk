package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.MaterialMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMasterMapper extends IBaseMapper<MaterialMaster, String> {

    List<MaterialMaster> selectByDirIds(@Param("studentCode") String studentCode, @Param("dirIds") List<String> dirIds);

    void updateRank(MaterialMaster materialMaster);

}