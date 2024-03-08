package me.gijung.DMforU.service.token;

public interface TokenService<T> {

    void updateToken(T t);

    void deleteToken(T t);
}
