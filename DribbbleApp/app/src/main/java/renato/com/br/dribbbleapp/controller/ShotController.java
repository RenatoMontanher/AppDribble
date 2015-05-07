package renato.com.br.dribbbleapp.controller;

import android.util.Log;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.List;

import renato.com.br.dribbbleapp.Inicio;
import renato.com.br.dribbbleapp.model.ModeloPagina;
import renato.com.br.dribbbleapp.model.ModeloShot;
import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Renato on 16/10/2014.
 */

@EBean
public class ShotController implements ErrorHandler {
    private static final String TAG = ShotController.class.getSimpleName();

    @RootContext
    Inicio activity;

    private ConsultaShotApi api;

    @AfterInject
    void afterInject() {

        api = new RestAdapter.Builder()
                .setConverter(new GsonConverter(new Gson()))
                .setClient(new OkClient())
                .setEndpoint("http://api.dribbble.com/shots")
                .setErrorHandler(this)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build().create(ConsultaShotApi.class);
    }

    @Override
    public Throwable handleError(RetrofitError cause) {

        if(cause.getKind() == RetrofitError.Kind.NETWORK)
            activity.trataErrosDeConexao();

        else if (cause.getKind() == RetrofitError.Kind.HTTP)
            activity.trataErrosDeHTTP(cause.getResponse().getStatus());

        return cause;
    }

    @Background
    public void buscaShot(int id) {

        try {
            final ModeloPagina modeloPagina = api.buscaShots(id);
            atualiza(modeloPagina.getShots());
        } catch (Exception e){
            Log.e(TAG, "Erro ao procurar Shot", e);
        }
    }
    @UiThread
    void atualiza(List<ModeloShot> ListamodeloShot) {
        activity.atualizaTelaComResultado(ListamodeloShot);
    }



    public static interface ConsultaShotApi {

        @GET("/{id}")
        ModeloShot buscaShot(@Path("id") int id);

        @GET("/popular")
        ModeloPagina buscaShots(@Query("page") int pagina);
    }
}
