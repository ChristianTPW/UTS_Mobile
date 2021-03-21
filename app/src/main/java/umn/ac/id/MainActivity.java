package umn.ac.id;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button profile;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        profile = findViewById(R.id.btnProfile);
        login = findViewById(R.id.btnLogin);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivityForResult(intentProfile, 1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intentProfile, 1);
            }
        });
    }


}