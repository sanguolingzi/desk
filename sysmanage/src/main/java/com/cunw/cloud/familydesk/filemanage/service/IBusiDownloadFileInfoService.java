package com.cunw.cloud.familydesk.filemanage.service;

import com.cunw.cloud.familydesk.common.model.BusiDownLoadFileInfo;
import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import com.cunw.cloud.framework.service.IBaseService;


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
public interface IBusiDownloadFileInfoService extends IBaseService<BusiUploadFileInfo, String>{
    BusiDownLoadFileInfo download(String fileId) throws Exception;
}