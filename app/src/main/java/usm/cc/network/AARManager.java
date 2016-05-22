package usm.cc.network;

import android.accounts.NetworkErrorException;
import android.app.Application;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import usm.cc.View.LoginActivity;

/**
 * Created by niko on 19/05/2016.
 */
public class AARManager {

    public static final String USER_AGENT = "Chrome/30.0.0.0";

    private String url;
    private String user_agent;

    /**
     * Default constructor
     *
     * @param url location of the resource
     */
    public AARManager(String url) {
        this.url = url;
        this.user_agent = USER_AGENT;
    }

    /**
     * Optional constructor, can set a different User Agent
     *
     * @param url        location of the resource
     * @param user_agent user agent to be used by the HttpURLConnection
     */
    public AARManager(String url, String user_agent) {
        this.url = url;
        this.user_agent = user_agent;
    }

    /**
     * Gets the raw data from the resource
     *
     * @return raw json string
     */
    public String getRawResource() throws JSONException, IOException, NetworkErrorException {
        return downloadData(url, user_agent);
    }

    /**
     * Gets the useful stuff from the data tag
     *
     * @return String with the raw data ready to use
     * @throws IOException
     * @throws JSONException
     */
    public String getJsonData() throws JSONException, IOException, NetworkErrorException {

        String response = downloadData(url, user_agent);
        JSONObject jsonObject = new JSONObject(response);
        String strData = jsonObject.getString("data"); //we only get the data from the label data, so clever.

        if (strData != null) {
            return strData;
        } else {
            throw new NullPointerException("There is no info");
        }

    }

    /**
     * Connects to the url given and gets the raw data
     *
     * @param url  location of the resource
     * @param user user agent to be used by the HttpURLConnection
     * @return raw json string
     * @throws IOException
     * @throws JSONException
     * @throws NetworkErrorException
     */
    private String downloadData(String url, String user) throws IOException, JSONException, NetworkErrorException {

        HttpURLConnection con;
        BufferedReader in;
        StringBuffer response;

        URL obj = new URL(url);
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", user);

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {
            Log.d("error","error en la conexión");
        }
        return "error";
    }
}
