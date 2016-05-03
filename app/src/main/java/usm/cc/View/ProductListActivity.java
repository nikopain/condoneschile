package usm.cc.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import usm.cc.Adapters.CondomListAdapter;
import usm.cc.Adapters.ListViewAdapter;
import usm.cc.Model.Condom;
import usm.cc.R;

public class ProductListActivity extends AppCompatActivity {

    private HashMap<Integer, Integer> mapColors = new HashMap<Integer, Integer>();
    private Condom condom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Random r = new Random();

        mapColors.put(0, R.color.blue);
        mapColors.put(1, R.color.purple);
        mapColors.put(2, R.color.green);
        mapColors.put(3, R.color.orange);
        mapColors.put(4, R.color.red);
        mapColors.put(5, R.color.darkblue);
        mapColors.put(6, R.color.darkpurple);
        mapColors.put(7, R.color.darkgreen);
        mapColors.put(8, R.color.darkorange);
        mapColors.put(9, R.color.darkred);

        ArrayList<ListViewItem> array = new ArrayList<ListViewItem>();



         for (int i = 0; i < 20; i++) {
            ArrayList<ViewPagerItem> viewPagerItems = new ArrayList<ViewPagerItem>();

            viewPagerItems.add(new ViewPagerItem(Integer.toString(0), mapColors.get(r.nextInt(10))));

            viewPagerItems.add(new ViewPagerItem(Integer.toString(1), mapColors.get(r.nextInt(10))));

            array.add(new ListViewItem(Integer.toString(i), viewPagerItems));
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListViewAdapter(this, array));
    }
}
