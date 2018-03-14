package br.com.marketpay.validaapp;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ocpsoft.pretty.PrettyFilter;

import br.com.marketpay.validaapp.conf.ValidaappProperties;
import lombok.extern.slf4j.Slf4j;

@EnableConfigurationProperties({ ValidaappProperties.class })
@Slf4j
@SpringBootApplication
@Configuration
public class ValidaApp extends SpringBootServletInitializer {
	
	public static void main(final String[] args) throws JsonParseException, JsonMappingException, IOException {
		SpringApplication app = new SpringApplication(ValidaApp.class);
		
		Environment env = app.run(args).getEnvironment();
		
		log.info("\n----------------------------------------------------------\n\t" +
                "Valida Processamento está rodando! Acesse uma das URLs:\n\t" +
                "Local: \t\thttp://localhost:{}{}\n\t" +
                "Externa: \thttp://{}:{}{}\n----------------------------------------------------------",
            env.getProperty("server.port"),
            env.getProperty("server.context-path"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getProperty("server.context-path"));
	}

	@Bean
	public FilterRegistrationBean prettyFilter() {
		FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new PrettyFilter());
		prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC,
				DispatcherType.ERROR);
		prettyFilter.addUrlPatterns("/*");
		return prettyFilter;
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
		t.setCorePoolSize(10);
		t.setMaxPoolSize(100);
		t.setQueueCapacity(50);
		t.setAllowCoreThreadTimeOut(true);
		t.setKeepAliveSeconds(120);
		t.setWaitForTasksToCompleteOnShutdown(true);
		return t;
	}
}
