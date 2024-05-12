package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gijung.DMforU.config.Topic;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InitRequestDto {
    private String token;
    private String department;
    private List<Topic> topics;
}
