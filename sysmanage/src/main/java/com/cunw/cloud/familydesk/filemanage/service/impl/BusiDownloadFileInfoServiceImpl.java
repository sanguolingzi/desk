package com.cunw.cloud.familydesk.filemanage.service.impl;

import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.filemanage.mapper.BusiUploadFileInfoMapper;
import com.cunw.cloud.familydesk.filemanage.service.IBusiDownloadFileInfoService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.base.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
/**
 * BusiUploadFileInfo服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:36
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiUploadFileInfo服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "busiDownloadFileInfoService")
@Slf4j
public class BusiDownloadFileInfoServiceImpl extends BaseServiceImpl<BusiUploadFileInfo, String> implements IBusiDownloadFileInfoService {
    @Override
    protected IBaseMapper<BusiUploadFileInfo, String> getMapper() {
        return busiUploadFileInfoMapper;
    }

    @Autowired
    private BusiUploadFileInfoMapper busiUploadFileInfoMapper;

    @Value("${file.path}")
    private String filePath;

    @Value("${static.path}")
    private String staticPath;

    @Override
    public BusiDownLoadFileInfo download(String fileId) throws Exception {
        if(StringUtils.isEmpty(fileId))
            throw new FileNotFoundException();

        BusiUploadFileInfo busiUploadFileInfo = busiUploadFileInfoMapper.selectByPrimaryKey(fileId);
        if(busiUploadFileInfo == null){
            log.warn(" busiUploadFileInfo is null");
            throw new FileNotFoundException();
        }

        String  absolutePath = (busiUploadFileInfo.getFileType()==2?staticPath:filePath)+busiUploadFileInfo.getPath();
        log.debug("** down file path:{}",absolutePath);
        byte[] buffer = null;
        File file = new File(absolutePath);
        if(!file.exists()){
            log.warn(" fileNot exists :{}",absolutePath);
            throw new FileNotFoundException();
        }
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos =  new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            buffer =  bos.toByteArray();



            BusiDownLoadFileInfo busiDownLoadFileInfo = new BusiDownLoadFileInfo();
            busiDownLoadFileInfo.setExten(busiUploadFileInfo.getExten());

            busiDownLoadFileInfo.setFileName(busiUploadFileInfo.getFileName());
            busiDownLoadFileInfo.setFileType(busiUploadFileInfo.getFileType());
            busiDownLoadFileInfo.setSize(busiUploadFileInfo.getSize());
            busiDownLoadFileInfo.setFileByte(buffer);

            return busiDownLoadFileInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            log.error("down error absolutePath:"+absolutePath,e);
            throw new FileNotFoundException();
        }finally {
            if(fis != null)
                fis.close();

            if(bos != null)
                bos.close();
        }
        throw new FileNotFoundException();
    }

}
