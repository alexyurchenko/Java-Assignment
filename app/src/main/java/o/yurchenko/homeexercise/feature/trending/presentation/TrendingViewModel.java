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

    public Single<List<Repository>> lastDayTrendingRepositories() {
        String time = formattedTime(1, ChronoUnit.DAYS);
        return trendingRepositories(time);
    }

    public Single<List<Repository>> lastWeekTrendingRepositories() {
        String time = formattedTime(7, ChronoUnit.DAYS);
        return trendingRepositories(time);
    }

    public Single<List<Repository>> lastMonthTrendingRepositories() {
        String time = formattedTime(30, ChronoUnit.DAYS);
        return trendingRepositories(time);
    }

    private Single<List<Repository>> trendingRepositories(String date) {
        return trendingRepository.repositories(date);
    }

    private String formattedTime(long amountToSubtract, TemporalUnit unit) {
        Instant instant = Instant.now().minus(amountToSubtract, unit);
        Instant dayBefore = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()).with(LocalTime.MIN).toInstant();
        return dateFormat.format(Date.from(dayBefore));
    }
}
