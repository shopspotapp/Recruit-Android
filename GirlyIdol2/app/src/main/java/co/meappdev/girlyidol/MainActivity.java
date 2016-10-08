package co.meappdev.girlyidol;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Response;

public class MainActivity extends Activity {

    public static final String url = "http://gank.avosapps.com/api/data/福利/50/1";

    private GridView girls_grid;
    private CustomAdapter mAdapter;
    public List<Result> create_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girls_grid = (GridView)findViewById(R.id.girlsgrid);

        //set width for each grid
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            girls_grid.setColumnWidth((screenWidth - 20) / 2);
            girls_grid.setMinimumHeight((screenWidth - 20) / 2);
        }
        else
        {
            girls_grid.setColumnWidth((screenWidth - 20) / 4);
            girls_grid.setMinimumHeight((screenWidth - 20) / 4);
        }

        //set adapter and intent on click grid
        //girls_grid.setAdapter(new CustomAdapter(this));
        girls_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //get position to open full screen picture
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent go_fullscreen = new Intent(MainActivity.this, picfullsrcreen.class);
                go_fullscreen.putExtra("position", position);
                go_fullscreen.putExtra("pic_url", create_data.get(position).getUrl());
                go_fullscreen.putExtra("name_model", create_data.get(position).getWho());
                go_fullscreen.putExtra("pic_date", create_data.get(position).getPublishedAt());
                startActivity(go_fullscreen);
            }
        });

        getApi();
        new SimpleTask().execute(url);

    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {
                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
            showData(jsonString);
        }
    }

    private void showData(String jsonString) {
        Gson gson = new Gson();
        getResource blog = gson.fromJson(jsonString, getResource.class);

        create_data = blog.getResults();

        mAdapter = new CustomAdapter(this, create_data);
        girls_grid.setAdapter(mAdapter);
    }


    public void getApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
