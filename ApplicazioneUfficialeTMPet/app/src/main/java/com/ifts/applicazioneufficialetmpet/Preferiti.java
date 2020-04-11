package com.ifts.applicazioneufficialetmpet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Preferiti extends Fragment {
    //private FragmentActivity myContext;
    private BottomNavigationView topNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferiti, container, false);

       // topNavigationView = container.findViewById(R.id.nav_view);
       // topNavigationView.setSelectedItemId(R.id.navigation_annunci_preferiti);

       /* if (savedInstanceState == null) {
            myContext.getSupportFragmentManager().beginTransaction().replace(R.id.container, new Swap()).commit();
        }*/

//        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Fragment fragment = null;
//                switch (menuItem.getItemId()) {
//                    case R.id.navigation_annunci_preferiti:
//                        fragment = new Preferiti();
//                        break;
//                    case R.id.navigation_annunci_accettati:
//                        fragment = new Accettati();
//                        break;
//                }
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//                return true;
//            }
//        });
        // Inflate the layout for this fragment
        return view;
    }
}
