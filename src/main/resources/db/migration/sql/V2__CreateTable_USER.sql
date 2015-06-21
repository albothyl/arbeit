USE arbeit;

CREATE TABLE `arbeit`.`user` (
  `email` VARCHAR(70) NOT NULL COMMENT 'login�� id�� �����',
  `password` VARCHAR(70) NOT NULL,
  `authority` VARCHAR(100) NULL DEFAULT 'USER' COMMENT 'user�� �������� ���߱����� ��� \" , \"���� concat�� �ϳ��� ���ڿ��� �����ϰ�, ���� �̸� �Ľ��Ͽ� ����Ѵ�.',
  `accountNonExpired` TINYINT(1) NULL DEFAULT 1 COMMENT 'ȸ�� ���Ῡ��',
  `accountNonLocked` TINYINT(1) NULL DEFAULT 1 COMMENT 'ȸ�� ��迩��',
  `credentialsNonExpired` TINYINT(1) NULL DEFAULT 1 COMMENT 'password ���Ῡ��',
  `enabled` TINYINT(1) NULL DEFAULT 1 COMMENT '���� ��뿩��',
  `memberId` BIGINT(20) NOT NULL COMMENT 'member���̺�� �����ϱ� ���� �ܷ�Ű',
  `updatedAt` DATETIME NULL DEFAULT NOW() COMMENT '���� �����Ͻ�',
  `createdAt` DATETIME NULL DEFAULT NOW() COMMENT '���� �����Ͻ�',
  PRIMARY KEY (`email`),
  UNIQUE KEY `memberId_UNIQUE` (`memberId`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = 'spring security�� login, logout�������� ����� ����� �����͸� �����ϴ� ���̺�';
