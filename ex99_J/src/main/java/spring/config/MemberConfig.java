package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import spring.dao.MemberDao;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
		
		txMgr.setDataSource(dataSource());
		
		return txMgr;
	}
	
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
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public MemberDao dao() {
		return new MemberDao(jdbcTemplate());
	}
	
	@Bean
	public MemberRegisterService regSvc() {
		return new MemberRegisterService(dao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService(dao());
	}
	
	@Bean
	public AuthService authService() {
		AuthService as = new AuthService();
		as.setMemberDao(dao());
		return as;
	}
}