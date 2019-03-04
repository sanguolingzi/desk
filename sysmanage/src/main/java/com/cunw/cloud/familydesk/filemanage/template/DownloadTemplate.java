package com.cunw.cloud.familydesk.filemanage.template;

import com.cunw.cloud.familydesk.common.model.BusiDownLoadFileInfo;
import com.cunw.cloud.familydesk.filemanage.service.IBusiDownloadFileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public abstract class DownloadTemplate implements DownloadService {

    @Autowired
    private IBusiDownloadFileInfoService busiDownloadFileInfoService;

    /**
     * 主体业务处理  这里加上了事务处理
     * @param fileId
     * @param params
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BusiDownLoadFileInfo download(String fileId,Object...params) throws Exception{
        if(beforeDownload(params)){
            BusiDownLoadFileInfo busiDownLoadFileInfo = busiDownloadFileInfoService.download(fileId);
            afterDownload(params);
            return busiDownLoadFileInfo;
        }
        log.error(this.getClass().getSimpleName()+"  beforeUpload return false");
        return null;
    }

    /**
     * 下载之前的业务操作 可有子类实现自己逻辑
     * @param params
     * @return
     */
    public boolean beforeDownload(Object... params){
        return true;
    }

    /**
     * 下载之后的业务操作 可有子类实现自己逻辑
     * @param params
     */
    public  void afterDownload(Object... params){

    }
}
