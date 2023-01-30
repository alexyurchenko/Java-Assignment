package o.yurchenko.homeexercise.feature.favorites.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import o.yurchenko.homeexercise.databinding.FavoritesFragmentBinding;
import o.yurchenko.homeexercise.feature.favorites.presentation.adapter.FavoriteAdapter;
import o.yurchenko.homeexercise.localstorage.Favorite;

@AndroidEntryPoint
public class FavoritesFragment extends Fragment {

    private FavoritesViewModel viewModel;
    private FavoritesFragmentBinding binding;
    private FavoriteAdapter adapter;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(FavoritesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FavoritesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        subscribeOnUpdates();
    }

    private void subscribeOnUpdates() {
        compositeDisposable.add(viewModel.getFavorites().subscribe(this::showContent, this::showError));
        compositeDisposable.add(viewModel.getError().subscribe(this::showError, this::showError));
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.listFavorites.setLayoutManager(layoutManager);
        adapter = new FavoriteAdapter(favorite -> {
            // todo add/remove favorite
        });
        binding.listFavorites.setAdapter(adapter);
    }

    private void showContent(List<Favorite> favorites) {
        binding.viewProgress.setVisibility(View.GONE);
        if (!favorites.isEmpty()) {
            binding.listFavorites.setVisibility(View.VISIBLE);
            binding.textEmpty.setVisibility(View.GONE);
            adapter.submitList(favorites);
        } else {
            binding.textEmpty.setVisibility(View.VISIBLE);
            binding.listFavorites.setVisibility(View.GONE);
        }
    }

    private void showError(Throwable throwable) {
        binding.viewProgress.setVisibility(View.GONE);
        throwable.printStackTrace();
        // TODO show error
    }
}
