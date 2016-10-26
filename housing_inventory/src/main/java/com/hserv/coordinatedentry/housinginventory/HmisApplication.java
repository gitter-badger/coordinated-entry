package com.hserv.coordinatedentry.housinginventory;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.servinglynk.hmis.warehouse.client.config.SpringConfig;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableEntityLinks
@EnableTransactionManagement
@EnableSpringDataWebSupport
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class HmisApplication extends SpringBootServletInitializer {

	@Inject
    private Environment env;
	
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HmisApplication.class,SpringConfig.class);
    }
  	
	public static void main(String[] args) {
		SpringApplication.run(HmisApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
		
		return dataSource;
	}

}
