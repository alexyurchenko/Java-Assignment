package o.yurchenko.homeexercise.feature.trending.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import o.yurchenko.homeexercise.feature.trending.api.TrendingRepository;
import o.yurchenko.homeexercise.feature.trending.api.model.Owner;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;
import o.yurchenko.homeexercise.feature.trending.data.dto.RepositoryDto;
import o.yurchenko.homeexercise.network.Api;

public class TrendingRepositoryImpl implements TrendingRepository {

    private final Api api;

    @Inject
    public TrendingRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<List<Repository>> repositories() {
        return api.repositories("created:>2023-01-18T00:00:00", "stars")
                .map(repositoriesDto -> {
                    List<Repository> repositories = new ArrayList<>();
                    for (RepositoryDto repositoryDto : repositoriesDto.getItems()) {
                        Repository repository = new Repository(
                                repositoryDto.getId(),
                                repositoryDto.getName(),
                                repositoryDto.getDescription(),
                                repositoryDto.getStargazersCount(),
                                new Owner(repositoryDto.getOwner().getLogin(), repositoryDto.getOwner().getAvatarUrl())
                        );
                        repositories.add(repository);
                    }
                    return repositories;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
