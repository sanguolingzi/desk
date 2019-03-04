package com.cunw.cloud.familydesk.filemanage.template;

import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
     BusiUploadFileInfo upload(MultipartFile file, String filePath, Object...params)  throws Exception;

     List<BusiUploadFileInfo> uploadBatch(MultipartFile[] listFile, String filePath, Object...params)  throws Exception;
}
