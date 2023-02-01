package o.yurchenko.homeexercise.feature.favorites.api;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import o.yurchenko.homeexercise.localstorage.favorites.entity.Favorite;

public interface FavoritesRepository {

    Flowable<List<Favorite>> favorites();

    Completable remove(long id);
}
