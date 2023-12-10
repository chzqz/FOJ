# 数据库



### user用户表

`user`表含有如下字段:

| 字段名            | 类型         | 解释                                        |
| ----------------- | ------------ | ------------------------------------------- |
| `id`              | long         | 主键                                        |
| `name`            | varchar(100) | 用户名                                      |
| `email`           | varchar(100) | 电子邮箱                                    |
| `picture`         | varchar(100) | 头像路径                                    |
| `password`        | varchar(100) | 密码(加密)                                  |
| `Accepted`        | int          | 通过题数                                    |
| `experiment`      | int          | 尝试题数                                    |
| `create_date`     | datetime     | 创建日期                                    |
| `last_login_time` | datetime     | 上次登录时间                                |
| `authority`       | int          | 用户权限(0: 普通, 1: 管理员, 2: 超级管理员) |



### questions题目表

`questions`表含有如下字段:

| 字段名        | 类型         | 解释             |
| ------------- | ------------ | ---------------- |
| `id`          | long         | 主键             |
| `name`        | varchar(100) | 题目名           |
| `description` | text         | 题目内容         |
| `upload_time` | datetime     | 上传时间         |
| `modify_time` | datetime     | 修改时间         |
| `ac_count`    | int          | 通过次数         |
| `count`       | int          | 提交次数         |
| `tip`         | text         | 提示             |
| `max_time`    | int          | 最大运行时间(ms) |
| `max_memory`  | int          | 最大运行内存(MB) |
| `max_stack`   | int          | 最大栈容量(MB)   |
| `input`       | text         | 输入样例         |
| `output`      | text         | 输出样例         |
| `uid`         | long         | 出题人id         |



### testcases测试案例表

`testcases`表含有如下字段:

| 字段名   | 类型   | 解释              |
| -------- | ------ | ----------------- |
| `id`     | long   | 主键              |
| `q_id`   | long   | 逻辑外键, 题目id  |
| `input`  | string | 输入文件          |
| `output` | string | 输出文件          |
| `status` | int    | 0: 不可用, 1:可用 |



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
| `q_name`        | string       | 题目名       |
| `u_id`          | long         | 用户id       |
| `username`      | varchar(100) | 用户名       |
| `submit_time`   | datetime     | 提交时间     |
| `status`        | int          | 判题结果     |
| `error_message` | text         | 错误信息     |
| `time`          | int          | 运行时间(ms) |
| `memory`        | int          | 所耗内存(MB) |
| `code`          | text         | 代码         |
| `language`      | varchar(20)  | 代码语言     |

