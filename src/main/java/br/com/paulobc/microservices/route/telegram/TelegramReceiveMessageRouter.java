package br.com.paulobc.microservices.route.telegram;

import org.apache.camel.builder.RouteBuilder;

import br.com.paulobc.microservices.manager.TelegramManager;
import br.com.paulobc.microservices.manager.processor.TelegramOptionProcessor;

public class TelegramReceiveMessageRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        TelegramManager telegramManager = new TelegramManager();
        String fromEndpoint = telegramManager.getFromEndpoint();
        String toEndpoint = telegramManager.getToEndpoint();
        TelegramOptionProcessor telegramOptionProcessor = new TelegramOptionProcessor();
        System.out.println(telegramOptionProcessor.getInstallSoftwareOptionMessage());
    
        from(fromEndpoint)
        .process(telegramOptionProcessor)
        .to(toEndpoint);

    }
    
}
