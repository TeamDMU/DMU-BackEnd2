package me.gijung.DMforU.utils;

public interface Mapper<T, V> {
    T mapToDto(V v);
}
