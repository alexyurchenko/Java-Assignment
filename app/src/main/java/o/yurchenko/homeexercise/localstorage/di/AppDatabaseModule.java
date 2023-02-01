package o.yurchenko.homeexercise.localstorage.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import o.yurchenko.homeexercise.localstorage.AppDatabase;

@Module
@InstallIn(SingletonComponent.class)
public class AppDatabaseModule {

    @Singleton
    @Provides
    public AppDatabase provideFavoritesDatabase(@ApplicationContext Context context) {
        return Room
                .databaseBuilder(
                        context,
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                .build();
    }
}
