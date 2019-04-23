package at.geilehner.jwt.security.model;

public class AccessToken {
    private String token_type;
    private String access_token;
    private long expires_in;

    public AccessToken() {

    }

    public AccessToken(boolean success, String msg, String tt, String at, long ei) {
        this.token_type = tt;
        this.access_token = at;
        this.expires_in = ei;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
