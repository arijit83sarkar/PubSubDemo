package com.raven.components.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raven.components.model.ArticleDetails;
import com.raven.components.model.MessageBody;
import com.raven.components.model.UserDetails;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UtilityService {

    public static byte[] convertObjectToByte() {
        var messageBody = getMessage();
        var mapper = new ObjectMapper();
        byte[] _byte = new byte[0];

        try {
            String message = mapper.writeValueAsString(messageBody);
            _byte = message.getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            System.out.println("Error in processing the JSON : " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }

        return _byte;
    }

    public static byte[] convertObjectToByte(MessageBody messageBody) {
        var mapper = new ObjectMapper();
        byte[] _byte = new byte[0];

        try {
            String message = mapper.writeValueAsString(messageBody);
            _byte = message.getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            System.out.println("Error in processing the JSON : " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }

        return _byte;
    }

    public static String convertObjectToString() {
        var messageBody = getMessage();
        var mapper = new ObjectMapper();
        String message = "";

        try {
            message = mapper.writeValueAsString(messageBody);
        } catch (JsonProcessingException e) {
            System.out.println("Error in processing the JSON : " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }

        return message;
    }

    public static String convertObjectToString(MessageBody messageBody) {
        var mapper = new ObjectMapper();
        String message = "";

        try {
            message = mapper.writeValueAsString(messageBody);
        } catch (JsonProcessingException e) {
            System.out.println("Error in processing the JSON : " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }

        return message;
    }

    public static MessageBody convertStringToObject(String message) {
        var mapper = new ObjectMapper();
        MessageBody messageBody = new MessageBody();

        try {
            messageBody = mapper.readValue(message, new TypeReference<MessageBody>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("Error in processing the JSON : " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }

        return messageBody;
    }

    public static MessageBody convertStringToObject(byte[] bytes) {
        var mapper = new ObjectMapper();
        MessageBody messageBody = new MessageBody();

        try {
            String message = new String(bytes, StandardCharsets.UTF_8);
            messageBody = mapper.readValue(message, new TypeReference<MessageBody>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("Error in processing the JSON : " + e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }

        return messageBody;
    }

    private static MessageBody getMessage() {
        return new MessageBody()
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
                );
    }
}
