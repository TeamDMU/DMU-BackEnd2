package me.gijung.DMforU.service.token;


public interface Token<T> {

    void updateToken(T t);

    void deleteToken(T t);

}
