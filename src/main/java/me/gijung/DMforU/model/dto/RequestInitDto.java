package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.gijung.DMforU.config.Topic;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RequestInitDto {
    private String tokens;
    private String department;
    private List<Topic> topic;
}
