package rohitdm97.java.jbox;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Log4j2
public class JboxApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JboxApplication.class, args);
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		log.info("The system is up");
	}

}
