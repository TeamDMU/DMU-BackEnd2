package com.dmforu.subscribe.dto;

import com.dmforu.subscribe.config.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequestDto {
    private String token;
    private List<Topic> topics;
}
