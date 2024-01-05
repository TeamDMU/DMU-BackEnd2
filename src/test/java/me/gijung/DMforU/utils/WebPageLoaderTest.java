package me.gijung.DMforU.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WebPageLoaderTest {

    WebPageLoader webPageLoader = new WebPageLoader();

    @DisplayName("HTML 정보를 가져오는지 테스트")
    @Test
    void getHTML() {
        assertThat(webPageLoader.getHTML("https://www.google.com/")).isNotNull();
    }

    @DisplayName("존재하지 않는 URL 요청 테스트")
    @Test
    void getInvalidHTML() {
        assertThat(webPageLoader.getHTML("https://empty")).isNull();
    }

}
