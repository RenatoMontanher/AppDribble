package renato.com.br.dribbbleapp.ContentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Renato on 04/12/2014.
 */
public  class DribbleContract {
    public static final String AUTHORITY = "renato.com.br.dribbbleapp.ContentProvider.DribbleContract";

    public static final Uri AUTHOIRITY_URI =   Uri.parse("content://"+AUTHORITY);



    public static final class Shot implements BaseColumns{
        private static final String SHOT_PATH = "Shot";
        public static final Uri CONTENT_URI= Uri.withAppendedPath(AUTHOIRITY_URI,SHOT_PATH);

        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String VIEWS_COUNT = "views_count";
        public static final String ID_PLAYER = "id_player";
        public static final String IMAGE_URL = "image_url";
        public static final String CREATED_AT = "created_at";
        public static final String SHOT_PICTURE = "shot_picture";

        private Shot (){}
    }

    public static final class Player implements BaseColumns{
        public static final String NAME = "name";
        public static final String AVATAR_URL = "avatar_url";
        public static final String CREATED_AT = "created_at";
        public static final String PALYER_PICTURE = "player_picture";

        
        private Player(){}
        
    }

}
