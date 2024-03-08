package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import me.gijung.DMforU.service.Mesaage.Messaging;
import me.gijung.DMforU.utils.NoticeMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MessageService {

    private RedisTemplate<String, String> redisTemplate;
    private Set<String> keys;
    public void send_message(Notice notice) throws FirebaseMessagingException {
        NoticeDto noticeDto = new NoticeDto(notice);
        String value = null;
        keys = redisTemplate.keys("*");
            for (String key : keys) {
                Set<String> values = redisTemplate.opsForZSet().range(key, -1, -1);
                noticeDto = NoticeMapper.mapToDto(notice);
                if (values != null && !values.isEmpty()) {
                    value = values.iterator().next();
                }
            }
        //학과 공지사항
        if (value.equals(noticeDto.getType())) {
            DepartmentMessaging(keys);
        }
        //대학 공지사항
        if(noticeDto.getType().equals("대학")){
            UniversityMessaging(noticeDto);
        }

    }
    
    //학과 공지사항
    private void DepartmentMessaging(Set<String> keys) throws FirebaseMessagingException {
        MulticastMessage multicastMessage = Messaging.sendMessage(keys);
        FirebaseMessaging.getInstance().sendEachForMulticast(multicastMessage);
    }

    //대학 공지사항
    private void UniversityMessaging(NoticeDto noticeDto) throws FirebaseMessagingException {
        Topic[] TopicValues = Topic.values();
        for (Topic topic : TopicValues) {

            boolean contains = noticeDto
                    .getTitle()
                    .contains(String.valueOf(topic.getKoreanName()));
            if (contains) {
                MessageDto messagedto = new MessageDto(topic.getEnglishName(), String.valueOf(topic.getKoreanName()));
                Message message = Messaging.sendMessage(messagedto);
                FirebaseMessaging.getInstance().send(message);
            }
        }

    }
}
