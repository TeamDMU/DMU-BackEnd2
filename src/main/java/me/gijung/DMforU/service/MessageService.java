package me.gijung.DMforU.service;

import com.google.firebase.messaging.*;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TypeNoticeDto;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.Mesaage.Messaging;
import me.gijung.DMforU.utils.NoticeMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MessageService {

    private RedisTemplate<String, String> redisTemplate;
    public void sendMessage(Notice notice) throws FirebaseMessagingException {
        TypeNoticeDto typeNoticeDto = NoticeMapper.maoToDepartmentNotice(notice);
        List<String> tokenList = findDepartmentValue(typeNoticeDto.getType());

        if(typeNoticeDto.getType().equals("대학")){
            //대학 공지사항
            universityMessaging(typeNoticeDto);
        }else{
            //학과 공지사항
            departmentMessaging(tokenList, typeNoticeDto);
        }
    }

    //학과 Type 검색 [ In Redis ]
    private List<String> findDepartmentValue(String departmentType) {
        List<String> tokenList = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            Set<String> values = redisTemplate.opsForZSet().range(key, 0, 0);
            if (values.iterator().next().equals(departmentType)) {
                tokenList.add(key);
            }
        }
        return tokenList;
    }

    //학과 공지사항
    private void departmentMessaging(List<String> keys, TypeNoticeDto typeNoticeDto) throws FirebaseMessagingException {
        MessageDto messageDto = new MessageDto(typeNoticeDto);
        MulticastMessage multicastMessage = Messaging.buildMessage(messageDto,keys);
        FirebaseMessaging.getInstance().sendEachForMulticast(multicastMessage);
    }

    //대학 공지사항
    private void universityMessaging(TypeNoticeDto typeNoticeDto) throws FirebaseMessagingException {
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
