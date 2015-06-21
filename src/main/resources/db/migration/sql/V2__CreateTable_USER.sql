USE arbeit;

CREATE TABLE `arbeit`.`user` (
  `email` VARCHAR(70) NOT NULL COMMENT 'login시 id로 사용할',
  `password` VARCHAR(70) NOT NULL,
  `authority` VARCHAR(100) NULL DEFAULT 'USER' COMMENT 'user의 권한으로 다중권한일 경우 \" , \"으로 concat한 하나의 문자열로 저장하고, 사용시 이를 파싱하여 사용한다.',
  `accountNonExpired` TINYINT(1) NULL DEFAULT 1 COMMENT '회원 만료여부',
  `accountNonLocked` TINYINT(1) NULL DEFAULT 1 COMMENT '회원 잠김여부',
  `credentialsNonExpired` TINYINT(1) NULL DEFAULT 1 COMMENT 'password 만료여부',
  `enabled` TINYINT(1) NULL DEFAULT 1 COMMENT '계정 사용여부',
  `memberId` BIGINT(20) NOT NULL COMMENT 'member테이블과 조인하기 위한 외래키',
  `updatedAt` DATETIME NULL DEFAULT NOW() COMMENT '최종 수정일시',
  `createdAt` DATETIME NULL DEFAULT NOW() COMMENT '최초 생성일시',
  PRIMARY KEY (`email`),
  UNIQUE KEY `memberId_UNIQUE` (`memberId`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = 'spring security의 login, logout과정에서 사용할 사용자 데이터를 관리하는 테이블';
