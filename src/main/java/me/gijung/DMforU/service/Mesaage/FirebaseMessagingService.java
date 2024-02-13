package me.gijung.DMforU.service.Mesaage;

import com.google.firebase.messaging.Message;
import me.gijung.DMforU.model.dto.MessageDto;

import java.util.List;

public interface FirebaseMessagingService<T>{

    T getInstance();

    Message sendMessage(MessageDto messageDto);

}
