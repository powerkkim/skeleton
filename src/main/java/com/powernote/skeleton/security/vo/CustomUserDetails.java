package com.powernote.skeleton.security.vo;

import com.powernote.skeleton.vo.UserVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

@ToString
@Setter
@Getter
public class CustomUserDetails implements UserDetails, OAuth2User, CredentialsContainer {

    private static final long serialVersionUID = 1000L;

    private final Set<GrantedAuthority> authorities;

    private UserVo user; // User정보

    private Map<String, Object> attributes;

    // 일반 로그인
    public CustomUserDetails( UserVo user ) {
        this.user = user;

        this.authorities = (StringUtils.hasText(user.getRoles()))
                ? Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities( Collections.singleton(new SimpleGrantedAuthority(user.getRoles())) )))
                : Collections.unmodifiableSet(new LinkedHashSet<>(AuthorityUtils.NO_AUTHORITIES));
    }

    // OAuth 로그인
    public CustomUserDetails(UserVo user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
        this.authorities = (StringUtils.hasText(user.getRoles()))
                ? Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities( Collections.singleton(new SimpleGrantedAuthority(user.getRoles())) )))
                : Collections.unmodifiableSet(new LinkedHashSet<>(AuthorityUtils.NO_AUTHORITIES));
    }

    // social login attribute [[
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
    // social login attribute ]]

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.user.getEmail();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public String getPassword() {
        return this.user.getPasswd();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public void eraseCredentials() {
//        this.password = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomUserDetails) {
            return this.user.getEmail().equals(((CustomUserDetails) obj).user.getEmail());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.user.getEmail().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName()).append(" [");
        sb.append("Username=").append(this.user.getEmail()).append(", ");
        sb.append("Password=[PROTECTED], ");
//        sb.append("Enabled=").append(this.enabled).append(", ");
//        sb.append("AccountNonExpired=").append(this.accountNonExpired).append(", ");
//        sb.append("credentialsNonExpired=").append(this.credentialsNonExpired).append(", ");
//        sb.append("AccountNonLocked=").append(this.accountNonLocked).append(", ");
        sb.append("Granted Authorities=").append(this.authorities).append("]");
        return sb.toString();
    }

    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>( Comparator.comparing(GrantedAuthority::getAuthority) );
        sortedAuthorities.addAll(authorities);
        return sortedAuthorities;
    }


}
