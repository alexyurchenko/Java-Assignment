package o.yurchenko.homeexercise.feature.details.presentation;

import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import o.yurchenko.homeexercise.COMMON;
import o.yurchenko.homeexercise.R;
import o.yurchenko.homeexercise.databinding.DetailsFragmentBinding;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;

public class DetailsFragment extends Fragment {

    private DetailsFragmentBinding binding;

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
        updateUI(getRepository());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateUI(Repository repository) {
        Context context = binding.getRoot().getContext();
        Glide.with(context)
                .load(repository.getOwner().getAvatarUrl())
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(binding.imgAvatar);
        binding.textUsername.setText(repository.getOwner().getLogin());
        binding.textRepositoryName.setText(repository.getName());
        String desc = repository.getDescription() != null ? repository.getDescription() : context.getString(R.string.no_description);
        binding.textRepositoryDesc.setText(desc);
        binding.textRepositoryStars.setText(String.format(getContext().getString(R.string.repository_stars_count), repository.getStargazersCount()));
        binding.textRepositoryForks.setText(String.format(getContext().getString(R.string.repository_forks_count), repository.getForks()));
        if (repository.getLanguage() != null) {
            binding.textRepositoryLanguage.setVisibility(View.VISIBLE);
            binding.textRepositoryLanguage.setText(String.format(getContext().getString(R.string.repository_language), repository.getLanguage()));
        }
        String pattern = "dd MMM yyyy";
        DateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        String date = df.format(repository.getCreatedAt());
        binding.textRepositoryDate.setText(String.format(getContext().getString(R.string.repository_created_at), date));
        binding.textRepositoryLink.setText(String.format(getContext().getString(R.string.repository_link), repository.getHtmlUrl()));
    }

    private Repository getRepository() {
        return (Repository) getArguments().getSerializable(COMMON.REPOSITORY_KEY);
    }
}
