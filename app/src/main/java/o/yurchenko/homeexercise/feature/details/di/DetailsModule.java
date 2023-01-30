package o.yurchenko.homeexercise.feature.details.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import o.yurchenko.homeexercise.feature.details.api.DetailsRepository;
import o.yurchenko.homeexercise.feature.details.data.DetailsRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class DetailsModule {

    @Binds
    @ViewModelScoped
    public abstract DetailsRepository bindDetailsRepository(DetailsRepositoryImpl detailsRepositoryImpl);
}
