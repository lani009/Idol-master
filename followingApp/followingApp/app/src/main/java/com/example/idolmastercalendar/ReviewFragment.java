package com.example.idolmastercalendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.idolmastercalendar.data.Review;
import com.example.idolmastercalendar.network.RemoteClient;
import com.example.idolmastercalendar.network.api.WimiApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {

    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView reviewRecycler = view.findViewById(R.id.reviewRecycler);
        ReviewAdapter reviewAdapter = new ReviewAdapter(getChildFragmentManager());
        reviewRecycler.setAdapter(reviewAdapter);

        ImageView imageView = view.findViewById(R.id.imageView6);
        imageView.setOnClickListener(v -> {
            ReviewDialog.Companion.getInstance().show(getChildFragmentManager(), "ReviewDialog");
        });

        getReview(reviewAdapter);
    }

    private void getReview(ReviewAdapter reviewAdapter) {
        WimiApi wimiApi = RemoteClient.INSTANCE.createRetrofit(true).create(WimiApi.class);

        Call<JsonObject> call = wimiApi.getReview("에그슬럿");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                assert response.body() != null;
                if(response.body().has("review")) {
                    JsonArray reviewArray = response.body().getAsJsonArray("review");

                    for(int i=0; i<reviewArray.size(); i++) {
                        reviewAdapter.getReviewList().add(new Review(reviewArray.get(i).getAsJsonObject().get("writer").getAsString(), reviewArray.get(i).getAsJsonObject().get("content").getAsString()));
                    }

                    reviewAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        /*call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                Timber.e("Data Size : %s", response.body().size());
                reviewAdapter.getReviewList().addAll(response.body());
                reviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });*/
    }
}