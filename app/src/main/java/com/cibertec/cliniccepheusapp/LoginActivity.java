package com.cibertec.cliniccepheusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity  implements  View.OnClickListener{

    private EditText txtUserName, txtPassword;
    private TextView txtRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* ToolBar Login */
        setToolbar();
        /* initialize */
        setVariables();
        setControls();


    }

    private void setVariables() {
    }

    private void setControls() {
        txtUserName=findViewById(R.id.txtUserName);
        txtPassword=findViewById(R.id.txtPassword);
        txtRegister=findViewById(R.id.txtRegister);

        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }


    private void setToolbar() {
        Toolbar toolbar=findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.txtRegister:
                Intent intent=new Intent(this,RegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnLogin:
                checkLogin();
                break;

        }


    }

    private void checkLogin() {
        final String userName = txtUserName.getText().toString();
        final String pass = txtPassword.getText().toString();

        if (!isValidUserName(userName)) {
            txtUserName.setError("Documento Inválido");
        }

        if (!isValidPassword(pass)) {
            txtPassword.setError("Clave debe ser mayo a 6 caracteres");
        }

        if(isValidUserName(userName) && isValidPassword(pass))
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

   /*
        validar correos
        private boolean isValidEmail(String userName) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    */


    private boolean isValidUserName(String userName) {
        if (userName != null && userName.length() == 8) {
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }
}
