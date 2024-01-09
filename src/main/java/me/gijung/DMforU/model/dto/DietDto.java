package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gijung.DMforU.model.domain.Diet;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DietDto {

    private List<Diet> diteList;
}
