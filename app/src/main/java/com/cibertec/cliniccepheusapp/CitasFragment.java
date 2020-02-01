package com.cibertec.cliniccepheusapp;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cibertec.cliniccepheusapp.model.User;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CitasFragment extends Fragment {

    private static final String TAG="CitasFragment";
    private static final String TITLE_KEY = "title";
    private static final String USER_KEY = "userlogin";
    private static String titleFragment="";
    private static String userLoginFragment="";


    FragmentInteractionListener mListener;


    public CitasFragment() {
        // Required empty public constructor
    }

    public static CitasFragment newInstance(String title, String userLogin){

        CitasFragment fragment=new CitasFragment();

        Bundle arguments=new Bundle();
        userLoginFragment=userLogin.split(" ")[0].toLowerCase();
        arguments.putString(TITLE_KEY,title);
        arguments.putString(USER_KEY,userLoginFragment);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // page = getArguments().getInt("someInt", 0);
       // title = getArguments().getString("someTitle");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView=inflater.inflate(R.layout.fragment_citas, container, false);
        titleFragment="Clínica Cepheus>"+userLoginFragment;
        Log.i(TAG,"title login final fragment:"+titleFragment);
        mListener.setToolBarTitle(titleFragment);

        //Attach the SectionsPagerAdapter1 to the ViewPager
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        ViewPager pager = rootView.findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to the TabLayout
        TabLayout tabLayout =  rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);


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

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ReserveCitasFragment();
                case 1:
                    return new DoctorFragment();
                case 2:
                    return new Resultragment();
                case 3:
                    return new PerfilFragment();

            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.reserve_citas_medicas_tab);//HOME
                case 1:
                    return getResources().getText(R.string.search_doctor_tab);//PIZZAS
                case 2:
                    return getResources().getText(R.string.results_tab);//PASTAS
                case 3:
                    return getResources().getText(R.string.perfil_tab);//STORES
            }
            return null;
        }
    }





}
