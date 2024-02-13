package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.DepartmentNoticeDto;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.model.entity.DepartmentNotice;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class MessageServiceTest {

    @Autowired
    private FirebaseMessagingService<FirebaseMessaging> firebaseMessaging;

    @Autowired
    private RedisTokenService redisTokenService;
    @Autowired
    private GoogleTokenService googleTokenService;

    private DepartmentNoticeDto mapToDto(DepartmentNotice departmentNotice) {
        return DepartmentNoticeDto.builder()
                .date(departmentNotice.getDate())
                .title(departmentNotice.getTitle())
                .author(departmentNotice.getAuthor())
                .url(departmentNotice.getUrl())
                .build();
    }
    private DepartmentNoticeDto DepartmentNoticeDto(String title, String author, String url) {
        DepartmentNotice dto = DepartmentNotice
                .builder()
                .date(LocalDate.now())
                .title(title)
                .author(author)
                .url(url)
                .build();
        return mapToDto(dto);
    }
/*------------------------------------------------------------------------------------*/

    @Test
    public void Category_extraction_And_Message_Send_Test() throws FirebaseMessagingException {

        DepartmentNoticeDto departmentNoticeDto1 = DepartmentNoticeDto("오늘의 학식 식단표 및 일정", "영양사", "gg");
        DepartmentNoticeDto departmentNoticeDto2 = DepartmentNoticeDto("일정 공지사항", "교수", "gg");

        TokensDto dto1 = TokensDto
                .builder()
                .tokens(
                        Collections.singletonList("e03VP9nbRgmVzON3YANfe1:APA91bELebvaIhQiY51Px4H5kxABmaj0g" +
                                "DQAZhuIJA1MHi7jOs_rzE4MMkWGwCyU36XvYev6WtrgLmE3KOJuwY" +
                                "T6i588Op13a8cflKTB-Ft7T461Xe4j85G__9jfA-Q-23MoU3VKo87I")
                )
//                .topic(Collections.singletonList(Topic.valueOf("menu")))
                .topic(Arrays.asList(Topic.valueOf("menu"), Topic.valueOf("schedule")))
                .build();


        redisTokenService.updateToken(dto1);
        googleTokenService.updateToken(dto1);
        Topic[] TopicValues = Topic.values();
        try {
            for (Topic topic : TopicValues) {
                boolean contains = departmentNoticeDto1
                        .getTitle()
                        .contains(String.valueOf(topic.getKoreanName()));
                if (contains) {
                    MessageDto messagedto = new MessageDto(topic.getEnglishName(), String.valueOf(topic.getKoreanName()));
                    Message message = firebaseMessaging.sendMessage(messagedto);
                    FirebaseMessaging.getInstance().send(message);
                }
            }
        }catch (Exception e) {
            try {
                // 20초 동안 일시 중지
                Thread.sleep(20000);
            } catch (InterruptedException e1) {
                // 스레드가 중단될 경우 처리할 작업
                e1.printStackTrace();
            }

            redisTokenService.deleteToken(dto1);
            googleTokenService.deleteToken(dto1);
        }finally {
            redisTokenService.deleteToken(dto1);
            googleTokenService.deleteToken(dto1);
        }


    }


}
