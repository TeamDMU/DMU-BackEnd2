package com.dmforu.subscribe.token;

import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.concurrent.ExecutionException;

public interface Token<T> {

    void updateToken(T t) throws FirebaseMessagingException, ExecutionException, InterruptedException;

    void deleteToken(T t);

    void createToken(T t);

    default void refreshToken(T t) {}

}