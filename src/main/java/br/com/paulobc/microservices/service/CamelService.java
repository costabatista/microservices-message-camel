package br.com.paulobc.microservices.service;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public interface CamelService {
    public List<RouteBuilder> getRoutes();
    public void addRoutesToContext(CamelContext context);
    public void executeContext(CamelContext context);
}
