package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TypeNoticeDto;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.entity.Notice;
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

    public void sendMessage(Notice notice) throws FirebaseMessagingException {
        TypeNoticeDto typeNoticeDto = NoticeMapper.maoToDepartmentNotice(notice);
        String departmentValue = findDepartmentValue();
        //학과 공지사항
        if (departmentValue.equals(typeNoticeDto.getType())) {
            DepartmentMessaging(keys, typeNoticeDto);
        }
        //대학 공지사항
        if(typeNoticeDto.getType().equals("대학")){
            UniversityMessaging(typeNoticeDto);
        }
    }

    //학과 Type 검색 [ In Redis ]
    private String findDepartmentValue() {
        String value = null;
        keys = redisTemplate.keys("*");
        for (String key : keys) {
            Set<String> values = redisTemplate.opsForZSet().range(key, 0, 0);
            System.out.println(values);
            if (values != null && !values.isEmpty()) {
                value = values.iterator().next();
            }
        }
        return value;
    }

    //학과 공지사항
    private void DepartmentMessaging(Set<String> keys, TypeNoticeDto typeNoticeDto) throws FirebaseMessagingException {
        MessageDto messageDto = new MessageDto(typeNoticeDto);
        MulticastMessage multicastMessage = Messaging.buildMessage(messageDto,keys);
        FirebaseMessaging.getInstance().sendEachForMulticast(multicastMessage);
    }

    //대학 공지사항
    private void UniversityMessaging(TypeNoticeDto typeNoticeDto) throws FirebaseMessagingException {
        Topic[] TopicValues = Topic.values();
        for (Topic topic : TopicValues) {

            boolean contains = typeNoticeDto
                    .getTitle()
                    .contains(String.valueOf(topic.getKoreanName()));

            if (contains) {
                MessageDto messagedto = new MessageDto(typeNoticeDto,topic.getEnglishName(), topic.getKoreanName());
                Message message = Messaging.buildMessage(messagedto);
                FirebaseMessaging.getInstance().send(message);
            }
        }
    }
}
