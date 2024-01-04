package com.pim.client.socket;


import com.alibaba.fastjson.JSONObject;
import com.pim.client.beans.MessageBody;
import com.pim.client.observer.PriManagerSubject;
import com.pim.client.utils.EncryptUtil;
import com.pim.client.utils.IMStatus;
import com.pim.client.utils.IMTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.*;

@Slf4j
public class PriWebSocketClient extends WebSocketClient {

    public String fromUid = "";
    public String token = "";
    public String deviceId = "";

    public boolean isLogin = false;

    public PriManagerSubject priManagerSubject;


    ScheduledExecutorService connectScheduler;
    ScheduledExecutorService pingScheduler;


    String key;
    String loginString = "";
    String pingString = "";

    public PriWebSocketClient(URI serverUris) {
        super(serverUris);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        key = EncryptUtil.getUidKey(fromUid);
        send(loginString);
    }


    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        IMStatus imStatus = new IMStatus();
        imStatus.setImcode("501");
        imStatus.setDesc("The server disconnected your connection:" + arg1);
        imStatus.setActionTime(IMTimeUtils.getDateTime());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(imStatus);
        priManagerSubject.publishError(jsonObject.toJSONString());
        isLogin = false;
    }

    @Override
    public void onError(Exception arg0) {
        IMStatus imStatus = new IMStatus();
        imStatus.setImcode("502");
        imStatus.setDesc(arg0.getMessage());
        imStatus.setActionTime(IMTimeUtils.getDateTime());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(imStatus);
        priManagerSubject.publishError(jsonObject.toJSONString());
        isLogin = false;
    }

    @Override
    public void onMessage(String arg0) {

        if (arg0.indexOf("{") >= 0) {
            if (arg0.indexOf("Login successful") >= 0) {
                isLogin = true;
            }
            priManagerSubject.publish(arg0);
        } else {
            String recStr = EncryptUtil.decrypt(key, arg0);
            priManagerSubject.publish(recStr);
        }
    }

    public void start() {

        loginString = createLoginString();
        pingString = createPingString();
        scheduleReconnect();
        schedulePing();

        this.connect();
    }


    public String createLoginString() {
        MessageBody messageBodyLogin = new MessageBody();
        messageBodyLogin.setEventId("1000000");
        messageBodyLogin.setFromUid(fromUid);
        messageBodyLogin.setToken(token);
        messageBodyLogin.setDeviceId(deviceId);
        messageBodyLogin.setCTimest(IMTimeUtils.getTimeSt());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(messageBodyLogin);
        return jsonObject.toJSONString();
    }

    public String createPingString() {
        MessageBody messageBodyLogin = new MessageBody();
        messageBodyLogin.setEventId("9000000");
        messageBodyLogin.setFromUid(fromUid);
        messageBodyLogin.setDeviceId(deviceId);
        messageBodyLogin.setCTimest(IMTimeUtils.getNanoTime() + "");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(messageBodyLogin);
        return EncryptUtil.encrypt(EncryptUtil.getUidKey(fromUid), jsonObject.toJSONString());
    }

    private void scheduleReconnect() {
        connectScheduler = Executors.newSingleThreadScheduledExecutor();
        long delay = 10; //Delay execution time (seconds)
        long period = 5; //Execution interval (seconds)
        connectScheduler.scheduleAtFixedRate(this::doReconnect, delay, period, TimeUnit.SECONDS);
    }

    private void schedulePing() {
        pingScheduler = Executors.newSingleThreadScheduledExecutor();
        long delay = 3; //Delay execution time (seconds)
        long period = 5; //Execution interval (seconds)
        pingScheduler.scheduleAtFixedRate(this::doPingStr, delay, period, TimeUnit.SECONDS);
    }

    private void doReconnect() {
        if (!isLogin) {
            this.reconnect();
        }
    }

    private void doPingStr() {
        if (isLogin) {
            this.send(pingString);
        }
    }
}

