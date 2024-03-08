package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.Mesaage.Messaging;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MessageService {

    private RedisTemplate<String, String> redisTemplate;
    private Set<String> keys;

    public void send_message(Notice notice) throws FirebaseMessagingException {
        String value = null;
        keys = redisTemplate.keys("*");

        for (String key : keys) {
            Set<String> values = redisTemplate.opsForZSet().range(key, -1, -1);
            if (values != null && !values.isEmpty()) {
                value = values.iterator().next();
            }
        }

        //학과 공지사항
        if (value.equals(notice.getType())) {
            DepartmentMessaging(keys);
        }

        //대학 공지사항
        if (notice.getType().equals("대학")) {
            UniversityMessaging(notice.getTitle());
        }
    }

    //학과 공지사항
    private void DepartmentMessaging(Set<String> keys) throws FirebaseMessagingException {
        MulticastMessage multicastMessage = Messaging.sendMessage(keys);
        FirebaseMessaging.getInstance().sendEachForMulticast(multicastMessage);
    }

    //대학 공지사항
    private void UniversityMessaging(String noticeTitle) throws FirebaseMessagingException {
        Topic[] TopicValues = Topic.values();
        for (Topic topic : TopicValues) {

            boolean contains = noticeTitle
                    .contains(String.valueOf(topic.getKoreanName()));

            if (contains) {
                MessageDto messagedto = new MessageDto(topic.getEnglishName(), String.valueOf(topic.getKoreanName()));
                Message message = Messaging.sendMessage(messagedto);
                FirebaseMessaging.getInstance().send(message);
            }
        }

    }
}
