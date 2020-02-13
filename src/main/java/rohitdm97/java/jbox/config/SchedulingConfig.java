package rohitdm97.java.jbox.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@Log4j2
public class SchedulingConfig {

	@Bean
	public ExecutorService defaultSchedulingExecutorService() {
		return Executors.newFixedThreadPool(10);
	}

	@Scheduled(fixedRate = 1000)
	public void schedulerStatus() {
		log.info("The scheduler is live");
	}

}
