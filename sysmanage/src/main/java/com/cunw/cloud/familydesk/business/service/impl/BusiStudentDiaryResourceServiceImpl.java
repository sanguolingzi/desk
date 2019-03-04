package com.cunw.cloud.familydesk.business.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.BusiStudentDiaryResource;
import com.cunw.cloud.familydesk.business.mapper.BusiStudentDiaryResourceMapper;
import com.cunw.cloud.familydesk.business.service.IBusiStudentDiaryResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * BusiStudentDiaryResource服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiStudentDiaryResource服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "busiStudentDiaryResourceService")
public class BusiStudentDiaryResourceServiceImpl extends BaseServiceImpl<BusiStudentDiaryResource, String> implements IBusiStudentDiaryResourceService {

    @Autowired
    private BusiStudentDiaryResourceMapper busiStudentDiaryResourceMapper;

    @Override
    protected IBaseMapper<BusiStudentDiaryResource, String> getMapper() {
        return busiStudentDiaryResourceMapper;
    }

    @Override
    public List<BusiStudentDiaryResource> selectByParameters(Map<String, Object> parameters) {

        return busiStudentDiaryResourceMapper.selectByParameters(parameters);
    }
}
