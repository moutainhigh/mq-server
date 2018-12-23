CREATE TABLE `mq_from` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `topic` varchar(1024) NOT NULL,
  `mq_from_status` int(11) NOT NULL,
  `biz_name` varchar(20) NOT NULL,
  `tags` varchar(80) NOT NULL,
  `body` text NOT NULL,
  `error_times` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_bizname_status` (`biz_name`,`mq_from_status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table mq_from add to_app_type int(4) comment '发送到哪个集群';