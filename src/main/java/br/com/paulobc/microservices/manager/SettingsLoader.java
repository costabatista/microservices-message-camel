package br.com.paulobc.microservices.manager;

import java.io.InputStream;
import java.util.Properties;

import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;

public class SettingsLoader {

    public SSLContextParameters getSSLContextParameters() {
        KeyStoreParameters trust_ksp = new KeyStoreParameters();
        trust_ksp.setResource("/cacerts.jks");
        trust_ksp.setPassword("changeit");
        TrustManagersParameters trustp = new TrustManagersParameters();
        trustp.setKeyStore(trust_ksp);

        SSLContextParameters scp = new SSLContextParameters();
        scp.setTrustManagers(trustp);

        return scp;
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();

        try (InputStream resourceStream = classLoader.getResourceAsStream(fileName)) {
            properties.load(resourceStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }
}
