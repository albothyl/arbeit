USE arbeit;

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '회원 ID',
  `email` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '회원 email??',
  `password` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '회원 비밀번호',
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '회원 이름',
  `nickName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '회원 별명',
  `memberGrade` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '회원 등급',
  `updatedAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '최종수정 일시',
  `registedAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '회원가입 일시',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `nick_name_UNIQUE` (`nickName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = 'spring security에서 사용하는 사용자정보관리 테이블';
