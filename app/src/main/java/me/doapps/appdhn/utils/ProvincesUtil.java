package me.doapps.appdhn.utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.FileDate;
import me.doapps.appdhn.models.FileProvince;
import me.doapps.appdhn.models.Provinces;
import me.doapps.appdhn.sqllite.Setences;
import me.doapps.appdhn.sqllite.Sqlite;

/**
 * Created by Leandro on 10/18/17.
 */

public class ProvincesUtil {


    private static String URL_BASE = "https://www.dhn.mil.pe/secciones/departamentos/oceanografia/apps/cartastsunamis/";
    private static Setences connexion;

    public static List<Provinces> listProvinces(Context context) {

        List<FileProvince> filetumbes = new ArrayList<>();
        filetumbes.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/Puerto_Pizarro.pdf", "Puerto Pizarro", "Puerto_Pizarro.pdf", isStatusFile(getNameData("Puerto_Pizarro.pdf", context)), false));
        filetumbes.add(new FileProvince(URL_BASE + "images/cartas_inundacion/LAcruz.jpg", "Caleta La Cruz", "LAcruz.jpg", isStatusFile(getNameData("LAcruz.jpg", context)), false));
        filetumbes.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/caleta_Grau.pdf", "Caleta Grau", "caleta_Grau.pdf", isStatusFile(getNameData("caleta_Grau.pdf", context)), false));
        filetumbes.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Pto_zorritos_final.pdf", "Puerto Zorritos", "Pto_zorritos_final.pdf", isStatusFile(getNameData("Pto_zorritos_final.pdf", context)), false));

        List<FileProvince> filepiura = new ArrayList<>();
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Mancora.pdf", "Caleta Mancora", "Mancora.pdf", isStatusFile(getNameData("Mancora.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Losorganos.pdf", "Caleta Los Organos", "Losorganos.pdf", isStatusFile(getNameData("Losorganos.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/Caleta_Lobitos.pdf", "Caleta Lobitos", "Caleta_Lobitos.pdf", isStatusFile(getNameData("Caleta_Lobitos.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/talara-piura.pdf", "Caleta Talara", "talara-piura.pdf", isStatusFile(getNameData("talara-piura.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/Caleta_NEGRITOS.pdf", "Caleta Negritos", "Caleta_NEGRITOS.pdf", isStatusFile(getNameData("Caleta_NEGRITOS.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/paita.jpg", "Caleta Paita", "paita.jpg", isStatusFile(getNameData("paita.jpg", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/constante-piura.pdf", "Caleta Constante", "constante-piura.pdf", isStatusFile(getNameData("constante-piura.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/Caleta_Parachique2016.pdf", "Caleta Parachique", "Caleta_Parachique2016.pdf", isStatusFile(getNameData("Caleta_Parachique2016.pdf", context)), false));
        filepiura.add(new FileProvince(URL_BASE + "images/cartas_inundacion/norte/PuertoRico2016.pdf", "Caleta Puerto Rico", "PuertoRico2016.pdf", isStatusFile(getNameData("PuertoRico2016.pdf", context)), false));

        List<FileProvince> filelambayegue = new ArrayList<>();
        filelambayegue.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Sanjose.pdf", "Caleta San Jose", "Sanjose.pdf", isStatusFile(getNameData("Sanjose.pdf", context)), false));
        filelambayegue.add(new FileProvince(URL_BASE + "images/cartas_inundacion/SantaRosa.pdf", "Caleta Santa Rosa", "SantaRosa.pdf", isStatusFile(getNameData("SantaRosa.pdf", context)), false));
        filelambayegue.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Pto_pimentel.pdf", "Puerto Pimentel", "Pto_pimentel.pdf", isStatusFile(getNameData("Pto_pimentel.pdf", context)), false));
        filelambayegue.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Eten.pdf", "Puerto Eten", "Eten.pdf", isStatusFile(getNameData("Eten.pdf", context)), false));

        List<FileProvince> filelalibertad = new ArrayList<>();
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Puerto_Pacasmayo.pdf", "Puerto Pacasmayo", "Puerto_Pacasmayo.pdf", isStatusFile(getNameData("Puerto_Pacasmayo.pdf", context)), false));
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Chicama.pdf", "Puerto Chicama", "Chicama.pdf", isStatusFile(getNameData("Chicama.pdf", context)), false));
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Huanchaco.jpg", "Bal. Huanchaco", "Huanchaco.jpg", isStatusFile(getNameData("Huanchaco.jpg", context)), false));
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Huanchaquito.jpg", "Bal, Huanchaquito", "Huanchaquito.jpg", isStatusFile(getNameData("Huanchaquito.jpg", context)), false));
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/BsAs_2012.pdf", "Bal. Buenos Aires", "BsAs_2012.pdf", isStatusFile(getNameData("BsAs_2012.pdf", context)), false));
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Delicias.jpg", "Bal. Las Delicias", "Delicias.jpg", isStatusFile(getNameData("Delicias.jpg", context)), false));
        filelalibertad.add(new FileProvince(URL_BASE + "images/cartas_inundacion/Puerto_Salaverry.pdf", "Puerto Salaverry", "Puerto_Salaverry.pdf", isStatusFile(getNameData("Puerto_Salaverry.pdf", context)), false));

        List<FileProvince> fileancash = new ArrayList<>();
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_SANTA_ANCASH.pdf", "Puerto Santa", "CIT_CENTRO_SANTA_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_SANTA_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Cal_Coishco.jpg", "Caleta Coishco", "Cal_Coishco.jpg", isStatusFile(getNameData("Cal_Coishco.jpg", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_CHIMBOTE_ANCASH.pdf", "Puerto Chimbote", "CIT_CENTRO_CHIMBOTE_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_CHIMBOTE_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_VESIQUE_ANCASH.pdf", "Balneario Vesique", "CIT_CENTRO_VESIQUE_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_VESIQUE_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/norte/CIT_CENTRO_SAMANCO_ANCASH.pdf", "Puerto Samanco", "CIT_CENTRO_SAMANCO_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_SAMANCO_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_CHIMUS_ANCASH.pdf", "Caleta Los Chimus", "CIT_CENTRO_CHIMUS_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_CHIMUS_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_TORTUGAS_ANCASH.pdf", "Balneario Tortugas", "CIT_CENTRO_TORTUGAS_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_TORTUGAS_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_CASMA_ANCASH.pdf", "Puerto Casma", "CIT_CENTRO_CASMA_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_CASMA_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_CULEBRAS_ANCASH.pdf", "Caleta Culebras", "CIT_CENTRO_CULEBRAS_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_CULEBRAS_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_HUARMEY_ANCASH.pdf", "Puerto huarmey", "CIT_CENTRO_HUARMEY_ANCASH.pdf", isStatusFile(getNameData("CIT_CENTRO_HUARMEY_ANCASH.pdf", context)), false));
        fileancash.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Puerto_Punta_Lobitos-Antamina.pdf", "Pto. Pta, Lobitos", "Puerto_Punta_Lobitos-Antamina.pdf", isStatusFile(getNameData("Puerto_Punta_Lobitos-Antamina.pdf", context)), false));

        List<FileProvince> filelima = new ArrayList<>();
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/norte/Caleta_Paramonga.pdf", "Caleta Paramonga", "Caleta_Paramonga.pdf", isStatusFile(getNameData("Caleta_Paramonga.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_BARRANCA_LIMA.pdf", "Balneario Barranca", "CIT_CENTRO_BARRANCA_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_BARRANCA_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Pto_supe.pdf", "Puerto Supe", "Pto_supe.pdf", isStatusFile(getNameData("Pto_supe.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Carquin_2013_corregido.pdf", "Caleta Carquin", "Carquin_2013_corregido.pdf", isStatusFile(getNameData("Carquin_2013_corregido.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/norte/CIT_CENTRO_VIDAL_LIMA.pdf", "Caleta Vidal", "CIT_CENTRO_VIDAL_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_VIDAL_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/norte/Caleta_Vegueta.pdf", "Caleta Vegueta", "Caleta_Vegueta.pdf", isStatusFile(getNameData("Caleta_Vegueta.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_HUACHO_LIMA.pdf", "Puerto Huacho", "CIT_CENTRO_HUACHO_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_HUACHO_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_CHANCAY_LIMA.pdf", "Puerto Chancay", "CIT_CENTRO_CHANCAY_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_CHANCAY_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_ANCON_LIMA.pdf", "Balneario Ancon", "CIT_CENTRO_ANCON_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_ANCON_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_SANTA%20ROSA_LIMA.pdf", "Playa Santa Rosa", "CIT_CENTRO_SANTA%20ROSA_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_SANTA%20ROSA_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Miraflores.pdf", "Miraflores", "Miraflores.pdf", isStatusFile(getNameData("Miraflores.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Magdalena_Mar_2013.pdf", "Magdalena del Mar", "Magdalena_Mar_2013.pdf", isStatusFile(getNameData("Magdalena_Mar_2013.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/norte/Chorrillos.pdf", "Chorrillos", "Chorrillos.pdf", isStatusFile(getNameData("Chorrillos.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/villachorrillos.pdf", "Playa Villa Chorillos", "villachorrillos.pdf", isStatusFile(getNameData("villachorrillos.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_VILLA%20EL%20SALVADOR_LIMA.pdf", "Playa Villa El Salvador", "CIT_CENTRO_VILLA%20EL%20SALVADOR_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_VILLA%20EL%20SALVADOR_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/lurin.pdf", "Lurín", "lurin.pdf", isStatusFile(getNameData("lurin.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Playa_Punta_Hermosa.pdf", "Playa Punta Hermosa", "Playa_Punta_Hermosa.pdf", isStatusFile(getNameData("Playa_Punta_Hermosa.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_SAN%20BARTOLO_LIMA.pdf", "Playa San Bartolo", "CIT_CENTRO_SAN%20BARTOLO_LIMA.pdf", isStatusFile(getNameData("CIT_CENTRO_SAN%20BARTOLO_LIMA.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Pucusana.pdf", "Pucusana", "Pucusana.pdf", isStatusFile(getNameData("Pucusana.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Chilca.pdf", "Chilca", "Chilca.pdf", isStatusFile(getNameData("Chilca.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Totoritas.pdf", "Totoritas", "Totoritas.pdf", isStatusFile(getNameData("Totoritas.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Bujama.pdf", "Bujama", "Bujama.pdf", isStatusFile(getNameData("Bujama.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Asia.pdf", "Asia", "Asia.pdf", isStatusFile(getNameData("Asia.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sarapampa_final.pdf", "Balneario Sarapampa", "sarapampa_final.pdf", isStatusFile(getNameData("sarapampa_final.pdf", context)), false));
        filelima.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Pto_Cerroazul.pdf", "Puerto Cerro Azul", "Pto_Cerroazul.pdf", isStatusFile(getNameData("Pto_Cerroazul.pdf", context)), false));

        List<FileProvince> filecallao = new ArrayList<>();
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/balneario_vent_300.jpg", "Balneario Ventanilla", "balneario_vent_300.jpg", isStatusFile(getNameData("balneario_vent_300.jpg", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/playa_ventanilla_300.jpg", "Playa Ventanilla", "playa_ventanilla_300.jpg", isStatusFile(getNameData("playa_ventanilla_300.jpg", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/ventanilla_300.jpg", "Ventanilla", "ventanilla_300.jpg", isStatusFile(getNameData("ventanilla_300.jpg", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/centro/Marquez_2014.pdf", "Marquez", "Marquez_2014.pdf", isStatusFile(getNameData("Marquez_2014.pdf", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Oquendo.pdf", "Hacienda Oquendo", "Oquendo.pdf", isStatusFile(getNameData("Oquendo.pdf", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/200_millas_300.jpg", "Urb. 200 Millas", "200_millas_300.jpg", isStatusFile(getNameData("200_millas_300.jpg", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sagus_300.jpg", "San Agustín", "sagus_300.jpg", isStatusFile(getNameData("sagus_300.jpg", context)), false));
        filecallao.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/La_Punta_2014.pdf", "La Punta -(PNUD-SIRAD)", "La_Punta_2014.pdf", isStatusFile(getNameData("La_Punta_2014.pdf", context)), false));

        List<FileProvince> fileica = new ArrayList<>();
        fileica.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Caleta_TambodeMora_110211_RUTAS_EVAC.pdf", "Tambo de Mora", "Caleta_TambodeMora_110211_RUTAS_EVAC.pdf", isStatusFile(getNameData("Caleta_TambodeMora_110211_RUTAS_EVAC.pdf", context)), false));
        fileica.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_PISCO_ICA.pdf", "Balneario Pisco", "CIT_CENTRO_PISCO_ICA.pdf", isStatusFile(getNameData("CIT_CENTRO_PISCO_ICA.pdf", context)), false));
        fileica.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_SAN%20ANDRÉS_ICA.pdf", "Caleta San Andrés", "CIT_CENTRO_SAN%20ANDRÉS_ICA.pdf", isStatusFile(getNameData("CIT_CENTRO_SAN%20ANDRÉS_ICA.pdf", context)), false));
        fileica.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/CIT_CENTRO_PARACAS_ICA.pdf", "Bahía Paracas", "CIT_CENTRO_PARACAS_ICA.pdf", isStatusFile(getNameData("CIT_CENTRO_PARACAS_ICA.pdf", context)), false));
        fileica.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Puerto_sanjuandemarcona.pdf", "San Juan de Marcona", "Puerto_sanjuandemarcona.pdf", isStatusFile(getNameData("Puerto_sanjuandemarcona.pdf", context)), false));

        List<FileProvince> filearequipa = new ArrayList<>();
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/CaletaLomasCIT_SUR_040311.pdf", "Caleta Lomas", "CaletaLomasCIT_SUR_040311.pdf", isStatusFile(getNameData("CaletaLomasCIT_SUR_040311.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/chala.pdf", "Puerto Chala", "chala.pdf", isStatusFile(getNameData("chala.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/La_planchada_2013.pdf", "La Planchada", "La_planchada_2013.pdf", isStatusFile(getNameData("La_planchada_2013.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/atico_arequipa_corregido.pdf", "Puerto Atico", "atico_arequipa_corregido.pdf", isStatusFile(getNameData("atico_arequipa_corregido.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/Camana_A_New_model_final.pdf", "Balneario de Camaná A", "Camana_A_New_model_final.pdf", isStatusFile(getNameData("Camana_A_New_model_final.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/Camana_B_New_model_final.pdf", "Balneario de Camaná B", "Camana_B_New_model_final.pdf", isStatusFile(getNameData("Camana_B_New_model_final.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/Camana_C_New_model_final.pdf", "Balneario de Camaná C", "Camana_C_New_model_final.pdf", isStatusFile(getNameData("Camana_C_New_model_final.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/quilca-arequipa.pdf", "Caleta Quilca", "quilca-arequipa.pdf", isStatusFile(getNameData("quilca-arequipa.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/matarani.pdf", "Puerto Matarani", "matarani.pdf", isStatusFile(getNameData("matarani.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/mollendo.jpg", "Puerto Mollendo", "mollendo.jpg", isStatusFile(getNameData("mollendo.jpg", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/mejia_arequipa_2013.pdf", "Baln. Mejia", "mejia_arequipa_2013.pdf", isStatusFile(getNameData("mejia_arequipa_2013.pdf", context)), false));
        filearequipa.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/sur/la_punta_bombom_catas-areq.pdf", "Punta - Bombón - Catas", "la_punta_bombom_catas-areq.pdf", isStatusFile(getNameData("la_punta_bombom_catas-areq.pdf", context)), false));

        List<FileProvince> filemoquecua = new ArrayList<>();
        filemoquecua.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Ilo_2007.pdf", "Puerto de Ilo", "Ilo_2007.pdf", isStatusFile(getNameData("Ilo_2007.pdf", context)), false));

        List<FileProvince> filetacna = new ArrayList<>();
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Vila_Vila_2013.pdf", "Caleta Vila Vila", "Vila_Vila_2013.pdf", isStatusFile(getNameData("Vila_Vila_2013.pdf", context)), false));
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/GrauSama_Oceano.pdf", "Caleta Grau (Morrosama)", "GrauSama_Oceano.pdf", isStatusFile(getNameData("GrauSama_Oceano.pdf", context)), false));
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Brtyo_Oceano.pdf", "Baln. Tomoyo y Boca del Río", "Brtyo_Oceano.pdf", isStatusFile(getNameData("Brtyo_Oceano.pdf", context)), false));
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Llostay_Oceano.pdf", "Playa Llostay", "Llostay_Oceano.pdf", isStatusFile(getNameData("Llostay_Oceano.pdf", context)), false));
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/Yarada_Oceano.pdf", "Playa La Yarada", "Yarada_Oceano.pdf", isStatusFile(getNameData("Yarada_Oceano.pdf", context)), false));
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/LosPalos_Oceano.pdf", "Playa Los Palos", "LosPalos_Oceano.pdf", isStatusFile(getNameData("LosPalos_Oceano.pdf", context)), false));
        filetacna.add(new FileProvince(URL_BASE + "/images/cartas_inundacion/StaRosa.pdf", "Playa Sta. Rosa", "StaRosa.pdf", isStatusFile(getNameData("StaRosa.pdf", context)), false));

        List<Provinces> provincesList = new ArrayList<>();

        provincesList.add(new Provinces("TUMBES", R.mipmap.carta_01, filetumbes, false));
        provincesList.add(new Provinces("PIURA", R.mipmap.carta_02, filepiura, false));
        provincesList.add(new Provinces("LAMBAYEQUE", R.mipmap.carta_03, filelambayegue, false));
        provincesList.add(new Provinces("LA LIBERTAD", R.mipmap.carta_04, filelalibertad, false));
        provincesList.add(new Provinces("ANCASH", R.mipmap.carta_05, fileancash, false));
        provincesList.add(new Provinces("LIMA", R.mipmap.carta_06, filelima, false));
        provincesList.add(new Provinces("CALLAO", R.mipmap.carta_07, filecallao, false));
        provincesList.add(new Provinces("ICA", R.mipmap.carta_08, fileica, false));
        provincesList.add(new Provinces("AREQUIPA", R.mipmap.carta_09, filearequipa, false));
        provincesList.add(new Provinces("MOQUEGUA", R.mipmap.carta_10, filemoquecua, false));
        provincesList.add(new Provinces("TACNA", R.mipmap.carta_11, filetacna, false));

        return provincesList;
    }

    private static String getNameData(String name, Context context) {
        connexion = new Setences(context);
        for (FileDate x : connexion.lista_histori()) {
            if (x.getName().equals(name.trim().replace("%20", " ")))
                return x.getBaseUrl();
        }
        return "";
    }

    private static boolean isStatusFile(String url) {
        File file = new File(url);
        if (file.exists()) {
            return true;
        }
        return false;
    }
}