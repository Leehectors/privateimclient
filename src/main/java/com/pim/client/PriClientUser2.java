package com.pim.client;

import com.pim.client.beans.MessageBody;
import com.pim.client.observer.PriManager;
import com.pim.client.observer.PriObserver;
import com.pim.client.utils.IMTimeUtils;

public class PriClientUser2 implements PriObserver {



    String fromUid = "1001_30334";
    String token = "2a0da03ba5204683a16de94fc8d92cb8";
    String deviceId = IMTimeUtils.getNanoTime() + "";

    String serverIp = "ws://127.0.0.1:9922";



    public static void main(String[] args) {
        PriClientUser2 priClient = new PriClientUser2();
        priClient.init();
    }


    private void init(){
        PriManager.instance().imIPAndPort = serverIp;
        PriManager.instance().fromUid = fromUid;
        PriManager.instance().token = token;
        PriManager.instance().deviceId = deviceId;
        PriManager.instance().priManagerSubject.addObserver(this);
        PriManager.instance().startSocket();
    }





    @Override
    public void onIMMessage(String message) {
        System.out.println("im message received:" + message);
    }

    @Override
    public void onIMError(String message) {
        System.out.println("im error message received:" + message);
    }


    String toUid = "1001_30333";//for test

    private void testSendToOtherUser(){
        MessageBody messageBody = new MessageBody();
        messageBody.setEventId("1000001");
        messageBody.setFromUid(fromUid);
        messageBody.setToUid(toUid);
        messageBody.setCTimest(IMTimeUtils.getTimeSt());
        messageBody.setDataBody("test send message to "+toUid+"!!🤣🤣🤣😁😁🤠🤠🤡🤡🤡🤡🐭🐭🐮🐮🌶🌶🥝🥝🥝🍟🍟🍟🍟");
        PriManager.instance().sendMessage(messageBody);
    }


}
