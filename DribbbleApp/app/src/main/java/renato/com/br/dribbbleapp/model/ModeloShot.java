package renato.com.br.dribbbleapp.model;

/**
 * Created by Renato on 15/10/2014.
 */
public class ModeloShot {
    private int id;
    private String title;
    private String description;
    private int comments_count;
    private int views_count;
    private String image_teaser_url;
    private String image_400_url;
    private String image_url;
    private ModeloPlayer player;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_400_url() {
        return image_400_url;
    }

    public void setImage_400_url(String image_400_url) {
        this.image_400_url = image_400_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_teaser_url() {
        return image_teaser_url;
    }

    public void setImage_teaser_url(String image_teaser_url) {
        this.image_teaser_url = image_teaser_url;
    }

    public ModeloPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ModeloPlayer player) {
        this.player = player;
    }


    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }
}
