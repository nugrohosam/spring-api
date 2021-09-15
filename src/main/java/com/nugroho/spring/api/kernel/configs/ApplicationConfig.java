package com.nugroho.spring.api.kernel.configs;

import java.util.concurrent.Executor;

import com.nugroho.spring.api.ApiApplication;
import com.nugroho.spring.api.kernel.runners.ContextAwarePoolExecutor;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ApplicationConfig {

	@Value("${http.timeout.connection}")
	private int connectionTimeout;

	@Value("${http.timeout.read}")
	private int readTimeout;

	@Value("${thread.corepoolsize}")
	private int corePoolSize;

	@Value("${thread.maxpoolsize}")
	private int maxPoolSize;

	@Value("${thread.queue}")
	private int queue;
	
	@Value("${jasypt.encryptor.password}")
	private String passEncryptor;
	
	@Bean
	public Executor taskExecutor() {
		ContextAwarePoolExecutor executor = new ContextAwarePoolExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queue);
		executor.setThreadNamePrefix(ApiApplication.class.getSimpleName() + "-");
		executor.initialize();
		return executor;
	}
	
	@Bean(name = "encryptorBean")
	public StringEncryptor stringEncryptor() {
	    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	    config.setPassword(passEncryptor);
	    config.setAlgorithm("PBEWithMD5AndDES");
	    config.setKeyObtentionIterations("1000");
	    config.setPoolSize("1");
	    config.setProviderName("SunJCE");
	    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	    config.setStringOutputType("base64");
	    encryptor.setConfig(config);
	    return encryptor;
	}
}