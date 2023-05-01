package cloud.liupeng;

import com.lk.api.annotation.LKADocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 物流服务
 *
 * @author liupeng
 */
@LKADocument(basePackages = "cloud.liupeng.controller", enabled = true)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceLogisticsApplication {

    /**
     * Lkadoc 接口文档自动生成工具
     */
    @Controller
    public class LogisticsLkadoc {
        @GetMapping("/")
        public String index() {
            return "redirect:/lkadoc.html";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceLogisticsApplication.class, args);
        System.out.println("集群启动模式，mvn pakage 打包后进入 target 目录下执行以下命令");
        System.out.println("后台启动：nohup java -jar spring-cloud-alibaba-service-logistics-2025.jar --server.port=不重复端口");
        System.out.println("spring-cloud-alibaba-service-logistics - 启动成功! 默认端口号：2025 \n " +
                ",-.    .---.    ,--,   ,-.   .---.  _______ ,-.  ,--,    .---. \n" +
                "| |   / .-. ) .' .'    |(|  ( .-._)|__   __||(|.' .')   ( .-._)\n" +
                "| |   | | |(_)|  |  __ (_) (_) \\     )| |   (_)|  |(_) (_) \\   \n" +
                "| |   | | | | \\  \\ ( _)| | _  \\ \\   (_) |   | |\\  \\    _  \\ \\  \n" +
                "| `--.\\ `-' /  \\  `-) )| |( `-'  )    | |   | | \\  `-.( `-'  ) \n" +
                "|( __.')---'   )\\____/ `-' `----'     `-'   `-'  \\____\\`----'  \n" +
                "(_)   (_)     (__)                                             \n");
    }

}
