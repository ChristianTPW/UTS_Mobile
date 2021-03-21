package umn.ac.id;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LaguActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulagu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuProfile:
                Intent intentProfile = new Intent(LaguActivity.this, ProfileActivity.class);
                startActivityForResult(intentProfile, 1);
                return true;

            case R.id.LogOut:
                Intent intentLogout = new Intent(LaguActivity.this, MainActivity.class);
                startActivityForResult(intentLogout, 1);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu);



        AlertDialog alertDialog = new AlertDialog.Builder(LaguActivity.this).create();
        alertDialog.setTitle("Selamat Datang");
        alertDialog.setMessage("Christian Teguh Prasetya Widjaja\n00000032062");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}