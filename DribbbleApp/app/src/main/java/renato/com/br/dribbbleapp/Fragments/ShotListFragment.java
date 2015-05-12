package renato.com.br.dribbbleapp.Fragments;

import android.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Renato on 10/05/2015.
 */
public class ShotListFragment extends ListFragment implements AdapterView.OnItemClickListener{

    public void onStart() {
        super.onStart();
        //recuperar os shots do WS
        //colocar no adapter seta-lo na list
        //Setar o listener

        List<String> viagens = Arrays.asList("Shot 1", "Shot 2",
                "Shot 3");
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, viagens);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //chamar a proxima fragment de detalhes
    }
}
