package com.cibertec.cliniccepheusapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.cliniccepheusapp.model.UserApp;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements  View.OnClickListener{

    private UserApp userApp;

    private EditText txtUserName, txtPassword;
    private TextView txtRegister;
    private Button btnLogin;

    private static final String TITLE_KEY = "title";
    private static final String ERROR_USERNAME_KEY="errorUser",ERROR_PASSWORD_KEY="errorPassword";
    private static HashMap<String,String> messageError=new HashMap<>();

    FragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String title){

        LoginFragment fragment=new LoginFragment();

        Bundle arguments=new Bundle();
        arguments.putString(TITLE_KEY,title);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_login, container, false);
        inicializarControles(rootView);
        return rootView;
    }

    private void inicializarControles(View rootView) {
        txtUserName=rootView.findViewById(R.id.txtUserName);
        txtPassword=rootView.findViewById(R.id.txtPassword);
        txtRegister=rootView.findViewById(R.id.txtRegister);

        btnLogin=rootView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.txtRegister:
                Intent intent=new Intent(getActivity(),RegistrationActivity.class);
                startActivity(intent);
             //   Toast.makeText(getActivity(), "seleccion txtRegistrar",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogin:
                checkLogin();
                break;

        }

    }

    private void checkLogin() {
        final String userName = txtUserName.getText().toString();
        final String pass = txtPassword.getText().toString();
        String userNameBD,passBD;
        userApp = getActivity().getIntent().getParcelableExtra("userapp");

        if (userApp != null) {
            userNameBD=userApp.getDocumentDni();
            passBD=userApp.getPassword();
            if (!isValidUserName(userName,userNameBD)) {
                txtUserName.setError("Documento Inválido");
            }

            if (!isValidPassword(pass,passBD)) {
                txtPassword.setError("Clave debe ser mayo a 6 caracteres");
            }

            if(isValidUserName(userName,userNameBD) && isValidPassword(pass,passBD))
            {
                //Bundle datosAEnviar = new Bundle();
                // Aquí pon todos los datos que quieras en formato clave, valor
                //datosAEnviar.putLong("id", 123L);
                Fragment fragmento = new CitasFragment();
                //fragmento.setArguments("datosAEnviar");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragmento);
                fragmentTransaction.addToBackStack(null);

                // Terminar transición y nos vemos en el fragmento de destino
                fragmentTransaction.commit();
            }
        }


    }

    private boolean isValidUserName(String userName,String userNameBD) {

        if(userName==null){
            messageError.put(ERROR_USERNAME_KEY,"Usuario Inválido");
            return false;
        }
        if(!userName.equalsIgnoreCase(userNameBD)){
            messageError.put(ERROR_USERNAME_KEY,"Usuario No Existe");
            return false;
        }

        if (userName != null && userName.equalsIgnoreCase(userNameBD)) {
            messageError.put(ERROR_USERNAME_KEY,"");
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String pass, String passBD) {

        if(pass==null){
            messageError.put(ERROR_PASSWORD_KEY,"Password Inválido");
            return false;
        }

        if(pass.length() < 6){
            messageError.put(ERROR_PASSWORD_KEY,"Password debe ser mayor a 6 caracteres");
            return false;
        }

        if(!pass.equalsIgnoreCase(passBD)){
            messageError.put(ERROR_PASSWORD_KEY,"Password No Existe");
            return false;
        }
        if (pass != null && pass.equalsIgnoreCase(passBD) ) {
            messageError.put(ERROR_PASSWORD_KEY,"");
            return true;
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }
}
