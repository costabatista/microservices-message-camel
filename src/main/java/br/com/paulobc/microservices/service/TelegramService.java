package br.com.paulobc.microservices.service;

public class TelegramService extends Thread {
    @Override
    public void run() {
        while(true) {
            System.out.println("primeira thread");
        }
    }
}
