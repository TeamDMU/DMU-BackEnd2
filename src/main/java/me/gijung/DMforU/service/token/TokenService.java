package me.gijung.DMforU.service.token;

import org.springframework.stereotype.Service;

@Service
public interface TokenService<T> {

    void update_Token(T t);

    void delete_Token(T t);

}
