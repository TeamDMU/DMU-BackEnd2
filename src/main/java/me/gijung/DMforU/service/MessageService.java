package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import me.gijung.DMforU.utils.NoticeMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private final FirebaseMessagingService<FirebaseMessaging> firebaseMessaging;

    /**
     * 알림 메세지 메서드
     * 정형화 필요!! [ 알림 메세지 틀 구조 ]
     */
    public void send_message(Notice notice) throws FirebaseMessagingException {

        NoticeDto noticeDto = NoticeMapper.mapToDto(notice);

        Topic[] TopicValues = Topic.values();
        for (Topic topic : TopicValues) {
            boolean contains = noticeDto
                    .getTitle()
                    .contains(String.valueOf(topic.getKoreanName()));
            if (contains) {
                MessageDto messagedto = new MessageDto(topic.getEnglishName(), String.valueOf(topic.getKoreanName()));
                Message message = firebaseMessaging.sendMessage(messagedto);
                FirebaseMessaging.getInstance().send(message);
            }
        }
    }

}
