package br.com.paulobc.microservices.service;

public class TwitterService extends Thread {
    @Override
    public void run() {
        while(true) {
            System.out.println("segunda thread");
        }
    }
}
