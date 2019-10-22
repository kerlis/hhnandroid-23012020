package me.doapps.appdhn.utils;

import android.content.Context;

/**
 * Created by William_ST on 02/10/15.
 */
public class PreprocessorHelper {

    Context context;

    public PreprocessorHelper(Context context){
        this.context = context;
    }

    public String cutCharts(String htmlPage){
        int indexStart, indexEnd;
        indexStart = htmlPage.indexOf("return;");
        indexEnd = htmlPage.indexOf("writeMenus()");
        if(indexStart!=-1 && indexEnd!=-1){
            return htmlPage.substring(indexStart, indexEnd);
        }else{
            return "";
        }
    }

    public String cutBulletin(String htmlPage){
        int indexStart, indexEnd;
        indexStart = htmlPage.indexOf("id=\"accordion\"");
        indexEnd = htmlPage.indexOf("<!-- left sec end -->");

        if(indexStart!=-1 && indexEnd!=-1){
            return htmlPage.substring(indexStart+15, indexEnd-18);
        }else{
            return "";
        }
    }

    public String cutNationalReportSismic(String htmlPage){
        int indexStart, indexEnd;
        indexStart = htmlPage.indexOf("<table");
        indexEnd = htmlPage.indexOf("</table>");

        if(indexStart!=-1 && indexEnd!=-1){
            return htmlPage.substring(indexStart+14, indexEnd);
        }else{
            return "";
        }
    }

}
