package cloud.liupeng.pay.alipay.controller;

import cloud.liupeng.api.utils.JsonResult;
import cloud.liupeng.pay.alipay.config.AlipayUtil;
import cn.hutool.http.HttpStatus;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liupeng
 */
@RestController
@LKAType(value = "AlipayController", description = "支付服务，支付宝支付接口")
@RequestMapping("/pay/alipay")
public class AlipayController {

    @Autowired
    private AlipayUtil alipayUtil;

    /**
     * 创建支付宝订单
     *
     * @param out_trade_no
     * @param total_amount
     * @param subject
     * @return
     */
    @LKAMethod(value = "create 方法", description = "创建支付宝订单，提交方式：post，URL：/pay/alipay/create")
    @LKAParam(names = {"out_trade_no", "total_amount", "subject"}, values = {"商户订单号", "付款金额", "订单名称"})
    @PostMapping("/create")
    public JsonResult create(@PathVariable("out_trade_no") String out_trade_no, @PathVariable("total_amount") String total_amount, @PathVariable("subject") String subject) {
        String pay = alipayUtil.pay(out_trade_no, total_amount, subject);
        return JsonResult.success(HttpStatus.HTTP_OK, "创建订单成功", pay, true);
    }

    /**
     * 支付宝支付同步通知
     *
     * @param out_trade_no
     * @return
     */
    @LKAMethod(value = "returnNotice 方法", description = "支付宝支付同步通知，提交方式：get，URL：/pay/alipay/create")
    @LKAParam(name = "out_trade_no", value = "商户订单号")
    @GetMapping("/returnNotice/{out_trade_no}")
    public JsonResult returnNotice(@PathVariable("value") String out_trade_no) {
        String query = alipayUtil.query(out_trade_no);
        return JsonResult.success(HttpStatus.HTTP_OK, "同步通知", query, true);
    }

    /**
     * 支付宝支付异步通知
     *
     * @param trade_no
     * @param total_amount
     * @param trade_status
     * @return
     */
    @LKAMethod(value = "notify 方法", description = "支付宝支付异步通知，提交方式：post，URL：/pay/alipay/create")
    @LKAParam(names = {"trade_no", "total_amount", "trade_status"}, values = {"支付宝订单编号", "订单金额", "订单状态"})
    @PostMapping("/notify")
    public JsonResult notify(@PathVariable("value") String trade_no, @PathVariable("value") String total_amount, @PathVariable("value") String trade_status) {
        String data = "支付宝订单编号：" + trade_no + ", 订单金额： " + total_amount + ",订单状态：" + trade_status;
        return JsonResult.success(HttpStatus.HTTP_OK, "异步通知", data, true);
    }
}
