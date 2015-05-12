package renato.com.br.dribbbleapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import renato.com.br.dribbbleapp.R;

/**
 * Created by Renato on 10/05/2015.
 */
public class ShotDetailsFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detalhes, container, false);
    }
    public void onStart() {
        super.onStart();
        //recuperar os
    }
}
