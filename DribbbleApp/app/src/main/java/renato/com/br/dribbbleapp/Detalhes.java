package renato.com.br.dribbbleapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import renato.com.br.dribbbleapp.util.CircleTransform;

/**
 * Created by Renato on 30/10/2014.
 */

@EActivity(R.layout.detalhes)
public class Detalhes extends Activity {

    @ViewById
    LinearLayout foto_shot;

    @ViewById
    LinearLayout info_shot;

    @ViewById
    LinearLayout descricao_shot;

    String urlImagem;
    String descricao;
    String titulo;
    private String urlAvatar;
    private String nome;
    private int views_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @AfterViews
    void afterView(){

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(getApplicationContext().WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;

        Transformation cropPosterTransformation = new Transformation() {

            @Override public Bitmap transform(Bitmap source) {
                int targetWidth = width;
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override public String key() {
                return "cropPosterTransformation" + width;
            }
        };


        Intent it = getIntent();

        urlImagem = it.getStringExtra("url_imagem");
        descricao = it.getStringExtra("descricao");
        titulo = it.getStringExtra("titulo");
        urlAvatar = it.getStringExtra("url_avatar");
        nome = it.getStringExtra("nome");
        views_count = it.getIntExtra("views_count",0);

        Picasso.with(getApplicationContext()).load(urlAvatar).placeholder(R.drawable.icone_user).transform(new CircleTransform()).into((ImageView)info_shot.findViewById(R.id.foto_circular));

        ImageView padrao = new ImageView(getApplicationContext());
        Picasso.with(getApplicationContext()).load(R.drawable.background).into(padrao);

        ImageView foto = new ImageView(getApplicationContext());
        Picasso.with(getApplicationContext()).load(urlImagem).placeholder(padrao.getDrawable()).transform(cropPosterTransformation).into(foto);//.transform(cropPosterTransformation)


        foto_shot.setBackground(foto.getDrawable());//foto.getDrawable()


        TextView nome =  (TextView)info_shot.findViewById(R.id.nome);
        nome.setText(this.nome);

        TextView titulo = (TextView)foto_shot.findViewById(R.id.title);
        titulo.setText(this.titulo);


        TextView comments_count  = (TextView)foto_shot.findViewById(R.id.views_count);
        comments_count.setText(String.valueOf(this.views_count));


        TextView descricao = (TextView)descricao_shot.findViewById(R.id.descricao);
        descricao.setText(this.descricao!=null?Html.fromHtml(this.descricao):"");
    }

}
