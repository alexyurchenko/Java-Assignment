package o.yurchenko.homeexercise.localstorage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import o.yurchenko.homeexercise.localstorage.favorites.dao.FavoriteDao;
import o.yurchenko.homeexercise.localstorage.favorites.entity.Favorite;
import o.yurchenko.homeexercise.localstorage.trending.dao.TrendingDao;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

@Database(
        entities = {Favorite.class, Repository.class},
        version = AppDatabase.DATABASE_VERSION
)
public abstract class AppDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AppDatabase";

    public abstract TrendingDao trendingDao();
    public abstract FavoriteDao favoriteDao();
}
