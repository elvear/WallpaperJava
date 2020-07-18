package elvear.com.kristian;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gecis extends yukleme {
    public Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecis);
        bt = (Button) findViewById(R.id.batin);
        bt.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

               openActivity2();
                mInterstitialAd.show();
            }
        });


        bt.setEnabled(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bt.setEnabled(true);
            }
        },2000);
    }
    public void openActivity2() {
        Intent myIntent = new Intent(gecis.this, MainActivity.class);
        startActivity(myIntent);
        finish();
}
}


