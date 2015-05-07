package renato.com.br.dribbbleapp.adapter;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import renato.com.br.dribbbleapp.Inicio;
import renato.com.br.dribbbleapp.R;
import renato.com.br.dribbbleapp.model.ModeloShot;

/**
 * Created by Renato on 22/10/2014.
 */
@EBean
public class ListShotAdapter extends BaseAdapter {
    @RootContext
    Inicio activity;

    protected int serverListSize = -1;
    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;

    public void setServerListSize(int serverListSize){
        this.serverListSize = serverListSize;
    }

    public boolean isEnabled(int position) {

        return getItemViewType(position) == VIEW_TYPE_ACTIVITY;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getCount() {
        return activity.getListaDeShots().size() + 1;
    }
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return (position >= activity.getListaDeShots().size()) ? VIEW_TYPE_LOADING
                : VIEW_TYPE_ACTIVITY;
    }

    public ModeloShot getItem(int position) {
        // TODO Auto-generated method stub
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? activity.getListaDeShots()
                .get(position) : null;
    }


    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? position
                : -1;
    }

    public  View getView(int position, View convertView, ViewGroup parent){
        if (getItemViewType(position) == VIEW_TYPE_LOADING) {
            // display the last row
            return getFooterView(position, convertView, parent);
        }
        View dataRow = convertView;
        dataRow = getDataRow(position, convertView, parent);

        return dataRow;
    };



    public View getDataRow(int posicao, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;

        Transformation cropPosterTransformation = new Transformation() {

            @Override public Bitmap transform(Bitmap source) {
                int targetWidth = width;
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    source.recycle();
                }
                return result;
            }

            @Override public String key() {
                return "cropPosterTransformation" + width;
            }
        };


        ModeloShot shot = activity.getListaDeShots().get(posicao);
        ImageView foto = new ImageView(activity);
        Picasso.with(activity).load(shot.getImage_url()).placeholder(R.drawable.background).transform(cropPosterTransformation).into(foto);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_shot,null);

            viewHolder = new ViewHolder();


            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.views_count = (TextView) view.findViewById(R.id.views_count);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();

        }
        view.setBackground(foto.getDrawable());
        viewHolder.title.setText(shot.getTitle());
        viewHolder.views_count.setText(String.valueOf(shot.getViews_count()));

/*
        ModeloShot shot = activity.getListaDeShots().get(posicao);
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(R.layout.item_shot,null);

        ImageView foto = new ImageView(activity);

        Picasso.with(activity).load(shot.getImage_url()).placeholder(R.drawable.background).transform(cropPosterTransformation).into(foto);

        item.setBackground(foto.getDrawable());

        TextView title = (TextView) item.findViewById(R.id.title);
        TextView views_count = (TextView) item.findViewById(R.id.views_count);

        title.setText(shot.getTitle());
        views_count.setText(String.valueOf(shot.getComments_count()));
*/
        return view;
    }

    static class ViewHolder {
        ImageView foto;
        TextView title;
        TextView views_count;
    }
    public View getFooterView(int position, View convertView,
                              ViewGroup parent) {
        if (position >= serverListSize && serverListSize > 0) {
            // the ListView has reached the last row
            TextView tvLastRow = new TextView(activity);
            tvLastRow.setHint("Reached the last row.");
            tvLastRow.setGravity(Gravity.CENTER);
            return tvLastRow;
        }

        View row = convertView;
        if (row == null) {
            row = activity.getLayoutInflater().inflate(
                    R.layout.progress, parent, false);
        }

        return row;
    }

}
