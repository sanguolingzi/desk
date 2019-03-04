package com.cunw.cloud.familydesk.common.dto;

import com.cunw.cloud.resource.model.Question;
import com.cunw.cloud.resource.model.ResourceQuestionDetail;
import lombok.Data;
import org.springframework.util.CollectionUtils;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/8/17 8:57
 * 类描述
 * 修订历史：
 * 日期：2018/8/17
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Data
public class TestQuestion {

    public TestQuestion() {

    }

    public TestQuestion(Question question) {
        this.questId = question.getQuestId();
        this.stem = question.getStem();
        this.options = question.getOptions();
        this.audioFileUrl = question.getAudioFileUrl();
        this.type = question.getType();
        this.typeName = question.getTypeName();
        this.baseType = question.getBaseType();
        this.baseTypeName = question.getBaseTypeName();
        this.answer = question.getAnswer();
        if (!CollectionUtils.isEmpty(question.getDetails())) {
            ResourceQuestionDetail detail = question.getDetails().get(0);
            this.answer = detail.getAnswer();
            this.answerJson = detail.getAnswerJson();
            this.explanation = detail.getExplanation();
        }
    }

    /**
     * 题目ID
     */
    private String questId;

    /**
     * 题目题干内容
     */
    private String stem;

    /**
     * 选项
     */
    private String options;

    /**
     * 音频文件地址，适用于听力题
     */
    private String audioFileUrl;

    /**
     * 题目题型
     */
    private Integer type;

    /**
     * 题目题型名称
     */
    private String typeName;

    /**
     * 题目基础题型，基础题型是固定不变的 说明：[ 1 = '单选题', 2 = '多选题', 3 = '判断题', 4 = '填空题', 5 = '解答题', 6 = '完形填空题', 7 = '复合题' ]
     */
    private Integer baseType;

    /**
     * 题目基础题型名称
     */
    private String baseTypeName;

    /**
     * 正确答案
     */
    private String answer;

    /**
     * 答案JSON数组格式，这个格式是所有题目都遵循方便程序对客观题的处理，比如选择题的答案为D 对应 answerJson 的第四个元素，判断题也是同样的原理
     */
    private String answerJson;

    /**
     * 答案分析详解
     */
    private String explanation;
}
