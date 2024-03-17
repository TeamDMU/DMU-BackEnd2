package me.gijung.DMforU.service.token;


import org.springframework.stereotype.Service;

@Service
public interface TokenService<T> {

    void updateToken(T t);

    void deleteToken(T t);

}
