package com.zerod.admindemo.models;

public enum Permission {
    LV0_READ("lv0:read"),
    LV0_WRITE("lv0:write"),
    LV1_READ("lv1:read"),
    LV1_WRITE("lv1:write"),
    LV2_READ("lv2:read"),
    LV2_WRITE("lv2:write");

    private final String permission;

    Permission(String s) {
        permission = s;
    }

    public String getPermission() {
        return permission;
    }
}
