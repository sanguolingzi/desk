package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/8/13 18:41
 * 类描述
 * 修订历史：
 * 日期：2018/8/13
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Data
public class DirStar {

    private String dirId;

    private String pDirId;

    private String name;

    private Integer star;

    private Integer score;

    private Integer rank;

    private Integer leaf;

    private String pathId;

    private String pathName;

    private Integer level;

    private Integer type;
}
