package me.yuanzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.annotation.Import;


/**
 * @author zhixiang.yuan
 * @since 2019/12/28 00:09:41
 */


@SpringBootApplication(
		exclude = {SpringApplicationAdminJmxAutoConfiguration.class},
		scanBasePackages = "me.yuanzx.controller"
)
public class MVCApplication {

	public static void main(String[] args) {
		SpringApplication.run(MVCApplication.class, args);

	}

}