package me.yuanzx.controller;

/**
 * @author zhixiang.yuan
 * @since 2019/12/28 00:15:34
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "world";
	}

}