package com.cunw.cloud.familydesk.filemanage.service.impl;

import com.cunw.cloud.familydesk.cachedata.base.ICacheData;
import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationCenterService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationDataService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationImgService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationUpdateRecordService;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.filemanage.mapper.BusiUploadFileInfoMapper;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.framework.utils.base.DateUtils;
import com.cunw.cloud.framework.utils.base.StringUtils;
import com.cunw.cloud.framework.utils.io.FileUtils;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import com.cunw.cloud.sysmanage.general.sysdict.model.SysDict;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.io.Files;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Service(value = "busiUploadFileInfoService")
@Slf4j
public class BusiUploadFileInfoServiceImpl extends BaseServiceImpl<BusiUploadFileInfo, String> implements IBusiUploadFileInfoService {

    @Autowired
    private BusiUploadFileInfoMapper busiUploadFileInfoMapper;

    @Autowired
    ISysApplicationUpdateRecordService applicationUpdateRecordService;
    @Autowired
    ISysApplicationImgService applicationImgService;
    @Autowired
    ISysApplicationCenterService applicationCenterService;
    @Autowired
    ISysApplicationDataService applicationDataService;
    @Autowired
    private ICacheData<SysDict> fileTypeCacheData;

    @Value("${file.path}")
    private String filePath;

    @Value("${static.path}")
    private String staticPath;
    @Value("${app.path}")
    private String appPath;

    @Value("${server.static.url}")
    private String serverStaticUrl;

    @Autowired
    private IdGenerator idGenerator;
    @Override
    protected IBaseMapper<BusiUploadFileInfo, String> getMapper() {
        return busiUploadFileInfoMapper;
    }

    private BigDecimal KB = new BigDecimal("1024");

    @Override
    public int updateFileInfoReferences(String fileId) {
        if(Strings.isNullOrEmpty(fileId))
            return 0;

        BusiUploadFileInfo busiUploadFileInfo = new BusiUploadFileInfo();
        busiUploadFileInfo.setId(fileId);
        busiUploadFileInfo.setHasReferences(1);
        int result = busiUploadFileInfoMapper.updateByPrimaryKeySelective(busiUploadFileInfo);
        if(result == 0)
            log.warn(" updateFileInfoReferences failed result:"+result+" .....fileId:"+fileId);
        return result;
    }

    @Override
    public int updateFileInfoReferences(List<String> fileIds) {
        for(String fileId:fileIds){
            updateFileInfoReferences(fileId);
        }
        return 1;
    }

    /**
     *
     * @param file  文件对象
     * @param relativePath  相对路径
     * @return
     * @throws Exception
     */
    @Override
    public BusiUploadFileInfo uploadAndSave(MultipartFile file, String relativePath) throws Exception {
        if(file == null)
            throw new BusinessException("file is null");

        String defaultPath = "";
        String endPath = createDefaultFilePath( file.getOriginalFilename());
        if(relativePath == null){
            relativePath="";
            defaultPath = "default/";
        }

        String srcFileName =  file.getOriginalFilename();

        String srcFileExten = FileUtils.getFileExtension(srcFileName);
        SysDict sysDict = fileTypeCacheData.getData(srcFileExten);
        //图片类型在码表中有remark字段
        int fileType= sysDict.getRemark()==null?0:Integer.parseInt(sysDict.getRemark());

        //若是图片 则将目录设置为static下 为了便于显示 其他类型文件上传则放入非static目录
        String absolutePath = (fileType==2?staticPath:filePath)+defaultPath+relativePath+endPath;
        log.debug(" absolutePath:"+absolutePath);
        File toLocalFile = new File(absolutePath);
        FileUtils.makesureDirExists(toLocalFile.getParent());

        toLocalFile.createNewFile();

        Files.write(file.getBytes(),toLocalFile);

        BusiUploadFileInfo busiUploadFileInfo = new BusiUploadFileInfo();
        busiUploadFileInfo.setId(genID());


        busiUploadFileInfo.setFileType(fileType);
        busiUploadFileInfo.setSize(BigDecimal.valueOf(file.getSize()).divide(KB,BigDecimal.ROUND_HALF_UP).intValue());
        busiUploadFileInfo.setFileName(srcFileName);
        busiUploadFileInfo.setPath(defaultPath+relativePath+endPath);
        busiUploadFileInfo.setExten(srcFileExten);

        busiUploadFileInfoMapper.insertSelective(busiUploadFileInfo);

        //图片 拼接静态资源访问路径
        if(fileType==2){
            busiUploadFileInfo.setPath(serverStaticUrl+busiUploadFileInfo.getPath());
        }

        return busiUploadFileInfo;
    }

    @Override
    public List<BusiUploadFileInfo> uploadAndSaveBatch(MultipartFile[] listFile, String filePath) throws Exception {
        List<BusiUploadFileInfo> list = Lists.newArrayList();
        for(MultipartFile file:listFile){
            list.add(uploadAndSave(file,filePath));
        }
        return list;
    }

    public  List<BusiUploadFileInfo>  uploadApp(MultipartFile file, MultipartFile [] imgs, MultipartFile logo, String stageId, String appCategoryId, String appId, String appName, String appDesc, String description, String version) throws Exception{
        List<BusiUploadFileInfo> uploadFileInfos=new ArrayList<>();
        BusiUploadFileInfo uploadFile=uploadAndSave(file,appPath);
        BusiUploadFileInfo uploadFileLogo=uploadAndSave(logo,appPath);
        appId=setSysApplicationCenter(stageId,appCategoryId,appId,uploadFileLogo.getPath(),appName,appDesc);
        SysApplicationUpdateRecord updateRecord=setSysApplicationUpdateRecord(uploadFile,appId,description,version);
        applicationUpdateRecordService.add(updateRecord);
        uploadFileInfos.add(uploadFile);
        for (MultipartFile img:imgs) {
            BusiUploadFileInfo uploadFileInfo=uploadAndSave(img,appPath);
            uploadFileInfos.add(uploadFileInfo);
            SysApplicationImg applicationImg=setSysApplicationImg(appId,uploadFileInfo);
            applicationImgService.add(applicationImg);
        }

        return  uploadFileInfos;
    }

    private SysApplicationUpdateRecord setSysApplicationUpdateRecord(BusiUploadFileInfo uploadFile,String appId,String description,String version){
        SysApplicationUpdateRecord updateRecord=new SysApplicationUpdateRecord();
        updateRecord.setAppId(appId);
        updateRecord.setFileId(uploadFile.getFileId());
        updateRecord.setAppSize(uploadFile.getSize());
        updateRecord.setStatus(0);
        updateRecord.setVersion(version);
        updateRecord.setDescription(description);
        updateRecord.setCrtDate(new Date());
        return  updateRecord;
    }
    private SysApplicationImg setSysApplicationImg(String appId,BusiUploadFileInfo uploadFileInfo){
        SysApplicationImg applicationImg=new SysApplicationImg();
        applicationImg.setAppId(appId);
        applicationImg.setImgUrl(uploadFileInfo.getPath());
        applicationImg.setFileId(uploadFileInfo.getFileId());
        applicationImg.setStatus(0);
        return  applicationImg;
    }
    private String setSysApplicationCenter(String stageId,String appCategoryId,String appId,String logo,String appName,String appDesc){
        SysApplicationCenter applicationCenter = applicationCenterService.getById(appId);
        if(StringUtils.isEmpty(appId) || applicationCenter==null){
            appId= idGenerator.getNextStr();
            SysApplicationCenter sysApplicationCenter=new SysApplicationCenter();
            sysApplicationCenter.setAppId(appId);
            sysApplicationCenter.setAppDesc(appDesc);
            sysApplicationCenter.setAppLogo(logo);
            sysApplicationCenter.setAppName(appName);
            sysApplicationCenter.setAppRecommend(1);
            sysApplicationCenter.setStatus(0);
            sysApplicationCenter.setStageId(Integer.parseInt(stageId));
            sysApplicationCenter.setAppCategoryId(appCategoryId);
            sysApplicationCenter.setCrtDate(new Date());
            applicationCenterService.add(sysApplicationCenter);
            SysApplicationData applicationData= new SysApplicationData();
            applicationData.setAppId(appId);
            applicationData.setDownload(0);
            applicationData.setEvaluate(0);
            applicationData.setFavourite(0);
            applicationData.setStatus(1);
            applicationDataService.add(applicationData);
            return  appId;
        }

        applicationCenter.setAppDesc(appDesc);
        applicationCenter.setAppLogo(logo);
        applicationCenter.setAppName(appName);
        applicationCenter.setStageId(Integer.parseInt(stageId));
        applicationCenter.setAppCategoryId(appCategoryId);
        applicationCenter.setCrtDate(new Date());
        applicationCenterService.modify(applicationCenter);
        applicationImgService.updateStatus(appId);
        return  appId;
    }


    /**
     * 生成一个文件路径
     * 年月日/genID()+"."+文件后缀名
     * @param srcFileName
     * @return
     */
    String createDefaultFilePath(String srcFileName){
        //文件夹名称 由年月日组成
        String dirName = DateUtils.formatDate(new Date(),DateUtils.FORMAT_DATE_1);
        String fileName = genID()+"."+FileUtils.getFileExtension(srcFileName);
        return dirName+File.separator+fileName;
    }

    @Override
    public List<BusiUploadFileInfo> selectUnReferencesFile(int expireDay) {
        return busiUploadFileInfoMapper.selectUnReferencesFile(expireDay);
    }

    @Override
    public int updateByPrimary(BusiUploadFileInfo busiUploadFileInfo) {
        return busiUploadFileInfoMapper.updateByPrimaryKeySelective(busiUploadFileInfo);
    }

    @Override
    public int addRetryCount(String fileId) {
        return busiUploadFileInfoMapper.addRetryCount(fileId);
    }

    @Override
    public BusiUploadFileInfo selectByPrimaryKey(String id) {
        return busiUploadFileInfoMapper.selectByPrimaryKey(id);
    }
}
