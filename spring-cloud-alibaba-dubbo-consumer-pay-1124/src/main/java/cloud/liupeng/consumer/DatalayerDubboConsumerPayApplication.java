package cloud.liupeng.consumer;

import com.lk.api.annotation.LKADocument;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dubbo 提供支付服务，数据消费
 * 注意：先启动 spring-cloud-alibaba-datalayer-dubbo-provider-pay-1024 再启动本应用
 *
 * @author liupeng
 */
@EnableDubbo
@LKADocument(basePackages = "cloud.liupeng.consumer.controller", enabled = true)
@SpringBootApplication
public class DatalayerDubboConsumerPayApplication {

    /**
     * Lkadoc 接口文档自动生成工具
     */
    @Controller
    public class AccountLkadoc {
        @GetMapping("/")
        public String index() {
            return "redirect:/lkadoc.html";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DatalayerDubboConsumerPayApplication.class, args);
        System.out.println("集群启动模式，mvn pakage 打包后进入 target 目录下执行以下命令");
        System.out.println("后台启动：nohup java -jar spring-cloud-alibaba-dubbo-consumer-pay-1124.jar --server.port=不重复端口");
        System.out.println("spring-cloud-alibaba-dubbo-consumer-pay - 启动成功! 默认端口号：1124 \n" +
                "  ,--,  .---.  .-. .-.   .---. .-. .-.         ,---.  ,---.    \n" +
                ".' .') / .-. ) |  \\| |  ( .-._)| | | ||\\    /| | .-'  | .-.\\   \n" +
                "|  |(_)| | |(_)|   | | (_) \\   | | | ||(\\  / | | `-.  | `-'/   \n" +
                "\\  \\   | | | | | |\\  | _  \\ \\  | | | |(_)\\/  | | .-'  |   (    \n" +
                " \\  `-.\\ `-' / | | |)|( `-'  ) | `-')|| \\  / | |  `--.| |\\ \\   \n" +
                "  \\____\\)---'  /(  (_) `----'  `---(_)| |\\/| | /( __.'|_| \\)\\  \n" +
                "       (_)    (__)                    '-'  '-'(__)        (__) \n");
    }
}
