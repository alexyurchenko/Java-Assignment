package o.yurchenko.homeexercise.localstorage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import o.yurchenko.homeexercise.localstorage.dao.FavoriteDao;
import o.yurchenko.homeexercise.localstorage.entity.Favorite;

@Database(
        entities = Favorite.class,
        version = FavoritesDatabase.DATABASE_VERSION
)
public abstract class FavoritesDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Favorites";

    public abstract FavoriteDao favoriteDao();
}
