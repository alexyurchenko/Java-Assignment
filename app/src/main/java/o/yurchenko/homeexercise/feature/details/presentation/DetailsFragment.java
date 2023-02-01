package o.yurchenko.homeexercise.feature.details.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import o.yurchenko.homeexercise.COMMON;
import o.yurchenko.homeexercise.R;
import o.yurchenko.homeexercise.databinding.DetailsFragmentBinding;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

@AndroidEntryPoint
public class DetailsFragment extends Fragment {

    private DetailsViewModel viewModel;
    private DetailsFragmentBinding binding;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private boolean isFavorite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(DetailsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = DetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long repositoryId = getRepositoryId();
        initUI(repositoryId);
        subscribeOnUpdates();
        viewModel.repository(repositoryId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        compositeDisposable.clear();
    }

    private void initUI(long repositoryId) {
        binding.buttonFavorite.setOnClickListener(v -> toggleFavorite(repositoryId));
    }

    private void subscribeOnUpdates() {
        compositeDisposable.add(viewModel.getRepository()
                .subscribe(this::updateContent, Throwable::printStackTrace));
        compositeDisposable.add(viewModel.getIsFavorite()
                        .subscribe(this::updateFavorite, Throwable::printStackTrace));
        compositeDisposable.add(viewModel.getError() // todo show toast?
                .subscribe(Throwable::printStackTrace, Throwable::printStackTrace));
    }

    private void toggleFavorite(long repositoryId) {
        if (isFavorite) {
            removeFromFavorites(repositoryId);
        } else {
            addToFavorites(repositoryId);
        }
    }

    private void addToFavorites(long repositoryId) {
        viewModel.addToFavorites(repositoryId);
    }

    private void removeFromFavorites(long id) {
        viewModel.removeFromFavorites(id);
    }

    private void updateContent(Repository repository) {
        Context context = binding.getRoot().getContext();
        Glide.with(context)
                .load(repository.getOwner().getAvatarUrl())
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(binding.imgAvatar);
        binding.textUsername.setText(repository.getOwner().getLogin());
        binding.textRepositoryName.setText(repository.getName());
        String desc = repository.getDescription() != null ? repository.getDescription() : context.getString(R.string.trending_no_description);
        binding.textRepositoryDesc.setText(desc);
        binding.textRepositoryStars.setText(repository.getStargazersCount());
        binding.textRepositoryForks.setText(repository.getForks());
        if (repository.getLanguage() != null) {
            binding.textRepositoryLanguageDesc.setVisibility(View.VISIBLE);
            binding.textRepositoryLanguage.setVisibility(View.VISIBLE);
            binding.textRepositoryLanguage.setText(repository.getLanguage());
        }
        String pattern = "dd MMM yyyy"; // todo move to constants
        DateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        String date = df.format(repository.getCreatedAt());
        binding.textRepositoryDate.setText(date);
        binding.textRepositoryLink.setText(repository.getHtmlUrl());
    }

    private void updateFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
        binding.buttonFavorite.setVisibility(View.VISIBLE);
        if (isFavorite) {
            binding.buttonFavorite.setText(requireContext().getString(R.string.repository_remove_from_favorites));
        } else {
            binding.buttonFavorite.setText(requireContext().getString(R.string.repository_add_to_favorites));
        }
    }

    private long getRepositoryId() {
        return getArguments().getLong(COMMON.REPOSITORY_ID_KEY);
    }
}
