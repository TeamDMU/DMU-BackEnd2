package com.dmforu.crawling.parser

abstract class UrlGenerator {
    protected abstract fun generateSearchUrl(): String

    protected abstract fun generateUrlFromSearch(url: String): String
}
