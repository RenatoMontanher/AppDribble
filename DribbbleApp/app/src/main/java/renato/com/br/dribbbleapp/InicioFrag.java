package renato.com.br.dribbbleapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import renato.com.br.dribbbleapp.Fragments.ShotListFragment;

/**
 * Created by Renato on 11/05/2015.
 */
public class InicioFrag extends Activity {
    private boolean tablet = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shot);
        View view = findViewById(R.id.fragment_unico);

        if (view != null){
            ShotListFragment fragment = new ShotListFragment();
            fragment.setArguments(savedInstanceState);

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_unico,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
}
