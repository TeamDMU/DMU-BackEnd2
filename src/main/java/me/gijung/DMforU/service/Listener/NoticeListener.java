package me.gijung.DMforU.service.Listener;

import com.google.firebase.messaging.FirebaseMessagingException;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NoticeListener {

    private final MessageService messageService;

    // 기본 생성자
    public NoticeListener() {
        this.messageService = null;
    }

    // 자동으로 주입
    @Autowired
    public NoticeListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @PrePersist
    public void prePersist(Notice notice) {
        System.out.println(">>> prePersist" + notice.getId());
    }

    //DB에 새로운 데이터가 추가되는 순간, 아래의 메서드가 호출
    //약 2만건 데이터, 그리고 하루에 10건의 데이터가 추가되는 상황이라면 성능이 나쁘지 않게
    //나올 수 있음.
    @PostPersist
    public void postPersist(Notice notice) throws FirebaseMessagingException {
        messageService.send_message(notice);
        System.out.println(">>> 메세지 전송 :: ");
    }
}
