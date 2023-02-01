package o.yurchenko.homeexercise.feature.trending.api;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

public interface TrendingRepository {

    Completable loadRepositories(String date);

    Flowable<List<Repository>> repositories();
}
