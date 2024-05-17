package com.dmforu.v2_messaging;

import com.dmforu.notice.Notice;
import com.dmforu.v2_messaging.dto.MessageDtoV2;
import com.dmforu.v2_messaging.dto.NoticeDtoV2;
import com.dmforu.v2_messaging.util.KeywordFiltering;
import com.dmforu.v2_messaging.util.MessagingV2;
import com.dmforu.v2_messaging.util.mapToDTO;
import com.dmforu.v2_subscrible.config.Keywords;
import com.dmforu.v2_subscrible.model.entity.Token;
import com.dmforu.v2_subscrible.repository.TokenRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceV2 {

    private final TokenRepository tokenRepository;

    public void sendMessage(Notice notice){
        noticeType(notice);
    }

    // notice 타입 구분
    private void noticeType(Notice notice){
        NoticeDtoV2 noticeDtoV2 = mapToDTO.noticeToNoticeDTO(notice);
        if (noticeDtoV2.getType().equals("대학")) {
            getUnibersityTokenList(noticeDtoV2);
        }else {
            getDepartmentTokenList(noticeDtoV2);
        }
    }

    //특정 학과가 있는 TokenList 조회 [ 학과 ]
    private void getDepartmentTokenList(NoticeDtoV2 noticeDtoV2) {
        List<Token> byDepartment = tokenRepository.getDepartment(noticeDtoV2.getType());
        List<String> tokenList = new ArrayList<>();
        for (Token token : byDepartment) {
            tokenList.add(token.getToken());
        }
        departmentBuildMessaging(noticeDtoV2,tokenList);
    }

    //특정 키워드가 속해있는 TokenList 조회 [ 대학 ]
    private void getUnibersityTokenList(NoticeDtoV2 noticeDtoV2) {
        List<String> tokenList = new ArrayList<>();
        if (!KeywordFiltering.keywordFilter(noticeDtoV2.getTitle()).equals("none")){
            String s = KeywordFiltering.keywordFilter(noticeDtoV2.getTitle());
            List<Token> allByKeywordsListIn = tokenRepository.findByTokenInKeyword(s);
            for (Token token : allByKeywordsListIn) {
                tokenList.add(token.getToken());
            }
            unibersityBuildMessaging(noticeDtoV2, tokenList, s);
        }
    }

    //학과 메시지 작성
    private void departmentBuildMessaging(NoticeDtoV2 noticeDtoV2, List<String> tokenList) {
        MessageDtoV2 messagedto = new MessageDtoV2(noticeDtoV2);
        MulticastMessage message = MessagingV2.buildMessage(messagedto, tokenList, noticeDtoV2.getType());
//        FirebaseMessaging.getInstance().send(message);
    }

    //대학 메시지 작성
    private void unibersityBuildMessaging(NoticeDtoV2 noticeDtoV2, List<String> tokenList, String keyWord) {
        MessageDtoV2 messagedto = new MessageDtoV2(noticeDtoV2, keyWord);
        MulticastMessage message = MessagingV2.buildMessage(messagedto, tokenList, noticeDtoV2.getType(),keyWord);
//        FirebaseMessaging.getInstance().send(message);
    }

}
