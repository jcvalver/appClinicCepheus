package com.cibertec.cliniccepheusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cibertec.cliniccepheusapp.database.AppDatabase;
import com.cibertec.cliniccepheusapp.database.UserAppDAO;
import com.cibertec.cliniccepheusapp.model.UserApp;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private UserApp userApp;

    private EditText txtFullName, txtEmail, txtDocumentDni, txtPasswordRegister;
    private TextView txtsignIn;
    private Button btnRegister;

    private long userApp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* ToolBar Registration */
        setToolbar();
        inicializarControls();

       /* userApp = getIntent().getParcelableExtra("userapp");

        if (userApp != null) {
            contacto_id=userApp.getId();
            edtNombre.setText(userApp.getNombre());
            edtTelefono.setText(contacto.getTelefono());
            edtEmail.setText(contacto.getEmail());
            edtDireccion.setText(contacto.getDireccion());
        }*/

    }

    private void inicializarControls() {
        txtFullName=findViewById(R.id.txtFullName);
        txtDocumentDni=findViewById(R.id.txtDocumentDni);
        txtEmail=findViewById(R.id.txtEmail);
        txtPasswordRegister=findViewById(R.id.txtPasswordRegister);
        txtsignIn= findViewById(R.id.txtsignIn);
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
                finish();
                break;
            case R.id.btnRegister:
                guardar();
            default:
                break;
        }
        finish();

    }


    private void guardar() {
        UserAppDAO contactoDao = AppDatabase.getInstance(getApplicationContext()).userAppDao();
        if (userApp == null) {
            userApp = new UserApp();
            userApp.setFullName(txtFullName.getText().toString());
            userApp.setEmail(txtEmail.getText().toString());
            userApp.setDocumentDni(txtDocumentDni.getText().toString());
            userApp.setPassword(txtPasswordRegister.getText().toString());
            contactoDao.insertar(userApp);
        }else {
            userApp.setId(userApp_id);
            userApp.setFullName(txtFullName.getText().toString());
            userApp.setEmail(txtEmail.getText().toString());
            userApp.setDocumentDni(txtDocumentDni.getText().toString());
            userApp.setPassword(txtPasswordRegister.getText().toString());
            contactoDao.actualizar(userApp);
        }
    }
}
