package com.dmforu.messaging;

import com.dmforu.notice.Notice;
import com.dmforu.messaging.dto.MessageDtoV2;
import com.dmforu.messaging.dto.NoticeDtoV2;
import com.dmforu.messaging.util.KeywordFiltering;
import com.dmforu.messaging.util.MessagingV2;
import com.dmforu.messaging.util.mapToDTO;
import com.dmforu.subscribe.repository.TokenRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageServiceV2 {

    private final TokenRepository tokenRepository;

    public void sendMessage(Notice notice) throws FirebaseMessagingException {
        noticeType(notice);
    }

    // notice 타입 구분
    private void noticeType(Notice notice) throws FirebaseMessagingException {
        NoticeDtoV2 noticeDtoV2 = mapToDTO.noticeToNoticeDTO(notice);
        if (noticeDtoV2.getType().equals("대학")) {
            universityMessage(noticeDtoV2);
        }else {
            departmentMessage(noticeDtoV2);
        }
    }

    //특정 학과가 있는 TokenList 조회 [ 학과 ]
    private void departmentMessage(NoticeDtoV2 noticeDtoV2) throws FirebaseMessagingException {
        List<String> tokenList = tokenRepository.getDepartment(noticeDtoV2.getType());
        buildMessaging(noticeDtoV2,tokenList);
    }

    //특정 키워드가 속해있는 TokenList 조회 [ 대학 ]
    private void universityMessage(NoticeDtoV2 noticeDtoV2) throws FirebaseMessagingException {
        Set<String> list = KeywordFiltering.keywordFilter(noticeDtoV2.getTitle());
        for (String s : list) {
            List<String> byTokenInKeyword = tokenRepository.findByTokenInKeyword(s);
            buildMessaging(noticeDtoV2, byTokenInKeyword, s);
        }
    }

    //학과 메시지 작성
    private void buildMessaging(NoticeDtoV2 noticeDtoV2, List<String> tokenList) throws FirebaseMessagingException {
        MessageDtoV2 messageDto = new MessageDtoV2(noticeDtoV2);
        MulticastMessage message = MessagingV2.buildMessage(messageDto, tokenList, noticeDtoV2.getType());
        FirebaseMessaging.getInstance().sendEachForMulticast(message);
    }

    //대학 메시지 작성
    private void buildMessaging(NoticeDtoV2 noticeDtoV2, List<String> tokenList, String keyWord) throws FirebaseMessagingException {
        MessageDtoV2 messageDto = new MessageDtoV2(noticeDtoV2, keyWord);
        MulticastMessage message = MessagingV2.buildMessage(messageDto, tokenList, noticeDtoV2.getType());
        FirebaseMessaging.getInstance().sendEachForMulticast(message);
    }

}
