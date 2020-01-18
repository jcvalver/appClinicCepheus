package com.cibertec.cliniccepheusapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cibertec.cliniccepheusapp.model.Benefit;


/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitsFragment extends Fragment{

    private static final String titleFragment="Beneficios";
    private static final String TITLE_KEY = "title";

    FragmentInteractionListener mListener;

    public BenefitsFragment() {
        // Required empty public constructor
    }

    public static BenefitsFragment newInstance(String title){

        BenefitsFragment fragment=new BenefitsFragment();

        Bundle arguments=new Bundle();
        arguments.putString(TITLE_KEY,title);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView=inflater.inflate(R.layout.fragment_benefits, container, false);
        RecyclerView benefitsRecycler = (RecyclerView)rootView;

        mListener.setToolBarTitle(titleFragment);

        String[] pizzaNames = new String[Benefit.benefits.length];

        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Benefit.benefits[i].getName();
        }

        int[] pizzaImages = new int[Benefit.benefits.length];

        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = Benefit.benefits[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(getContext(),pizzaNames, pizzaImages);
        benefitsRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
      //  GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        benefitsRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
              //  Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
               // intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
               // getActivity().startActivity(intent);
            }
        });

        return benefitsRecycler;
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
