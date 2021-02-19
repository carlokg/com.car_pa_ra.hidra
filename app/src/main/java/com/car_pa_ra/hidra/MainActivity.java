package com.car_pa_ra.hidra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
/**
 * @author Raúl
 * @author Pablo
 * @author Carlos
 *
 * Clase MainActiviti contendrá la barra de navegación y sobre este activity se cargarán
 * Todos los fragmentos de la aplicación, de tal forma que la barra estará presente en
 * todos los Fragmentos.
 * @see MainActivity
 * @see AboutFragment
 * @see AjustesFragment
 *

 */
public class MainActivity extends AppCompatActivity
        implements OnControlerFragmentListener{

    FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        fba = FirebaseAuth.getInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExploraFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.explora:

                    selectedFragment = new ExploraFragment();
                    break;
                case R.id.social:
                    ///selectedFragment = new CourseFragment();
                    break;
                case R.id.perfil:
                    //selectedFragment = new ProfileFragment();
                    break;
                case R.id.ayuda:
                    //selectedFragment = new ProfileFragment();
                    break;
                case R.id.config:
                    selectedFragment = new AjustesFragment();
                    break;
            }


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .addToBackStack(null)
                    .commit();



            return true;
        }
    };


    @Override
    public void selectFrgment(String texto) {

        Fragment selectedFragment = null;

        switch (texto){
            case "about":
                selectedFragment = new AboutFragment();
                break;
            case "logout":
                fba.signOut();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                finish();
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .addToBackStack(null)
                .commit();
    }
}