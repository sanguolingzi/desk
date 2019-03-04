package com.cunw.cloud.familydesk.sysmanage.job;

import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.familydesk.sysmanage.job.template.JobTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@Slf4j
public class ClearBusiFileInfo extends JobTemplate{

    @Autowired
    private IBusiUploadFileInfoService busiUploadFileInfoService;


    @Value("${expireDay:3}")
    private Integer expireDay;

    @Value("${file.path}")
    private String filePath;

    @Value("${static.path}")
    private String staticPath;

    @Override
    public void handleBusiness() throws Exception {
        List<BusiUploadFileInfo> list = busiUploadFileInfoService.selectUnReferencesFile(expireDay);
        if(list!=null && !list.isEmpty()){
            File file = null;
            BusiUploadFileInfo forUpdate = null;
            for(BusiUploadFileInfo busiUploadFileInfo:list){
                try{
                    file = new File((busiUploadFileInfo.getFileType()==2?staticPath:filePath)+File.separator+busiUploadFileInfo.getPath());
                    if(file != null && file.exists()){
                        if(file.delete()){
                            //删除成功 更新文件状态
                            forUpdate = new BusiUploadFileInfo();
                            forUpdate.setId(busiUploadFileInfo.getId());
                            forUpdate.setStatus(0);
                            busiUploadFileInfoService.updateByPrimary(forUpdate);
                            continue;
                        }
                    }
                    busiUploadFileInfoService.addRetryCount(busiUploadFileInfo.getId());
                }catch (Exception e){
                    log.error(" busiUploadFileInfo "+busiUploadFileInfo,e);
                    busiUploadFileInfoService.addRetryCount(busiUploadFileInfo.getId());
                }
            }
        }
    }
}
