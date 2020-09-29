/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reto1;
import java.io.*;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author Alexandru
 */
public class Conversor {
    
    public static double getRate(String monedaOrigen, String monedaDestino) throws Exception {
        String urlString = "https://free.currconv.com/api/v7/convert?q=" + monedaOrigen + "_" + monedaDestino + "&compact=ultra&apiKey=ddd381c0195c88cd0c0e";
        JSONObject jsonRate = getJSON(urlString);
        return jsonRate.getDouble(monedaOrigen + "_" + monedaDestino);
    }
    
    private static JSONObject getJSON(String urlString) throws Exception{
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        int status = con.getResponseCode();
        JSONObject json = null;
        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
                json = new JSONObject(sb.toString());
        }
        return json;
    }
}
