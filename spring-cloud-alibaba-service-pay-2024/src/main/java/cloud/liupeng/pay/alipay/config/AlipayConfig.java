package cloud.liupeng.pay.alipay.config;

/**
 * 支付宝配置
 *
 * @author liupeng
 */
public class AlipayConfig {

    /**
     * APPID 即创建小程序后生成
     * https://opendocs.alipay.com/mini/introduce/create
     */
    public static String app_id;
    /**
     * 支付宝网关（固定）
     */
    public static String gateway_url = "https://openapi.alipay.com/gateway.do";
    /**
     * 商户私钥，您的 PKCS8 格式 RSA2 私钥
     */
    public static String privateKe = "开发者私钥，由开发者自己生成";
    /**
     * 支付宝公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应 APPID 下的支付宝公钥。
     */
    public static String alipay_public_key = "应用公钥";
    /**
     * 服务器异步通知页面路径  需 http:// 格式的完整路径，不能加 ?id=123 这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://127.0.0.1:8905/notify";
    /**
     * 页面跳转同步通知页面路径 需 http:// 格式的完整路径，不能加 ?id=123 这类自定义参数，必须外网可以正常访问
     */
    public static String return_url = "http://127.0.0.1:8905/return";

    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    public static String charset = "utf-8";
    /**
     * 参数返回格式，只支持 JSON 格式
     */
    public static String format = "json";
}
