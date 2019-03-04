package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/8/14 16:38
 * 类描述
 * 修订历史：
 * 日期：2018/8/14
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Data
public class StudyTestParam {

    @NotNull(message = "测验类型不能为空！")
    private Integer type;

    @NotNull(message = "学生编码不能为空！")
    private String studentCode;

    @NotNull(message = "章节id或知识点id不能为空！")
    private String id;

    @NotNull(message = "层级不能为空！")
    private Integer rank;

    @NotNull(message = "用时不能为空！")
    private Integer useTime;

    @NotNull(message = "答题结果不能为空！")
    private String answers;

}
