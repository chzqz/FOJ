package icu.chzqz.foj.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结果类
 * @param <T> 结果类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 响应码
     */
    Integer code;
    /**
     * 响应信息
     */
    String msg;
    /**
     * 响应数据
     */
    T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 生成响应结果
     * @param data 响应数据
     * @return 生成响应结果对象
     * @param <T> 对象类型
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(200,"OK",data);
    }
    public static <T> Result<T> success(){
        return new Result<T>(200,"OK");
    }

    /**
     * 生成错误结果
     * @param msg 错误信息
     * @return 生成错误结果对象
     */
    public static Result error(Integer code,String msg){
        return new Result(code,msg,null);
    }
}
