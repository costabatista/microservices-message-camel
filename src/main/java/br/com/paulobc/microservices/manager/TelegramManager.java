package br.com.paulobc.microservices.manager;

import java.util.Properties;

public class TelegramManager implements Manager {
    private Properties properties;
    private String token;
    private String fromEndpoint;
    private String toEndpoint;

    public TelegramManager() {
        this.loadProperties("telegram.properties");

    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void loadProperties(String filePropertiesName) {
        SettingsLoader settingsLoader = new SettingsLoader();
        Properties properties = settingsLoader.getProperties(filePropertiesName);

        this.setProperties(properties);

        String tokenProperty = properties.getProperty("token");
        this.setToken(tokenProperty);
        this.createFromEndpoint();
        this.createToEndpoint();
    }

    private String getBaseEndpoint() {
        StringBuilder builder = new StringBuilder();

        builder.append("telegram:bots?authorizationToken=").append(this.getToken());
        String base = builder.toString();

        return base;
    }

    @Override
    public void createFromEndpoint() {
        String base = this.getBaseEndpoint();
        this.setFromEndpoint(base);
    }

    @Override
    public void createToEndpoint() {
        String base = this.getBaseEndpoint();
        StringBuilder builder = new StringBuilder();
        builder.append(base).append("&chatId=${header.CamelTelegramId}");
        String endpoint = builder.toString();
        this.setToEndPoint(endpoint);

    }

    @Override
    public void setFromEndpoint(String fromEndpoint) {
        this.fromEndpoint = fromEndpoint;

    }

    @Override
    public String getFromEndpoint() {
        return this.fromEndpoint;
    }

    @Override
    public String getToEndpoint() {
        return this.toEndpoint;
    }

    @Override
    public void setToEndPoint(String toEnpoint) {
        this.toEndpoint = toEnpoint;

    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
