package com.hydatis.KycmicroserviceCQRS.query.service;

import java.util.List;

public interface QueryService<T> {
    T findOneById(Long id);
    List<T> findAll();
}
