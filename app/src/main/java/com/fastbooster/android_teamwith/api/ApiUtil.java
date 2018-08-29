package com.fastbooster.android_teamwith.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ApiUtil {
    private static final String URL_STR = "http://192.168.30.64:8089/api";

    public static JSONObject getJsonObject(String urlStr) {
        try {
            return new JSONObject(getData(null, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getMyJsonObject(Context context, String urlStr) {
        try {
            return new JSONObject(getData(context, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getJsonArray(String urlStr) {
        try {
            return new JSONArray(getData(null, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getMyJsonArray(Context context, String urlStr) {
        try {
            return new JSONArray(getData(context, urlStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getData(Context context, String urlStr) throws Exception {
        HttpURLConnection conn = null;
        URL url = new URL(URL_STR + urlStr);
        Log.v("request URL", URL_STR + urlStr);
        StringBuilder sb = new StringBuilder();

        conn = (HttpURLConnection) url.openConnection();

        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences("memberPref", Context.MODE_PRIVATE);
            conn.setRequestProperty("Cookie", sp.getString("sessionId", ""));
        }

        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setConnectTimeout(1000);


        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } else {
            Log.d("Teamwith app error", "URL=" + urlStr);
        }

        conn.disconnect();

        return sb.toString();
    }

    public static String sendImage(String sessionId, Bitmap file) throws Exception {
        HttpURLConnection conn = null;
        URL url = new URL(URL_STR+"/member/image");
        Log.v("request URL", URL_STR);
        StringBuilder sb = new StringBuilder();

        conn = (HttpURLConnection) url.openConnection();

        String boundary = "*****";
        String lineEnd = "\r\n";
        String twoHyphens = "--";

        conn.setRequestProperty("Cookie", sessionId);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(10000);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

        // write data
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.writeBytes(twoHyphens + boundary + lineEnd);
        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
                + UUID.randomUUID() + "\"" + lineEnd);
        dos.writeBytes(lineEnd);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        file.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
        InputStream mFileInputStream = bs;

        int bytesAvailable = mFileInputStream.available();
        int maxBufferSize = 1024 * 1024 * 10;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);

        byte[] buffer = new byte[bufferSize];
        int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);


        // read image
        while (bytesRead > 0) {
            dos.write(buffer, 0, bufferSize);
            bytesAvailable = mFileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
        }

        dos.writeBytes(lineEnd);
        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

        // close streams
        mFileInputStream.close();
        dos.flush(); // finish upload...
        dos.close();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } else {
            Log.d("Teamwith app error", "사진 올리기 오류");
        }

        conn.disconnect();
        Log.v("camera result", sb.toString());
        return sb.toString();
    }
}
