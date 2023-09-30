package com.example.thread.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    ACTIVE(1),DEACTIVE(0);
    public int status;
}
