package com.cunw.cloud.familydesk.resource.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.PracticeItems;

import java.util.List;
import java.util.Map;

/**
 * PracticeItems服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:38
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：PracticeItems服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IPracticeItemsService extends IBaseService<PracticeItems, String> {

    int batchInsert(List<PracticeItems> list);
    int deleteByParameters(Map<String, Object> parameters);
}