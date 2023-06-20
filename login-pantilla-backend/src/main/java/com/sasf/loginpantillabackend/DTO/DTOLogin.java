package com.sasf.loginpantillabackend.DTO;

import java.util.List;

public class DTOLogin {
    private String username;
    private String password;
    private List<AuthorityDTO> authorities;

    public DTOLogin() {
    }

    public DTOLogin(String username, List<AuthorityDTO> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    public static class AuthorityDTO {
        private String authority;

        public AuthorityDTO(String authority) {
            this.authority = authority;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }
}
