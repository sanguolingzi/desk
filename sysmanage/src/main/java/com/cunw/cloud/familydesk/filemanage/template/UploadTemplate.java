package com.cunw.cloud.familydesk.filemanage.template;

import com.cunw.cloud.familydesk.cachedata.base.ICacheData;
import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.utils.io.FileUtils;
import com.cunw.cloud.sysmanage.general.sysdict.model.SysDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
public class UploadTemplate implements UploadService{

    @Autowired
    private IBusiUploadFileInfoService busiUploadFileInfoService;

    @Autowired
    private ICacheData<SysDict> fileTypeCacheData;

    public BusiUploadFileInfo upload(MultipartFile file, String filePath,Object...params) throws Exception{
        if(beforeUpload(file,params)){
            BusiUploadFileInfo busiUploadFileInfo = busiUploadFileInfoService.uploadAndSave(file,filePath);
            afterUpload(params);
            return busiUploadFileInfo;
        }
        log.error(this.getClass().getSimpleName()+"  beforeUpload upload return false");
        return null;
    }

    public List<BusiUploadFileInfo> uploadBatch(MultipartFile[] listFile, String filePath, Object...params) throws Exception{
        if(beforeUpload(listFile,params)){
            List<BusiUploadFileInfo> list  = busiUploadFileInfoService.uploadAndSaveBatch(listFile,filePath);
            afterUpload(params);
            return list;
        }
        log.error(this.getClass().getSimpleName()+" beforeUpload uploadBatch return false");
        return null;
    }


    /**
     * 做一些校验 如 文件大小 或者 文件格式
     * @return
     */
    public boolean beforeUpload(Object...params){
        if(params != null && params.length > 0){
            try{
                Object obj = params[0];
                if(obj instanceof MultipartFile){
                    MultipartFile file = (MultipartFile)obj;
                    String fileExten = FileUtils.getFileExtension(file.getOriginalFilename());
                    if(fileTypeCacheData.getData(fileExten) == null)
                        throw new BusinessException("不支持的文件类型: "+fileExten);
                    //}
                }else if(obj instanceof MultipartFile[]){
                    for(MultipartFile file:(MultipartFile[])obj){
                        String fileExten = FileUtils.getFileExtension(file.getOriginalFilename());
                        if(fileTypeCacheData.getData(fileExten) == null)
                            throw new BusinessException("不支持的文件类型: "+fileExten);
                        //}
                    }

                }
            }catch (Exception e){
                if(e instanceof BusinessException)
                    throw e;
                throw new RuntimeException(" beforeUpload error",e);
            }
        }
        return true;
    }


    /**
     * 做上传之后的业务逻辑
     */
    public void afterUpload(Object...params){
    }
}
