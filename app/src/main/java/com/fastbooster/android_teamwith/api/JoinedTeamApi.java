package com.fastbooster.android_teamwith.api;

public class JoinedTeamApi {
    public static JSONObject getJoinedTeam() throws Exception{
        JSONObject json=null;
        URL url = new URL("http://192.168.30.64:8089/api/teamInfo/joinedTeam");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(10000);
        conn.setDoInput(true);
        int responseCode = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //JSON object 받아오기
            json = new JSONObject(sb.toString());
            conn.disconnect();
            return json;
        }
        conn.disconnect();
        return null;
    }
}
