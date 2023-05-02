package cloud.liupeng.pay.alipay.config;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 支付包工具类
 *
 * @author liupeng
 */
@Slf4j
@Component
public class AlipayUtil {

    /**
     * 创建订单
     *
     * @param out_trade_no
     * @param total_amount
     * @param subject
     * @return
     */
    public String pay(String out_trade_no, String total_amount, String subject) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway_url, AlipayConfig.app_id, AlipayConfig.privateKe, AlipayConfig.format, AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);

        JSONObject bizContent = new JSONObject();
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        bizContent.put("out_trade_no", out_trade_no);
        // 付款金额，必填
        bizContent.put("total_amount", total_amount);
        // 订单名称，必填
        bizContent.put("subject", subject);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());

        AlipayTradePagePayResponse response = null;
        String form = null;
        try {
            response = alipayClient.pageExecute(request);
            form = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            log.info("调用成功");
        } else {
            log.info("调用失败");
        }
        return form;
    }

    public String query(String id) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway_url, AlipayConfig.app_id, AlipayConfig.privateKe, AlipayConfig.format, AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", id);
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = null;
        String body = null;
        try {
            response = alipayClient.execute(request);
            body = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            log.info("调用成功");
        } else {
            log.info("调用失败");
        }
        return body;
    }
}
