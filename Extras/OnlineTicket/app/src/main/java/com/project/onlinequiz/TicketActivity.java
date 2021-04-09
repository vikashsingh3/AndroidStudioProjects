package com.project.onlinequiz;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TicketActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeLayout;
    String LOG_TAG = "ONLINE TICKET: ";
    ArrayList<String> ticketIDList, issueList, statusList, created_dateList;
    RecyclerView recyclerView;
    private Calendar MySingleton;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ticketIDList = new ArrayList<>();
        issueList = new ArrayList<>();
        statusList = new ArrayList<>();
        created_dateList = new ArrayList<>();

        // Getting SwipeContainerLayout
        swipeLayout = findViewById(R.id.swipeContainer);
        recyclerView = findViewById(R.id.rvItems);


//                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                        builder.setCancelable(false); // if you want user to wait for some process to finish,
//                        builder.setView(R.layout.layout_progress_bar);
//                        AlertDialog dialog = builder.create();
//
//                        dialog.show();

//        getTicketDetails();
        test_json_volley_task();

//        dialog.dismiss();

//        // Adding Listener
//        swipeLayout.setOnRefreshListener(() -> {
//            swipeLayout.setRefreshing(true);
////            getTicketDetails();
//            shuffleListItems();
//            Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_LONG).show();
//
//        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                // To keep animation for 4 seconds
                new Handler() {
                    public void postDelayed() {
//                        shuffleListItems();
//                        getTicketDetails();

//
//                        dialog.show(); // to show this dialog
                        test_json_volley_task();

//                        dialog.dismiss(); // to show this dialog
//                        Toast.makeText(getApplicationContext(), "Works!", Toast.LENGTH_LONG).show();
//                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void publish(LogRecord record) {

                    }

                    @Override
                    public void flush() {

                    }

                    @Override
                    public void close() throws SecurityException {

                    }
                }.postDelayed(); // Delay in millis
            }
        });



        // VOLLEY REQUEST

//        requestQueue = Volley.newRequestQueue(this);

//        String url = "http://my-json-feed";
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.i(LOG_TAG, "Response: " + response.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO: Handle error
//
//            }
//        });
//
//        // Access the RequestQueue through your singleton class.
////        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }


    void getTicketDetails() {
        ticketIDList.clear();
        issueList.clear();
        statusList.clear();
        created_dateList.clear();

        Thread thread = new Thread(() -> {
            try {
                String response_code;
                String response_data = NetworkUtils.ticket_details("dev", "General");

                if (response_data != null) {
                    JSONObject obj_response = new JSONObject(response_data);
                    response_code = obj_response.getString("ResponseCode");

                    String issue, ticket_number, created_date, status;
                    if (response_code.equals("200")) {
                        JSONArray response_all_data = obj_response.getJSONArray("ResponseData");
                        for (int j = 0; j < response_all_data.length(); j++) {
                            JSONObject row_details = response_all_data.getJSONObject(j);
                            ticket_number = row_details.getString("ticket_number");
                            issue = row_details.getString("issue");
                            created_date = row_details.getString("created_date");
                            status = row_details.getString("status");

                            ticketIDList.add(ticket_number);
                            issueList.add(issue);
                            statusList.add(status);
                            created_dateList.add(created_date);
                        }
                    }
                }
            } catch (Exception e) {
                Log.i(LOG_TAG, "Error" + e.toString());
            }
        });
        thread.start();

        try {
            thread.join();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(gridLayoutManager);

            CustomAdapter_Tickets customAdapter = new CustomAdapter_Tickets(this, ticketIDList, issueList, statusList, created_dateList);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
//            swipeLayout.setRefreshing(false);
        } catch (Exception e) {
            Log.i(LOG_TAG, "Error" + e.toString());
        } finally {
            swipeLayout.setRefreshing(false);
        }
    }

    public void shuffleListItems() {
        // Stop animation (This will be after 3 seconds)
        // Shuffling the arraylist items on the basis of system time
        Collections.shuffle(ticketIDList, new Random(System.currentTimeMillis()));
        CustomAdapter_Tickets customAdapter = new CustomAdapter_Tickets(getApplicationContext(), ticketIDList, issueList, statusList, created_dateList);
        recyclerView.setAdapter(customAdapter);
//        swipeLayout.setRefreshing(false);
    }

    void test_json_volley_task() {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest();
//        JSONArray jsonArray=new JSONArray()



//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//        builder.setCancelable(false); // if you want user to wait for some process to finish,
//        builder.setView(R.layout.layout_progress_bar);
//        AlertDialog dialog = builder.create();
//
//        dialog.show(); // to show this dialog

        requestQueue = Volley.newRequestQueue(this);

        String url = "http://13.54.185.113/api/?event=ticket&category=General";

        Log.i(LOG_TAG, "Show me some response");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.i(LOG_TAG, "Response: " + response.toString());
                    getTicketDetails_new(response.toString());
                }, error -> {
                    // TODO: Handle error

                });

        requestQueue.add(jsonObjectRequest);


//        dialog.dismiss(); // to show this dialog
    }

    void getTicketDetails_new(String response_data) {
        ticketIDList.clear();
        issueList.clear();
        statusList.clear();
        created_dateList.clear();


        try {
            String response_code;
//                String response_data = NetworkUtils.ticket_details("dev", "General");

            if (response_data != null) {
                JSONObject obj_response = new JSONObject(response_data);
                response_code = obj_response.getString("ResponseCode");

                String issue, ticket_number, created_date, status;
                if (response_code.equals("200")) {
                    JSONArray response_all_data = obj_response.getJSONArray("ResponseData");
                    for (int j = 0; j < response_all_data.length(); j++) {
                        JSONObject row_details = response_all_data.getJSONObject(j);
                        ticket_number = row_details.getString("ticket_number");
                        issue = row_details.getString("issue");
                        created_date = row_details.getString("created_date");
                        status = row_details.getString("status");

                        ticketIDList.add(ticket_number);
                        issueList.add(issue);
                        statusList.add(status);
                        created_dateList.add(created_date);
                    }
                }
            }


            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(gridLayoutManager);

            CustomAdapter_Tickets customAdapter = new CustomAdapter_Tickets(this, ticketIDList, issueList, statusList, created_dateList);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
            swipeLayout.setRefreshing(false);

            swipeLayout.setRefreshing(false);
        } catch (Exception e){
            // TODO
        }
    }

}