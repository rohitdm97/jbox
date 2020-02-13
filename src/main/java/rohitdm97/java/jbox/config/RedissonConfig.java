package rohitdm97.java.jbox.config;

import lombok.extern.log4j.Log4j2;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@Log4j2
public class RedissonConfig {

	@Bean
	public Config redissonConfigYaml() {
		try {
			return Config.fromYAML("redisson.yaml");
		} catch (IOException e) {
			log.error("Failed to load redisson configuration");
			return null;
		}
	}

	@Bean
	public RedissonClient redissonClient(Config redissonConfigYaml) {
		return redissonConfigYaml != null ? Redisson.create(redissonConfigYaml) : null;
	}

}
