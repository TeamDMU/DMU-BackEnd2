package com.dmforu.subscribe.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum Topic {

    // TODO: 리팩토링 대상
    // exam -> EXAM, signup -> SIGNUP, speciallecture -> SPECIAL_LECTURE ...
    // + 이미 상수명 자체가 영어인데 굳이 영어이름이 필요한가?

    exam("시험", "exam"),
    signup("수강", "signup"),
    speciallecture("특강", "speciallecture"),
    seasonalsemester("계절학기", "seasonalsemester"),
    leaveofabsence("휴학", "leaveofabsence"),
    returntoschool("복학", "returntoschool"),
    graduate("졸업", "graduate"),
    switchmajors("전과", "switchmajors"),
    givingupthesemester("학기포기", "givingupthesemester"),
    scholarship("장학", "scholarship"),
    nationalscholarship("국가장학", "nationalscholarship"),
    registration("등록", "registration"),
    employment("채용", "employment"),
    contest("공모전", "contest"),
    competition("대회", "competition"),
    fieldtraining("현장실습", "fieldtraining"),
    volunteer("봉사", "volunteer"),
    dormitory("기숙사", "dormitory"),
    group("동아리", "group"),
    studentcouncil("학생회", "studentcouncil");


    private final String koreanName;
    private final String englishName;
}