package o.yurchenko.homeexercise.localstorage.trending.entity;

import java.util.Objects;

public class Owner {

    public Owner(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    private String login;

    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(login, owner.login) && Objects.equals(avatarUrl, owner.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, avatarUrl);
    }
}
