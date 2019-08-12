package net.csdn.container.jetty.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 19-8-9 上午10:51
 * @MOdified by:
 **/
@Configuration
public class JettyConfig {

    @Bean
    public JettyServletWebServerFactory jettyServletWebServerFactory(
            JettyServerCustomizer jettyServerCustomizer) {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.addServerCustomizers(jettyServerCustomizer);
        return factory;
    }

    @Bean
    public JettyServerCustomizer jettyServerCustomizer() {
        return this::threadPool;
    }

    private void threadPool(Server server) {
        // Tweak the connection config used by Jetty to handle incoming HTTP
        // connections
        final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
        // 默认最大线程连接数200
        threadPool.setMaxThreads(100);
        // 默认最小线程连接数8
        threadPool.setMinThreads(20);
        // 默认线程最大空闲时间60000ms
        threadPool.setIdleTimeout(60000);
    }
}
