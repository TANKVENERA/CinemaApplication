package com.mina.mail.ru.cinema.service.converter;

/**
 * Created by Mina on 23.04.2019.
 */
public interface CommonConverter<T1, T2> {

    T1 convertToDbo(T2 t2);

    T2 convertToDto(T1 t1);
}
