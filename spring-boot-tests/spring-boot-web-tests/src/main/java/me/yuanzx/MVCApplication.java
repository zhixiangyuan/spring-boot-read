package me.yuanzx;

/**
 * @author zhixiang.yuan
 * @since 2019/12/28 00:09:41
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MVCApplication {

	public static void main(String[] args) {
		SpringApplication.run(MVCApplication.class, args);

	}

}