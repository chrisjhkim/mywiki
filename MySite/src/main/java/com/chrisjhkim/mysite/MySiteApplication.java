package com.chrisjhkim.mysite;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
@MapperScan(basePackages = "com.chrisjhkim.mysite.dao")
public class MySiteApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MySiteApplication.class);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		System.out.println("DatabaseConfig sqlSessionFactory!!!");
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
		System.out.println("res list ");
		for( int i = 0 ; i < res.length ; i ++ ) {
			System.out.println(res[i]);
		}
//		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/SQL_MySql.xml");
		sessionFactory.setMapperLocations(res);
		return sessionFactory.getObject();
	}
	
}
