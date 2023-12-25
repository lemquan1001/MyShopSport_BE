package com.example.ecommerce_be.jwt.email;

public interface EmailSender {

    void send(String to , String email);
}
