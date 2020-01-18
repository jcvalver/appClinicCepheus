package com.cibertec.cliniccepheusapp;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private static final String titleFragment="Ubicación";
    private static final String TITLE_KEY = "title";

    FragmentInteractionListener mListener;


    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(String title){

        MapFragment fragment=new MapFragment();

        Bundle arguments=new Bundle();
        arguments.putString(TITLE_KEY,title);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_map, container, false);
        mListener.setToolBarTitle(titleFragment);
        return rootView;
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
