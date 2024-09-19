package com.dmforu.cafeteria

import com.dmforu.crawling.parser.Parser
import org.springframework.stereotype.Service

@Service
class CafeteriaService(
        private val cafeteriaParser: Parser<Diet>
) {
    fun getData(): List<Diet> {
        return cafeteriaParser.parse()
    }
}
