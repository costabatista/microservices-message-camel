package br.com.paulobc.microservices;

import br.com.paulobc.microservices.service.TelegramService;
import br.com.paulobc.microservices.service.TwitterService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
       TelegramService thread1 = new TelegramService();
       TwitterService thread2 = new TwitterService();
       thread1.start();
       thread2.start();
    }
}
