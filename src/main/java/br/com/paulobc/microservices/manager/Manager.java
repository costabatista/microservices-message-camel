package br.com.paulobc.microservices.manager;

import java.util.Properties;

public interface Manager {

    public Properties getProperties();

    public void setProperties(Properties properties);

    public void loadProperties(String filePropertiesName);

    public void createFromEndpoint();

    public void createToEndpoint();

    public void setFromEndpoint(String fromEndpoint);

    public void setToEndPoint(String toEnpoint);

    public String getFromEndpoint();

    public String getToEndpoint();

}
