package com.hydatis.KycmicroserviceCQRS.events;

import java.time.LocalDateTime;

public interface Event<T> {
    public T getData();

    public String getType();
    public LocalDateTime getCreationDate();
}

