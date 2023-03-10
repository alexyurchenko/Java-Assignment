package o.yurchenko.homeexercise.feature.details.data;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import o.yurchenko.homeexercise.feature.details.api.DetailsRepository;
import o.yurchenko.homeexercise.localstorage.favorites.dao.FavoriteDao;
import o.yurchenko.homeexercise.localstorage.favorites.entity.Favorite;
import o.yurchenko.homeexercise.localstorage.trending.dao.TrendingDao;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

public class DetailsRepositoryImpl implements DetailsRepository {

    private TrendingDao trendingDao;
    private FavoriteDao favoriteDao;

    @Inject
    public DetailsRepositoryImpl(TrendingDao trendingDao, FavoriteDao favoriteDao) {
        this.trendingDao = trendingDao;
        this.favoriteDao = favoriteDao;
    }

    @Override
    public Single<Repository> repository(long id) {
        return trendingDao.repositoryById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Boolean> isFavorite(long id) {
        return favoriteDao.exist(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable addFavorite(Repository repository) {
        Favorite favorite = new Favorite(
                repository.getId(),
                repository.getName(),
                repository.getDescription(),
                repository.getStargazersCount(),
                repository.getLanguage(),
                repository.getForks(),
                repository.getCreatedAt(),
                repository.getHtmlUrl(),
                repository.getOwner().getLogin(),
                repository.getOwner().getAvatarUrl()
        );
        return favoriteDao.insert(favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable removeFavorite(long id) {
        return favoriteDao.removeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
