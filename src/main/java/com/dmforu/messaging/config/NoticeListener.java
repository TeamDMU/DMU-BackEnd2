package com.dmforu.messaging.config;

import com.dmforu.messaging.MessageService;
import com.dmforu.notice.Notice;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
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
