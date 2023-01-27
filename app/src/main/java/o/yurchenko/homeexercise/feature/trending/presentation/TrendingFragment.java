package o.yurchenko.homeexercise.feature.trending.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import o.yurchenko.homeexercise.databinding.TrendingFragmentBinding;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;
import o.yurchenko.homeexercise.feature.trending.presentation.adapter.TrendingAdapter;

@AndroidEntryPoint
public class TrendingFragment extends Fragment {

    TrendingViewModel viewModel;
    private TrendingFragmentBinding binding;

    private TrendingAdapter adapter;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(TrendingViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = TrendingFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TrendingAdapter();
        binding.viewRepositories.setAdapter(adapter);
        getRepositories();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        compositeDisposable.clear();
    }

    private void getRepositories() {
        compositeDisposable.add(
                viewModel.trendingRepositories()
                        .doOnSubscribe(disposable -> {
                            binding.viewProgress.setVisibility(View.VISIBLE);
                            binding.textEmpty.setVisibility(View.GONE);
                            binding.viewRepositories.setVisibility(View.GONE);
                        })
                        .subscribe(this::updateContent, Throwable::printStackTrace)
        );
    }

    private void updateContent(List<Repository> repositories) {
        binding.viewProgress.setVisibility(View.GONE);
        if (!repositories.isEmpty()) {
            binding.viewRepositories.setVisibility(View.VISIBLE);
            binding.textEmpty.setVisibility(View.GONE);
            adapter.submitList(repositories);
        } else {
            binding.textEmpty.setVisibility(View.VISIBLE);
            binding.viewRepositories.setVisibility(View.GONE);
        }
    }

    private void showError() {
        binding.viewProgress.setVisibility(View.GONE);
        // TODO show error
    }
}
