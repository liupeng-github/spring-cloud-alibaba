package cloud.liupeng.api.utils;

import com.lk.api.annotation.LKAProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liupeng
 */
@Data
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 17721020985L;

    /**
     * 状态码
     */
    @LKAProperty(value = "code", description = "状态码")
    private int code;

    /**
     * 描述
     */
    @LKAProperty(value = "message", description = "描述")
    private String message = "";

    /**
     * 数据
     */
    @LKAProperty(value = "data", description = "数据")
    private T data;

    /**
     * 状态
     */
    @LKAProperty(value = "flag", description = "状态")
    private boolean flag = true;

    /**
     * 通过静态方法获取实例
     */
    public static <T> JsonResult<T> success(int code) {
        return new JsonResult<>(code);
    }

    public static <T> JsonResult<T> success(int code, String message) {
        return new JsonResult<>(code, message);
    }

    public static <T> JsonResult<T> success(int code, String message, T data) {
        return new JsonResult<>(code, message, data);
    }

    public static <T> JsonResult<T> success(int code, String message, T data, boolean flag) {
        return new JsonResult<>(code, message, data, flag);
    }

    @Deprecated
    public JsonResult() {

    }

    private JsonResult(int code) {
        this.code = code;
    }

    private JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private JsonResult(int code, String message, T data, boolean flag) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.flag = flag;
    }
}
