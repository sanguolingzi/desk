package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.school.common.model.FavorFile;
import com.cunw.cloud.school.common.model.FavorResFile;

import java.util.List;

/**
 * FavorFile服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:22
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：FavorFile服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IFavorFileService extends IBaseService<FavorFile, String> {

    FavorFile addFromCloudFile(FavorFile favorFile);

    List<FavorResFile> queryByFavorite(String favorId, String titleName);
}