package com.cibertec.cliniccepheusapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.cliniccepheusapp.database.AppDatabase;
import com.cibertec.cliniccepheusapp.database.UserDAO;
import com.cibertec.cliniccepheusapp.model.User;

import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements  View.OnClickListener{

    private User userLogin;

    private EditText txtUserName, txtPassword;
    private TextView txtRegister;
    private Button btnLogin;
    private static String titleFragment="";
    private static final String TAG="LoginFragment";
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
        titleFragment=title;
        Log.i(TAG,"title login 1 fragment:"+titleFragment);
        arguments.putString(TITLE_KEY,titleFragment);
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

            if (!isValidUserName(userName)|| !isValidPassword(pass)) {
                txtUserName.setError(messageError.get(ERROR_USERNAME_KEY));
                txtPassword.setError(messageError.get(ERROR_PASSWORD_KEY));
            }else{
                if(isValidateDB(userName,pass)){
                    //Bundle datosAEnviar = new Bundle();
                    // Aquí pon todos los datos que quieras en formato clave, valor
                    //datosAEnviar.putLong("id", 123L);
                    mListener.setOutSession(true);
                    Fragment citasFragment = CitasFragment.newInstance(titleFragment,userLogin.getFullName());
                    //fragmento.setArguments("datosAEnviar");
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, citasFragment);
                    fragmentTransaction.addToBackStack(null);
                    // Terminar transición y nos vemos en el fragmento de destino
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(getActivity(),"Usuario No Existe", Toast.LENGTH_LONG).show();
                }
            }
    }

    private boolean isValidateDB(String userName, String pass) {
        UserDAO userDAO = AppDatabase.getInstance(getContext()).userDao();
        User usuarioActual=userDAO.getxUser(userName,pass);
        if(usuarioActual!=null){
            userLogin=usuarioActual;
            return true;
        }else{
            return false;
        }
    }

    private boolean isValidUserName(String userName) {

        if(userName==null){
            messageError.put(ERROR_USERNAME_KEY,"Usuario Vacio");
            return false;
        }else {
            if (userName.length() != 8) {
                messageError.put(ERROR_USERNAME_KEY, "Usuario debe tener 8 digitos");
                return false;
            }
        }
        messageError.clear();
        return true;
    }

    private boolean isValidPassword(String pass) {

        if(pass==null){
            messageError.put(ERROR_PASSWORD_KEY,"Password Vacio");
            return false;
        }else{
            if(pass.length() < 6){
                messageError.put(ERROR_PASSWORD_KEY,"Password debe ser mayor a 6 digitos");
                return false;
            }
        }
        messageError.clear();
        return true;
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
