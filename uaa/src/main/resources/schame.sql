DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
    id                  int(10)                                                 NOT NULL AUTO_INCREMENT,
    username            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
    password            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
    account_expired     bit(1)                                                  NULL DEFAULT NULL COMMENT '账号是否到期',
    account_locked      bit(1)                                                  NULL DEFAULT NULL COMMENT '账号是否锁定',
    credentials_expired bit(1)                                                  NULL DEFAULT NULL COMMENT '密码是否过期',
    enabled             bit(1)                                                  NULL DEFAULT NULL COMMENT '账号是否启用',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户表'
  ROW_FORMAT = Compact;



DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
    id   int(10)                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色表'
  ROW_FORMAT = Compact;



DROP TABLE IF EXISTS sys_authority;
CREATE TABLE sys_authority
(
    id   int(10)                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    code varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限代码',
    url  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '权限表'
  ROW_FORMAT = Compact;



DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role
(
    id      int(10) NOT NULL AUTO_INCREMENT,
    user_id int(10) NULL DEFAULT NULL,
    role_id int(10) NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE,
    INDEX FK_Reference_1 (user_id) USING BTREE,
    INDEX FK_Reference_2 (role_id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户角色表'
  ROW_FORMAT = Compact;



DROP TABLE IF EXISTS sys_user_authority;
CREATE TABLE sys_user_authority
(
    id           int(10) NOT NULL AUTO_INCREMENT,
    user_id      int(10) NULL DEFAULT NULL,
    authority_id int(10) NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE,
    INDEX FK_Reference_3 (user_id) USING BTREE,
    INDEX FK_Reference_4 (authority_id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色权限表'
  ROW_FORMAT = Compact;



DROP TABLE IF EXISTS sys_role_authority;
CREATE TABLE sys_role_authority
(
    id           int(10) NOT NULL AUTO_INCREMENT,
    role_id      int(10) NULL DEFAULT NULL,
    authority_id int(10) NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE,
    INDEX FK_Reference_3 (role_id) USING BTREE,
    INDEX FK_Reference_4 (authority_id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色权限表'
  ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
