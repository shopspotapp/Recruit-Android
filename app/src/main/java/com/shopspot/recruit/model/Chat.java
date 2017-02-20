package com.shopspot.recruit.model;

public class Chat {
    private String id;
    private String message;
    private String ownerId;
    private String ownerName;
    private long   createdDate;

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

    public long getCreatedDate() {
        return createdDate;
    }
}