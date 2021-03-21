package umn.ac.id;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.nio.file.Path;
import java.util.LinkedList;

public class LaguActivity extends AppCompatActivity {
    static LinkedList<String> mDaftarLagu = new LinkedList<>();
    static LinkedList<String> mPathLagu = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private LaguAdapter mAdapter;

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
            Log.e("test", mDaftarLagu.getLast());
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            mAdapter = new LaguAdapter(this, mDaftarLagu);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


    }

    public void getMusic(){
        ContentResolver contentResolver = getContentResolver();
        Uri songuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.TITLE ,MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION};
        Cursor song = contentResolver.query(songuri, projection, null,null, null);

        if(song != null && song.moveToFirst()){

            do{
                String currTitle = song.getString(1);
                String path = song.getString(0);
                mDaftarLagu.add(currTitle);
                mPathLagu.add(path);
                Log.e("tag", mPathLagu.getLast());
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