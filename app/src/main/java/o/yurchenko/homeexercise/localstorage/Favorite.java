package o.yurchenko.homeexercise.localstorage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "favorite")
@TypeConverters(DateConverter.class)
public class Favorite {
    @PrimaryKey
    private long id;
    private String name;
    private String description;
    private String stargazersCount;
    private String language;
    private String forks;
    private Date createdAt;
    private String htmlUrl;
    private String login;
    private String avatarUrl;

    public Favorite(long id, String name, String description, String stargazersCount, String language,
                    String forks, Date createdAt, String htmlUrl, String login, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stargazersCount = stargazersCount;
        this.language = language;
        this.forks = forks;
        this.createdAt = createdAt;
        this.htmlUrl = htmlUrl;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public String getForks() {
        return forks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return id == favorite.id && Objects.equals(name, favorite.name) && Objects.equals(description, favorite.description) && Objects.equals(stargazersCount, favorite.stargazersCount) && Objects.equals(language, favorite.language) && Objects.equals(forks, favorite.forks) && Objects.equals(createdAt, favorite.createdAt) && Objects.equals(htmlUrl, favorite.htmlUrl) && Objects.equals(login, favorite.login) && Objects.equals(avatarUrl, favorite.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, stargazersCount, language, forks, createdAt, htmlUrl, login, avatarUrl);
    }
}
