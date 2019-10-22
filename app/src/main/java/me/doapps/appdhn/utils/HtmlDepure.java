package me.doapps.appdhn.utils;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.ChartsActivity;
import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.models.ArrayMarkerReport;
import me.doapps.appdhn.models.Bulletin;
import me.doapps.appdhn.models.Charts;
import me.doapps.appdhn.models.MarkerReport;
import me.doapps.appdhn.models.Report;

/**
 * Created by William_ST on 02/10/15.
 */
public class HtmlDepure {

    PreferencesUtil preferencesUtil;

    Map<String, Integer> dictionary = new HashMap(){
        {
            put("TUMBES", R.mipmap.carta_01);
            put("PIURA", R.mipmap.carta_02);
            put("LAMBAYEQUE", R.mipmap.carta_03);
            put("LA LIBERTAD", R.mipmap.carta_04);
            put("ANCASH", R.mipmap.carta_05);
            put("LIMA", R.mipmap.carta_06);
            put("CALLAO", R.mipmap.carta_07);
            put("ICA", R.mipmap.carta_08);
            put("AREQUIPA", R.mipmap.carta_09);
            put("MOQUEGUA", R.mipmap.carta_10);
            put("TACNA", R.mipmap.carta_11);
        }
    };

    private final String TAG = HtmlDepure.class.getSimpleName();
    public InterfaceSaveBulletin interfaceSaveBulletin;
    public InterfaceSaveNationalReportSismic interfaceSaveNationalReportSismic;
    public InterfaceSavecharts interfaceSavecharts;

    public DatabaseHelper databaseHelper;
    public Context context;

    public HtmlDepure(Context context){
        this.context = context;
    }

    public DatabaseHelper getDatabaseHelper(){
        if(databaseHelper == null)  databaseHelper = new DatabaseHelper(context);
        return databaseHelper;
    }

    public String clearSpacing(String page){
        return page.replace("&nbsp;", " ");
    }

    public void loadSismic(String cutPage){

    }

    public void loadCharts(String cutPage){
        String itemDescription, subitemDescription, subitemUrl;
        int subitemUrlStart, subitemUrlEnd = -2, itemStart, itemEnd, subitemStart, subitemEnd, flagNewMenu = -2, flagNewSubMenu = -2;
        int auxEnd = 0;
        flagNewMenu = cutPage.indexOf("new Menu");

        while(flagNewMenu != -1){

            auxEnd = cutPage.indexOf("window.mm_menu", flagNewMenu);
            if(auxEnd == -1){
                break;
            }
            String row = cutPage.substring(flagNewMenu, auxEnd);

            itemStart = row.indexOf("new Menu")+10;
            itemEnd = row.indexOf("\"", itemStart);
            itemDescription = clearSpacing(row.substring(itemStart, itemEnd).trim());

            if (!itemDescription.equals("root")){
                flagNewSubMenu = row.indexOf("addMenuItem", itemEnd);
                while (flagNewSubMenu != -1) {
                    subitemStart = flagNewSubMenu + 13;
                    subitemEnd = row.indexOf("\"", subitemStart);
                    subitemDescription = clearSpacing(row.substring(subitemStart, subitemEnd).trim());

                    subitemUrlStart = row.indexOf("..", subitemEnd) + 2;
                    subitemUrlEnd = row.indexOf("'", subitemUrlStart);

                    subitemUrl = row.substring(subitemUrlStart, subitemUrlEnd).trim();

//                    Log.e(TAG, "Menu: " + itemDescription);
//                    Log.e(TAG, "   SubMenu: " + subitemDescription + "  SubMenuUrl: " + subitemUrl);


                    ((ChartsActivity)context).getDataBaseHelper().getChartsRuntimeExceptionDao().create(new Charts(itemDescription, getIcon(itemDescription),subitemDescription, subitemUrl));
                    Log.e(TAG, "add(new Charts(\"" + itemDescription + "\", R.mipmap.carta_00, \"" + subitemDescription + "\", \"" + subitemUrl + "\"));");
                    flagNewSubMenu = row.indexOf("addMenuItem", subitemUrlEnd);
                }
            }

            flagNewMenu = cutPage.indexOf("new Menu", auxEnd);
        }

        interfaceSavecharts.finish();


    }

    public int getIcon(String department){
        int icon = R.mipmap.ic_launcher;
        for(Map.Entry<String, Integer> entry : dictionary.entrySet()){
            Log.e("DEPARTMENT: "+department, "ENTRY GETKRY: "+entry.getKey());
            if(entry.getKey().trim().equalsIgnoreCase(department.trim())){
                Log.e(TAG, "=== :)!!!!");
                icon = entry.getValue();
                break;
            }
        }
        return icon;
    }

    public  void loadBulletin(String cutPage, boolean forceSismic){
        int nextIndex = 0, indexTitleStart = -2, indexTitleEnd = -2, indexDescriptionStart = -2, indexDescriptionEnd = -2,
            indexUrlStart = -2, indexUrlEnd = -2, indexSubTitleStart = -2, indexSubTitleEnd = -2;
        String bulletinType = "";

        int tempCount = 0;
        nextIndex = cutPage.indexOf("panel panel-default", 0);
        while(nextIndex != -1){
            tempCount++;

            indexTitleStart = cutPage.indexOf("</span>", nextIndex);
            indexTitleEnd = cutPage.indexOf("</a>", indexTitleStart);

            indexUrlStart = cutPage.indexOf("href=\"", indexTitleEnd+4);
            indexUrlEnd = cutPage.indexOf("\"", indexUrlStart+6);

            indexSubTitleStart = cutPage.indexOf("text-align:center;", indexUrlEnd+1)+20;
            indexSubTitleEnd = cutPage.indexOf("</div>", indexSubTitleStart+18);

            indexDescriptionStart = cutPage.indexOf("text-align:justify;", indexSubTitleEnd+6)+21;
            indexDescriptionEnd = cutPage.indexOf("</div>", indexDescriptionStart);

            bulletinType = ValueHelper.BULLETIN_WINDS;

            if(indexTitleStart != -1 && indexTitleEnd != -1 && indexUrlStart != -1 && indexUrlEnd != -1
               && indexSubTitleStart != -1 && indexSubTitleEnd !=-1 && indexDescriptionStart != -1 && indexDescriptionEnd != -1){
                if(forceSismic){
                    bulletinType = ValueHelper.BULLETIN_SEISMIC;
                }else {
                    //oleajes
                    String tempAux = cutPage.substring(indexDescriptionStart, indexDescriptionEnd);
//                    Log.e(TAG, "TEMP AUX: "+tempAux);
//                    Log.e(TAG, "oleaje"+(tempAux.indexOf("oleaje"))+"");
//                    Log.e(TAG, "oleajes"+(tempAux.indexOf("oleajes", indexDescriptionStart))+"");

                    Log.e(TAG, "if( "+tempAux.indexOf("oleaje")+" || "+tempAux.indexOf("oleajes"));

                    if(tempAux.indexOf("oleaje") != -1 || tempAux.indexOf("oleajes") != -1){
                        Log.e(TAG, "add: mareas");
                        bulletinType = ValueHelper.BULLETIN_TIDES;
                    }else{
                        bulletinType = ValueHelper.BULLETIN_WINDS;
                        Log.e(TAG, "add: vientos");
                    }

                }

                try {
                    getDatabaseHelper().getBulletinRuntimeExceptionDao().create(
                            new Bulletin(bulletinType
                                    , cutPage.substring(indexTitleStart + 7, indexTitleEnd).trim()
                                    , cutPage.substring(indexSubTitleStart, indexSubTitleEnd).trim()
                                    , cutPage.substring(indexDescriptionStart, indexDescriptionEnd).trim(), cutPage.substring(indexUrlStart + 6, indexUrlEnd).trim()));

                } catch(Exception e){
                    Log.e(TAG, "YA ESTA INSERTADO!!!");
                }
                nextIndex = cutPage.indexOf("panel panel-default", indexDescriptionEnd);

//                Log.e(TAG, "nextIndex: "+nextIndex+" - tempCount: "+tempCount);
            }else{
                Toast.makeText(context, "www.dhn.mil.pe was modified", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "title: "+indexTitleStart+" a "+indexTitleEnd);
                Log.e(TAG, "url: "+indexUrlStart+" a "+indexUrlEnd);
                Log.e(TAG, "subTitle: "+indexSubTitleStart+" a "+indexSubTitleEnd);
                Log.e(TAG, "description: "+indexDescriptionStart+" a "+indexDescriptionEnd);
                break;
            }

        }

        interfaceSaveBulletin.finish();

    }

    /*
    public void loadNationReportSismic(String cutPage){

        try {
            Log.e(TAG, "loadNationReportSismic");
            preferencesUtil = new PreferencesUtil(context);
            int indexStartPosition, indexEndPosition, indexStartDescription, indexEndDescription;
            int flag = cutPage.indexOf("new google.maps.LatLng(");
            ArrayMarkerReport arrayMarkerReport = new ArrayMarkerReport();
            List<MarkerReport> reportList = new ArrayList<>();
            String position, description;
            while (flag != -1) {
                indexStartPosition = flag;
                indexEndPosition = cutPage.indexOf(")", indexStartPosition);
                indexStartDescription = cutPage.indexOf("content: '", indexEndPosition) + 10;
                indexEndDescription = cutPage.indexOf("'", indexStartDescription);
                position = cutPage.substring(indexStartPosition + 23, indexEndPosition);
                description = cutPage.substring(indexStartDescription, indexEndDescription);
                Log.e(TAG, "--->01");
                reportList.add(new MarkerReport(1, position, description));
                Log.e(TAG, "--->02");
                flag = cutPage.indexOf("new google.maps.LatLng(", flag + 23);
            }
            arrayMarkerReport.setMarkerReportList(reportList);
            preferencesUtil.setMarkers(arrayMarkerReport);

            Log.e(TAG, "SAVEEEE PREFERENCE SIZE = "+preferencesUtil.getMarkers().getMarkerReportList().size());

            int nextIndex = 0, indexEpicenterStart = 0, indexEpicenterEnd = 0, indexDateStart, indexDateEnd, indexMagnitudeStart, indexMagnitudeEnd, indexEvaluationStart, indexEvaluationEnd, indexTypeStart, indexTypeEnd;

            DeleteBuilder<Report, Integer> reporteDb = getDatabaseHelper().getReportDao().deleteBuilder();
            Log.e(TAG, "Delete: " + reporteDb.delete());

            nextIndex = cutPage.indexOf("<tr style=\" background-color:");
            while (nextIndex != -1) {

                indexEpicenterStart = cutPage.indexOf("\"", cutPage.indexOf("<td style=\"", nextIndex) + 11) + 2;
                indexEpicenterEnd = cutPage.indexOf("</td>", indexEpicenterStart);

                indexDateStart = cutPage.indexOf("<td>", indexEpicenterEnd) + 4;
                indexDateEnd = cutPage.indexOf("</td>", indexDateStart);

                indexMagnitudeStart = cutPage.indexOf("<td>", indexDateEnd) + 4;
                indexMagnitudeEnd = cutPage.indexOf(" ", indexMagnitudeStart);

                indexEvaluationStart = cutPage.indexOf("<td>", indexMagnitudeEnd) + 4;
                indexEvaluationEnd = cutPage.indexOf("</td>", indexEvaluationStart);

                indexTypeStart = cutPage.indexOf("<td>", indexEvaluationEnd) + 4;
                indexTypeEnd = cutPage.indexOf("</td>", indexTypeStart);

                if (indexEpicenterStart != -1 && indexEpicenterEnd != -1 && indexDateStart != -1 && indexDateEnd != -1
                        && indexMagnitudeStart != -1 && indexMagnitudeEnd != -1 && indexEvaluationStart != -1 && indexEpicenterEnd != -1
                        && indexTypeStart != -1 && indexTypeEnd != -1) {
                    Report temp = new Report(cutPage.substring(indexEpicenterStart, indexEpicenterEnd).trim()
                            , cutPage.substring(indexDateStart, indexDateEnd).trim()
                            , Double.parseDouble(cutPage.substring(indexMagnitudeStart, indexMagnitudeEnd).trim())
                            , cutPage.substring(indexEvaluationStart, indexEvaluationEnd).trim()
                            , cutPage.substring(indexTypeStart, indexTypeEnd).trim());

                    getDatabaseHelper().getReportRuntimeExceptionDao().create(temp);

                } else {
                    Toast.makeText(context, "www.dhn.mil.pe was modified", Toast.LENGTH_SHORT).show();
                    break;
                }
                nextIndex = cutPage.indexOf("<tr style=\" background-color:", indexTypeEnd);
            }
            interfaceSaveNationalReportSismic.finish();
        } catch(Exception e){
            Log.e(TAG, "loadNationReportSismic: "+e.toString());
        }

    }*/

    public interface InterfaceSaveBulletin{
        void finish();
    }

    public void setInterfaceSaveBulletin(InterfaceSaveBulletin interfaceSaveBulletin){
        this.interfaceSaveBulletin = interfaceSaveBulletin;
    }

    public interface InterfaceSaveNationalReportSismic{
        void finish();
    }

    public void setInterfaceSaveNationalReportSismic(InterfaceSaveNationalReportSismic interfaceSaveNationalReportSismic){
        this.interfaceSaveNationalReportSismic = interfaceSaveNationalReportSismic;
    }

    public interface InterfaceSavecharts{
        void finish();
    }

    public void setInterfaceSavecharts(InterfaceSavecharts interfaceSavecharts){
        this.interfaceSavecharts = interfaceSavecharts;
    }

}
