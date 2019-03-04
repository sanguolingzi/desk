package com.cunw.cloud.familydesk.filemanage.mapper;

import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusiUploadFileInfoMapper extends IBaseMapper<BusiUploadFileInfo, String> {

    /**
     * 查询超过expireDay天的未引用文件信息 用于管理(删除、迁移)
     * @param expireDay 过期时间间隔 天
     * @return
     */
    List<BusiUploadFileInfo> selectUnReferencesFile(int expireDay);

    /**
     * 增加文件处理的失败次数字段
     * @param fileId
     * @return
     */
    int addRetryCount(String fileId);
}