package elvear.com.kristian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class yukleme extends AppCompatActivity {
    public InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yukleme);

        AdRequest adRequest = new AdRequest.Builder().build();
        MobileAds.initialize(this, "ca-app-pub-4381954817561200~1122526122");
        mInterstitialAd = new InterstitialAd(yukleme.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4381954817561200/6350802071");
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }

        });


        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(1500);
                    Intent intent = new Intent(yukleme.this, gecis.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };


    }

    public void displayInterstitial() {
        if (mInterstitialAd.isLoaded()) {
        }
    }
}
