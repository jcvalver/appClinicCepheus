package com.cibertec.cliniccepheusapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.cibertec.cliniccepheusapp.database.AppDatabase;
import com.cibertec.cliniccepheusapp.database.ReserveCitaDAO;
import com.cibertec.cliniccepheusapp.model.Doctor;
import com.cibertec.cliniccepheusapp.model.Especialidad;
import com.cibertec.cliniccepheusapp.model.Horario;
import com.cibertec.cliniccepheusapp.model.ReserveCita;
import com.cibertec.cliniccepheusapp.model.Sede;

import java.util.ArrayList;
import java.util.Calendar;

public class ReserveCitaActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG="ReserveCitaActivity";

    private ReserveCita reserveCita;

    private Spinner spEspecialidad,spSede,spDoctor,spHorario;

    private EditText txtPaciente,txtFechaReserve;

    private long reservecita_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_cita);

        /* ToolBar Registration */
        setToolbar();

        txtPaciente=findViewById(R.id.txtPaciente);

        txtFechaReserve=findViewById(R.id.txtFechaReserve);

        spEspecialidad=findViewById(R.id.spEspecialidad);

        spSede=findViewById(R.id.spSede);

        spDoctor=findViewById(R.id.spDoctor);

        spHorario=findViewById(R.id.spHorario);

        llenarListaEspecialidad();

        reserveCita = getIntent().getParcelableExtra("reservecita");



        spEspecialidad.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Especialidad espSeleccionado=(Especialidad)spEspecialidad.getSelectedItem();
                        String codigoEspecialidad=espSeleccionado.getCodigoEspecialidad();
                        llenarListaSede(codigoEspecialidad);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        spSede.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Sede spSeleccionado=(Sede)spSede.getSelectedItem();
                        String codigoSede=spSeleccionado.getCodigoSede();
                        llenarListaDoctor(codigoSede);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        spDoctor.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Doctor spSeleccionado=(Doctor)spDoctor.getSelectedItem();
                        String codigoDoctor=spSeleccionado.getCodigoDoctor();
                        llenarListaHorario(codigoDoctor);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        txtFechaReserve.setOnClickListener(this);


        if (reserveCita != null) {
            reservecita_id = reserveCita.getId();
            txtPaciente.setText(reserveCita.getPaciente());
            txtFechaReserve.setText(reserveCita.getFecha());
            int positionEspecialidad=getPosicionEnSpinnerByCodigoCampo(spEspecialidad,reserveCita.getCodigoEspecialidad());
            spEspecialidad.setSelection(positionEspecialidad);
        }
    }

    private void setToolbar() {
        Toolbar toolbar=findViewById(R.id.reservecitatoolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Proceso de Reserva");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reserve_cita, menu);
        if (reserveCita == null) {
            menu.getItem(1).setVisible(false);
        }else{
            menu.getItem(0).setTitle("Modificar");
        }
        return super.onCreateOptionsMenu(menu);

    }


    private void llenarListaEspecialidad() {

        String codigoEspecialidadDefault="0000";
        ArrayList<Especialidad> listEspecialidad=new ArrayList<>();

        Especialidad especialidad0=new Especialidad("0000","<<SELECCIONE>>");
        listEspecialidad.add(especialidad0);
        Especialidad especialidad=new Especialidad("0001","CARDIOLOGÍA");
        listEspecialidad.add(especialidad);
        Especialidad especialidad1=new Especialidad("0002","PATOLIGIA");
        listEspecialidad.add(especialidad1);
        Especialidad especialidad2=new Especialidad("0003","NEUMOLOGIA");
        listEspecialidad.add(especialidad2);
        Especialidad especialidad3=new Especialidad("0004","UROLOGIA");
        listEspecialidad.add(especialidad3);
        Especialidad especialidad4=new Especialidad("0005","PODOLOGIA");
        listEspecialidad.add(especialidad4);
        Especialidad especialidad5=new Especialidad("0006","ONCOLOGIA");
        listEspecialidad.add(especialidad5);
        Especialidad especialidad6=new Especialidad("0007","DERMATOLOGIA");
        listEspecialidad.add(especialidad6);
        Especialidad especialidad7=new Especialidad("0008","ALERGOLOGIA");
        listEspecialidad.add(especialidad7);
        Especialidad especialidad8=new Especialidad("0009","GASTROENTEROLOGIA");
        listEspecialidad.add(especialidad8);
        Especialidad especialidad9=new Especialidad("0010","GENETICA");
        listEspecialidad.add(especialidad9);
        Especialidad especialidad10=new Especialidad("0011","GINECOLOGIA");
        listEspecialidad.add(especialidad10);



        ArrayAdapter<Especialidad> arrayAdapter=new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item,listEspecialidad);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spEspecialidad.setAdapter(arrayAdapter);

        int positionDepartmentDefault=getPosicionEnSpinnerByCodigoCampo(spEspecialidad,codigoEspecialidadDefault);

        spEspecialidad.setSelection(positionDepartmentDefault);


    }

    private void llenarListaSede(String codigoEspecialidad){
        String codigoSedeDefault="0000";
        if(reserveCita!=null){
            codigoSedeDefault=reserveCita.getCodigoSede();
        }

        ArrayAdapter<Sede> arrayAdapter=new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item,listarSede(codigoEspecialidad));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spSede.setAdapter(arrayAdapter);

        int positionProvinceDefault=getPosicionEnSpinnerByCodigoCampo(spSede,codigoSedeDefault);

        spSede.setSelection(positionProvinceDefault);
    }

    private ArrayList<Sede> listarSede(String codigoEspecialidad){
        ArrayList<Sede> listSede=new ArrayList<>();
        Sede sede;
        switch (codigoEspecialidad){
            case "0001":
                sede=new Sede("0001","LIMA");
                listSede.add(sede);
                sede=new Sede("0002","SAN BORJA");
                listSede.add(sede);
                sede=new Sede("0003","SAN ISIDRO");
                listSede.add(sede);
                sede=new Sede("0004","JESU MARIA");
                listSede.add(sede);
                break;
            case "0002":
                sede=new Sede("0005","LIMA");
                listSede.add(sede);
                sede=new Sede("0006","SAN BORJA");
                listSede.add(sede);
                sede=new Sede("0007","SAN ISIDRO");
                listSede.add(sede);
                sede=new Sede("0008","MIRAFLORES");
                listSede.add(sede);
                break;
            case "0003":
                sede=new Sede("0009","LIMA");
                listSede.add(sede);
                sede=new Sede("0010","SAN BORJA");
                listSede.add(sede);
                sede=new Sede("0011","SAN ISIDRO");
                listSede.add(sede);
                sede=new Sede("0012","MAGDALENA DEL MAR");
                listSede.add(sede);
                break;
            case "0004":case "0005":case "0006":
                sede=new Sede("0013","LIMA");
                listSede.add(sede);
                sede=new Sede("0014","SAN BORJA");
                listSede.add(sede);
                sede=new Sede("0015","SAN ISIDRO");
                listSede.add(sede);
                sede=new Sede("0016","TRUJILLO");
                listSede.add(sede);
                break;
            case "0007":
                sede=new Sede("0017","LIMA");
                listSede.add(sede);
                sede=new Sede("0018","SAN BORJA");
                listSede.add(sede);
                sede=new Sede("0019","SAN ISIDRO");
                listSede.add(sede);
                sede=new Sede("0020","CHIMBOTE");
                listSede.add(sede);
                break;
            case "0008": case "0009": case "0010":
                sede=new Sede("0021","LIMA");
                listSede.add(sede);
                sede=new Sede("0022","SAN BORJA");
                listSede.add(sede);
                sede=new Sede("0023","SAN ISIDRO");
                listSede.add(sede);
                sede=new Sede("0024","PIURA");
                listSede.add(sede);
                break;
            default:
                sede=new Sede("0000","<<SELECCIONE>>");
                listSede.add(sede);
                break;
        }

        return listSede;
    }

    private void llenarListaDoctor(String codigoSede){
        String codigoDoctorDefault="0000";

        if(reserveCita!=null){
            codigoDoctorDefault=reserveCita.getCodigoDoctor();
        }

        ArrayAdapter<Doctor> arrayAdapter=new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item,listarDoctor(codigoSede));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spDoctor.setAdapter(arrayAdapter);

        int positionDoctorDefault=getPosicionEnSpinnerByCodigoCampo(spDoctor,codigoDoctorDefault);

        spDoctor.setSelection(positionDoctorDefault);
    }

    private ArrayList<Doctor> listarDoctor(String codigoSede){
        ArrayList<Doctor> listDoctor=new ArrayList<>();
        Doctor doctor;
        switch (codigoSede){
            case "0001": case "0005": case "0009": case "0013": case "0017": case "0021":
                doctor=new Doctor("0001","JOSE PEREZ LUNA");
                listDoctor.add(doctor);
                doctor=new Doctor("0002","KAREN VALVERDE PINILLOS");
                listDoctor.add(doctor);
                doctor=new Doctor("0003","NORMAN SIFUENTES PEREZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0004","JUAN BENITEZ LEYVA");
                listDoctor.add(doctor);
                doctor=new Doctor("0005","SUSANA MEDINA CASTILLO");
                listDoctor.add(doctor);
                doctor=new Doctor("0006","MARIANELLY BEDOLLA SIFUENTES");
                listDoctor.add(doctor);
                doctor=new Doctor("0007","PEDRO SARMIENTO DOMINGUEZ");
                listDoctor.add(doctor);
                break;
            case "0002":case "0006":case "0010":case "0014":case "0018":case "0022":
                doctor=new Doctor("0008","RONALD PAZ SALAZAR");
                listDoctor.add(doctor);
                doctor=new Doctor("0009","CARMEN CHANG LINARES");
                listDoctor.add(doctor);
                doctor=new Doctor("0010","MELISSA LOZANO HERRERA");
                listDoctor.add(doctor);
                doctor=new Doctor("0011","SANDRA SAAVEDRA CASTILLO");
                listDoctor.add(doctor);
                doctor=new Doctor("0012","SILVIA MEJIA NUÑEZ");
                listDoctor.add(doctor);
                break;
            case "0003":case "0007":case "0011":case "0015":case "0019":case "0023":
                doctor=new Doctor("0013","JUAN ESPEJO MEDRANO");
                listDoctor.add(doctor);
                doctor=new Doctor("0014","DAVID LEYVA BENITES");
                listDoctor.add(doctor);
                doctor=new Doctor("0015","LUCIA FLORES MEDINA");
                listDoctor.add(doctor);
                doctor=new Doctor("0016","JULIO CASTAÑEDA MORALES");
                listDoctor.add(doctor);
                break;
            case "0004":
                doctor=new Doctor("0017","RONALD CASTRO RAMIREZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0018","RAUL PEREZ GARCIA");
                listDoctor.add(doctor);
                doctor=new Doctor("0019","JUANA JIMENEZ MEJIA");
                listDoctor.add(doctor);
                doctor=new Doctor("0020","LILIANA QUISPE AREDO");
                listDoctor.add(doctor);
                break;
            case "0008":
                doctor=new Doctor("0021","RONALD CASTRO RAMIREZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0022","RAUL PEREZ GARCIA");
                listDoctor.add(doctor);
                doctor=new Doctor("0023","JUANA JIMENEZ MEJIA");
                listDoctor.add(doctor);
                break;
            case "0012":
                doctor=new Doctor("0024","MANUEL VELARDE PEREZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0025","JUAN QUISPE GUEVARA");
                listDoctor.add(doctor);
                doctor=new Doctor("0026","CINTYA GUZMAN SALAZAR");
                listDoctor.add(doctor);
                break;
            case "0016":
                doctor=new Doctor("0027","LORENA IPARRAGUIRRE DE LA CRUZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0028","PAOLO OSPINA VASQUEZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0029","JORGE MENDIETA AVALOZ");
                listDoctor.add(doctor);
                break;
            case "0020":
                doctor=new Doctor("0030","PAMELA BENITEZ LEYVA");
                listDoctor.add(doctor);
                doctor=new Doctor("0031","FRANCISCO PARDO SARMIENTO");
                listDoctor.add(doctor);
                break;
            case "0024":
                doctor=new Doctor("0032","GABRIEL REYES PEREZ");
                listDoctor.add(doctor);
                doctor=new Doctor("0033","CARLOS WAGNER MEJIA");
                listDoctor.add(doctor);
                break;
            default:
                doctor=new Doctor("0000","<<SELECCIONE>>");
                listDoctor.add(doctor);
                break;
        }

        return listDoctor;
    }

    private void llenarListaHorario(String codigoDoctor){
        String codigoHorarioDefault="0000";

        if(reserveCita!=null){
            codigoHorarioDefault=reserveCita.getCodigoHorario();
        }

        ArrayAdapter<Horario> arrayAdapter=new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item,listarHorario(codigoDoctor));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spHorario.setAdapter(arrayAdapter);

        int positionHorarioDefault=getPosicionEnSpinnerByCodigoCampo(spHorario,codigoHorarioDefault);

        spHorario.setSelection(positionHorarioDefault);
    }


    private ArrayList<Horario> listarHorario(String codigoHorario){
        ArrayList<Horario> listHorario=new ArrayList<>();
        Horario horario;
        switch (codigoHorario){
            case "0001": case "0008": case "0015": case "0022": case "0029": case "0033":
                horario=new Horario("0001","8am-9am");
                listHorario.add(horario);
                horario=new Horario("0002","9am-10am");
                listHorario.add(horario);
                horario=new Horario("0003","10am-11am");
                listHorario.add(horario);
                break;
            case "0002":case "0009":case "0016":case "0023":case "0030":case "0032":
                horario=new Horario("0004","11am-12pm");
                listHorario.add(horario);
                horario=new Horario("0005","12pm-1pm");
                listHorario.add(horario);
                horario=new Horario("0006","1pm-2pm");
                listHorario.add(horario);
                break;
            case "0003":case "0010":case "0017":case "0024":case "0031":case "0028":
                horario=new Horario("0007","2pm-3pm");
                listHorario.add(horario);
                horario=new Horario("0008","3pm-4pm");
                listHorario.add(horario);
                horario=new Horario("0009","4pm-5pm");
                listHorario.add(horario);
                horario=new Horario("0010","5pm-6pm");
                listHorario.add(horario);
                break;
            case "0004":case "0011":case "0018":case "0025":case "0026":case "0027":
                horario=new Horario("0011","6pm-7pm");
                listHorario.add(horario);
                horario=new Horario("0012","7pm-8pm");
                listHorario.add(horario);
                horario=new Horario("0013","8pm-9pm");
                listHorario.add(horario);
                break;
            case "0005":  case "0012":  case "0019":
                horario=new Horario("0014","9pm-10pm");
                listHorario.add(horario);
                horario=new Horario("0015","10pm-11pm");
                listHorario.add(horario);
                horario=new Horario("0016","11pm-12am");
                listHorario.add(horario);
                break;
            case "0006": case "0013": case "0020":
                horario=new Horario("0017","10am-11am");
                listHorario.add(horario);
                horario=new Horario("0018","2pm-3pm");
                listHorario.add(horario);
                horario=new Horario("0019","6pm-7pm");
                listHorario.add(horario);
                break;
            case "0007":case "0014":
                horario=new Horario("0020","1pm-2pm");
                listHorario.add(horario);
                horario=new Horario("0021","5pm-6pm");
                listHorario.add(horario);
                horario=new Horario("0022","9pm-10pm");
                listHorario.add(horario);
                break;
            case "0021":
                horario=new Horario("0023","8am-9am");
                listHorario.add(horario);
                horario=new Horario("0024","9am-10am");
                listHorario.add(horario);
                break;
            default:
                horario=new Horario("0000","<<SELECCIONE>>");
                listHorario.add(horario);
                break;
        }

        return listHorario;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guardar:
                guardar();
                break;
            case R.id.action_eliminar:
                eliminar();
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void guardar() {
        ReserveCitaDAO reserveCitaDAO = AppDatabase.getInstance(getApplicationContext()).reserveCitaDAO();
        if (reserveCita == null) {
            reserveCita = new ReserveCita();
            reserveCita.setPaciente(txtPaciente.getText().toString());
            Log.i(TAG,"especialidad:"+spEspecialidad.getSelectedItem().toString());
            reserveCita.setCodigoEspecialidad(((Especialidad)(spEspecialidad.getSelectedItem())).getCodigoEspecialidad());
            reserveCita.setCodigoSede(((Sede)(spSede.getSelectedItem())).getCodigoSede());
            reserveCita.setCodigoDoctor(((Doctor)(spDoctor.getSelectedItem())).getCodigoDoctor());
            reserveCita.setCodigoHorario(((Horario)(spHorario.getSelectedItem())).getCodigoHorario());
            reserveCita.setFecha(txtFechaReserve.getText().toString());
            reserveCitaDAO.insertar(reserveCita);
        }else {
            reserveCita.setId(reservecita_id);
            reserveCita.setPaciente(txtPaciente.getText().toString());
            reserveCita.setCodigoEspecialidad(((Especialidad)(spEspecialidad.getSelectedItem())).getCodigoEspecialidad());
            reserveCita.setCodigoSede(((Sede)(spSede.getSelectedItem())).getCodigoSede());
            reserveCita.setCodigoDoctor(((Doctor)(spDoctor.getSelectedItem())).getCodigoDoctor());
            reserveCita.setCodigoHorario(((Horario)(spHorario.getSelectedItem())).getCodigoHorario());
            reserveCita.setFecha(txtFechaReserve.getText().toString());
            reserveCitaDAO.actualizar(reserveCita);
        }

    }

    public int getPosicionEnSpinnerByCodigoCampo(Spinner spinner, String codigoSpinner){

        int i=0 ;
        int posicion=-1 ;
        boolean find=false;

        while (i<spinner.getCount() && find==false){

            Object objectSpinner=spinner.getItemAtPosition(i);

            if(objectSpinner instanceof Especialidad){
                Especialidad department=(Especialidad) objectSpinner;
                //Log.i(TAG, "getPosicionEnSpinnerByCodigoCampo: "  + tabla.getCodigoTabla());
                if (codigoSpinner.equals(department.getCodigoEspecialidad())){
                    // Log.i("TAG", "POSITION: "  + tabla.getCodigoTabla());
                    posicion=i;
                    find=true;
                }
            }

            if(objectSpinner instanceof Sede){
                Sede sede=(Sede) objectSpinner;
                //Log.i(TAG, "getPosicionEnSpinnerByCodigoCampo: "  + tabla.getCodigoTabla());
                if (codigoSpinner.equals(sede.getCodigoSede())){
                    // Log.i("TAG", "POSITION: "  + tabla.getCodigoTabla());
                    posicion=i;
                    find=true;
                }

            }

            if(objectSpinner instanceof  Doctor){
                Doctor doctor=(Doctor) objectSpinner;
                //Log.i(TAG, "getPosicionEnSpinnerByCodigoCampo: "  + tabla.getCodigoTabla());
                if (codigoSpinner.equals(doctor.getCodigoDoctor())){
                    // Log.i("TAG", "POSITION: "  + tabla.getCodigoTabla());
                    posicion=i;
                    find=true;
                }

            }

            if(objectSpinner instanceof  Horario){
                Horario horario=(Horario) objectSpinner;
                //Log.i(TAG, "getPosicionEnSpinnerByCodigoCampo: "  + tabla.getCodigoTabla());
                if (codigoSpinner.equals(horario.getCodigoHorario())){
                    // Log.i("TAG", "POSITION: "  + tabla.getCodigoTabla());
                    posicion=i;
                    find=true;
                }

            }

            i++;
        }
        return posicion;
    }

    private void eliminar() {
        if (reserveCita != null) {
            ReserveCitaDAO reserveCitaDAO = AppDatabase.getInstance(getApplicationContext()).reserveCitaDAO();
            reserveCitaDAO.eliminar(reserveCita);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txtFechaReserve:
                final Calendar c=Calendar.getInstance();
                int dia=c.get(Calendar.DAY_OF_MONTH);
                int mes=c.get(Calendar.MONTH);
                int anio=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtFechaReserve.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },dia,mes,anio);
                datePickerDialog.show();
                break;
        }

    }
}
