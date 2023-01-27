package o.yurchenko.homeexercise.feature.trending.api;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;

public interface TrendingRepository {

    Single<List<Repository>> repositories();
}
