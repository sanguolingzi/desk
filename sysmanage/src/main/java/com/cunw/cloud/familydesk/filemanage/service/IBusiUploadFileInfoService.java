package com.cunw.cloud.familydesk.filemanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * BusiUploadFileInfo服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:36
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiUploadFileInfo服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IBusiUploadFileInfoService extends IBaseService<BusiUploadFileInfo, String>{

    /**
     * 更新资源的 引用状态 标明该资源是有用的
     * @param fileId
     * @return
     */
    int updateFileInfoReferences(String fileId);

    /**
     * 更新资源重载函数
     * @param fileIds
     * @return
     */
    int updateFileInfoReferences(List<String> fileIds);

    BusiUploadFileInfo uploadAndSave(MultipartFile file,String filePath) throws Exception;

    List<BusiUploadFileInfo> uploadAndSaveBatch(MultipartFile[] listFile,String filePath) throws Exception;

    List<BusiUploadFileInfo> selectUnReferencesFile(int expireDay);

    int updateByPrimary(BusiUploadFileInfo busiUploadFileInfo);

    int addRetryCount(String fileId);

    List<BusiUploadFileInfo>  uploadApp(MultipartFile file,MultipartFile [] imgs,MultipartFile logo,String stageId,String appCategoryId,String appId,String appName,String appDesc,String description,String version) throws Exception;

    BusiUploadFileInfo selectByPrimaryKey(String id);
}