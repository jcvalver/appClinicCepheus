package com.cibertec.cliniccepheusapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cibertec.cliniccepheusapp.database.AppDatabase;
import com.cibertec.cliniccepheusapp.database.ReserveCitaDAO;
import com.cibertec.cliniccepheusapp.model.ReserveCita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReserveCitasFragment extends Fragment {

    private static final String TAG="ReserveCitasFragment";

    private ArrayAdapter<ReserveCita> adapter;

    public ReserveCitasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_reserve_citas, container, false);


        ListView listView = rootView.findViewById(R.id.listReserveCitas);
        FloatingActionButton fab = rootView.findViewById(R.id.fab);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReserveCitaActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ReserveCitaActivity.class);
                intent.putExtra("reservecita", adapter.getItem(position));
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(getReserveCitas());
    }



    private List<ReserveCita> getReserveCitas() {
        ReserveCitaDAO reserveCitaDAO = AppDatabase.getInstance(getActivity().getApplicationContext()).reserveCitaDAO();
        return reserveCitaDAO.listar();
    }

}
