package com.cibertec.cliniccepheusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.cliniccepheusapp.database.AppDatabase;
import com.cibertec.cliniccepheusapp.database.UserDAO;
import com.cibertec.cliniccepheusapp.model.User;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG="RegistrationFragment";

    private User userApp;

    private EditText txtFullName, txtEmail, txtDocumentDni, txtPasswordRegister;
    private TextView txtsignIn;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* ToolBar Registration */
        setToolbar();
        inicializarControls();

    }

    private void inicializarControls() {
        txtFullName=findViewById(R.id.txtFullName);
        txtDocumentDni=findViewById(R.id.txtDocumentDni);
        txtEmail=findViewById(R.id.txtEmail);
        txtPasswordRegister=findViewById(R.id.txtPasswordRegister);
        btnRegister=findViewById(R.id.btnRegister);
        txtsignIn= findViewById(R.id.txtsignIn);
        btnRegister.setOnClickListener(this);
        txtsignIn.setOnClickListener(this);
    }

    private void setToolbar() {
        Toolbar toolbar=findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Proceso de Registro");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_registration,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txtsignIn:
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

                break;
            case R.id.btnRegister:
                guardar();
                Toast.makeText(getApplicationContext(),"Usuario Registrado correctamente", Toast.LENGTH_LONG).show();
            default:
                break;
        }
        finish();
    }


    private void guardar() {
        UserDAO userAppDAO = AppDatabase.getInstance(getApplicationContext()).userDao();
        if (userApp == null) {
            Log.i(TAG, "Usuario registrado..........");
            userApp = new User();
            userApp.setFullName(txtFullName.getText().toString());
            userApp.setEmail(txtEmail.getText().toString());
            userApp.setDocumentDni(txtDocumentDni.getText().toString());
            userApp.setPassword(txtPasswordRegister.getText().toString());
            userAppDAO.insertar(userApp);

        }
    }
}
