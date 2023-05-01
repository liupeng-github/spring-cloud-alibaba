package cloud.liupeng.sentinel;

import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 自定义的限流返回信息
 *
 * @author liupeng
 */
@Component
public class CustomBlockRequestHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
        SortedMap<Object, Object> bodyJson = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("code", 429);
                put("data", "异常类：" + throwable.getClass().getSimpleName());
                put("message", "这个请求 Sentinel 阻止了。");
            }
        };
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .bodyValue(JSONUtil.toJsonPrettyStr(bodyJson));
    }
}
