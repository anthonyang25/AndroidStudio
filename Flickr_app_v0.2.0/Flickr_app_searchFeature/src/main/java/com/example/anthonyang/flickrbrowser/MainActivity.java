package com.example.anthonyang.flickrbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = "MainActivity";
    private List<Photo> mPhotoList = new ArrayList<Photo>();
    private RecyclerView mRecycleView;
    private FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //GetRawData rawData = new GetRawData("https://api.flickr.com/services/feeds/photos_public.gne?tags=android,nougat&format=json&nojsoncallback=1"); //initialise
        //GetFlickrJsonData jsonData = new GetFlickrJsonData("android,nougat", true); //search criteria, matchALL ?
        //rawData.execute(); //invoke
        //jsonData.execute();

        /*
           GetRawData <- GetFlickrJsonData <- ProcessPhotos
           Adapter -> View -> Screen
         */
        activateToolbar();

        mRecycleView = (RecyclerView) findViewById(R.id.recycler_view); //reference to view in content_main.xml
        mRecycleView.setLayoutManager(new LinearLayoutManager(this)); // LayoutManager must be provided for RecyclerView to function

        ProcessPhotos processPhotos = new ProcessPhotos("android,nougat", true);
        processPhotos.execute();


//comment this out every time blank activity is created
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

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
        if(id == R.id.menu_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(flickrRecyclerViewAdapter != null){
            String query = getSavedPreferenceData(FLICKR_QUERY); //get the stored user input from SearchActivity.java
            if(query.length() > 0){
                ProcessPhotos processPhotos = new ProcessPhotos(query, true);
                processPhotos.execute();
            }
        }
    }

    private String getSavedPreferenceData(String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(key,"");
    }

    /*
            CLASS that inherits from parent (GetFlickrJsonData)
            does the parsing of data
         */
    public class ProcessPhotos extends GetFlickrJsonData{

        public ProcessPhotos(String searchCriteria, Boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        @Override
        public void execute() {
            //super.execute();
            ProcessData processData = new ProcessData();
            processData.execute(); //async task
        }
        /*
            Inner Class
         */
        public class ProcessData extends DownloadJsonData{

            @Override
            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                //have data, initialise adapter -> pass photoList + context
                flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(getmPhotos(),MainActivity.this);
                mRecycleView.setAdapter(flickrRecyclerViewAdapter); //add adapter to view
            }

        }
    }
}
