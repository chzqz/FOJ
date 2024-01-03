package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cmd {
    /**
     *  程序命令行参数
     */
    String[] args;
    /**
     *  程序环境变量
     */
    String[] env;

    /**
     * 指定 标准输入、标准输出和标准错误的文件
     */
    File[] files;
    /**
     * 开启 TTY （需要保证标准输出和标准错误为同一文件）同时需要指定 TERM 环境变量 （例如 TERM=xterm）
     */
    Boolean tty;

    //资源限制

    /**
     * CPU时间限制，单位纳秒
     */
    Long cpuLimit;
    /**
     * 等待时间限制，单位纳秒 （通常为 cpuLimit 两倍）
     */
    Long clockLimit;
    /**
     * 内存限制，单位 byte
     */
    Long memoryLimit;
    /**
     * 栈内存限制，单位 byte
     */
    Long stackLimit;
    /**
     * 线程数量限制
     */
    Long procLimit;
    /**
     *  仅 Linux，CPU 使用率限制，1000 等于单核 100%
     */
    Long cpuRateLimit;
    /**
     *  仅 Linux，限制 CPU 使用，使用方式和 cpuset cgroup 相同 （例如，`0` 表示限制仅使用第一个核）
     */
    String cpuSetLimit;
    /**
     *  deprecated: 使用 dataSegmentLimit （这个选项依然有效）
     */
    Boolean strictMemoryLimit;
    /**
     * 仅linux，开启 rlimit 堆空间限制（如果不使用cgroup默认开启）
     */
    Boolean dataSegmentLimit;
    /**
     * 仅linux，开启 rlimit 虚拟内存空间限制（非常严格，在所以申请时触发限制）
     */
    Boolean addressSpaceLimit;

    /**
     * 在执行程序之前复制进容器的文件列表
     */
    Map<String,File> copyIn;
    /**
     * 在执行程序后从容器文件系统中复制出来的文件列表
     */
    String[] copyOut;

    /**
     * 和 copyOut 相同，不过文件不返回内容，而是返回一个对应文件 ID ，内容可以通过 /file/:fileId 接口下载
     */
    String[] copyOutCached;

    /**
     * 指定 copyOut 复制文件大小限制，单位 byte
     */
    int copyOutMax;
}
