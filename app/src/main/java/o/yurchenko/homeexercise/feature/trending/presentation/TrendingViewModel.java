package o.yurchenko.homeexercise.feature.trending.presentation;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.feature.trending.api.TrendingRepository;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;

@HiltViewModel
public class TrendingViewModel extends ViewModel {

    private final TrendingRepository trendingRepository;

    @Inject
    TrendingViewModel(TrendingRepository trendingRepository) {
        this.trendingRepository = trendingRepository;
    }

    public Single<List<Repository>> trendingRepositories() {
        return trendingRepository.repositories();
    }
}
