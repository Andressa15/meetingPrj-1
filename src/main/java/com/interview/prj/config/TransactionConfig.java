package com.interview.prj.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = { "com.interview.prj.meeting.repository", "com.interview.prj.person.repository",
	//	"com.interview.prj.room.repository", "com.interview.prj.login.repository" })
public class TransactionConfig {

	//@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:sqlserver://1433;DatabaseName=agenda");
		dataSourceBuilder.username("sa");
		dataSourceBuilder.password("andressa");
		return dataSourceBuilder.build();
	}
}