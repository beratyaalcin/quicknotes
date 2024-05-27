package com.example.quicknotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

    ImageView backButton;
    TextView logout;
    ImageView turkishFlag, americanFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.loadLocale(this); // Uygulamanın başlangıcında dil tercihini yükle
        setContentView(R.layout.activity_settings);

        logout = findViewById(R.id.logout);
        turkishFlag = findViewById(R.id.turkish_flag);
        americanFlag = findViewById(R.id.american_flag);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener((v)->startActivity(new Intent(Settings.this,MainActivity.class)) );

        turkishFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Settings.this, "tr");
                Utility.showToast(Settings.this,getResources().getString(R.string.LanguageChanged));
                recreate(); // Değişiklikleri yansıtmak için aktiviteyi yeniden oluştur
            }
        });

        americanFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Settings.this, "en");
                Utility.showToast(Settings.this,getResources().getString(R.string.LanguageChanged));
                recreate(); // Değişiklikleri yansıtmak için aktiviteyi yeniden oluştur
            }
        });
    }

    public void logoutClicked(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Settings.this, LoginActivity.class));
        finish();
    }
}