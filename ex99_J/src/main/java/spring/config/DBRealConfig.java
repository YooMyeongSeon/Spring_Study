package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@Profile("real")
public class DBRealConfig {
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("oracle.jdbc.OracleDriver");
		} catch(PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		ds.setJdbcUrl("jdbc:oracle:thin:@oracle.interstander.com:41521:xe");
		ds.setUser("green03");
		ds.setPassword("1234");
		ds.setMaxPoolSize(20);
		
		return ds;
	}
}