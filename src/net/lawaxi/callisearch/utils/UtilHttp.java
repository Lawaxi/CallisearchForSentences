package net.lawaxi.callisearch.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilHttp {

    public static final InputStream getInputStream (String path) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) (new URL(path)).openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                return httpURLConnection.getInputStream();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static final String getInputText (String path){
        InputStream  inputStream = getInputStream(path);
        try {
            if(inputStream!=null)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String s="";
                while(reader.read()!=-1) {
                    s += reader.readLine();
                }
                return s;
            }
        }
        catch (Exception e){}
        return "";
    }
}
