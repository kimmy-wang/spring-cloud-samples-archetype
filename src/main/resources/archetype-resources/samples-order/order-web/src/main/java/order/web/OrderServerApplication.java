#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.order.web;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "${package}.product.feign")
@ComponentScan(basePackages = "${package}")
@SpringCloudApplication
@EnableSwagger2Doc
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

}
