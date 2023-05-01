package cloud.liupeng.api.exception.handler;

import cloud.liupeng.api.utils.JSONResult;
import cloud.liupeng.api.exception.BusinessException;
import cloud.liupeng.api.exception.ServiceException;
import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)  //加载优先级
@SuppressWarnings("all")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({ServiceException.class})
    public String serviceExceptionHandler(HttpServletRequest req, ServiceException e) throws Exception {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public JSONResult businessExceptionHandler(HttpServletRequest req, BusinessException e) throws Exception {
        return JSONResult.message(HttpStatus.HTTP_INTERNAL_ERROR, "自定义 业务 异常", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({ArithmeticException.class})
    public JSONResult arithmeticExceptionHandler(HttpServletRequest req, ArithmeticException e) throws Exception {
        return JSONResult.message(HttpStatus.HTTP_INTERNAL_ERROR, "自定义 算数 异常", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({IllegalStateException.class})
    public JSONResult illegalStateExceptionHandler(HttpServletRequest req, IllegalStateException e) throws Exception {
        return JSONResult.message(HttpStatus.HTTP_INTERNAL_ERROR, "IllegalStateException 异常", e.getMessage());
    }
}