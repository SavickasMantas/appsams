package edu.ktu.lab2;

import android.view.Display;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestOperator extends Thread {
    public interface RequestOperatorListener{
        //void success (ModelPost publication);
        void success(ArrayList<ModelPost> publications);
        void failed (int responseCode);
    }

    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener(RequestOperatorListener listener) {
        this.listener = listener;
    }

    @Override
    public void run(){
        super.run();
        try {
            ArrayList<ModelPost> publications = request();

            if(publications != null){
                success(publications);
            }else{
                failed(responseCode);
            }
        }catch (IOException e){
            failed(-1);
        }catch (JSONException e){
            failed(-2);
        }
    }

    private ArrayList<ModelPost> request() throws IOException, JSONException{

        URL obj = new URL("http://jsonplaceholder.typicode.com/posts");

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");

        responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        InputStreamReader streamReader;

        if (responseCode == 200){
            streamReader = new InputStreamReader(con.getInputStream());
        }else{
            streamReader = new InputStreamReader(con.getErrorStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());

        if (responseCode == 200)
            return parsingJsonObject(response.toString());
        else
            return null;
    }

    public ArrayList<ModelPost> parsingJsonObject(String response) throws JSONException{
            JSONArray array = new JSONArray(response);
            ArrayList<ModelPost> posts = new ArrayList<>();
            for (int i=0; i < array.length(); i++) {
                ModelPost post = new ModelPost();
                post.setId(array.getJSONObject(i).optInt("id", 0));
                post.setUserId(array.getJSONObject(i).optInt("userId", 0));

                post.setTitle(array.getJSONObject(i).getString("title"));
                post.setBodyText(array.getJSONObject(i).getString("body"));
                posts.add(post);
            }
            return posts;
    }

    private void failed(int code){
        if (listener != null)
            listener.failed(code);
    }
    private void success(ArrayList<ModelPost> publications){
        if (listener != null){
            listener.success(publications);
        }
    }
}
