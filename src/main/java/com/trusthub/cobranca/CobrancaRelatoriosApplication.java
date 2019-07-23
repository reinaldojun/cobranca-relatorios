package com.trusthub.cobranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.trusthub.cobranca.configuration.repository.GenericJdbcTemplate;

import comum.jdbc.sql.AuditSQLServerDataSource;
import io.swagger.annotations.Api;

/**
 * Classe principal Cobranca Relatorios 
 * @author jose.viana
 */
@Api(value = "Cobranca")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class, JdbcTemplateAutoConfiguration.class, MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class, MongoRepositoriesAutoConfiguration.class })
@EnableDiscoveryClient
public class CobrancaRelatoriosApplication {

	private static final String NOME_SERVICO = "trusthub-cobranca-relatorios";

	public static void main(String[] args) {
		SpringApplication.run(CobrancaRelatoriosApplication.class, args);
	}
	
	@Bean
	@Autowired
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource(@Value("${databasedwrefactory.host}") String host, @Value("${databasedwrefactory.port}") Integer port,
			@Value("${databasedwrefactory.username}") String username, @Value("${databasedwrefactory.password}") String password,
			@Value("${databasedwrefactory.name}") String database, @Value("${databasedwrefactory.name}") String instanceName,
			@Value("${databasedwrefactory.pool}") Integer pool) {
		
		AuditSQLServerDataSource ds = new AuditSQLServerDataSource();
		ds.setServerName(host);
		ds.setPort(port);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setDatabaseName(database);
		ds.setMaximumPoolSize(pool);
		ds.setInstanceName(instanceName);
		ds.setApplicationName(NOME_SERVICO);		
		return ds;
	}
	
	@Bean
	@Autowired
	public GenericJdbcTemplate genericJdbcTemplate(DataSource ds) {
		return new GenericJdbcTemplate(ds, new ClassPathResource(NOME_SERVICO + ".elsql"));
	}


}
