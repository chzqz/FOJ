# 数据库

```mysql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS foj;
USE foj;

-- 创建用户表
CREATE TABLE IF NOT EXISTS user(
	id INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(100) COMMENT '用户名',
	`email` VARCHAR(100) NOT NULL COMMENT '电子邮箱',
	picture VARCHAR(100) COMMENT '头像路径',
	`password` VARCHAR(32) COMMENT '密码',
	accepted INT COMMENT '通过题数',
	experiment INT COMMENT '尝试题数',
	create_date DATETIME COMMENT '创建时间', 
	last_login_time DATETIME COMMENT '上次登录时间', 
	authority INT COMMENT '用户权限'
	`comment` TEXT COMMENT '用户介绍',
);


-- 创建题目表
CREATE TABLE IF NOT EXISTS questions(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(100) COMMENT '题目名',
	description TEXT COMMENT '题目描述',
	upload_time DATETIME COMMENT '上传时间', 
	modify_time DATETIME COMMENT '修改时间', 
	ac_count INT  COMMENT '通过次数',
	sub_count INT  COMMENT '提交次数',
	tip TEXT COMMENT '提示',
	max_time BIGINT  COMMENT '最大运行时间(ms)',
	max_memory BIGINT  COMMENT '最大运行内存(MB)',
	max_proc BIGINT  COMMENT '最大线程数',
	uid INT COMMENT '出题人id',
	`status` INT  COMMENT '题目状态',
	`level` INT  COMMENT '题目难度'
);

-- 测试案例表
CREATE TABLE IF NOT EXISTS testcases(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	q_id LONG COMMENT '题目id',
	input VARCHAR(100) COMMENT '输入文件',
	output VARCHAR(100) COMMENT '输出文件',
	`status` INT COMMENT '状态'
);

-- 标签表
CREATE TABLE IF NOT EXISTS tag(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(10) COMMENT '标签名',
	color VARCHAR(20) COMMENT '标签颜色'
);

-- 问题_标签表
CREATE TABLE IF NOT EXISTS questions_tag(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	t_id LONG COMMENT '标签id',
	q_id LONG COMMENT '题目id'
);

-- 判断结果表
CREATE TABLE IF NOT EXISTS judge(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	q_id LONG COMMENT '题目id',
	q_name VARCHAR(100) COMMENT '题目名',
	u_id INT COMMENT '用户id',
	u_name VARCHAR(100) COMMENT '用户名',
	submit_time DATETIME COMMENT '提交时间', 
	`status`INT COMMENT '判题结果',
	error_message TEXT COMMENT '错误信息',
	`time`BIGINT COMMENT '运行时间(ms)',
	`memory`BIGINT COMMENT '所耗内存(MB)',
	`code` TEXT COMMENT '代码', 
	`language` VARCHAR(20) COMMENT '代码语言'
);

```





### user用户表

`user`表含有如下字段:

| 字段名            | 类型         | 解释                                        |
| ----------------- | ------------ | ------------------------------------------- |
| `id`              | int          | 主键                                        |
| `name`            | varchar(100) | 用户名                                      |
| `email`           | varchar(100) | 电子邮箱                                    |
| `picture`         | varchar(100) | 头像路径                                    |
| `password`        | varchar(32)  | 密码(MD5加密)                               |
| `accepted`        | int          | 通过题数                                    |
| `experiment`      | int          | 尝试题数                                    |
| `create_date`     | datetime     | 创建日期                                    |
| `last_login_time` | datetime     | 上次登录时间                                |
| `authority`       | int          | 用户权限(0: 普通, 1: 管理员, 2: 超级管理员) |
| `comment`         | text         | 用户介绍                                    |



### questions题目表

`questions`表含有如下字段:

| 字段名        | 类型         | 解释                         |
| ------------- | ------------ | ---------------------------- |
| `id`          | long         | 主键                         |
| `name`        | varchar(100) | 题目名                       |
| `description` | text         | 题目内容                     |
| `upload_time` | datetime     | 上传时间                     |
| `modify_time` | datetime     | 修改时间                     |
| `ac_count`    | int          | 通过次数                     |
| `sub_count`   | int          | 提交次数                     |
| `tip`         | text         | 提示                         |
| `max_time`    | int          | 最大运行时间(ms)             |
| `max_memory`  | int          | 最大运行内存(MB)             |
| `max_proc`    | int          | 最大线程数                   |
| `uid`         | long         | 出题人id                     |
| `status`      | int          | 题目状态(0:未开放, 1:已开放) |
| `level`       | int          | 难度                         |



### testcases测试案例表

`testcases`表含有如下字段:

| 字段名   | 类型         | 解释                           |
| -------- | ------------ | ------------------------------ |
| `id`     | long         | 主键                           |
| `q_id`   | long         | 逻辑外键, 题目id               |
| `input`  | varchar(100) | 输入文件                       |
| `output` | varchar(100) | 输出文件                       |
| `status` | int          | 0: 不可用, 1:可用, 2: 展示样例 |



### tag标签表

`tag`表含有如下字段:

| 字段名  | 类型        | 解释     |
| ------- | ----------- | -------- |
| `id`    | long        | 主键     |
| `name`  | varchar(10) | 标签名   |
| `color` | varchar(20) | 标签颜色 |



### questions_tag表

| 字段名 | 类型 | 解释   |
| ------ | ---- | ------ |
| id     | long | 主键   |
| t_id   | long | 标签id |
| q_id   | long | 题目id |



### judge判断结果表

> 判题结果状态`status`可取以下值:

未提交：STATUS_NOT_SUBMITTED = -10

提交中：STATUS_SUBMITTING = 9

排队中：STATUS_PENDING = 6

评测中：STATUS_JUDGING = 7

编译错误：STATUS_COMPILE_ERROR = -2

输出格式错误：STATUS_PRESENTATION_ERROR = -3

答案错误：STATUS__WRONG_ANSWER = -1

评测通过：STATUS_ACCEPTED = 0

cpu时间超限：STATUS__CPU_TIME_LIMIT_EXCEEDED = 1

真实时间超限：STATUS__REAL_TIME_LIMIT_EXCEEDED = 2

空间超限：STATUS__MEMORY_LIMIT_EXCEEDED = 3

运行错误：STATUS__RUNTIME_ERROR = 4

系统错误：STATUS__SYSTEM_ERROR = 5

OI评测部分通过：STATUS_PARTIAL_ACCEPTED = 8

提交失败：STATUS_SUBMITTED_FAILED= 10

judge表包含以下字段:

| 字段名          | 类型         | 解释         |
| --------------- | ------------ | ------------ |
| `id`            | long         | 类型         |
| `q_id`          | long         | 题目id       |
| `q_name`        | varchar(100) | 题目名       |
| `u_id`          | int          | 用户id       |
| `u_name`        | varchar(100) | 用户名       |
| `submit_time`   | datetime     | 提交时间     |
| `status`        | int          | 判题结果     |
| `error_message` | text         | 错误信息     |
| `time`          | BIGINT       | 运行时间(ms) |
| `memory`        | BIGINT       | 所耗内存(MB) |
| `code`          | text         | 代码         |
| `language`      | varchar(20)  | 代码语言     |



