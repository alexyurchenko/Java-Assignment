package o.yurchenko.homeexercise.localstorage.favorites.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import o.yurchenko.homeexercise.localstorage.AppDatabase;
import o.yurchenko.homeexercise.localstorage.favorites.dao.FavoriteDao;

@Module
@InstallIn(SingletonComponent.class)
public class FavoritesDatabaseModule {

    @Singleton
    @Provides
    public FavoriteDao provideFavoriteDao(AppDatabase database) {
        return database.favoriteDao();
    }
}
