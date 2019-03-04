package com.cunw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/4/20 16:24
 * 类描述
 * 修订历史：
 * 日期：2018/4/20
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@EnableTransactionManagement
@SpringBootApplication
public class FamilyDeskApplication {
    public static void main(String[] args) {
        SpringApplication.run(FamilyDeskApplication.class, args);
    }
}
