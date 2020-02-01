package com.cibertec.cliniccepheusapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements FragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener{

    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    private static final String TAG="MainActivity";
    private MenuItem mitemCloseSession;
    private Menu menuToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        /// esto se realiza para que se pueda ver el estilo tipo hamburguesa
        toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuToolbar=navigationView.getMenu();



        Fragment fragment = LoginFragment.newInstance(getTitle().toString());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, fragment);
        ft.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch (id) {
           /* case R.id.nav_drafts:
                fragment = new DraftsFragment();
                break;*/
            case R.id.action_login_toolbar_map:
                fragment=MapFragment.newInstance(getTitle().toString());
                break;
            case R.id.action_login_toolbar_benefits:
                fragment=BenefitsFragment.newInstance(getTitle().toString());
                break;
           /*case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                break;*/
            case R.id.action_login_toolbar_closeSession:
                setOutSession(false);
                fragment=LoginFragment.newInstance(getTitle().toString());

                break;
            default:
                Log.i(TAG,"title MainActivity:"+getTitle().toString());
                fragment=LoginFragment.newInstance(getTitle().toString());
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);// cerrar el drawer
        return true;

    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();// es necesario para renderizar el toogle tipo hamburguesa
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void setToolBarTitle(String title) {
        toolbar.setTitle(title);

    }

   @Override
   public void setOutSession(boolean state) {
        if(state){
            mitemCloseSession=menuToolbar.findItem(R.id.action_login_toolbar_closeSession);
            if(mitemCloseSession!=null)mitemCloseSession.setVisible(true);
        }else
        {
            mitemCloseSession=menuToolbar.findItem(R.id.action_login_toolbar_closeSession);
            if(mitemCloseSession!=null)mitemCloseSession.setVisible(false);
            toolbar.setTitle(getTitle());
        }
    }


}
