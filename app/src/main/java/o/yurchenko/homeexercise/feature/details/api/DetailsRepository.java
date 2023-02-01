package o.yurchenko.homeexercise.feature.details.api;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

public interface DetailsRepository {

    Single<Repository> repository(long id);
    Single<Boolean> isFavorite(long id);
    Completable addFavorite(Repository repository);
    Completable removeFavorite(long id);
}
