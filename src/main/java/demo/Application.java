package demo;



import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
@ComponentScan
@EnableCaching
@EnableAutoConfiguration
public class Application {
	
	@Bean
    public CacheManager tweetSearchCacheManager() {
        return new ConcurrentMapCacheManager("userinfo");
    }
	
	 @Bean
	    public ShallowEtagHeaderFilter etagFilter() {
		 ShallowEtagHeaderFilter shallowEtagHeaderFilter = new ShallowEtagHeaderFilter();
	    return shallowEtagHeaderFilter;
		}


    public static void main(String[] args) throws IOException {    	
        SpringApplication.run(Application.class, args);
              
        
    }
}

