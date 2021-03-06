package com.delta.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class FirstActivity extends Activity {

    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first); //set view from activity_first.xml

        button = (Button) (findViewById(R.id.goSecondActivity)); //find button by it's id in res.layout.activity_first.xml

//        button.setOnClickListener(new View.OnClickListener() { //callback when view is clicked
//            @Override
//            public void onClick(View v) {
//                //get and access view's context and execute a hard-coded class name
//                Intent i = new Intent(v.getContext(), SecondActivity.class);
//                startActivity(i);
//            }
//        });

        button.setOnClickListener((view) -> {
                //get and access view's context and execute a hard-coded class name
                Intent i = new Intent(view.getContext(),SecondActivity.class);
                startActivity(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
