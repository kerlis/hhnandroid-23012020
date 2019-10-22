package me.doapps.appdhn.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import me.doapps.appdhn.R;
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
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "appdhn.db";
    private static final int DATABASE_VERSION = 3;
    private final String TAG = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private Dao<Bulletin, Integer> bulletinDao = null;
    private RuntimeExceptionDao<Bulletin, Integer> bulletinRuntimeExceptionDao = null;

    private Dao<RefugePlaces, Integer> refugePlacesDao = null;
    private RuntimeExceptionDao<RefugePlaces, Integer> refugePlacesRuntimeExceptionDao = null;

    private Dao<Report, Integer> reportDao = null;
    private RuntimeExceptionDao<Report, Integer> reportRuntimeExceptionDao = null;

    private Dao<Video, Integer> videoDao = null;
    private RuntimeExceptionDao<Video, Integer> videoRuntimeExceptionDao = null;

    private Dao<Charts, Integer> chartsDao = null;
    private RuntimeExceptionDao<Charts, Integer> chartsRuntimeExceptionDao = null;

    private Dao<Distrito, Integer> departmentDao = null;
    private RuntimeExceptionDao<Distrito, Integer> departmentRuntimeExceptionDao = null;

    private Dao<Notification, Integer> notificationDao = null;
    private RuntimeExceptionDao<Notification, Integer> notificationRuntimeExceptionDao = null;

    private Dao<PressRelease, Integer> pressreleasesDao = null;
    private RuntimeExceptionDao<PressRelease, Integer> pressreleasesRuntimeExceptionDao = null;

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource source) {
        try {
            TableUtils.createTable(source, Bulletin.class);
            TableUtils.createTable(source, RefugePlaces.class);
            TableUtils.createTable(source, Report.class);
            TableUtils.createTable(source, Video.class);
            TableUtils.createTable(source, Charts.class);
            TableUtils.createTable(source, Distrito.class);
            TableUtils.createTable(source, Notification.class);
            TableUtils.createTable(source, PressRelease.class);
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        /* departments for default */
        RuntimeExceptionDao<Distrito, Integer> tempDepartment = getDepartmentRuntimeExceptionDao();

        tempDepartment.create(new Distrito("Ancash Bahia Chimbote", -9.099295, -78.568640, R.raw.inunda_bahia_chimbote, R.raw.refugio_bahia_chimbote, R.raw.evac_bahia_chimbote, true));
        tempDepartment.create(new Distrito("Ancash Balneario Vesique", -9.20691, -78.48461, R.raw.inunda_balneario_vesique, R.raw.refugio_balenario_vesique, R.raw.evac_balneario_vesique, true));
        tempDepartment.create(new Distrito("Ancash Caleta Coishco", -9.019392, -78.620161, R.raw.inunda_caleta_coishco, 0, 0, true));
        tempDepartment.create(new Distrito("Ancash Caleta Culebras", -9.953112, -78.227775, R.raw.inunda_caleta_culebras, R.raw.refugio_caleta_culebras, R.raw.evac_caleta_culebras, true));
        tempDepartment.create(new Distrito("Ancash Caleta Los chimus", -9.321838, -78.473132, R.raw.inunda_caleta_los_chimus, R.raw.refugio_caleta_los_chimus, R.raw.evac_caleta_los_chimus, true));
        tempDepartment.create(new Distrito("Ancash Caleta Paramonga", -10.682492, -77.828114, R.raw.inunda_caleta_paramonga, 0, 0, true));
        tempDepartment.create(new Distrito("Ancash Caleta Samanco", -9.255323, -78.497030, R.raw.inunda_caleta_samanco, 0, R.raw.evac_caleta_samanco, true));
        tempDepartment.create(new Distrito("Ancash Caleta Tortugas", -9.94448, -78.22546, R.raw.inunda_caleta_tortugas, R.raw.refugio_caleta_tortugas, R.raw.evac_caleta_tortugas, true));
        tempDepartment.create(new Distrito("Ancash Caleta Huarmey", -10.09218, -78.16214, R.raw.inunda_caleta_huarmey, R.raw.refugio_caleta_huarmey, R.raw.evac_caleta_huarmey, true));
        tempDepartment.create(new Distrito("Ancash Balneario Tortugas", -9.369417, -78.411418, R.raw.inunda_balneario_tortugas, R.raw.refugio_balneario_tortugas, R.raw.evac_balneario_tortugas, true));
        tempDepartment.create(new Distrito("Ancash Puerto Casma", -9.45143, -78.37921, R.raw.inunda_puerto_casma, R.raw.refugio_puerto_casma, R.raw.evac_puerto_casma, true));
        tempDepartment.create(new Distrito("Ancash Puerto Chimbote", -9.08988, -78.57033, R.raw.inunda_bahia_chimbote, R.raw.refugio_puerto_chimbote, R.raw.evac_puerto_chimbote, true));
        tempDepartment.create(new Distrito("Ancash Puerto Samanco", -9.25766, -78.49503, R.raw.inunda_puerto_samanco, R.raw.refugio_puerto_samanco, R.raw.evac_puerto_samanco, true));
        tempDepartment.create(new Distrito("Ancash Puerto Huarmey", -10.090899, -78.159221, R.raw.inunda_puerto_huarmey, R.raw.refugio_puerto_huarmey, R.raw.evac_puerto_huarmey, true));
        tempDepartment.create(new Distrito("Ancash Puerto Santa", -8.991230, -78.637493, R.raw.inunda_puerto_santa, R.raw.refugio_puerto_santa, R.raw.evac_puerto_santa, true));

        tempDepartment.create(new Distrito("Arequipa Atico", -16.234470, -73.604760, R.raw.inunda_atico, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa Balneario Mejia", -17.102043, -71.908712, R.raw.inunda_balneario_mejia, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa Balneario Camana", -16.648293, -72.689428, R.raw.inunda_balneario_camana, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa Caleta Lomas", -15.566696, -74.847922, R.raw.inunda_caleta_lomas, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa Caleta Quilca", -16.712543, -72.437128, R.raw.inunda_caleta_quilca, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa La Planchada", -16.401794, -73.220680, R.raw.inunda_la_planchada, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa Puerto Chala", -15.846012, -74.252300, R.raw.inunda_puerto_chala, R.raw.refugio_puerto_chala, 0, true));
        tempDepartment.create(new Distrito("Arequipa Puerto Matarani", -17.007874, -72.103569, R.raw.inunda_puerto_matarani, 0, 0, true));
        tempDepartment.create(new Distrito("Arequipa Puerto Mollendo", -17.025810, -72.011005, R.raw.inunda_puerto_mollendo, 0, 0, true));

        tempDepartment.create(new Distrito("Callao", -12.017753, -77.137388, R.raw.inunda_callao, R.raw.refugio_callao, R.raw.evac_callao, true));

        tempDepartment.create(new Distrito("Ventanilla", -11.87255, -77.15556, R.raw.inunda_ventanilla, 0, 0, true));

        tempDepartment.create(new Distrito("Ica Bahia Paracas", -13.834433, -76.248821, R.raw.inunda_bahia_paracas, R.raw.refugio_bahia_paracas, R.raw.evac_bahia_paracas, true));
        tempDepartment.create(new Distrito("Ica Balneario Pisco", -13.70964, -76.21431, R.raw.inunda_balneario_pisco, R.raw.refugio_balneario_pisco, R.raw.evac_balneario_pisco, true));
        tempDepartment.create(new Distrito("Ica Caleta San Andres", -13.74311, -76.22461, R.raw.inunda_caleta_san_andres, R.raw.refugio_caleta_san_andres, R.raw.evac_caleta_san_andres, true));
        tempDepartment.create(new Distrito("Ica San Juan Marcona", -13.459265, -76.184284, R.raw.inunda_san_juan_marcona, 0, 0, true));
        tempDepartment.create(new Distrito("Ica Tambo de Mora", -13.459265, -76.184284, R.raw.inunda_tambo_de_mora, 0, 0, true));

        tempDepartment.create(new Distrito("La Libertad Balneario Buenos Aires", -8.144734, -79.054560, R.raw.inunda_balneario_buenos_aires, 0, 0, true));
        tempDepartment.create(new Distrito("La Libertad Balneario Huanchaco", -8.077743, -79.118548, R.raw.inunda_balneario_huanchaco, 0, 0, true));
        tempDepartment.create(new Distrito("La Libertad Balneario Huanchaquito", -8.102956, -79.105409, R.raw.inunda_balneario_huanchaquito, 0, 0, true));
        tempDepartment.create(new Distrito("La Libertad Balneario Las Delicias", -8.179614, -79.015192, R.raw.inunda_balneario_las_delicias, 0, 0, true));
        tempDepartment.create(new Distrito("La Libertad Puerto Chicama", -7.702570, -79.436373, R.raw.inunda_puerto_chicama, 0, 0, true));
        tempDepartment.create(new Distrito("La Libertad Puerto Pacasmayo", -7.412345, -79.570191, R.raw.inunda_puerto_pacasmayo, 0, 0, true));
        tempDepartment.create(new Distrito("La Libertad Puerto Salaverry", -8.230137, -78.983084, R.raw.inunda_puerto_salaverry, 0, 0, true));

        tempDepartment.create(new Distrito("Lambayeque Puerto Eten", -6.916905, -79.876978, R.raw.inunda_puerto_eten, 0, 0, true));
        tempDepartment.create(new Distrito("Lambayeque Puerto Pimental", -6.834130, -79.933206, R.raw.inunda_puerto_pimentel, 0, 0, true));
        tempDepartment.create(new Distrito("Lambayeque Caleta San José", -6.834130, -79.933206, R.raw.inunda_caleta_san_jose, 0, 0, true));
        tempDepartment.create(new Distrito("Lambayeque Caleta Santa Rosa", -6.834130, -79.933206, R.raw.inunda_caleta_santa_rosa, 0, 0, true));

        tempDepartment.create(new Distrito("Lima Ancon", -11.722615, -77.162224, R.raw.inunda_ancon, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Balneario Ancon", -11.722615, -77.162224, R.raw.inunda_balenario_ancon, R.raw.refugio_balneario_ancon, R.raw.evac_balneario_ancon, true));
        tempDepartment.create(new Distrito("Lima Asia", -12.790018, -76.583941, R.raw.inunda_asia, R.raw.refugio_asia, R.raw.evac_asia, true));
        tempDepartment.create(new Distrito("Lima Balneario Barranca", -10.760313, -77.763805, R.raw.inunda_balneario_barranca, R.raw.refugio_balneario_barranca, R.raw.evac_balneario_barranca, true));
//            tempDepartment.create(new Distrito("Lima Balneario Puerto Chancay", -10.760313, -77.763805, R.raw.chanca, R.raw.refugio_balneario_barranca, R.raw.evac_balneario_barranca, true));
        tempDepartment.create(new Distrito("Lima Balneario Bujama", -12.701754, -76.646421, R.raw.inunda_balneario_bujama, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Balneario Chilca", -12.529515, -76.750812, R.raw.inunda_balneario_chilca, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Balneario Totoritas", -12.685143, -76.656403, R.raw.inunda_balneario_totoritas, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Caleta Carquin", -11.092174, -77.627795, R.raw.inunda_caleta_carquin, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Caleta Vidal", -10.85388, -77.70459, R.raw.inunda_caleta_vidal, R.raw.refugio_caleta_vidal, R.raw.evac_caleta_vidal, true));
        tempDepartment.create(new Distrito("Lima Caleta Vegueta", -11.024971, -77.650563, R.raw.inunda_caleta_vegueta, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Chorillos", -12.161320, -77.025886, R.raw.inunda_chorillos, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Magdalena", -12.100193, -77.069410, R.raw.inunda_magdalena, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Miraflores", -12.126761, -77.036905, R.raw.inunda_miraflores, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Lurín", -12.27576, -76.88541, R.raw.inunda_lurin, R.raw.refugio_lurin, R.raw.evac_lurin, true));
        tempDepartment.create(new Distrito("Lima Playa San Bartolo", -12.39026, -76.7813, R.raw.inunda_playa_san_bartolo, R.raw.refugio_playa_san_bartolo, R.raw.evac_playa_san_bartolo, true));
        tempDepartment.create(new Distrito("Lima Playa Pucusana", -12.479549, -76.783696, R.raw.inunda_playa_pucusana, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Playa Punta Hermosa", -12.333911, -76.828717, R.raw.inunda_playa_punta_hermosa, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Playa Villa Chorillos", -12.210658, -77.015925, R.raw.inunda_playa_villa_chorillos, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Puerto Cerro Azul", -12.999847, -76.483692, R.raw.inunda_puerto_cerro_azul, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Puerto Chancay", -11.574109, -77.270537, R.raw.inunda_puerto_chancay, R.raw.refugio_puerto_chancay, R.raw.evac_puerto_chancay, true));
        tempDepartment.create(new Distrito("Lima Puerto Huacho", -11.128720, -77.614441, R.raw.inunda_puerto_huacho, R.raw.refugio_puerto_huacho, R.raw.evac_puerto_huacho, true));
        tempDepartment.create(new Distrito("Lima Puerto Supe", -10.851881, -77.705273, R.raw.inunda_puerto_supe, R.raw.refugio_puerto_supe, R.raw.evac_puerto_supe, true));
        tempDepartment.create(new Distrito("Lima PLaya Santa Rosa", -11.806854, -77.176327, R.raw.inunda_playa_santa_rosa, R.raw.refugio_playa_santa_rosa, R.raw.evac_playa_santa_rosa, true));
        tempDepartment.create(new Distrito("Lima Santa Rosa", -11.806854, -77.176327, R.raw.inunda_santa_rosa, 0, 0, true));
        tempDepartment.create(new Distrito("Lima Villa El Salvador", -12.24263, -76.94166, R.raw.inunda_villa_el_savador, R.raw.refugio_villa_el_salvador, R.raw.evac_villa_el_salvador, true));
//            tempDepartment.create(new Distrito("Lima Lurín", -12.24263, -76.94166, R.raw.inunda_villa_el_savador, R.raw.refugio_villa_el_salvador, R.raw.evac_villa_el_salvador, true));
        tempDepartment.create(new Distrito("Lima Playa Villa El Salvador", -12.24263, -76.94166, R.raw.inunda_playa_villa_el_salvador, 0, 0, true));

        tempDepartment.create(new Distrito("Moquegua Puerto Ilo", -17.664491, -71.351342, R.raw.inunda_puerto_ilo, 0, 0, true));

        tempDepartment.create(new Distrito("Piura Caleta Constante", -5.683651, -80.849773, R.raw.inunda_caleta_constante, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Caleta Lobitos", -4.456058, -81.281515, R.raw.inunda_caleta_lobitos, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Caleta Negritos", -4.456058, -81.281515, R.raw.inunda_caleta_negritos, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Caleta Mancora", -4.102298, -81.053342, R.raw.inunda_caleta_mancora, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Caleta Parachique", -5.764424, -80.863302, R.raw.inunda_caleta_parachique, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Negritos", -4.642884, -81.304154, R.raw.inunda_negritos, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Puerto Paita", -5.085927, -81.097369, R.raw.inunda_puerto_paita, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Puerto Talara", -4.579087, -81.277325, R.raw.inunda_puerto_talara, 0, 0, true));
        tempDepartment.create(new Distrito("Piura Caleta Los Organos", -4.579087, -81.277325, R.raw.inunda_caleta_los_organos, 0, 0, true));

        tempDepartment.create(new Distrito("Tacna Balneario Tomoyo", -18.135089, -70.708072, R.raw.inunda_balneario_tomoyo, 0, 0, true));
        tempDepartment.create(new Distrito("Tacna Caleta Morro Sama", -17.995324, -70.882927, R.raw.inunda_caleta_morro_sama, 0, 0, true));
        tempDepartment.create(new Distrito("Tacna Caleta Vila Vila", -18.116820, -70.725458, R.raw.inunda_caleta_vila_vila, 0, 0, true));
        tempDepartment.create(new Distrito("Tacna Playa Llostay", -18.175384, -70.643174, R.raw.inunda_playa_llostay, 0, 0, true));
        tempDepartment.create(new Distrito("Tacna Playa Yarada", -18.233201, -70.540383, R.raw.inunda_playa_yarada, 0, 0, true));
        tempDepartment.create(new Distrito("Tacna Playa Los Palos", -18.233201, -70.540383, R.raw.inunda_playa_los_palos, 0, 0, true));
        tempDepartment.create(new Distrito("Tacna Playa Santa Rosa", -18.33187, -70.39489, R.raw.inunda_tacna_playa_santa_rosa, 0, 0, true));

        tempDepartment.create(new Distrito("Tumbes Caleta Grau", -3.667499, -80.633664, R.raw.inunda_caleta_grau, 0, 0, true));
        tempDepartment.create(new Distrito("Tumbes Puerto Pizarro", -3.506705, -80.390584, R.raw.inunda_puerto_pizarro, 0, 0, true));
        tempDepartment.create(new Distrito("Tumbes Puerto Zorritos", -3.680247, -80.679238, R.raw.inunda_puerto_zorritos, 0, 0, true));
        tempDepartment.create(new Distrito("Tumbes Caleta La Cruz", -3.680247, -80.679238, R.raw.inunda_caleta_la_cruz, 0, 0, true));
        tempDepartment.create(new Distrito("Tumbes Contralmirante Villar", -3.680247, -80.679238, R.raw.inunda_contralmirante_villar, 0, 0, true));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(source, Bulletin.class, false);
            TableUtils.dropTable(source, RefugePlaces.class, false);
            TableUtils.dropTable(source, Report.class, false);
            TableUtils.dropTable(source, Video.class, false);
            TableUtils.dropTable(source, Charts.class, false);
            TableUtils.dropTable(source, Distrito.class, false);
            TableUtils.dropTable(source, Notification.class, false);
            TableUtils.dropTable(source, PressRelease.class, false);
            onCreate(db, source);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(), e.toString());
            throw new RuntimeException(e);
        }
    }

    public Dao<PressRelease, Integer> getPressreleasesDao() throws SQLException {
        if (pressreleasesDao == null) pressreleasesDao = getDao(PressRelease.class);
        return pressreleasesDao;
    }

    public RuntimeExceptionDao<PressRelease, Integer> getPressReleasesRuntimeExceptionDao() {
        if (pressreleasesRuntimeExceptionDao == null)
            pressreleasesRuntimeExceptionDao = getRuntimeExceptionDao(PressRelease.class);
        return pressreleasesRuntimeExceptionDao;
    }

    public Dao<Notification, Integer> getNotificationDao() throws SQLException {
        if (notificationDao == null) notificationDao = getDao(Notification.class);
        return notificationDao;
    }

    public RuntimeExceptionDao<Notification, Integer> getNotificationRuntimeExceptionDao() {
        if (notificationRuntimeExceptionDao == null)
            notificationRuntimeExceptionDao = getRuntimeExceptionDao(Notification.class);
        return notificationRuntimeExceptionDao;
    }

    public Dao<Distrito, Integer> getDepartmentDao() throws SQLException {
        if (departmentDao == null) departmentDao = getDao(Distrito.class);
        return departmentDao;
    }

    public RuntimeExceptionDao<Distrito, Integer> getDepartmentRuntimeExceptionDao() {
        if (departmentRuntimeExceptionDao == null)
            departmentRuntimeExceptionDao = getRuntimeExceptionDao(Distrito.class);
        return departmentRuntimeExceptionDao;
    }

    public Dao<Charts, Integer> getChartsDao() throws SQLException {
        if (chartsDao == null) chartsDao = getDao(Charts.class);
        return chartsDao;
    }

    public RuntimeExceptionDao<Charts, Integer> getChartsRuntimeExceptionDao() {
        if (chartsRuntimeExceptionDao == null)
            chartsRuntimeExceptionDao = getRuntimeExceptionDao(Charts.class);
        return chartsRuntimeExceptionDao;
    }

    public Dao<Video, Integer> getVideoPlacesDao() throws SQLException {
        if (videoDao == null) videoDao = getDao(Video.class);
        return videoDao;
    }

    public RuntimeExceptionDao<Video, Integer> getVideoRuntimeExceptionDao() {
        if (videoRuntimeExceptionDao == null)
            videoRuntimeExceptionDao = getRuntimeExceptionDao(Video.class);
        return videoRuntimeExceptionDao;
    }

    public Dao<Report, Integer> getReportDao() throws SQLException {
        if (reportDao == null) reportDao = getDao(Report.class);
        return reportDao;
    }

    public RuntimeExceptionDao<Report, Integer> getReportRuntimeExceptionDao() {
        if (reportRuntimeExceptionDao == null)
            reportRuntimeExceptionDao = getRuntimeExceptionDao(Report.class);
        return reportRuntimeExceptionDao;
    }

    public Dao<RefugePlaces, Integer> getRefugePlacesDao() throws SQLException {
        if (refugePlacesDao == null) refugePlacesDao = getDao(RefugePlaces.class);
        return refugePlacesDao;
    }

    public RuntimeExceptionDao<RefugePlaces, Integer> getRefugePlacesRuntimeExceptionDao() {
        if (refugePlacesRuntimeExceptionDao == null)
            refugePlacesRuntimeExceptionDao = getRuntimeExceptionDao(RefugePlaces.class);
        return refugePlacesRuntimeExceptionDao;
    }

    public Dao<Bulletin, Integer> getBulletinDao() throws SQLException {
        if (bulletinDao == null) bulletinDao = getDao(Bulletin.class);
        return bulletinDao;
    }

    public RuntimeExceptionDao<Bulletin, Integer> getBulletinRuntimeExceptionDao() {
        if (bulletinRuntimeExceptionDao == null)
            bulletinRuntimeExceptionDao = getRuntimeExceptionDao(Bulletin.class);
        return bulletinRuntimeExceptionDao;
    }

}
