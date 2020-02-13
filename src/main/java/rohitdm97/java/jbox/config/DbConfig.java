package rohitdm97.java.jbox.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
	basePackageClasses = {rohitdm97.java.jbox.repository.Marker.class}
)
@EnableTransactionManagement
@Log4j2
public class DbConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.primary.datasource")
	public DataSource dataSource(JpaProperties jpaProperties) {
		DataSource result = DataSourceBuilder.create()
			.build();
		log.info("The dataSource is {}", result);
		return result;
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		DataSource dataSource,
		EntityManagerFactoryBuilder builder
	) {
		return builder
			.dataSource(dataSource)
			.packages(rohitdm97.java.jbox.model.Marker.class)
			.build();
	}

	@Primary
	@Bean
	public JpaTransactionManager transactionManager(
		EntityManagerFactory primaryEntityManager
	) {
		return new JpaTransactionManager(primaryEntityManager);
	}

}
