package za.co.uride.userservice.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private long userId;
    private final String principal;

    /**
     *
     * @param authorities authorities
     * @param principal   principal
     * @param userId      user id
     */

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String principal, long userId) {
        super(authorities);
        this.principal = principal;
        this.userId = userId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }


}
