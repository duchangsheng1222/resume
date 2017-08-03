

create TABLE t_resume_info(
	`id`  bigint(16) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) NULL DEFAULT NULL COMMENT '名字' ,
`age`  int(11)  NULL DEFAULT NULL COMMENT '年龄' ,
`gender`  enum('f','m') NULL DEFAULT NULL COMMENT '性别' ,
`position`  varchar(500)  NULL DEFAULT NULL COMMENT '职位' ,
`phone`  varchar(100)  NULL DEFAULT NULL COMMENT '手机号' ,
`citizenship`  varchar(100)  NULL DEFAULT NULL COMMENT '国籍' ,
`education`  varchar(500) NULL DEFAULT NULL COMMENT '教育背景' ,
`major`  varchar(500) NULL DEFAULT NULL COMMENT '主修课' ,
`country`  varchar(500) NULL DEFAULT NULL COMMENT '教育所在国家' ,
`certification`  varchar(500) NULL DEFAULT NULL COMMENT '证书' ,
`specialized`  varchar(500) NULL DEFAULT NULL COMMENT '专业技能' ,
`experience_length`  varchar(500) NULL DEFAULT NULL COMMENT '工作年限' ,
`other_positions`  varchar(500) NULL DEFAULT NULL COMMENT '其他职位' ,

`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`creator_id`  bigint(16)  NULL DEFAULT NULL COMMENT '新建用户' ,
`updater_id`  bigint(16)  NULL DEFAULT NULL COMMENT '更新用户' ,
PRIMARY KEY (`id`),
KEY `idx_creator_id` (`creator_id`) USING BTREE 
);



create TABLE t_resume_file(
	`id`  bigint(16) NOT NULL AUTO_INCREMENT ,
  `resume_id`  bigint(16) NOT NULL COMMENT '简历id', 
  `user_id`  bigint(16) NOT NULL COMMENT '用户id', 
  `file_address`  varchar(500)  NULL DEFAULT NULL COMMENT '文件地址' ,
  `file_type`  varchar(500)  NULL DEFAULT NULL COMMENT '文件类型' ,
  `type`  int  NULL DEFAULT 0 COMMENT '业务类型 0:简历，1:自荐视频' ,
  `deleted` enum('0','1')  NULL DEFAULT '0' COMMENT '文件地址' ,
  PRIMARY KEY (`id`),
  KEY `idx_resume_id_type` (`resume_id`,`type`) USING BTREE 
);

drop table if exists t_interview_flow;
create TABLE t_interview_flow(
	`id`  bigint(16) NOT NULL AUTO_INCREMENT ,
	`resume_id` bigint(16) NULL DEFAULT NULL COMMENT '简历id' ,
	`step`  int(11) NULL DEFAULT NULL COMMENT '状态' ,
	`received`  enum('0','1')  NULL DEFAULT NULL COMMENT '是否收到offer 0：未收到 1：收到' ,
	`accepted`  enum('0','1')  NULL DEFAULT NULL COMMENT '是否接受offer 0：不接受 1：接受' ,
	`arrive_date`  datetime  NULL DEFAULT NULL COMMENT '抵达中国日期' ,
	`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`creator_id`  bigint(16)  NULL DEFAULT NULL COMMENT '新建用户' ,
	`updater_id`  bigint(16)  NULL DEFAULT NULL COMMENT '更新用户' ,
	PRIMARY KEY (`id`)
);


create TABLE t_comment(
	`id`  bigint(16) NOT NULL AUTO_INCREMENT ,
	`code`  varchar(255) NULL DEFAULT NULL COMMENT '唯一编码' ,
	`content`  text NULL DEFAULT NULL COMMENT '内容' ,
	PRIMARY KEY (`id`)
);

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `nickname` varchar(255) default NULL,
  `locked` smallint(6) default NULL,
  `phone` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
);

drop table if exists t_role;
create table t_role(
 `id` bigint(20) NOT NULL auto_increment,
  `role` varchar(255) default NULL,
  `role_name` varchar(255) default NULL,
  `role_key` varchar(255) default NULL,
   PRIMARY KEY  (`id`)
)

create table t_user_role(
	`id` bigint(20) NOT NULL auto_increment,
	`user_id` bigint(20) NOT NULL ,
	`role_id` bigint(20) NOT NULL ,
	 PRIMARY KEY  (`id`)
 );
 
 create table t_resources(
	`id` bigint(20) NOT NULL auto_increment,
	`name` varchar(255) NULL default null ,
	`parent_id` bigint(20) NOT NULL default 0 ,
	`url`  varchar(255)  default NULL,
	`res_type`  int(11)  NOT NULL default 0 COMMENT '资源类型 0：菜单 1：事件' ,
	 PRIMARY KEY  (`id`)
 )
 
create table t_res_role(
	`id` bigint(20) NOT NULL auto_increment,
	`user_id` bigint(20) NOT NULL ,
	`role_id` bigint(20) NOT NULL ,
	 PRIMARY KEY  (`id`)
 )









