package com.veeam.assignment.dimitris.nikolaidis.models;

public enum Status {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
