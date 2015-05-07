package renato.com.br.dribbbleapp.model;

import java.util.List;

/**
 * Created by Renato on 21/10/2014.
 */
public class ModeloPagina {
    private int page;
    private List<ModeloShot> shots;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ModeloShot> getShots() {
        return shots;
    }

    public void setShots(List<ModeloShot> shots) {
        this.shots = shots;
    }
}
