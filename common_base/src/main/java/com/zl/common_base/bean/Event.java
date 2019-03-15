package com.zl.common_base.bean;

/***
 * EventBus事件类
 */
public class Event<T> {

    private String action;//行为
    private T data;

    public Event(String action) {
        this.action = action;
    }

    public Event(String action, T data) {
        this.action = action;
        this.data = data;
    }

}
