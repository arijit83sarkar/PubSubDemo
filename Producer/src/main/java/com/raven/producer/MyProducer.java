package com.raven.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.raven.components.model.ArticleDetails;
import com.raven.components.model.MessageBody;
import com.raven.components.model.UserDetails;
import com.raven.components.utility.UtilityService;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MyProducer {
    private static final String HOST = "localhost";
    private static final String EXCHANGE = "pub_sub";

    public static void main(String[] args) {
        // CREATE A CONNECTION FACTORY
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        // CREATE A CONNECTION FROM FACTORY
        try (Connection connection = connectionFactory.newConnection()) {
            // GET CHANNEL FROM CONNECTION
            Channel channel = connection.createChannel();

            // DECLARE A EXCHANGE
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT);

            // MESSAGE DETAILS
            var message = UtilityService.convertObjectToString(
                    new MessageBody()
                            .setUserDetails(new UserDetails(
                                    "Paula Small",
                                    "paula.small@bilearner.com")
                            )
                            .setArticleDetails(new ArticleDetails(
                                    "1289 words",
                                    "03/12/2024",
                                    true,
                                    "john.dow@yahoomail.com",
                                    80,
                                    "second")
                            )
            );

            System.out.println("\nProducer is running ...");

            // PUBLISH A MESSAGE TO CHANNEL
            channel.basicPublish(EXCHANGE, "", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("\n Message sent by publisher : '" + message + "'");
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Error in sending message: " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }
    }
}
