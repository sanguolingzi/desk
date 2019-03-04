-- 初始化年级 ---
INSERT INTO `sys_grade` VALUES ('515107196183384064', '一年级', 'Org000022018112210001', null);
INSERT INTO `sys_grade` VALUES ('515166652636205056', '二年级', 'Org000022018112210002', null);
INSERT INTO `sys_grade` VALUES ('515166789081108480', '三年级', 'Org000022018112210003', null);
INSERT INTO `sys_grade` VALUES ('515183148431183872', '四年级', 'Org000022018112210004', null);
INSERT INTO `sys_grade` VALUES ('515183237232988160', '五年级', 'Org000022018112210005', null);
INSERT INTO `sys_grade` VALUES ('515184038806425600', '六年级', 'Org000022018112210006', null);
INSERT INTO `sys_grade` VALUES ('515184115495079936', '七年级', 'Org000022018112210007', null);
INSERT INTO `sys_grade` VALUES ('515184190640230400', '八年级', 'Org000022018112210008', null);
INSERT INTO `sys_grade` VALUES ('515184262807425024', '九年级', 'Org000022018112210009', null);
INSERT INTO `sys_grade` VALUES ('522419698609033217', '高一年级', 'Org000022018112210010', null);
INSERT INTO `sys_grade` VALUES ('522419699590500352', '高二年级', 'Org000022018112210011', null);
INSERT INTO `sys_grade` VALUES ('522419700286754817', '高三年级', 'Org000022018112210012', null);



--- 初始化 学段 ---
INSERT INTO `sys_stage` VALUES ('1', '小学', '1');
INSERT INTO `sys_stage` VALUES ('2', '初中', '1');
INSERT INTO `sys_stage` VALUES ('3', '高中', '1');
INSERT INTO `sys_stage` VALUES ('4', '学前', '1');

--- 初始化学段年级关系-------

INSERT INTO `sys_stage_grade` VALUES ('538320577531187200', '1', '515107196183384064', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187201', '3', '522419698609033217', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187202', '3', '522419699590500352', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187203', '3', '522419700286754817', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187204', '1', '515166652636205056', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187205', '1', '515166789081108480', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187206', '1', '515183148431183872', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187207', '1', '515183237232988160', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187208', '1', '515184038806425600', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187209', '2', '515184115495079936', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187210', '2', '515184190640230400', '1');
INSERT INTO `sys_stage_grade` VALUES ('538320577531187211', '2', '515184262807425024', '1');


---- 应用中心  分类 ----------------------------
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*applicationCategory','0','applicationCategory','applicationCategory','应用分类','应用分类',0,0,1);

insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*applicationCategory_1','*applicationCategory','538412084024020992','applicationCategory','课内辅导','课内辅导',0,0,1);

insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*applicationCategory_2','*applicationCategory','538412084024020993','applicationCategory','能力培养','能力培养',0,0,1);

insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*applicationCategory_3','*applicationCategory','538412084024020994','applicationCategory','亲自幼教','亲自幼教',0,0,1);

insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*applicationCategory_4','*applicationCategory','538412084024020995','applicationCategory','儿童健康','儿童健康',0,0,1);

insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*applicationCategory_5','*applicationCategory','538412084024020996','applicationCategory','社交娱乐','社交娱乐',0,0,1);
