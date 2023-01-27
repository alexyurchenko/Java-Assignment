package o.yurchenko.homeexercise.feature.trending.data.dto;

import com.google.gson.annotations.SerializedName;

public class RepositoryDto {

    private long id;
    private String name;
    private String description;
    @SerializedName("stargazers_count")
    private String stargazersCount;
    private OwnerDto owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(String stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public void setOwner(OwnerDto owner) {
        this.owner = owner;
    }
}
