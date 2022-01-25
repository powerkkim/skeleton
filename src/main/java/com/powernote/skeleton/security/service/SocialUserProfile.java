package com.powernote.skeleton.security.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SocialUserProfile {
    private final String socialType;
    private final String oauthId;
    private final String email;
    private final String name;
    private final String imageUrl;

    public SocialUserProfile(String socialType, String oauthId, String name, String email, String imageUrl) {
        this.socialType = socialType;
        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

}
