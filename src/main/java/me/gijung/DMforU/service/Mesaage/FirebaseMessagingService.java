package me.gijung.DMforU.service.Mesaage;

import com.google.firebase.messaging.Message;

import java.util.List;

public interface FirebaseMessagingService<T>{

    T getInstance();

    Message sendMessage(String topic, String title, String body);

}
