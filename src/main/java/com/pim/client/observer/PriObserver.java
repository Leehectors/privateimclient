package com.pim.client.observer;

public interface PriObserver {
    public void onIMMessage(String message);
    public void onIMError(String message);
}
