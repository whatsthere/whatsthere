package com.app.whatsthere;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.app.whatsthere.customcomponents.WhatsthereListAdapter;
import com.app.whatsthere.datamodels.WhatsthereListItem;

import java.util.ArrayList;
import java.util.List;


public class WhatsThereActivty extends Activity {

    private ListView whatsThereListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whats_there_activty);
         whatsThereListView = (ListView)findViewById(R.id.whatsThereListView);
        List<WhatsthereListItem> data = new ArrayList<WhatsthereListItem>();
        WhatsthereListItem item ;
        for(int i = 0;i<30;i++) {

            item = new WhatsthereListItem();
            item.setImage("http://i.imgur.com/DvpvklR.png");
            data.add(item);
        }
        WhatsthereListAdapter adapter = new WhatsthereListAdapter(this,data);
        whatsThereListView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.whats_there_activty, menu);
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
