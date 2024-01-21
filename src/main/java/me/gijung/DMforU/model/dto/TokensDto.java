package me.gijung.DMforU.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TokensDto {

    private List<String> tokens;

    private String topic;
}
