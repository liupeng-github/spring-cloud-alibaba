package cloud.liupeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 应用网关
 *
 * @author liupeng
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
        System.out.println("集群启动模式，mvn pakage 打包后进入 target 目录下执行以下命令");
        System.out.println("后台启动：nohup java -jar spring-cloud-alibaba-service-gateway-9527.jar --server.port=不重复端口");
        System.out.println("spring-cloud-alibaba-service-gateway - 启动成功! 默认端口号：9527 \n" +
                "  ,--,     .--.  _______ ,---.  .-.  .-.  .--..-.   .-.\n" +
                ".' .'     / /\\ \\|__   __|| .-'  | |/\\| | / /\\ \\\\ \\_/ )/\n" +
                "|  |  __ / /__\\ \\ )| |   | `-.  | /  \\ |/ /__\\ \\\\   (_)\n" +
                "\\  \\ ( _)|  __  |(_) |   | .-'  |  /\\  ||  __  | ) (   \n" +
                " \\  `-) )| |  |)|  | |   |  `--.|(/  \\ || |  |)| | |   \n" +
                " )\\____/ |_|  (_)  `-'   /( __.'(_)   \\||_|  (_)/(_|   \n" +
                "(__)                    (__)                   (__)    \n");
    }
}
