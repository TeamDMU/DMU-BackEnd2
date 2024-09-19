package com.dmforu.cafeteria

import com.dmforu.crawling.parser.Parser
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CafeteriaService {
    private val cafeteriaParser: Parser<Diet>? = null

    val data: List<Diet>
        get() = cafeteriaParser!!.parse()
}
