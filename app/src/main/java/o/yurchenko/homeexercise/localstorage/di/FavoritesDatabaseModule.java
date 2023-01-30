package o.yurchenko.homeexercise.localstorage.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import o.yurchenko.homeexercise.localstorage.FavoritesDatabase;
import o.yurchenko.homeexercise.localstorage.dao.FavoriteDao;

@Module
@InstallIn(SingletonComponent.class)
public class FavoritesDatabaseModule {

    @Singleton
    @Provides
    public FavoritesDatabase provideFavoritesDatabase(@ApplicationContext Context context) {
        return Room
                .databaseBuilder(
                        context,
                        FavoritesDatabase.class,
                        FavoritesDatabase.DATABASE_NAME)
                .build();
    }

    @Singleton
    @Provides
    public FavoriteDao provideFavoriteDao(FavoritesDatabase favoritesDatabase) {
        return favoritesDatabase.favoriteDao();
    }
}
