package com.powernote.skeleton.security.vo;

import com.powernote.skeleton.security.service.SocialUserProfile;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GITHUB("github", (attributes) -> {
        return new SocialUserProfile(
                "github",
                String.valueOf(attributes.get("id")),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("avatar_url")
        );
    }),
    GOOGLE("google", (attributes) -> {
        return new SocialUserProfile(
                "google",
                String.valueOf(attributes.get("sub")),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture")
        );
    }),
    NAVER("naver", (attributes) -> {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return new SocialUserProfile(
                "naver",
                (String) response.get("id"),
                (String) response.get("name"),
                (String) response.get("email"),
                (String) response.get("profile_image")
        );
    });

    private final String registrationId;
    private final Function<Map<String, Object>, SocialUserProfile> of;

    OAuthAttributes(String registrationId, Function<Map<String, Object>, SocialUserProfile> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static SocialUserProfile extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                     .filter(provider -> registrationId.equals(provider.registrationId))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }


}
