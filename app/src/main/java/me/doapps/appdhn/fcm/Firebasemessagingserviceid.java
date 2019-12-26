package me.doapps.appdhn.fcm;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import static android.content.ContentValues.TAG;
import android.content.Context;

public class Firebasemessagingserviceid extends FirebaseInstanceIdService {
    String elvalor = "a";
    String elvalor2 = "bb";
    String json;
    String nombre;
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(token);
        ver();
      //  createNotificationChannel();
      //  createNotificationChannellahar();
        valoresconfiguracion();

    }



    private void registerToken(String token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();
        Request request = new Request.Builder()
                .url("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token)
                .post(body)
                .build();
        //  vale .url("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token)
        //  .url("http://arteypixel.com/envio_notificaciones/register.php?Token="+token)

        consulta("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token);

        //   vale  consulta("http://arteypixel.com/envio_notificaciones/register.php?Token="+token);


        // FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESD");
        //http://intranet.igp.gob.pe/eLdZpqDtLN
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consulta(String urlString)  {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = bufferedReader.readLine();
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "fdfdfd json: " + json);
            ver2(json);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ver2(String dato) {
        String Message5 = dato;
        String file_namex = "datos_ordences";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());
            //FirebaseMessaging.getInstance().subscribeToTopic(Message5);

            FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU5000333NOTDHN");


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ver() {
        String Message3 = elvalor + ",";
        String Message4 = elvalor2 + ",";
        String file_name = "datos_configuracion";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message3.getBytes());
            fileOutputStream.write(Message4.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
        String Message;
        String ko,ajustes,tipo;
        Integer r,s;
        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            try {
                FileInputStream fileInputStream =  openFileInput("datos_configuracion");
                InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer =  new StringBuffer();
                try {
                    while ((Message = bufferedReader.readLine())!=null)
                    {
                        stringBuffer.append(Message);
                    }
                    ko = stringBuffer.toString();
                    StringTokenizer st = new StringTokenizer(stringBuffer.toString(), ",");
                    ajustes = st.nextToken();
                    tipo = st.nextToken();
                    r = ajustes.length();
                    s = tipo.length();
                    if (r == 1){
                        //showNotification(remoteMessage.getData().get("message"));
                    }
                    else {
                        String bb = "";
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }




    private void valoresconfiguracion() {
        String mapas[] = {
                "evac_asia",
                "evac_bahia_chimbote",
                "evac_bahia_paracas",
                "evac_balneario_ancon",
                "evac_balneario_barranca",
                "evac_balneario_pisco",
                "evac_balneario_tortugas",
                "evac_balneario_vesique",
                "evac_caleta_culebras",
                "evac_caleta_huarmey",
                "evac_caleta_los_chimus",
                "evac_caleta_samanco",
                "evac_caleta_san_andres",
                "evac_caleta_tortugas",
                "evac_caleta_vidal",
                "evac_callao",
                "evac_lurin",
                "evac_playa_san_bartolo",
                "evac_playa_santa_rosa",
                "evac_puerto_casma",
                "evac_puerto_chancay",
                "evac_puerto_chimbote",
                "evac_puerto_huacho",
                "evac_puerto_huarmey",
                "evac_puerto_samanco",
                "evac_puerto_santa",
                "evac_puerto_supe",
                "evac_villa_el_salvador",
                "inunda_ancon",
                "inunda_asia",
                "inunda_atico",
                "inunda_bahia_chimbote",
                "inunda_bahia_paracas",
                "inunda_balenario_ancon",
                "inunda_balneario_barranca",
                "inunda_balneario_buenos_aires",
                "inunda_balneario_bujama",
                "inunda_balneario_camana",
                "inunda_balneario_chilca",
                "inunda_balneario_huanchaco",
                "inunda_balneario_huanchaquito",
                "inunda_balneario_las_delicias",
                "inunda_balneario_mejia",
                "inunda_balneario_pisco",
                "inunda_balneario_tomoyo",
                "inunda_balneario_tortugas",
                "inunda_balneario_totoritas",
                "inunda_balneario_vesique",
                "inunda_caleta_carquin",
                "inunda_caleta_coishco",
                "inunda_caleta_constante",
                "inunda_caleta_culebras",
                "inunda_caleta_grau",
                "inunda_caleta_huarmey",
                "inunda_caleta_la_cruz",
                "inunda_caleta_lobitos",
                "inunda_caleta_lomas",
                "inunda_caleta_los_chimus",
                "inunda_caleta_los_organos",
                "inunda_caleta_mancora",
                "inunda_caleta_morro_sama",
                "inunda_caleta_negritos",
                "inunda_caleta_parachique",
                "inunda_caleta_paramonga",
                "inunda_caleta_quilca",
                "inunda_caleta_samanco",
                "inunda_caleta_san_andres",
                "inunda_caleta_san_jose",
                "inunda_caleta_santa_rosa",
                "inunda_caleta_tortugas",
                "inunda_caleta_vegueta",
                "inunda_caleta_vidal",
                "inunda_caleta_vila_vila",
                "inunda_callao",
                "inunda_chorillos",
                "inunda_contralmirante_villar",
                "inunda_la_planchada",
                "inunda_lurin",
                "inunda_magdalena",
                "inunda_miraflores",
                "inunda_negritos",
                "inunda_playa_llostay",
                "inunda_playa_los_palos",
                "inunda_playa_pucusana",
                "inunda_playa_punta_hermosa",
                "inunda_playa_san_bartolo",
                "inunda_playa_santa_rosa",
                "inunda_playa_villa_chorillos",
                "inunda_playa_villa_el_salvador",
                "inunda_playa_yarada",
                "inunda_puerto_casma",
                "inunda_puerto_cerro_azul",
                "inunda_puerto_chala",
                "inunda_puerto_chancay",
                "inunda_puerto_chicama",
                "inunda_puerto_chimbote",
                "inunda_puerto_eten",
                "inunda_puerto_huacho",
                "inunda_puerto_huarmey",
                "inunda_puerto_ilo",
                "inunda_puerto_matarani",
                "inunda_puerto_mollendo",
                "inunda_puerto_pacasmayo",
                "inunda_puerto_paita",
                "inunda_puerto_pimentel",
                "inunda_puerto_pizarro",
                "inunda_puerto_salaverry",
                "inunda_puerto_samanco",
                "inunda_puerto_santa",
                "inunda_puerto_supe",
                "inunda_puerto_talara",
                "inunda_puerto_zorritos",
                "inunda_punta_bombom_catas",
                "inunda_san_juan_marcona",
                "inunda_santa_rosa",
                "inunda_tacna_playa_santa_rosa",
                "inunda_tambo_de_mora",
                "inunda_ventanilla",
                "inunda_villa_el_savador",
                "refugio_asia",
                "refugio_bahia_chimbote",
                "refugio_bahia_paracas",
                "refugio_balenario_vesique",
                "refugio_balneario_ancon",
                "refugio_balneario_barranca",
                "refugio_balneario_pisco",
                "refugio_balneario_tortugas",
                "refugio_caleta_culebras",
                "refugio_caleta_huarmey",
                "refugio_caleta_los_chimus",
                "refugio_caleta_san_andres",
                "refugio_caleta_tortugas",
                "refugio_caleta_vidal",
                "refugio_callao",
                "refugio_lurin",
                "refugio_playa_san_bartolo",
                "refugio_playa_santa_rosa",
                "refugio_puerto_casma",
                "refugio_puerto_chala",
                "refugio_puerto_chancay",
                "refugio_puerto_chimbote",
                "refugio_puerto_huacho",
                "refugio_puerto_huarmey",
                "refugio_puerto_samanco",
                "refugio_puerto_santa",
                "refugio_puerto_supe",
                "refugio_villa_el_salvador"};
        int size = mapas.length;
        for(int i=0; i < size; i++){
            System.out.println(mapas[i]);
            nombre =  mapas[i];
            // new  DownloadKmlFile("http://arteypixel.com/archivos_kmz/kmz_files/"+mapas[i]+".kmz",  "DEDEDE").execute("http://arteypixel.com/archivos_kmz/kmz_files/"+mapas[i]+".kmz",   mapas[i]);
            new Firebasemessagingserviceid.DownloadKmlFile("http://arteypixel.com/archivos_kml/raw/"+mapas[i]+".kml",  "DEDEDE").execute("http://arteypixel.com/archivos_kml/raw/"+mapas[i]+".kml",   mapas[i]);




        }
    }

    private class MyTaskResult {
        String text1;
        String text2;
    }

    public class DownloadKmlFile extends AsyncTask<Object, Void, byte[]> {
        private String val1;
        private String val2;
        private String val6;
        private String val7;

        public DownloadKmlFile(String in3, String in4) {
            this.val6 = in3;
            this.val7 = in4;
        }

        @Override
        protected byte[] doInBackground(Object... params) {
            Firebasemessagingserviceid.MyTaskResult res = new Firebasemessagingserviceid.MyTaskResult();
            val1 = (String) params[0];
            val2 = (String) params[1];
            try {
                InputStream is =  new URL(val1).openStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                return buffer.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(byte[] byteArr) {
            FileOutputStream outputStream;
            try {
                if (true) {
                    outputStream = openFileOutput(val2+".kml", Context.MODE_APPEND);
                } else {
                    outputStream = openFileOutput(val2+".kml", Context.MODE_PRIVATE);
                }
                outputStream.write(byteArr);
                outputStream.flush();
                outputStream.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}