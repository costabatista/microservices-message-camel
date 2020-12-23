package br.com.paulobc.microservices.manager;

import java.util.Properties;

public class MailManager implements Manager {
    private String username;
    private String smtpAddress;
    private String imapAddress;
    private String port;
    private boolean isSSL;

    private Properties properties;

    public MailManager() {
        this.loadProperties("mail.properties");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setSmtpAddress(String smtpAddress) {
        this.smtpAddress = smtpAddress;
    }

    public String getSmtpAddress() {
        return this.smtpAddress;
    }

    public void setImapAddress(String imapAddress) {
        this.imapAddress = imapAddress;
    }

    public String getImapAddress() {
        return this.imapAddress;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return this.port;
    }

    public void setSSL(boolean ssl) {
        this.isSSL = ssl;
    }

    public boolean getSSL() {
        return this.isSSL;
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
    public void setFromEndpoint(String fromEndpoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setToEndPoint(String toEnpoint) {
        // TODO Auto-generated method stub

    }

}
