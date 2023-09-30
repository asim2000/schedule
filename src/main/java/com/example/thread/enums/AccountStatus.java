package com.example.thread.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountStatus {
    SAVED(0),TAKED(1),PENDING(2),COMPLETE(3),DEACTIVE(4);
    public int status;
}
