package de.otto.platform.gitactionboard.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import de.otto.platform.gitactionboard.adapters.service.job.WorkflowsJobDetailsResponse.WorkflowsJobDetails;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
public class CacheConfig {
  @Bean
  @Primary
  public Caffeine caffeineConfig(@Value("${CACHE_EXPIRES_AFTER:60}") int expiresAfter) {
    return Caffeine.newBuilder().expireAfterWrite(expiresAfter, TimeUnit.SECONDS);
  }

  @Bean(name = "workflowJobDetailsCache")
  public Cache<String, List<WorkflowsJobDetails>> workflowJobDetailsCache() {
    return Caffeine.newBuilder().expireAfterAccess(12, TimeUnit.HOURS).build();
  }
}
