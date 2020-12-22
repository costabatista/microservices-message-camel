package br.com.paulobc.microservices.manager;

import java.util.Properties;

public class TelegramManager implements Manager {
    private Properties properties;

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
    }

    @Override
    public void createFromEndpoint() {
        // TODO Auto-generated method stub

    }

    @Override
    public void createToEndpoint() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setFromEndpoint(String fromEndpoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getFromEndpoint() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getToEndpoint() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setToEndPoint(String toEnpoint) {
        // TODO Auto-generated method stub

    }
    
}
