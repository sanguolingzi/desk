package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationDataService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationImgService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationUpdateRecordService;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysApplicationCenterMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationCenterService;
import com.cunw.cloud.framework.service.pagehelper.PageHelper;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.base.StringUtils;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * SysApplicationCenter服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationCenter服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysApplicationCenterService")
public class SysApplicationCenterServiceImpl extends BaseServiceImpl<SysApplicationCenter, String> implements ISysApplicationCenterService {

    @Autowired
    private SysApplicationCenterMapper sysApplicationCenterMapper;

    @Autowired
    private ISysApplicationDataService applicationDataService;

    @Autowired
    private ISysApplicationImgService applicationImgService;

    @Autowired
    private ISysApplicationUpdateRecordService applicationUpdateRecordService;

    @Autowired
    private IBusiUploadFileInfoService busiUploadFileInfoService;

    @Autowired
    private IdGenerator idGenerator;

    @Value("${server.static.url}")
    private String  serverPath;

    @Override
    protected IBaseMapper<SysApplicationCenter, String> getMapper() {
        return sysApplicationCenterMapper;
    }

   public  PageList<SysApplicationCenterVO> selectForPage(Map<String, Object> parameters, Integer pageNum, Integer pageSize){
       PageHelper.startPage(pageNum, pageSize);
       parameters.put("serverPath",serverPath);
       List<SysApplicationCenterVO>  rows = getCenterVos(parameters);
       PageList pageInfo = new PageList(rows);
       return pageInfo;
    }

    private  List<SysApplicationCenterVO> getCenterVos(Map<String, Object> parameters){
        List<SysApplicationCenterVO> rows = sysApplicationCenterMapper.selectForPage(parameters);
        return  rows;
    }

    public SysApplicationCenterInfo getAppInfoById(String appId) throws Exception{
        SysApplicationCenterInfo applicationCenterInfo=new SysApplicationCenterInfo();
        SysApplicationCenter sysApplicationCenter = sysApplicationCenterMapper.selectByPrimaryKey(appId);
        if(sysApplicationCenter == null)
            throw new BusinessException("应用不存在!");

        //查询基础信息
        SysApplicationCenterVO  vo = new SysApplicationCenterVO();
        BeanUtils.copyProperties(vo,sysApplicationCenter);

        vo.setAppLogo(serverPath+vo.getAppLogo());
        //查询动态数据
        SysApplicationData sysApplicationData = applicationDataService.selectByPrimary(vo.getAppId());
        vo.setDownloadNum(sysApplicationData.getDownload());

        //查询版本相关
        SysApplicationUpdateRecord sysApplicationUpdateRecord = applicationUpdateRecordService.getNewestByAppId(vo.getAppId());
        vo.setFileId(sysApplicationUpdateRecord.getFileId());
        Double AppSizeMb = BigDecimal.valueOf(sysApplicationUpdateRecord.getAppSize())
                .divide(BigDecimal.valueOf(1024),BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        vo.setAppSize(AppSizeMb);
        vo.setVersion(sysApplicationUpdateRecord.getVersion());


        applicationCenterInfo.setApplicationCenterVO(vo);
        List<SysApplicationImg> applicationImgs = applicationImgService.queryByProperty("appId",appId);
        List<String >ims=new ArrayList<>();
        for (SysApplicationImg img: applicationImgs) {
            ims.add(serverPath+img.getImgUrl());
        }
        applicationCenterInfo.setImgUrls(ims);
        return   applicationCenterInfo;
    }
    public SysApplicationCenterInfo modifyApp(SysApplicationUploadVO applicationUploadVO){
        SysApplicationCenterInfo info =new SysApplicationCenterInfo();
        SysApplicationCenter  center=setSysApplicationCenter(applicationUploadVO);
        SysApplicationUpdateRecord record = setSysApplicationUpdateRecord(applicationUploadVO.getAppFileId(), center.getAppId(), applicationUploadVO.getDescription(), applicationUploadVO.getVersion());
        SysApplicationCenterVO centerVO = setCenterVO(center,record);
        info.setApplicationCenterVO(centerVO);
        info.setImgUrls(saveSysApplicationImg(center.getAppId(),applicationUploadVO.getImgFileIds()));
        return  info;
    }

    private SysApplicationCenter setSysApplicationCenter(SysApplicationUploadVO applicationUploadVO){
        String appId = applicationUploadVO.getAppId();
        SysApplicationCenter applicationCenter = sysApplicationCenterMapper.selectByPrimaryKey(appId);
        BusiUploadFileInfo uploadFileInfo = busiUploadFileInfoService.getById(applicationUploadVO.getLogoFileId());
        if(StringUtils.isEmpty(appId) || applicationCenter==null){
            appId= idGenerator.getNextStr();
            SysApplicationCenter sysApplicationCenter=new SysApplicationCenter();
            sysApplicationCenter.setAppId(appId);
            sysApplicationCenter.setAppDesc(applicationUploadVO.getAppDesc());
            sysApplicationCenter.setAppLogo(uploadFileInfo.getPath());
            sysApplicationCenter.setAppName(applicationUploadVO.getAppName());
            sysApplicationCenter.setAppRecommend(0);
            sysApplicationCenter.setStatus(1);
            sysApplicationCenter.setPackageName(applicationUploadVO.getPackageName());
            sysApplicationCenter.setStageId(Integer.parseInt(applicationUploadVO.getStageId()));
            sysApplicationCenter.setAppCategoryId(applicationUploadVO.getAppCategoryId());
            try{
                sysApplicationCenterMapper.insertSelective(sysApplicationCenter);
            }catch (DuplicateKeyException e){
                throw new BusinessException("模块结构的包名已存在,请更换!");
            }
            SysApplicationData applicationData= new SysApplicationData();
            applicationData.setAppId(appId);
            applicationData.setDownload(0);
            applicationData.setEvaluate(0);
            applicationData.setFavourite(0);
            applicationData.setStatus(1);
            applicationDataService.add(applicationData);
            return  sysApplicationCenter;
        }
        applicationCenter.setAppDesc(applicationUploadVO.getAppDesc());
        applicationCenter.setAppLogo(uploadFileInfo.getPath());
        applicationCenter.setAppName(applicationUploadVO.getAppName());
        applicationCenter.setStageId(Integer.parseInt(applicationUploadVO.getStageId()));
        applicationCenter.setAppCategoryId(applicationUploadVO.getAppCategoryId());
        //applicationCenter.setCrtDate(new Date());
        sysApplicationCenterMapper.updateByPrimaryKeySelective(applicationCenter);
        applicationImgService.updateStatus(appId);
        return  applicationCenter;
    }

    @Override
    public SysApplicationUpdateRecord setSysApplicationUpdateRecord(String appFileId,String appId,String description,String version){
        applicationUpdateRecordService.updateStatus(appId);
        SysApplicationUpdateRecord updateRecord=new SysApplicationUpdateRecord();
        BusiUploadFileInfo uploadFileInfo = busiUploadFileInfoService.getById(appFileId);
        updateRecord.setAppId(appId);
        updateRecord.setFileId(appFileId);
        updateRecord.setAppSize(uploadFileInfo.getSize());
        updateRecord.setStatus(1);
        updateRecord.setVersion(version);
        updateRecord.setDescription(description);
        applicationUpdateRecordService.addSelective(updateRecord);
        busiUploadFileInfoService.updateFileInfoReferences(appFileId);
        return  updateRecord;
    }

    private List<String> saveSysApplicationImg(String appId,String imgFileIds){
        List<String> imgList=new ArrayList<>();
        String [] fileIds=imgFileIds.split(",");
        for (String imgFileId: fileIds) {
            BusiUploadFileInfo uploadFileInfo = busiUploadFileInfoService.getById(imgFileId);
            SysApplicationImg applicationImg=new SysApplicationImg();
            applicationImg.setAppId(appId);
            applicationImg.setImgUrl(uploadFileInfo.getPath());
            applicationImg.setFileId(imgFileId);
            applicationImg.setStatus(1);
            imgList.add(serverPath+uploadFileInfo.getPath());
            applicationImgService.add(applicationImg);
            busiUploadFileInfoService.updateFileInfoReferences(imgFileId);
        }
        return  imgList;
    }
    private  SysApplicationCenterVO setCenterVO(SysApplicationCenter  center,SysApplicationUpdateRecord record){
        SysApplicationCenterVO centerVO = new SysApplicationCenterVO();
        centerVO.setAppId(center.getAppId());
        centerVO.setAppName(center.getAppName());
        centerVO.setAppDesc(center.getAppDesc());
        centerVO.setAppLogo(serverPath+center.getAppLogo());
        centerVO.setAppRecommend(center.getAppRecommend());
        double size=record.getAppSize()/1024;
        centerVO.setAppSize(size>0.01?size:0.01);
        centerVO.setCrtDate(center.getCrtDate());
        centerVO.setVersion(record.getVersion());
        return centerVO;
    }

}
