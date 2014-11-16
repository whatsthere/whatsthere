package com.app.whatsthere;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.TextView;

import com.app.whatsthere.customcomponents.WtListAdapter;
import com.app.whatsthere.datamodels.WtListItem;
import com.app.whatsthere.utils.BlurBuilder;

import java.util.ArrayList;
import java.util.List;


public class WhatsThereActivty extends Activity {

    private ListView whatsThereListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whats_there_activty);



         whatsThereListView = (ListView)findViewById(R.id.whatsThereListView);


        List<WtListItem> data = new ArrayList<WtListItem>();
        WtListItem item ;
        for(int i = 0;i<30;i++) {

            item = new WtListItem();
            List<String> urls = new ArrayList<String>();
            for(int j = 0;j<12;j++){
                urls.add("http://i.imgur.com/DvpvklR.png");
            }
            item.setImage(urls);
            data.add(item);

        }
        WtListAdapter adapter = new WtListAdapter(this,data);
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
