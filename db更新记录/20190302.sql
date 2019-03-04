-- 文件类型字典数据 ----------
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*fileType','0','fileType','fileType','应用分类','应用分类',0,0,1);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*fileType_1','*fileType','551420890108719104','fileType','doc','doc文档',0,0,1);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*fileType_2','*fileType','551420890112913408','fileType','docx','docx文档',0,0,1);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*fileType_3','*fileType','551420890112913409','fileType','ppt','ppt幻灯片',0,0,1);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*fileType_4','*fileType','551420890112913410','fileType','pptx','pptx幻灯片',0,0,1);
-- 图片类型编码注意 remark值 用来区分是否图片
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status,remark)
values('*fileType_5','*fileType','551420890112913411','fileType','JPG','JPG 图片类型remark有值为2',0,0,1,2);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status,remark)
values('*fileType_6','*fileType','551420890112913415','fileType','JPEG','JPEG  图片类型remark有值为2',0,0,1,2);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status,remark)
values('*fileType_7','*fileType','551420890112913414','fileType','PNG','PNG  图片类型remark有值为2',0,0,1,2);
insert into sys_dict(dict_id,p_dict_id,dict_code,dict_type,dict_name,dict_desc,level,seq,status)
values('*fileType_8','*fileType','551420890112913413','fileType','APK','APK',0,0,1);
