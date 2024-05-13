package com.dmforu.crawling.parser;

public abstract class UrlGenerator {

    abstract protected String generateSearchUrl();

    abstract protected String generateUrlFromSearch(String url);

}
