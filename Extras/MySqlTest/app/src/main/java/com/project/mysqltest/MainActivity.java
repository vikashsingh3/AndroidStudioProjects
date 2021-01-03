package com.project.mysqltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    EditText username, password;
    TextView response;

    // FOR MYSQL **********
    private static String myIP = "192.168.0.24";
    private static String myPORT = "3306";
    private static String myUSER_NAME = "demo_test";
    private static String myPASSWORD = "demo_test";
//    private static String myClass = "net.sourceforge.jtdc.jdbc.Driver";
    private static String myClass = "com.project.mysqltest.jdbc.Driver";
    private static String myDB = "php_test_db";
    private static String myURL = "jdbc:mysql://" + myIP+":"+myPORT+"/"+myDB;

    private Connection connection=null;
    // FOR MYSQL **********



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        response = findViewById(R.id.response);


        // FOR MYSQL **********
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(myClass);
            connection = DriverManager.getConnection(myURL, myUSER_NAME, myPASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            Log.i("MYSQL", "Error: 1. "+ e.toString());
        }
        // FOR MYSQL **********




//        //Check if a Loader is running, if it is, reconnect to it
//        if(getSupportLoaderManager().getLoader(0)!=null){
//            getSupportLoaderManager().initLoader(0,null,this);
//        }

    }

    public void btn_login(View view) {
        String user_id = username.getText().toString();
        String user_pwd = password.getText().toString();


        // Hide the keyboard when the button is pushed.
//        InputMethodManager inputManager = (InputMethodManager)
//                getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                InputMethodManager.HIDE_NOT_ALWAYS);

/*
        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty,
        // add the search term to the arguments Bundle and start the loader.
        if (networkInfo != null && networkInfo.isConnected() && user_id.length()!=0 && user_pwd.length()!=0) {
            response.setText("");
            response.setText(R.string.loading);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryUserName", user_id);
            queryBundle.putString("queryUserPassword", user_pwd);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // Otherwise update the TextView to tell the user there is no connection or no search term.
        else {
            if (user_id.length() == 0) {
                response.setText("Invalid User Name");
//                response.setText(R.string.no_search_term);
            } else {
                response.setText("No Network");
//                mTitleText.setText(R.string.no_network);
            }
        }

*/

        // FOR MYSQL **********
        if(connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * FROM tbl_login_details " +
                        " where user_id = '" + user_id + "' and pwd = '" + user_pwd +  "';");

                response.setText("From MySQL: Welcome" + resultSet.getString(1));
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                Log.i("MYSQL", "Error: 2. "+ throwables.toString());
            }
        }else {
            response.setText("From MySQL: No connection" );
        }
        // FOR MYSQL **********
    }



    /**
     * The LoaderManager calls this method when the loader is created.
     *
     * @param id ID integer to id   entify the instance of the loader.
     * @param args The bundle that contains the search parameter.
     * @return Returns a new BookLoader containing the search term.
     */
    @Override
    public androidx.loader.content.Loader<String> onCreateLoader(int id, Bundle args){
//    public androidx.loader.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, args.getString("queryUserName"), args.getString("queryUserPassword"));
    }

    @Override
    public void onLoadFinished(@NonNull androidx.loader.content.Loader<String> loader, String data) {
        response.setText("Result: " + data);
    }

    @Override
    public void onLoaderReset(@NonNull androidx.loader.content.Loader<String> loader) {

    }

    /**
     * Called when the data has been loaded. Gets the desired information from
     * the JSON and updates the Views.
     *
     * @param loader The loader that has finished.
     * @param data The JSON response from the Books API.
     */

    public void onLoadFinished(Loader<String> loader, String data) {
        try {
            response.setText(data);

        } catch (Exception e){
            // If onPostExecute does not receive a proper JSON string, update the UI to show failed results.
            response.setText("No Result");
//            mAuthorText.setText("");
            e.printStackTrace();
        }


    }

    /**
     * In this case there are no variables to clean up when the loader is reset.
     *
     * @param loader The loader that was reset.
     */

    public void onLoaderReset(Loader<String> loader) {}
}