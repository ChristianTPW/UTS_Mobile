package umn.ac.id;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private String uname;
    private String pwd;
    private String unameInfo = "uasmobile";
    private String pwdInfo = "uasmobilegenap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = username.getText().toString();
                pwd = password.getText().toString();

                if (uname.equals(unameInfo) && pwd.equals(pwdInfo)){
                    Intent intentProfile = new Intent(LoginActivity.this, LaguActivity.class);
                    startActivityForResult(intentProfile, 1);
                }else{
                    Toast.makeText(v.getContext(), "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}