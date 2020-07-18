package elvear.com.kristian;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.reward.RewardedVideoAd;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import android.widget.ImageView;
import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {

    private RewardedVideoAd mRewardedVideoAd;
    private AdView mAdView;
    ImageView img;
    ImageButton btr;
    ImageButton btx;
    Button btc;
    Button saver;
    int taylornum = 0;

    private InterstitialAd mInterstitialAd;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    int taylorphoto[] = {R.drawable.karius_1,
            R.drawable.karius_2,
            R.drawable.karius_3,
            R.drawable.karius_4,
            R.drawable.karius_5,
            R.drawable.karius_6,
            R.drawable.karius_7,
            R.drawable.karius_8,
            R.drawable.karius_9,
            R.drawable.karius_10,
            R.drawable.karius_11,
            R.drawable.karius_12,
            R.drawable.karius_13,
            R.drawable.karius_14,
            R.drawable.karius_15,
            R.drawable.karius_16,
            R.drawable.karius_17,
            R.drawable.karius_18,
            R.drawable.karius_19,
            R.drawable.karius_20,
            R.drawable.karius_21,
            R.drawable.karius_22,
            R.drawable.karius_23,
            R.drawable.karius_24,
            R.drawable.karius_25,
            R.drawable.karius_26,
            R.drawable.karius_27,
            R.drawable.karius_28,
            R.drawable.karius_29,
            R.drawable.karius_30,
            R.drawable.karius_31,
            R.drawable.karius_32,
            R.drawable.karius_33,
            R.drawable.karius_34,
            R.drawable.karius_35};

    public static Bitmap viewToBitmap(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btr = (ImageButton) findViewById(R.id.buttonx);
        btx = (ImageButton) findViewById(R.id.button2);
        btc = (Button) findViewById(R.id.button6);
        saver = (Button) findViewById(R.id.button3);
        img = (ImageView) findViewById(R.id.imageView);
        btx.setEnabled(false);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(this, "ca-app-pub-4381954817561200~1122526122");
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4381954817561200/6350802071");
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }

        });



        int Permission_All = 1;

        String[] Permissions = {Manifest.permission.SET_WALLPAPER, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,};
        if (!hasPermissions(this, Permissions)) {
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }


    }

    //public class nasıla extends yukleme {

        public void displayInterstitial() {

            if (mInterstitialAd.isLoaded()) {

            }
        }



    public void btn_setWallpaper(View view) {

        {
            mInterstitialAd.show();
        }


        WallpaperManager wallpaperManager = WallpaperManager.getInstance(MainActivity.this);


        try {
            wallpaperManager.setResource(taylorphoto[taylornum]);
            Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    {

    }

    public void btc_click(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(MainActivity.this);

        try {
            wallpaperManager.setBitmap(viewToBitmap(img, img.getWidth(), img.getHeight()));
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void save_click(View view) {
        {
            mInterstitialAd.show();
        }

        FileOutputStream fileOutputStream = null;
        File file = getDisc();
        if (!file.exists() && !file.mkdir()) {
            Toast.makeText(this, "Can't creator to directory save image", Toast.LENGTH_SHORT);
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img" + date + ".jpg";
        String file_name = file.getAbsolutePath() + "/" + name;
        File new_file = new File(file_name);
        try {
            fileOutputStream = new FileOutputStream(new_file);
            Bitmap bitmap = viewToBitmap(img, img.getWidth(), img.getHeight());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Toast.makeText(this, "Save image success", Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        refreshGallery(new_file);
    }

    public void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);


    }

    private File getDisc() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(file, "Wallpaper");




    }






    public void btr_click(View view) {

        {

        }
        if (taylornum < 35) {
            taylornum--;

            img.setImageResource(taylorphoto[taylornum]);
        } else if (taylornum == 1)
            taylornum = 0;

        img.setImageResource(taylorphoto[taylornum]);
        {
            if (taylornum == 1)
                btx.setEnabled(false);


        }


    }



    public void img_click(View view) {
        if (taylornum < 34) {
            taylornum++;
            img.setImageResource(taylorphoto[taylornum]);
        }
          else if (taylornum <35)
                taylornum = 1;

            img.setImageResource(taylorphoto[taylornum]);

        }

    public void btx_click(View view) {
        if (taylornum < 34) {
            taylornum++;
            btx.setEnabled(true);
            img.setImageResource(taylorphoto[taylornum]);
        }
            else if (taylornum < 35) {
                taylornum = 1;
            btx.setEnabled(false);
            }
            img.setImageResource(taylorphoto[taylornum]);

        {
            if(taylornum == 1)
            btx.setEnabled(false);
        }


    }



    /*public void onClick(View btx) {
        btx.startAnimation(buttonClick);
    }
    */




    public static boolean hasPermissions(Context context, String... permissions){


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!=PackageManager.PERMISSION_GRANTED){


                    return  false;
                }
            }
        }
        return true;
    }
}
