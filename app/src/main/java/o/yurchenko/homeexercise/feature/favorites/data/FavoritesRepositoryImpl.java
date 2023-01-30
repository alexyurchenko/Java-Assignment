package o.yurchenko.homeexercise.feature.favorites.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import o.yurchenko.homeexercise.feature.favorites.api.FavoritesRepository;
import o.yurchenko.homeexercise.localstorage.Favorite;
import o.yurchenko.homeexercise.localstorage.dao.FavoriteDao;

public class FavoritesRepositoryImpl implements FavoritesRepository {

    private FavoriteDao favoriteDao;

    @Inject
    public FavoritesRepositoryImpl(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @Override
    public Single<List<Favorite>> favorites() {
        return favoriteDao.favorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable remove(long id) {
        return favoriteDao.removeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
