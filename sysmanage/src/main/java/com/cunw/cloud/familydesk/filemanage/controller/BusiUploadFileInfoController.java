package com.cunw.cloud.familydesk.filemanage.controller;

import com.cunw.cloud.familydesk.common.model.BusiUploadFileInfo;
import com.cunw.cloud.familydesk.filemanage.template.UploadService;
import com.cunw.cloud.familydesk.json.annotation.JSON;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/${api.version}/busiUploadFileInfos")
public class BusiUploadFileInfoController extends BaseRESTfulController {

    @Autowired
    IBusiUploadFileInfoService busiUploadFileInfoService;

    @Autowired
    UploadService uploadTemplateImpl;


    @Value("${diaryresources.path}")
    private String diaryresourcesPath;

    @Value("${app.path}")
    private String appPath;

    @ApiOperation(value="上传文件")
    @PostMapping("/upload")
    @JSON(type = BusiUploadFileInfo.class,include = "fileId,path")
    public RESTfulResult upload(@RequestParam("file") MultipartFile file) throws Exception{
        return success(uploadTemplateImpl.upload(file,null));
    }

    @ApiOperation(value="上传应用资源文件")
    @PostMapping("/uploadAppFile")
    @JSON(type = BusiUploadFileInfo.class,include = "fileId,path")
    public RESTfulResult uploadAppFile(@RequestParam("file") MultipartFile file) throws Exception{
        return success(uploadTemplateImpl.upload(file,appPath));
    }

    @ApiOperation(value="上传日记资源文件")
    @PostMapping("/uploadDiaryFile")
    @JSON(type = BusiUploadFileInfo.class,include = "fileId,path")
    public RESTfulResult uploadDiaryFile(@RequestParam("file") MultipartFile file) throws Exception{
        return success(uploadTemplateImpl.upload(file,diaryresourcesPath));
    }


    //----------------------------------------------多文件上传-------------------------------------------------

    @ApiOperation(value="上传多个文件")
    @PostMapping("/uploadBatch")
    @JSON(type = BusiUploadFileInfo.class,include = "fileId,path")
    public RESTfulResult uploadBatch(@RequestParam("file") MultipartFile[] listFile) throws Exception{
        return success(uploadTemplateImpl.uploadBatch(listFile,null));
    }


    @ApiOperation(value="上传多个日记资源文件")
    @PostMapping("/uploadDiaryFileBatch")
    @JSON(type = BusiUploadFileInfo.class,include = "fileId,path")
    public RESTfulResult uploadDiaryFileBatch(@RequestParam("file") MultipartFile[] listFile) throws Exception{
        return success(uploadTemplateImpl.uploadBatch(listFile,diaryresourcesPath));
    }

}
