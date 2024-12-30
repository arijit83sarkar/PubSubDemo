package com.raven.payment_cosumer;

import com.rabbitmq.client.*;
import com.raven.components.model.ArticleDetails;
import com.raven.components.utility.UtilityService;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PaymentConsumer {
    private static final String HOST = "localhost";
    private static final String EXCHANGE = "pub_sub";

    public static void main(String[] args) {
        // CREATE A CONNECTION FACTORY
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        try {
            // CREATE A CONNECTION
            Connection connection = connectionFactory.newConnection();

            // CREATE A CHANNEL FROM CONNECTION
            Channel channel = connection.createChannel();

            // DECLARE A EXCHANGE
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT);

            // DECLARE A QUEUE IN THE CHANNEL AND GET ITS NAME
            String queueName = channel.queueDeclare().getQueue();
            System.out.println("Consumer: Payment Service: Queue name : " + queueName);

            // BIND THE QUEUE WITH THE EXCHANGE
            channel.queueBind(queueName, EXCHANGE, "");

            System.out.println("Waiting for message...");

            DeliverCallback deliverCallback = (consumeMsg, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Consumer: Article Service :: Message received : '" + message + "'");

                System.out.println();
                var messageBody = UtilityService.convertStringToObject(message);

                ArticleDetails articleDetails = messageBody.getArticleDetails();
                System.out.println("-> Article details: " + articleDetails);

                if (articleDetails.isClapped() && articleDetails.readTime() > 50) {
                    System.out.println("-> Article is read time is " + articleDetails.readTime() + articleDetails.timeUnit());
                    System.out.println("-> You have received 30 cents.");
                    System.out.println("-> Your total earning is $10.45.");
                }
            };

            // GET MESSAGE FROM EXCHANGE
            channel.basicConsume(queueName, true, deliverCallback, consumeMsg -> {
            });
        } catch (Exception e) {
            System.out.println("Payment Consumer: Error in consuming message: " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }
    }
}
