package o.yurchenko.homeexercise.localstorage.trending.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import o.yurchenko.homeexercise.localstorage.converter.DateConverter;

@Entity(tableName = "trending_repositories")
@TypeConverters(DateConverter.class)
public class Repository implements Serializable {

    public Repository(long id, String name, String description, String stargazersCount,
                      String language, String forks, Date createdAt, String htmlUrl, Owner owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stargazersCount = stargazersCount;
        this.language = language;
        this.forks = forks;
        this.createdAt = createdAt;
        this.htmlUrl = htmlUrl;
        this.owner = owner;
    }

    @PrimaryKey
    private long id;
    private String name;
    private String description;
    private String stargazersCount;
    private String language;
    private String forks;
    private Date createdAt;
    private String htmlUrl;
    @Embedded
    private Owner owner;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Owner getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(stargazersCount, that.stargazersCount) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, stargazersCount, owner);
    }
}
