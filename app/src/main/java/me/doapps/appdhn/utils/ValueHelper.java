package me.doapps.appdhn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Charts;
import me.doapps.appdhn.models.FrequentQuestion;
import me.doapps.appdhn.models.Video;

/**
 * Created by William_ST on 02/10/15.
 */
public class ValueHelper {

    public static final int ZOOM_LEVEL = 12;
    public static final String BULLETIN_TIDES = "MAREAS", BULLETIN_WINDS = "VIENTOS", BULLETIN_SEISMIC = "SISMOS";

    public static final Map<Integer, Video> collectionVideos = new HashMap(){{
        put(0, new Video("#CAMBIEMOSLASCIFRAS", R.mipmap.im_video_cambiemoslascifras, "2015", "url", "ekgAHHVwLh0"));
        put(1, new Video("Conoce más sobre el CNAT", R.mipmap.im_video_institucional, "2015", "url", "p6Zv5I0gc2k"));
        put(2, new Video("¿Sabes que es un Tsunami?", R.mipmap.im_sensibilition, "2015", "url", "Q_zHGUjXMQE"));
        put(4, new Video("Enseñemos a los niños sobre los Tsunamis", R.mipmap.im_sensibilition_boys, "2015", "url", "tWodosWmwaE"));
        put(5, new Video("Spot Televisivo", R.mipmap.im_spot, "2015", "url", "mKC3-Hsg-vE"));
    }};

    public static final List<FrequentQuestion> collectionFrequentQuestion = new ArrayList<FrequentQuestion>(){{
        add(new FrequentQuestion("¿Qué es la carta de inundación por tsunami?", "Las carta de inundación por tsunami es una herramienta de gestión ante el riesgo de inundación por tsunami, proporciona información necesaria para desarrollar planes y acciones de preparación que busca reducir la vulnerabilidad de la población costera."));
        add(new FrequentQuestion("¿Qué hacer en caso de alarma de tsunami?", "Antes:\n" +
                "Mantener siempre lista su mochila salvavidas debidamente equipada con:\n" +
                "- Ropa. \n" +
                "- Agua, galletas y alimentos enlatados.\n" +
                "- Linterna, radio y pilas.\n" +
                "- Kit de primeros auxilios.\n" +
                "- Papel higiénico. \n" +
                "- Dinero.\n" +
                "- Conocer su zona de refugio, a través de rutas de evacuación.\n" +
                "- Participar en los simulacros siguiendo las indicaciones de las autoridades.\n\n" +
                "Durante\n" +
                "Estar preparado, con la mochila de emergencia debidamente equipada con ropa de abrigo, agua, alimentos enlatados, linterna, radio, kit de primeros auxilios.\n" +
                "\n" +
                "Dirigirse por las rutas de evacuación hacia el área de refugio más cercano establecido en las cartas de inundación por tsunami.\n" +
                "No bajar a la playa a observar las olas\n\n" +
                "Después\n" +
                "Mantener la radio encendida \n" +
                "No alejarse del grupo o buscar compañía\n" +
                "Esperar las indicaciones de las autoridades\n"));
        add(new FrequentQuestion("¿Qué es un tsunami?", "Un tsunami es un conjunto de olas que se producen a causa de un movimiento sísmico de gran magnitud con epicentro en el mar provocando una violenta perturbación en la superficie oceánica, propagando ondas en todas direcciones causando destrucción en la costa, conocido también como maremoto"));
        add(new FrequentQuestion("¿A quienes afecta?", "Los tsunamis afectan a la población que habita en la costa; en especial en zonas costeras bajas, sientes un sismo muy fuerte, ¡aléjate de la playa! y ubica tu zona de refugio, pues es la alerta natural de un tsunami."));
        add(new FrequentQuestion("¿Qué es ALARMATOPIC?", "Una alarma es una comunicación que corresponde a la confirmación inminente de la ocurrencia de un fenómeno peligroso que afectará a una determinada zona, para lo cual las autoridades competentes deberán activar los planes de evacuación de la población."));
        add(new FrequentQuestion("¿Qué es ALARMATOPIC?", "Es un estado que se declara con el fin que los organismos operativos activen protocolos de acción para que la población tome precauciones específicas debido a la “posible” ocurrencia de un Tsunami que afecta a una determinada zona."));
        add(new FrequentQuestion("¿Quiénes son los responsables en emitir los boletines  de alerta y alarma?", "La Dirección de Hidrografía y Navegación de la Marina de Guerra del Perú es el ente encargado de emitir un boletín de alerta y/o de alarma."));
        add(new FrequentQuestion("¿Que nos muestra una Carta de Inundación por tsunami?", "Nos muestra la cota de inundación máxima que alcanzará el efecto del mar en la costa  como producto de  Tsunami, asimismo muestra información precisa de zonas de refugio así como las rutas de evacuación de los  puertos, poblados o balnearios  entre otros.\n"));
    }};

    public static final List<Charts> collectionChart = new ArrayList<Charts>(){{

        add(new Charts("TUMBES", R.mipmap.carta_01, "Puerto Pizarro", "images/cartas_inundacion/norte/Puerto_Pizarro.pdf"));
        add(new Charts("TUMBES", R.mipmap.carta_01, "Caleta La Cruz", "images/cartas_inundacion/LAcruz.jpg"));
        add(new Charts("TUMBES", R.mipmap.carta_01, "Caleta Grau", "images/cartas_inundacion/norte/caleta_Grau.pdf"));
        add(new Charts("TUMBES", R.mipmap.carta_01, "Puerto Zorritos", "images/cartas_inundacion/Pto_zorritos_final.pdf"));
        add(new Charts("TUMBES", R.mipmap.carta_01, "Contralmirante Villar", "images/cartas_inundacion/norte/villar-tumbes.pdf"));

        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Mancora", "images/cartas_inundacion/Mancora.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Los Organos", "images/cartas_inundacion/Losorganos.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Lobitos", "images/cartas_inundacion/norte/Caleta_Lobitos.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Talara", "images/cartas_inundacion/norte/talara-piura.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Negritos", "images/cartas_inundacion/norte/Caleta_NEGRITOS.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Paita", "images/cartas_inundacion/paita.jpg"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Constante", "images/cartas_inundacion/norte/constante-piura.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Parachique", "images/cartas_inundacion/norte/Caleta_Parachique2016.pdf"));
        add(new Charts("PIURA", R.mipmap.carta_02, "Caleta Puerto Rico", "images/cartas_inundacion/norte/PuertoRico2016.pdf"));

        add(new Charts("LAMBAYEQUE", R.mipmap.carta_03, "Caleta San Jose", "images/cartas_inundacion/Sanjose.pdf"));
        add(new Charts("LAMBAYEQUE", R.mipmap.carta_03, "Caleta Santa Rosa", "images/cartas_inundacion/SantaRosa.pdf"));
        add(new Charts("LAMBAYEQUE", R.mipmap.carta_03, "Puerto Pimentel", "images/cartas_inundacion/Pto_pimentel.pdf"));
        add(new Charts("LAMBAYEQUE", R.mipmap.carta_03, "Puerto Eten", "images/cartas_inundacion/Eten.pdf"));

        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Puerto Pacasmayo", "images/cartas_inundacion/Puerto_Pacasmayo.pdf"));
        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Puerto Chicama", "images/cartas_inundacion/Chicama.pdf"));
        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Bal. Huanchaco", "images/cartas_inundacion/Huanchaco.jpg"));
        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Bal, Huanchaquito", "images/cartas_inundacion/Huanchaquito.jpg"));
        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Bal. Buenos Aires", "images/cartas_inundacion/BsAs_2012.pdf"));
        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Bal. Las Delicias", "images/cartas_inundacion/Delicias.jpg"));
        add(new Charts("LA LIBERTAD", R.mipmap.carta_04, "Puerto Salaverry", "images/cartas_inundacion/Puerto_Salaverry.pdf"));

        add(new Charts("ANCASH", R.mipmap.carta_05, "Puerto Santa", "/images/cartas_inundacion/CIT_CENTRO_SANTA_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Caleta Coishco", "/images/cartas_inundacion/Cal_Coishco.jpg"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Puerto Chimbote", "/images/cartas_inundacion/CIT_CENTRO_CHIMBOTE_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Balneario Vesique", "/images/cartas_inundacion/CIT_CENTRO_VESIQUE_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Puerto Samanco", "/images/cartas_inundacion/norte/CIT_CENTRO_SAMANCO_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Caleta Los Chimus", "/images/cartas_inundacion/CIT_CENTRO_CHIMUS_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Balneario Tortugas", "/images/cartas_inundacion/CIT_CENTRO_TORTUGAS_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Puerto Casma", "/images/cartas_inundacion/CIT_CENTRO_CASMA_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Caleta Culebras", "/images/cartas_inundacion/CIT_CENTRO_CULEBRAS_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Puerto huarmey", "/images/cartas_inundacion/CIT_CENTRO_HUARMEY_ANCASH.pdf"));
        add(new Charts("ANCASH", R.mipmap.carta_05, "Pto. Pta, Lobitos", "/images/cartas_inundacion/Puerto_Punta_Lobitos-Antamina.pdf"));

        add(new Charts("LIMA", R.mipmap.carta_06, "Caleta Paramonga", "/images/cartas_inundacion/norte/Caleta_Paramonga.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Balneario Barranca", "/images/cartas_inundacion/CIT_CENTRO_BARRANCA_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Puerto Supe", "/images/cartas_inundacion/Pto_supe.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Caleta Carquin", "images/cartas_inundacion/Carquin_2013_corregido.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Caleta Vidal", "/images/cartas_inundacion/norte/CIT_CENTRO_VIDAL_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Caleta Vegueta", "/images/cartas_inundacion/norte/Caleta_Vegueta.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Puerto Huacho", "/images/cartas_inundacion/CIT_CENTRO_HUACHO_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Puerto Chancay", "/images/cartas_inundacion/CIT_CENTRO_CHANCAY_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Balneario Ancon", "/images/cartas_inundacion/CIT_CENTRO_ANCON_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Playa Santa Rosa", "/images/cartas_inundacion/CIT_CENTRO_SANTA%20ROSA_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Miraflores", "/images/cartas_inundacion/Miraflores.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Magdalena del Mar", "/images/cartas_inundacion/Magdalena_Mar_2013.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Chorrillos", "/images/cartas_inundacion/norte/Chorrillos.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Playa Villa Chorillos", "/images/cartas_inundacion/villachorrillos.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Playa Villa El Salvador", "/images/cartas_inundacion/CIT_CENTRO_VILLA%20EL%20SALVADOR_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Lurín", "/images/cartas_inundacion/lurin.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Playa Punta Hermosa", "/images/cartas_inundacion/Playa_Punta_Hermosa.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Playa San Bartolo", "/images/cartas_inundacion/CIT_CENTRO_SAN%20BARTOLO_LIMA.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Pucusana", "/images/cartas_inundacion/Pucusana.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Chilca", "/images/cartas_inundacion/Chilca.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Totoritas", "/images/cartas_inundacion/Totoritas.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Bujama", "/images/cartas_inundacion/Bujama.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Asia", "/images/cartas_inundacion/Asia.pdf"));
        add(new Charts("LIMA", R.mipmap.carta_06, "Puerto Cerro Azul", "/images/cartas_inundacion/Pto_Cerroazul.pdf"));

        add(new Charts("CALLAO", R.mipmap.carta_07, "Balneario Ventanilla", "/images/cartas_inundacion/balneario_vent_300.jpg"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "Playa Ventanilla", "/images/cartas_inundacion/playa_ventanilla_300.jpg"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "Ventanilla", "/images/cartas_inundacion/ventanilla_300.jpg"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "Marquez", "/images/cartas_inundacion/centro/Marquez_2014.pdf"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "Hacienda Oquendo", "/images/cartas_inundacion/Oquendo.pdf"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "Urb. 200 Millas", "/images/cartas_inundacion/200_millas_300.jpg"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "San Agustín", "/images/cartas_inundacion/sagus_300.jpg"));
        add(new Charts("CALLAO", R.mipmap.carta_07, "La Punta -&nbsp(PNUD-SIRAD)", "/images/cartas_inundacion/La_Punta_2014.pdf"));

        add(new Charts("ICA", R.mipmap.carta_08, "Tambo de Mora", "/images/cartas_inundacion/Caleta_TambodeMora_110211_RUTAS_EVAC.pdf"));
        add(new Charts("ICA", R.mipmap.carta_08, "Balneario Pisco ", "/images/cartas_inundacion/CIT_CENTRO_PISCO_ICA.pdf"));
        add(new Charts("ICA", R.mipmap.carta_08, "Caleta San Andrés", "/images/cartas_inundacion/CIT_CENTRO_SAN%20ANDRÉS_ICA.pdf"));
        add(new Charts("ICA", R.mipmap.carta_08, "Bahía Paracas", "/images/cartas_inundacion/CIT_CENTRO_PARACAS_ICA.pdf"));
        add(new Charts("ICA", R.mipmap.carta_08, "San Juan de Marcona ", "/images/cartas_inundacion/Puerto_sanjuandemarcona.pdf"));

        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Caleta Lomas", "/images/cartas_inundacion/sur/CaletaLomasCIT_SUR_040311.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Puerto Chala", "/images/cartas_inundacion/sur/chala.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "La Planchada", "/images/cartas_inundacion/sur/La_planchada_2013.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Puerto Atico", "/images/cartas_inundacion/sur/atico_arequipa_corregido.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Balneario de Camaná A", "/images/cartas_inundacion/sur/Camana_A_New_model_final.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Balneario de Camaná B", "/images/cartas_inundacion/sur/Camana_B_New_model_final.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Balneario de Camaná C", "/images/cartas_inundacion/sur/Camana_C_New_model_final.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Caleta Quilca", "/images/cartas_inundacion/sur/quilca-arequipa.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Puerto Matarani", "/images/cartas_inundacion/sur/matarani.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Puerto Mollendo", "/images/cartas_inundacion/mollendo.jpg"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Baln. Mejia", "/images/cartas_inundacion/sur/mejia_arequipa_2013.pdf"));
        add(new Charts("AREQUIPA", R.mipmap.carta_09, "Punta - Bombón - Catas", "/images/cartas_inundacion/sur/la_punta_bombom_catas-areq.pdf"));

        add(new Charts("MOQUEGUA", R.mipmap.carta_10, "Puerto de Ilo", "/images/cartas_inundacion/Ilo_2007.pdf"));

        add(new Charts("TACNA", R.mipmap.carta_11, "Caleta Vila Vila", "images/cartas_inundacion/Vila_Vila_2013.pdf"));
        add(new Charts("TACNA", R.mipmap.carta_11, "Caleta Grau (Morrosama)", "images/cartas_inundacion/GrauSama_Oceano.pdf"));
        add(new Charts("TACNA", R.mipmap.carta_11, "Baln. Tomoyo y Boca del Río", "images/cartas_inundacion/Brtyo_Oceano.pdf"));
        add(new Charts("TACNA", R.mipmap.carta_11, "Playa Llostay", "images/cartas_inundacion/Llostay_Oceano.pdf"));
        add(new Charts("TACNA", R.mipmap.carta_11, "Playa La Yarada", "images/cartas_inundacion/Yarada_Oceano.pdf"));
        add(new Charts("TACNA", R.mipmap.carta_11, "Playa Los Palos", "images/cartas_inundacion/LosPalos_Oceano.pdf"));
        add(new Charts("TACNA", R.mipmap.carta_11, "Playa Sta. Rosa", "images/cartas_inundacion/StaRosa.pdf"));
    }};
}
