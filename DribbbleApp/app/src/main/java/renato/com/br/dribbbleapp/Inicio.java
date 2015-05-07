package renato.com.br.dribbbleapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;


import java.util.ArrayList;
import java.util.List;

import renato.com.br.dribbbleapp.adapter.ListShotAdapter;
import renato.com.br.dribbbleapp.controller.EndlessScrollListener;
import renato.com.br.dribbbleapp.controller.ShotController;
import renato.com.br.dribbbleapp.dialog.InfoDialog_;
import renato.com.br.dribbbleapp.model.ModeloShot;

@EActivity(R.layout.activity_inicio)
public class Inicio extends Activity {

    @ViewById
    ListView listViewShots;

    @Bean
    ShotController controller;

    @StringRes
    String problemasNoRequest,erroNoServidor;


    @Bean
    ListShotAdapter adapter;

    int pagina = 0;

    private List<ModeloShot> listaDeShots = new ArrayList<>();

    @AfterViews
    void afterView() {
        listViewShots.setAdapter(adapter);
        listViewShots.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                pagina+=1;

                controller.buscaShot(pagina);
            }
        });
        listViewShots.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent it = new Intent(getApplicationContext(),Detalhes_.class);
                it.putExtra("url_imagem",getListaDeShots().get(position).getImage_url());
                it.putExtra("descricao",getListaDeShots().get(position).getDescription());
                it.putExtra("titulo",getListaDeShots().get(position).getTitle());
                it.putExtra("url_avatar",getListaDeShots().get(position).getPlayer().getAvatar_url());
                it.putExtra("nome",getListaDeShots().get(position).getPlayer().getName());
                it.putExtra("views_count",getListaDeShots().get(position).getViews_count());
                startActivity(it);
            }
        });
    }

    @AfterInject
    void afterInject() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void atualizaTelaComResultado(List<ModeloShot> shots) {
        listaDeShots.addAll(shots);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void trataErrosDeConexao() {

    }

    public void trataErrosDeHTTP(int status) {
        String mensagemId;

        if (status == 400)
            mensagemId = problemasNoRequest;
        else
            mensagemId = erroNoServidor;

        InfoDialog_.builder().infoMessage(mensagemId).build()
                .show(getFragmentManager(), "info_dialog");

    }


    public List<ModeloShot> getListaDeShots() {
        return listaDeShots;
    }
}
