package com.project.onlinequiz;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String APP_BASE_LIVE_URL = "http://13.54.185.113/api/";     // PROD TBD
    private static final String APP_BASE_DEV_URL = "http://13.54.185.113/api/";

        private static final String ACTIVITY = "event";
    private static final String LOG_TAG = "ONLINE TICKET: " + NetworkUtils.class.getSimpleName();


    public static String ticket_details(String env, String category) {
        String APP_BASE_URL;
        if (env.equals("LIVE")) {
            APP_BASE_URL = APP_BASE_LIVE_URL;
        } else {
            APP_BASE_URL = APP_BASE_DEV_URL;
        }

        HttpURLConnection urlScanConnection = null;
        BufferedReader reader = null;
        String url_response;
        try {
            Uri builtURI = Uri.parse(APP_BASE_URL).buildUpon()
                    .appendQueryParameter(ACTIVITY, "ticket")
                    .appendQueryParameter("category", category)
                    .build();

            URL requestURL = new URL(builtURI.toString());
            Log.i(LOG_TAG, builtURI.toString());

            // Open the network connection.
            urlScanConnection = (HttpURLConnection) requestURL.openConnection();
            urlScanConnection.setRequestMethod("GET");
            urlScanConnection.setConnectTimeout(10000);
            urlScanConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlScanConnection.getInputStream();

            // Read the response string into a StringBuilder.
            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }
            url_response = builder.toString();

        } catch (IOException e) {
            Log.i(LOG_TAG, e.toString());
            return null;

            // Close the connections.
        } finally {
            try {
                if (urlScanConnection != null) {
                    urlScanConnection.disconnect();
                }
            } catch (Exception e) {
                Log.i(LOG_TAG, e.toString());
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.i(LOG_TAG, e.toString());
                }
            }
        }

        // Return the response.
        return url_response;
    }

}
