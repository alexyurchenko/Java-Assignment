package o.yurchenko.homeexercise.feature.trending.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import o.yurchenko.homeexercise.feature.trending.api.TrendingRepository;
import o.yurchenko.homeexercise.feature.trending.data.dto.RepositoryDto;
import o.yurchenko.homeexercise.localstorage.trending.dao.TrendingDao;
import o.yurchenko.homeexercise.localstorage.trending.entity.Owner;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;
import o.yurchenko.homeexercise.network.Api;

public class TrendingRepositoryImpl implements TrendingRepository {

    private final Api api;

    private final TrendingDao trendingDao;

    @Inject
    public TrendingRepositoryImpl(Api api, TrendingDao trendingDao) {
        this.api = api;
        this.trendingDao = trendingDao;
    }

    @Override
    public Completable loadRepositories(String date) {
        String q = "created:>" + date;
        return api.repositories(q, "stars")
                .map(repositoriesDto -> {
                    List<Repository> repositories = new ArrayList<>();
                    for (RepositoryDto repositoryDto : repositoriesDto.getItems()) {
                        Repository repository = new Repository(
                                repositoryDto.getId(),
                                repositoryDto.getName(),
                                repositoryDto.getDescription(),
                                repositoryDto.getStargazersCount(),
                                repositoryDto.getLanguage(),
                                repositoryDto.getForks(),
                                repositoryDto.getCreatedAt(),
                                repositoryDto.getHtmlUrl(),
                                new Owner(repositoryDto.getOwner().getLogin(), repositoryDto.getOwner().getAvatarUrl())
                        );
                        repositories.add(repository);
                    }
                    return repositories;
                })
                .flatMapCompletable(repositories -> {
                    trendingDao.clearRepositories();
                    return trendingDao.insertAll(repositories);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<List<Repository>> repositories() {
        return trendingDao.repositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
