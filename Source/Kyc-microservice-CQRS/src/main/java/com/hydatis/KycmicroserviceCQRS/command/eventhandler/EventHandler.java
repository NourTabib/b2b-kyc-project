package com.hydatis.KycmicroserviceCQRS.command.eventhandler;

import com.hydatis.KycmicroserviceCQRS.events.Event;

public interface EventHandler<T> {
    public Boolean publish(Event<T> event);
}
