package cloud.liupeng.provider;

import com.lk.api.annotation.LKADocument;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 支付服务，数据提供
 *
 * @author liupeng
 */
@EnableDubbo
@EntityScan("cloud.liupeng.domain.entity.pay")
@LKADocument(basePackages = "cloud.liupeng.provider.pay", enabled = true)
@SpringBootApplication(scanBasePackages = "cloud.liupeng")
public class DatalayerDubboProviderPayApplication {

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
        SpringApplication.run(DatalayerDubboProviderPayApplication.class, args);
        System.out.println("集群启动模式，mvn pakage 打包后进入 target 目录下执行以下命令");
        System.out.println("后台启动：nohup java -jar spring-cloud-alibaba-dubbo-provider-pay-1024.jar --server.port=不重复端口");
        System.out.println("spring-cloud-alibaba-dubbo-provider-pay - 启动成功! 默认端口号：1024 \n" +
                " ,---.  ,---.    .---..-.   .-.,-. ,'|\"\\   ,---.  ,---.    \n" +
                " | .-.\\ | .-.\\  / .-. )\\ \\ / / |(| | |\\ \\  | .-'  | .-.\\   \n" +
                " | |-' )| `-'/  | | |(_)\\ V /  (_) | | \\ \\ | `-.  | `-'/   \n" +
                " | |--' |   (   | | | |  ) /   | | | |  \\ \\| .-'  |   (    \n" +
                " | |    | |\\ \\  \\ `-' / (_)    | | /(|`-' /|  `--.| |\\ \\   \n" +
                " /(     |_| \\)\\  )---'         `-'(__)`--' /( __.'|_| \\)\\  \n" +
                "(__)        (__)(_)                       (__)        (__) \n");
    }
}
