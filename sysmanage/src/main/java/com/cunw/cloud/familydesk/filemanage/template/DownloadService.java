package com.cunw.cloud.familydesk.filemanage.template;

import com.cunw.cloud.familydesk.common.model.BusiDownLoadFileInfo;

public interface DownloadService {
     BusiDownLoadFileInfo download(String fileId, Object...params) throws Exception;
}
