package com.backendapi.appconfig;

import com.backendapi.json.ServerConfigJson;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerPortCustomizer
        implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ServerConfigJson config = ConfigService.getServerConfig();
        int port = config.getPort();
        factory.setPort(port);
    }
}