package o.yurchenko.homeexercise.feature.favorites.api;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.localstorage.Favorite;

public interface FavoritesRepository {

    Single<List<Favorite>> favorites();

    Completable remove(long id);
}
