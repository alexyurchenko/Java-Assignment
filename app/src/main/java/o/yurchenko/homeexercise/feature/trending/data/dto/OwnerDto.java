package o.yurchenko.homeexercise.feature.trending.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class OwnerDto {

    private String login;
    @SerializedName("avatar_url")
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
        OwnerDto owner = (OwnerDto) o;
        return Objects.equals(login, owner.login) && Objects.equals(avatarUrl, owner.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, avatarUrl);
    }
}
