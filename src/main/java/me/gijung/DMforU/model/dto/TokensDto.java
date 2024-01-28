package me.gijung.DMforU.model.dto;

import lombok.Data;
import me.gijung.DMforU.config.Topic;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TokensDto {

    private List<String> tokens;

    private Topic topic;
}
