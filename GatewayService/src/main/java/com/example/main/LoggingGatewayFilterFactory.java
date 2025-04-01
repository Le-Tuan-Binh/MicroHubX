package com.example.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("Logging")
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    final Logger logger = LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            logger.info("Pre GatewayFilter logging");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Post GatewayFilter logging");
            }));
        }, -2);
    }

    public static class Config {
        private String baseMessage;

        public void setBaseMessage(String baseMessage) {
            this.baseMessage = baseMessage;
        }

        public String getBaseMsg() {
            return baseMessage;
        }
    }
}
