package com.cunw.cloud.familydesk.filemanage.controller;

import com.cunw.cloud.familydesk.common.model.BusiDownLoadFileInfo;
import com.cunw.cloud.familydesk.filemanage.template.DownloadService;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.utils.base.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * BusiUploadFileInfo RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:36
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiUploadFileInfo RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/busiDownloadFileInfos")
public class BusiDownloadFileInfoController extends BaseRESTfulController {

    @Autowired
    DownloadService appDownloadTemplate;
    @Autowired
    DownloadService downloadTemplate;

    @ApiOperation(value="下载文件")
    @GetMapping("/download")
    public void download(HttpServletResponse response, String fileId) throws Exception{
        BusiDownLoadFileInfo busiDownLoadFileInfo = downloadTemplate.download(fileId);
        outPutFile(response,busiDownLoadFileInfo);
    }

    @ApiOperation(value = "下载应用")
    @GetMapping(value = "/downloadApp")
    public void downloadApp(HttpServletResponse response, @RequestParam String fileId, @RequestParam String appId, @RequestParam String studentId) throws Exception {
        if(StringUtils.isEmpty(fileId)){
            throw new MissingServletRequestParameterException("fileId","");
        }

        if(StringUtils.isEmpty(appId)){
            throw new MissingServletRequestParameterException("appId","");
        }

        if(StringUtils.isEmpty(studentId)){
            throw new MissingServletRequestParameterException("studentId","");
        }
        BusiDownLoadFileInfo busiDownLoadFileInfo = appDownloadTemplate.download(fileId,appId,studentId);
        outPutFile(response, busiDownLoadFileInfo);
    }

    private void outPutFile(HttpServletResponse response,BusiDownLoadFileInfo busiDownLoadFileInfo) throws Exception{
        OutputStream output = null;
        ByteArrayInputStream byteArrayInputStream = null;
        byte[] bytes = new byte[1024];
        try {
            output = response.getOutputStream();
            byteArrayInputStream = new ByteArrayInputStream(busiDownLoadFileInfo.getFileByte());
            response.reset();
            //StringUtil.encodeFileName(fileName, userAgent)
            String fileName = URLEncoder.encode(busiDownLoadFileInfo.getFileName(), "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("charset=utf-8");
            response.setCharacterEncoding("utf-8");
            int len = -1;
            while((len = byteArrayInputStream.read(bytes)) != -1){
                output.write(bytes,0,len);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(byteArrayInputStream != null){
                byteArrayInputStream.close();
            }

            if (output != null) {
                output.close();
            }
        }

    }

}
