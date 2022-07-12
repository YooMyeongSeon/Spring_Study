package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.config.DBDevConfig;
import spring.config.DBRealConfig;
import spring.config.MemberConfig;
import spring.dao.MemberDao;
import spring.vo.Member;

public class Main01 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		ctx.getEnvironment().setActiveProfiles("dev");
//		ctx.getEnvironment().setActiveProfiles("real");
		
		ctx.register(MemberConfig.class);
//		ctx.register(MemberConfig.class, DBDevConfig.class, DBRealConfig.class);
		
		ctx.refresh();
		
		MemberDao dao = ctx.getBean("dao", MemberDao.class);
		
		for (Member m : dao.selectAll()) {
			System.out.println(m.getName());
		}
		
		ctx.close();
	}
}