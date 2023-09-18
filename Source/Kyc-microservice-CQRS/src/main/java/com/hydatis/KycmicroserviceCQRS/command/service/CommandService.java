package com.hydatis.KycmicroserviceCQRS.command.service;

import java.util.List;

public interface CommandService<T> {
    T findOneById(Long id);
    List<T> findAll();
    T save(T entity);
    T update(T entity);
    T delete(Long id);

}
