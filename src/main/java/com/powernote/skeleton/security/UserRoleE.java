package com.powernote.skeleton.security;

public enum UserRoleE {
    ROLE_ANONYMOUS_USER("ANONYMOUSUSER"),
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_MASTER("MASTER");

    String value;

    UserRoleE(String value){
        this.value = value;
    }

    public String getRole() {
        return this.value;
    }
}
