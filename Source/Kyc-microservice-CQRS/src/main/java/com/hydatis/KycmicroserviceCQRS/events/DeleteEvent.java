package com.hydatis.KycmicroserviceCQRS.events;

import java.time.LocalDateTime;

public class DeleteEvent<T> implements Event<T> {
    private T data;

    private String type;
    private LocalDateTime creationDate ;
    public DeleteEvent(){

    }
    public DeleteEvent(T data){
        this.creationDate = LocalDateTime.now();
        this.data = data;
        this.type =DeleteEvent.class.getName();
    }
    public void setData(T data) {
        this.data = data;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public T getData(){
        return this.data;
    }
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }
}
