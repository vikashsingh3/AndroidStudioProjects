package com.project.mysqltest;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String BOOK_BASE_URL =  "http://192.168.0.24:8080/demo_php/login.php";
//    private static final String BOOK_BASE_URL =  "http://localhost:8080/demo_php/login.php";
    private static final String QUERY_PARAM = "q"; // Parameter for the search string.
//    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results.
//    private static final String PRINT_TYPE = "printType"; // Parameter to filter by print type.

    private static final String USER_NAME = "USER_NAME"; // Parameter that limits search results.
    private static final String PASSWORD = "PASSWORD"; // Parameter to filter by print type.

    // Class name for Log tag.
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    /**
     * Method for downloading book information from the Books API based on a search term.
     * This method makes a network call so it can not be called on the main thread.
     * @param queryUserName The search term for the Books API query
     * @return The raw response from the API as a JSON String
     */
    static String getBookInfo(String queryUserName, String queryPassword){

        // Set up variables for the try block that need to be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        // Attempt to query the Books API.
        try {
            // Base URI for the Books API.


            // Build up your query URI, limiting results to 10 items and printed books.
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(USER_NAME, queryUserName)
                    .appendQueryParameter(PASSWORD, queryPassword)
                    .build();

            Log.i("VKS", builtURI.toString());
            URL requestURL = new URL(builtURI.toString());

            // Open the network connection.
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Read the response string into a StringBuilder.
            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // but it does make debugging a *lot* easier if you print out the completed buffer for debugging.
                builder.append(line + "\n");
            }

            if (builder.length() == 0) {
                // Stream was empty.  No point in parsing.
                // return null;
                return null;
            }
            bookJSONString = builder.toString();

            // Catch errors.
        } catch (IOException e) {
            e.printStackTrace();

            // Close the connections.
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        // Return the raw response.
        return bookJSONString;
    }
}
