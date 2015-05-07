package renato.com.br.dribbbleapp.model;

import renato.com.br.dribbbleapp.controller.ShotController;

/**
 * Created by Renato on 19/10/2014.
 */
public class ModeloPlayer {
    private int id;
    private String name;
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
