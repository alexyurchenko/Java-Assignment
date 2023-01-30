package o.yurchenko.homeexercise.feature.details.api;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;

public interface DetailsRepository {

    Single<Boolean> isFavorite(long id);
    Completable addFavorite(Repository repository);
    Completable removeFavorite(long id);
}
