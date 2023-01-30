package o.yurchenko.homeexercise.feature.details.presentation;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.feature.details.api.DetailsRepository;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;

@HiltViewModel
public class DetailsViewModel extends ViewModel {

    private final DetailsRepository detailsRepository;

    @Inject
    DetailsViewModel(DetailsRepository repository) {
        this.detailsRepository = repository;
    }

    public Single<Boolean> isFavorite(long id) {
        return detailsRepository.isFavorite(id);
    }

    public Completable addToFavorites(Repository repository) {
        return detailsRepository.addFavorite(repository);
    }

    public Completable removeFromFavorites(long id) {
        return detailsRepository.removeFavorite(id);
    }
}
