package cloud.liupeng;

import com.lk.api.annotation.LKADocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 消息中间件
 * @author liupeng
 */
@LKADocument(basePackages = "cloud.liupeng.controller", enabled = true)
@SpringBootApplication
public class ServiceRocketmqApplication {

    /**
     * Lkadoc 接口文档自动生成工具
     */
    @Controller
    public class AuthLkadoc {
        @GetMapping("/")
        public String index() {
            return "redirect:/lkadoc.html";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceRocketmqApplication.class, args);
        System.out.println("集群启动模式，mvn pakage 打包后进入 target 目录下执行以下命令");
        System.out.println("后台启动：nohup java -jar spring-cloud-alibaba-service-rocketmq-3020.jar --server.port=不重复端口");
        System.out.println("spring-cloud-alibaba-service-rocketmq - 启动成功! 默认端口号：3020 \n " +
                ",---.    .---.    ,--,  ,-. .-.,---. _______           .---.  \n" +
                "| .-.\\  / .-. ) .' .')  | |/ / | .-'|__   __||\\    /| ( .-. \\ \n" +
                "| `-'/  | | |(_)|  |(_) | | /  | `-.  )| |   |(\\  / |(_)| | | \n" +
                "|   (   | | | | \\  \\    | | \\  | .-' (_) |   (_)\\/  | | ||\\ | \n" +
                "| |\\ \\  \\ `-' /  \\  `-. | |) \\ |  `--. | |   | \\  / | \\ `-\\\\/ \n" +
                "|_| \\)\\  )---'    \\____\\|((_)-'/( __.' `-'   | |\\/| |  `---\\| \n" +
                "    (__)(_)             (_)   (__)           '-'  '-'         \n");
    }
}
