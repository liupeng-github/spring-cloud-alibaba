package cloud.liupeng.api.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 全局拦截器
 *
 * @author liupeng
 */
@Configuration
public class GlobalInterceptor implements HandlerInterceptor {

    /**
     * 进入Controller方法之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (response.getStatus() == 404) {
            returnJson(response, "404响应");
        }

        /*HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            return true;
        }else{
            returnJson(response, "用户被拦截了");
            return false;
        }*/

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 调用完Controller之后，视图渲染之前，如果Controller出现异常，将不会执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个完成之后，通常用于资源清理，不管有没有异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 返回json数据
     *
     * @param response
     * @param message
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private void returnJson(HttpServletResponse response, String message) throws Exception {
        SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("status", response.getStatus());
                put("message", message);
            }
        };

        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(sortedMap);
        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
