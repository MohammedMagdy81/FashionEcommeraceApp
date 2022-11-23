package ddd.magdy.fashione_commerace.model;

import com.google.firebase.Timestamp;

public class Message {

    private String messageTxt;
    private Timestamp timeStamp;
    private String senderId;
    private String senderName;

    public Message() {
    }

    public String formatTime() {
        return this.timeStamp.toDate().toLocaleString();
    }

    public Message(String messageTxt, Timestamp timeStamp, String senderId, String senderName) {
        this.messageTxt = messageTxt;
        this.timeStamp = timeStamp;
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }
}
