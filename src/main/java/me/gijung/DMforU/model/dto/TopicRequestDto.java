package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gijung.DMforU.config.Topic;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequestDto {
    private String token;
    private List<Topic> topics;
}
