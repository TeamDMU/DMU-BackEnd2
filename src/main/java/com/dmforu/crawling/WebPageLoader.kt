package com.dmforu.crawling

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

object WebPageLoader {
    /**
     * Jsoup을 사용하여 URL에 해당하는 웹 페이지의 HTML을 가져온다.
     *
     * @param url HTML을 가져오고자 하는 웹 페이지의 URL
     * @return URL에 접속하면 볼 수 있는 HTML을 반환
     */
    @JvmStatic
    fun getHTML(url: String?): Document? {
        return try {
            Jsoup.connect(url).get()
        } catch (e: IOException) {
            // 값이 비어있는 상황을 에러 핸들링 해야함
            null
        }
    }
}