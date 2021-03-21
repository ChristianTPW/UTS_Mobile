package umn.ac.id;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.LinkedList;

public class LaguActivity extends AppCompatActivity {
    private final LinkedList<String> mDaftarLagu = new LinkedList<>();

    private static final int PERMISSION_REQ = 1;

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

        if (ContextCompat.checkSelfPermission(LaguActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(LaguActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(LaguActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQ);
            }else{
                ActivityCompat.requestPermissions(LaguActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQ);}

        }else{
            getMusic();
        }




    }

    public void getMusic(){
        ContentResolver contentResolver = getContentResolver();
        Uri songuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor song = contentResolver.query(songuri, null, null, null, null);

        if(song != null && song.moveToFirst()){
            int songTitle = song.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = song.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do{
                String currTitle = song.getString(songTitle);
                mDaftarLagu.add(currTitle);
                Log.e("myTag", currTitle);
            }while (song.moveToNext());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQ:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    if(ContextCompat.checkSelfPermission(LaguActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
        }
    }
}