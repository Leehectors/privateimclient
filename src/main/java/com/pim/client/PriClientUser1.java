package com.pim.client;

import com.pim.client.beans.MessageBody;
import com.pim.client.observer.PriManager;
import com.pim.client.observer.PriObserver;
import com.pim.client.utils.IMTimeUtils;

public class PriClientUser1 implements PriObserver {



    String fromUid = "1001_30333";
    String token = "cf7cac30d1bf4d0fa59ab9d1e2884bb9";
    String deviceId = IMTimeUtils.getNanoTime() + "";

    String serverIp = "ws://127.0.0.1:9922";



    public static void main(String[] args) {
        PriClientUser1 priClientUser1 = new PriClientUser1();
        priClientUser1.init();
    }


    private void init(){
        PriManager.instance().imIPAndPort = serverIp;
        PriManager.instance().fromUid = fromUid;
        PriManager.instance().token = token;
        PriManager.instance().deviceId = deviceId;
        PriManager.instance().priManagerSubject.addObserver(this);
        PriManager.instance().startSocket();

        //testSendToOtherUser();
        //createGroup();
        //sendMessageToGroup();
    }





    @Override
    public void onIMMessage(String message) {
        System.out.println("im message received:" + message);
    }

    @Override
    public void onIMError(String message) {
        System.out.println("im error message received:" + message);
    }


    String toUid = "1001_30334";//for test

    private void testSendToOtherUser(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("1000001");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid(toUid);
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("test send message to "+toUid+"!!🤣🤣🤣😁😁🤠🤠🤡🤡🤡🤡🐭🐭🐮🐮🌶🌶🥝🥝🥝🍟🍟🍟🍟");
        PriManager.instance().sendMessage(messageBody);
    }


    private void createGroup(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("5000000");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid("");
        messageBody.setIsGroup("1");
        messageBody.setGroupId("JOE100000000");
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("");
        PriManager.instance().sendMessage(messageBody);
    }

    private void joinGroup(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("5000001");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid("");
        messageBody.setIsGroup("1");
        messageBody.setGroupId("JOE100000000");
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("");
        PriManager.instance().sendMessage(messageBody);
    }

    private void leaveGroup(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("5000002");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid("");
        messageBody.setIsGroup("1");
        messageBody.setGroupId("JOE100000000");
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("");
        PriManager.instance().sendMessage(messageBody);
    }

    private void disbandGroup(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("5000003");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid("");
        messageBody.setIsGroup("1");
        messageBody.setGroupId("JOE100000000");
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("");
        PriManager.instance().sendMessage(messageBody);
    }

    private void sendMessageToGroup(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("5000004");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid("");
        messageBody.setIsGroup("1");
        messageBody.setGroupId("JOE100000000");
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("group message for test 🍋🍋🍋🍌🍌🍌🍇🍇🍇🍇");
        PriManager.instance().sendMessage(messageBody);
    }
}
