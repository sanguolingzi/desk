package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.MaterialMaster;

import java.util.List;

/**
 * MaterialMaster服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：MaterialMaster服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IMaterialMasterService extends IBaseService<MaterialMaster, String> {

    List queryChapters(String studentCode, String pDirId);

    MaterialMaster getAndInit(String studentCode, String subject, String chapterId);

    void updateRank(MaterialMaster materialMaster);

}