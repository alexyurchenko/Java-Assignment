package o.yurchenko.homeexercise.feature.trending.presentation;

import static o.yurchenko.homeexercise.COMMON.dateFormat;

import androidx.lifecycle.ViewModel;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import o.yurchenko.homeexercise.feature.trending.api.TrendingRepository;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

@HiltViewModel
public class TrendingViewModel extends ViewModel {

    private final TrendingRepository trendingRepository;

    private final BehaviorSubject<List<Repository>> successSubject = BehaviorSubject.create();

    private final PublishSubject<Throwable> errorSubject = PublishSubject.create(); // todo map errors

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    TrendingViewModel(TrendingRepository trendingRepository) {
        this.trendingRepository = trendingRepository;
        trendingRepositories();
        lastDayTrendingRepositories();
    }

    public Observable<List<Repository>> getRepositories() {
        return successSubject.hide();
    }

    public Observable<Throwable> getError() {
        return errorSubject.hide();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void lastDayTrendingRepositories() {
        String time = formattedTime(1, ChronoUnit.DAYS);
        loadTrendingRepositories(time);
    }

    public void lastWeekTrendingRepositories() {
        String time = formattedTime(7, ChronoUnit.DAYS);
        loadTrendingRepositories(time);
    }

    public void lastMonthTrendingRepositories() {
        String time = formattedTime(30, ChronoUnit.DAYS);
        loadTrendingRepositories(time);
    }

    private void trendingRepositories() {
        compositeDisposable.add(trendingRepository.repositories()
                .subscribe(successSubject::onNext, errorSubject::onNext));
    }

    private void loadTrendingRepositories(String date) {
        compositeDisposable.add(trendingRepository.loadRepositories(date)
                .subscribe(() -> {
                }, errorSubject::onNext));
    }

    private String formattedTime(long amountToSubtract, TemporalUnit unit) {
        Instant instant = Instant.now().minus(amountToSubtract, unit);
        Instant dayBefore = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()).with(LocalTime.MIN).toInstant();
        return dateFormat.format(Date.from(dayBefore));
    }
}
