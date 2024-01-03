package icu.chzqz.foj.entity;

public class Status {
    /**
     * 未提交
     */
    public static Integer STATUS_NOT_SUBMITTED = -10;
    /**
     * 提交中
     */
    public static Integer STATUS_SUBMITTING  = 9;
    /**
     * 排队中
     */
    public static Integer STATUS_PENDING  = 6;
    /**
     * 评测中
     */
    public static Integer STATUS_JUDGING  = 7;
    /**
     * 编译错误
     */
    public static Integer STATUS_COMPILE_ERROR  = -2;
    /**
     * 输出格式错误
     */
    public static Integer STATUS_PRESENTATION_ERROR = -3;
    /**
     * 答案错误
     */
    public static Integer STATUS__WRONG_ANSWER  = -1;
    /**
     * 评测通过
     */
    public static Integer STATUS_ACCEPTED  = 0;
    /**
     * cpu时间超限
     */
    public static Integer STATUS__CPU_TIME_LIMIT_EXCEEDED  = 1;
    /**
     * 真实时间超限
     */
    public static Integer STATUS__REAL_TIME_LIMIT_EXCEEDED  = 2;
    /**
     * 空间超限
     */
    public static Integer STATUS__MEMORY_LIMIT_EXCEEDED  = 3;
    /**
     * 运行错误
     */
    public static Integer STATUS__RUNTIME_ERROR   = 4;
    /**
     * 系统错误
     */
    public static Integer STATUS__SYSTEM_ERROR   = 5;
    /**
     * OI评测部分通过
     */
    public static Integer STATUS_PARTIAL_ACCEPTED   = 8;
    /**
     * 提交失败
     */
    public static Integer STATUS_SUBMITTED_FAILED  = 10;

}
