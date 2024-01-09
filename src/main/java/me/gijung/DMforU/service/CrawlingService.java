package me.gijung.DMforU.service;

import org.springframework.stereotype.Service;

@Service
public interface CrawlingService<T> {

    public T getData();
}
