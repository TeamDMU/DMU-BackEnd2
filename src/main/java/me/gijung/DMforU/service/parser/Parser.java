package me.gijung.DMforU.service.parser;

import java.util.List;

public interface Parser<T> {

    /**
     * HTML을 파싱하여 파싱결과 목록을 반환한다.
     *
     * @return 파싱 목록
     */
    List<T> parse();
}
