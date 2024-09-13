package com.dmforu.subscribe.dto;

import com.dmforu.subscribe.config.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RequestTokenDto {
    private String token;
    private List<Topic> topic;
}
