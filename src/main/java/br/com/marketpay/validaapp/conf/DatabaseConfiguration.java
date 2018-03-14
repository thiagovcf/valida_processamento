package br.com.marketpay.validaapp.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "br.com.marketpay.validaapp.repository"})
public class DatabaseConfiguration {

    @Autowired
    ValidaappProperties validaappProperties;
	
	@Bean
	public HikariDataSource dataSource() {
		final HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(10);
		ds.setConnectionTestQuery(validaappProperties.getDatasource().getConnectionTestQuery());
		ds.setDriverClassName(validaappProperties.getDatasource().getDriverClassName()); 
		ds.setJdbcUrl(validaappProperties.getDatasource().getUrl());
		ds.setUsername(validaappProperties.getDatasource().getUsername());
		ds.setPassword(validaappProperties.getDatasource().getPassword());

		return ds;
	}
	
	
}
