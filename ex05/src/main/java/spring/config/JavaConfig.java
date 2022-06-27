package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.bean.Client2;
import spring.bean.Client3;

@Configuration
public class JavaConfig {
	@Bean(initMethod = "open", destroyMethod = "close")
	public Client2 c2() {
		Client2 c2 = new Client2();
		c2.setHost("웹서버2");
		return c2;
	}
	
	@Bean
	@Scope("prototype") //범위가 프로토 타입으로 작동
	public Client3 c3() {
		Client3 c3 = new Client3();
		c3.setHost("프로토 서버");
		return c3;
	}
}