USE arbeit;

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '?? ??? ID',
  `email` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '?? email??',
  `password` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '?? ????',
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '?? ??',
  `nickName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '?? ??',
  `grade` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '?? ??',
  `updatedAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '?? ?? ????',
  `registedAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '?? ????',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `nick_name_UNIQUE` (`nickName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;