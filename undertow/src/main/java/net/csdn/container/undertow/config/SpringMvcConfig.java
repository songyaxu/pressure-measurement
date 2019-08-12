package net.csdn.container.undertow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 19-8-8 上午11:41
 * @MOdified by:
 **/
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	@Value("${springmvc.thread.core}")
	private Integer core;
	@Value("${springmvc.thread.max}")
	private Integer max;
	@Value("${springmvc.thread.queue}")
	private Integer queue;

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		threadPool.setCorePoolSize(this.core);
		threadPool.setMaxPoolSize(this.max);
		threadPool.setQueueCapacity(this.queue);
		threadPool.initialize();
		asyncSupportConfigurer.setTaskExecutor(threadPool);
	}
}
