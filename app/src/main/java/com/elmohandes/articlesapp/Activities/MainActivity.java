package com.elmohandes.articlesapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.elmohandes.articlesapp.Adapters.SourceAdapter;
import com.elmohandes.articlesapp.Models.Source;
import com.elmohandes.articlesapp.Models.WebSite;
import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.databinding.ActivityMainBinding;
import com.elmohandes.articlesapp.retrofitPackage.APIClient;
import com.elmohandes.articlesapp.retrofitPackage.APIInterface;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    APIInterface apiInterface;
    ArrayList<Source> listSources;
    SourceAdapter adapter;
    AlertDialog dialog;

    private FrameLayout mAdView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = APIClient.getApiClient().create(APIInterface.class);
        listSources = new ArrayList<>();

        binding.mainRecycler.setHasFixedSize(true);
        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SourceAdapter(this,listSources);
        binding.mainRecycler.setAdapter(adapter);

        dialog = new SpotsDialog(this);

        loadWebsiteSources();

        setupBannerAd();

    }

    private void setupBannerAd(){
        MobileAds.initialize(this, initializationStatus -> {

        });

        mAdView = findViewById(R.id.adView);

        adView = new AdView(this);
        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.setAdUnitId("ca-app-pub-5401550443462421/5814639353");
        //my adUnitId ca-app-pub-5401550443462421/5814639353
        // TODO: Add adView to your view hierarchy.

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mAdView.addView(adView);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }

    private AdSize getAdSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density; int adWidth = (int) (widthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth); }

    private void loadWebsiteSources() {

        dialog.show();

        apiInterface.getResources().enqueue(new Callback<WebSite>() {
            @Override
            public void onResponse(@NonNull Call<WebSite> call, @NonNull Response<WebSite> response) {

                dialog.dismiss();
                WebSite webSite = response.body();
                if (webSite != null && response.body().getSources().size() > 0){

                    listSources.clear();
                    listSources.addAll(webSite.getSources());

                }else {
                    Toast.makeText(MainActivity.this,
                            "No sources found", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(@NonNull Call<WebSite> call, @NonNull Throwable t) {

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}