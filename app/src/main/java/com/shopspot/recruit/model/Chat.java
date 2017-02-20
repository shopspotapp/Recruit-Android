package com.shopspot.recruit.model;

public class Chat {
    private String  id;
    private String  message;
    private String  ownerId;
    private String  ownerName;
    private boolean isOwner;
    private boolean isRead;
    private long    createdDate;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public boolean isRead() {
        return isRead;
    }

    public long getCreatedDate() {
        return createdDate;
    }
}