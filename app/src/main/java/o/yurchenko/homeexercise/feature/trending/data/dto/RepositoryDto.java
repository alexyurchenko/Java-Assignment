package o.yurchenko.homeexercise.feature.trending.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RepositoryDto {

    private long id;
    private String name;
    private String description;
    @SerializedName("stargazers_count")
    private String stargazersCount;
    private String language;
    private String forks;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("html_url")
    private String htmlUrl;
    private OwnerDto owner;

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

    public OwnerDto getOwner() {
        return owner;
    }
}
