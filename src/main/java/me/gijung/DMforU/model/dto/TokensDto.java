package me.gijung.DMforU.model.dto;

import lombok.Builder;
import lombok.Data;
import me.gijung.DMforU.config.Topic;

import java.util.List;

@Data
@Builder
public class TokensDto {
    private List<String> tokens;
    private List<Topic> topic;

}
