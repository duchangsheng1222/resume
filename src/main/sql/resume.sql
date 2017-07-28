

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
`experience_length`  varchar(500) NULL DEFAULT NULL COMMENT '专业技能' ,

`create_time`  date_time NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`creator_id`  varchar(100)  NULL DEFAULT NULL COMMENT '新建用户' ,
`updater_id`  varchar(100)  NULL DEFAULT NULL COMMENT '更新用户' ,
PRIMARY KEY (`id`),
KEY `idx_creator_id` (`creator_id`) USING BTREE 
);

create TABLE t_resume_file(
	`id`  bigint(16) NOT NULL AUTO_INCREMENT ,
  `resume_id`  bigint(16) NOT NULL COMMENT '简历id', 
  `file_address`  varchar(500)  NULL DEFAULT NULL COMMENT '文件地址' ,
  `deleted` enum('0','1')  NULL DEFAULT '0' COMMENT '文件地址' ,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8










