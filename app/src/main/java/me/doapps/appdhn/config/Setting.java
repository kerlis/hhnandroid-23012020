package me.doapps.appdhn.config;

import me.doapps.appdhn.BuildConfig;

/**
 * Created by jonathannolasco on 10/31/17.
 */

public class Setting {
    public static final String NOTA_PRENSA = "sonido_informativo";
    public static final String REPORTE_SISMICO = "sonido_alarma";
    public static final String ALARM = "alarm";
    public static int countAlarm = 0;
    public static boolean isExecute = false;
    public static final String BOLETINTOPIC = BuildConfig.TOPIC_BOLETIN;
    public static final String ALARMATOPIC = BuildConfig.TOPIC_ALARMA;

    public static final String BOLETIN = "boletin";
    public static final String ALARMA = "alarma";

    public static final String SISMOTOPIC = BuildConfig.TOPIC_SISMO;

    public static final String BACK_KEY = "back_key";
    public static final String BACK_NORMAL = "back_normal";

    public final static int REQUEST_LOCATION = 199;
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 101;
    public static final int PERMISSION_REQUEST_LOCATION = 102;

}
