package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.KnowledgeQuestion;
import com.cunw.cloud.familydesk.sysmanage.mapper.KnowledgeQuestionMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IKnowledgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * KnowledgeQuestion服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-14 06:09
 * 类描述
 * 修订历史：
 * 日期：2018-08-14
 * 作者：generator
 * 参考：
 * 描述：KnowledgeQuestion服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "knowledgeQuestionService")
public class KnowledgeQuestionServiceImpl extends BaseServiceImpl<KnowledgeQuestion, String> implements IKnowledgeQuestionService {

    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;

    @Override
    protected IBaseMapper<KnowledgeQuestion, String> getMapper() {
        return knowledgeQuestionMapper;
    }

    @Override
    public List<KnowledgeQuestion> queryByMasterIdAndRank(String masterId, Integer rank) {
        Map<String, Object> params = new HashMap<>();
        params.put("masterId", masterId);
        params.put("rank", rank);
        return query(params);
    }
}
