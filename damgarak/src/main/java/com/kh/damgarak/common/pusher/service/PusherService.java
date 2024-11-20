package com.kh.damgarak.common.pusher.service;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pusher.rest.Pusher;

@Service
public class PusherService {

    private final Pusher pusher;

    public PusherService() {
        this.pusher = new Pusher("1897478", "b6fa21c979dd4a7fb342", "040a58cd587fc409ebb0");
        this.pusher.setCluster("ap3");
        this.pusher.setEncrypted(true);
    }

    public void sendMessage(String channel, String event, Map<String, String> messagePayload) {
        // Pusher의 trigger 메서드 사용
        pusher.trigger(channel, event, messagePayload);
    }
}
