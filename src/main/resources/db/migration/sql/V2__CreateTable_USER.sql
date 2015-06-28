USE arbeit;

CREATE TABLE `arbeit`.`user` (
  `email` VARCHAR(70) NOT NULL COMMENT '사용자 username',
  `password` VARCHAR(70) NOT NULL COMMENT '사용자 password',
  `authority` VARCHAR(100) NULL DEFAULT 'USER' COMMENT '사용자 권한',
  `accountNonExpired` TINYINT(1) NULL DEFAULT 1 COMMENT '사용자 만료여부',
  `accountNonLocked` TINYINT(1) NULL DEFAULT 1 COMMENT '사용자 블럭여부',
  `credentialsNonExpired` TINYINT(1) NULL DEFAULT 1 COMMENT '사용자 인증여부',
  `enabled` TINYINT(1) NULL DEFAULT 1 COMMENT '사용자 사용여부',
  `memberId` BIGINT(20) NOT NULL COMMENT '회원테이블과 조인하기위한 외래키',
  `updatedAt` DATETIME NULL DEFAULT NOW() COMMENT '최종수정 일시',
  `createdAt` DATETIME NULL DEFAULT NOW() COMMENT '최초생성 일시',
  PRIMARY KEY (`email`),
  UNIQUE KEY `memberId_UNIQUE` (`memberId`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = 'spring security에서 사용하는 사용자정보관리 테이블';

ALTER TABLE `arbeit`.`member` DROP COLUMN `password`;
