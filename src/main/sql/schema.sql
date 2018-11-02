-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
USE seckill;
-- 创建秒杀库存表
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `number` INT NOT NULL COMMENT '商品库存',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY idx_start_time(`start_time`),
  KEY idx_end_time(`end_time`),
  KEY idx_create_time(`create_time`)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀iPhoneX','100','2018-11-01 00:00:00','2018-11-02 23:59:59'),
  ('800元秒杀iPad','100','2018-11-01 00:00:00','2018-11-02 23:59:59'),
  ('600元秒杀OPPO R20','100','2018-11-01 00:00:00','2018-11-02 23:59:59'),
  ('400元秒杀vivo xs','100','2018-11-01 00:00:00','2018-11-02 23:59:59');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` VARCHAR(11) NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态：-1 无效 0：成功 1：已付款 2：已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`), /*联合主键*/
  KEY idx_create_time(`create_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';


