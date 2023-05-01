package cloud.liupeng.openfeign.exception;

import cloud.liupeng.api.exception.ServiceException;
import cn.hutool.http.HttpStatus;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 异常场景，例如服务调用异常
 *
 * @author liupeng
 */
@Configuration
public class FeignServiceException implements ErrorDecoder {

    /**
     * 官网示例
     *
     * @param methodKey
     * @param response
     * @return
     */
    /*@Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            return new StashClientException(
                    response.status(),
                    response.reason()
            );
        }
        if (response.status() >= 500 && response.status() <= 599) {
            return new StashServerException(
                    response.status(),
                    response.reason()
            );
        }
        return errorStatus(methodKey, response);
    }*/

    /**
     * 自定义服务调用异常
     *
     * @param methodKey
     * @param response
     * @return
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= HttpStatus.HTTP_INTERNAL_ERROR && response.status() <= HttpStatus.HTTP_VERSION) {
            try {
                SortedMap<Object, Object> bodyJson = new TreeMap<Object, Object>() {
                    private static final long serialVersionUID = 1L;

                    {
                        put("status", response.status());
                        put("data", Util.toString(response.body().asReader(Charset.defaultCharset())));
                        put("message", "自定义服务调用异常，服务调用 URL：" + response.request().url());
                    }
                };
                return new ServiceException(bodyJson.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return FeignException.errorStatus(methodKey, response);
    }
}