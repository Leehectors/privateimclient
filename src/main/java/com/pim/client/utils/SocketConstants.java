package com.pim.client.utils;


public class SocketConstants {

    /**
     * Log in
     */
    public static final String LoginEventId = "1000000";
    /**
     * Send message to client
     */
    public static final String SendMsgEventId = "1000001";

    /**
     * Reply ACK to IM
     */
    public static final String AckMsgEventId = "1000002";

    /**
     * Clear offline messages
     */
    public static final String ClearCacheMsg = "1000003";

    /**
     *Create room
     */
    public static final String CreateRoomEventId = "5000000";
    /**
     * Join a room
     */
    public static final String JoinRoomEventId = "5000001";
    /**
     * Leave the room
     */
    public static final String LeaveRoomEventId = "5000002";
    /**
     * Dismiss room
     */
    public static final String DestroyRoomEventId = "5000003";

    /**
     * Send group information
     */
    public static final String SEND_GROUP_MSG_EVENT_ID = "5000004";
    /**
     *Weak network environment information
     */
    public static final String SEND_WEAK_NETWORK_EVENT_ID = "8000000";
    /**
     * Login failed
     */
    public static final String LOGIN_FAIL = "3000001";
    /**
     *Heartbeat
     */
    public static final String HEARTBEAT_EVENT_ID = "9000000";

}
