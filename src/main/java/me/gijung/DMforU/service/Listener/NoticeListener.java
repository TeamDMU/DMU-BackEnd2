package me.gijung.DMforU.service.Listener;

import com.google.firebase.messaging.FirebaseMessagingException;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class NoticeListener {

    private final MessageService messageService;

    // DB에 새로운 데이터가 추가되는 순간, 아래의 메서드가 호출
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void postPersist(Notice notice) throws FirebaseMessagingException {
            messageService.send_message(notice);
    }
}
