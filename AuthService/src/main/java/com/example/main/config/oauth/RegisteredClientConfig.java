package com.example.main.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import java.time.Duration;

@Configuration
public class RegisteredClientConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        RegisteredClient tbin = RegisteredClient.withId("tbin")
                .clientId("tbin")
                .clientSecret(passwordEncoder.encode("tbin"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauthdebugger.com/debug")
                .redirectUri("http://127.0.0.1:5500")
                .redirectUri("http://127.0.0.1:3000/login")
                .scope("read")
                .scope("write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder()
                                       .accessTokenTimeToLive(Duration.ofMinutes(10))
                                       .refreshTokenTimeToLive(Duration.ofMinutes(3600))
                                       .build())
                .build();

        return new InMemoryRegisteredClientRepository(tbin);
    }
}
