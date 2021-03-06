package com.atguigu.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

/**
 * 实现Interceptor接口，重写其方法
 *
 * @author CZY
 * @date 2021/11/2 14:13
 * @description com.atguigu.flume.CustomInterceptor
 */
public class CustomInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        if (body[0] >= '0' && body[0] <= '9') {
            event.getHeaders().put("type", "number");
        } else if (body[0] >= 'a' && body[0] <= 'z' || body[0] >= 'A' && body[0] <= 'Z') {
            event.getHeaders().put("type", "letter");
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class myBuilder implements Builder {

        @Override
        public Interceptor build() {
            return new CustomInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
