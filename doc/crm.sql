# 这段SQL，是为了用来演示用户权限管理的


# 用户表
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
     `userid` int NOT NULL AUTO_INCREMENT,
     `loginname` varchar(10) DEFAULT NULL,
     `password` varchar(12) DEFAULT NULL,
     `realname` varchar(10) DEFAULT NULL,
     `sex` int DEFAULT NULL,
     `email` varchar(20) DEFAULT NULL,
     `address` varchar(50) DEFAULT NULL,
     `phone` varchar(12) DEFAULT NULL,
     `cardid` varchar(20) DEFAULT NULL,
     `desc` varchar(50) DEFAULT NULL,
#      用户是1方，角色是多方
     `roleid` int DEFAULT NULL,
     PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


# 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `roleid` int NOT NULL AUTO_INCREMENT,
    `rolename` varchar(10) DEFAULT NULL,
    `rolestate` int DEFAULT NULL COMMENT '0 禁用 1 启用',
    PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 中间表 （角色表和菜单表之间的关系）
DROP TABLE IF EXISTS `middle`;
CREATE TABLE `middle` (
  `middleid` int NOT NULL AUTO_INCREMENT,
  `roleid` int DEFAULT NULL,
  `menuid` int DEFAULT NULL,
  PRIMARY KEY (`middleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 菜单表（其实就是用户的权限表）
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `menuid` int NOT NULL AUTO_INCREMENT,
    `menuname` varchar(255) DEFAULT NULL comment '菜单名称',
    `upmenuid` int DEFAULT NULL comment '表示菜单级别  如果为0，表示的是 顶级菜单',
    `state` int DEFAULT NULL comment '菜单状态 1为启用  0为禁用',
    `desc` varchar(50) DEFAULT NULL comment '菜单介绍',
    `url` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;