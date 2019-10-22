package me.doapps.appdhn.databases;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import me.doapps.appdhn.models.Bulletin;
import me.doapps.appdhn.models.Charts;
import me.doapps.appdhn.models.Distrito;
import me.doapps.appdhn.models.Notification;
import me.doapps.appdhn.models.PressRelease;
import me.doapps.appdhn.models.RefugePlaces;
import me.doapps.appdhn.models.Report;
import me.doapps.appdhn.models.Video;

/**
 * Created by William_ST on 03/08/15.
 */
public class DatabaseConfig extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {
            Bulletin.class,
            RefugePlaces.class,
            Report.class,
            Video.class,
            Charts.class,
            Distrito.class,
            Notification.class,
            PressRelease.class
    };

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }

    /*
    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }
    */

}
