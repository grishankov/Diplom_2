package Models;

public class Token{
    public User user;
    public String accessToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken(){
        return accessToken.replace("Bearer ", "");
    }
}
