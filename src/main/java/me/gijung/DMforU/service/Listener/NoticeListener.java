package me.gijung.DMforU.service.Listener;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.MessageService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class NoticeListener {

    private final MessageService messageService;

    // DB에 새로운 데이터가 추가되는 순간, 아래의 메서드가 호출
    @TransactionalEventListener
    public void messageListener(Notice notice) throws FirebaseMessagingException {
        messageService.sendMessage(notice);
    }
}
