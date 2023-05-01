package cloud.liupeng.api.sentinel;

import cloud.liupeng.api.utils.JSONResult;
import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 自定义 Sentinel 流量控制
 * @author liupeng
 */
public class SentinelFallback {

    public static JSONResult handlerFallback(Throwable throwable) {
        return JSONResult.message(HttpStatus.HTTP_INTERNAL_ERROR, "自定义 handlerFallback 服务异常！", "异常信息：" + throwable);
    }

    public static JSONResult blockHandler(BlockException blockException) {
        return JSONResult.message(HttpStatus.HTTP_INTERNAL_ERROR, "自定义 blockHandler 服务限流，来源于 sentine 配置！", "异常信息：" + blockException);
    }
}
