package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.MaterialQuestion;

import java.util.List;

/**
 * MaterialQuestion服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-14 06:09
 * 类描述
 * 修订历史：
 * 日期：2018-08-14
 * 作者：generator
 * 参考：
 * 描述：MaterialQuestion服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IMaterialQuestionService extends IBaseService<MaterialQuestion, String> {

    List<MaterialQuestion> queryByMasterIdAndRank(String masterId, Integer rank);

}