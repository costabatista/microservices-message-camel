package br.com.paulobc.microservices.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.paulobc.microservices.manager.SettingsLoader;
import br.com.paulobc.microservices.route.telegram.TelegramReceiveMessageRouter;

public class TelegramService extends Thread implements CamelService {

    @Override
    public void run() {
        CamelContext context = new DefaultCamelContext();
        this.addRoutesToContext(context);
        this.executeContext(context);
        SettingsLoader settingsLoader = new SettingsLoader();
        context.setSSLContextParameters(settingsLoader.getSSLContextParameters());

    }

    @Override
    public List<RouteBuilder> getRoutes() {
        List<RouteBuilder> routes = new ArrayList<>();
        routes.add(new TelegramReceiveMessageRouter());
        return routes;
    }

    @Override
    public void addRoutesToContext(CamelContext context) {
        List<RouteBuilder> routes = this.getRoutes();

        for (RouteBuilder r : routes) {
            try {
                context.addRoutes(r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        

    }

    @Override
    public void executeContext(CamelContext context) {
        try {
            context.start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
