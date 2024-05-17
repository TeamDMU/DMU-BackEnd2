package com.dmforu.v2_subscrible.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Keywords {

    // TODO: 리팩토링 대상
    // exam -> EXAM, signup -> SIGNUP, speciallecture -> SPECIAL_LECTURE ...
    // + 이미 상수명 자체가 영어인데 굳이 영어이름이 필요한가?

    EXAM("시험"),
    SIGN_UP("수강"),
    SPECIAL_LECTURE("특강"),
    SEASONAL_SEMESTER("계절학기"),
    LEAVE_OF_ABSENCE("휴학"),
    RETURN_TO_SCHOOL("복학"),
    GRADUATE("졸업"),
    SWITCH_MAJORS("전과"),
    GIVING_UP_THE_SEMESTER("학기포기"),
    SCHOLARSHIP("장학"),
    NATIONAL_SCHOLARSHIP("국가장학"),
    REGISTRATION("등록금"),
    EMPLOYMENT("채용"),
    CONTEST("공모전"),
    COMPETITION("대회"),
    FIELD_TRAINING("현장실습"),
    VOLUNTEER("봉사"),
    DORMITORY("기숙사"),
    GROUP("동아리"),
    STUDENT_COUNCIL("학생회"),
    OVERSEAS_TRAINING("해외연수"),
    RESERVE_FORCES("예비군"),
    WORK("근로");

    private final String koreanName;

}