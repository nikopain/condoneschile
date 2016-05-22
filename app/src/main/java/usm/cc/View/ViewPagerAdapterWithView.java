package usm.cc.View;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import usm.cc.Model.Condom;
import usm.cc.R;

/**
 * Created by flashok on 9.8.14.
 */
public class ViewPagerAdapterWithView extends PagerAdapter {
    private ArrayList<ViewPagerItem> pagerItems;
    private LayoutInflater inflater;
    private Context context;

    public ViewPagerAdapterWithView(Context context, ArrayList<ViewPagerItem> pagerItems) {
        super();
        this.pagerItems = pagerItems;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout layout = null;
        if (position == 0){
            layout = (LinearLayout) inflater.inflate(R.layout.row_condom_carrito,null);
            TextView nameTextView = (TextView) layout.findViewById(R.id.textViewRowName);
            TextView descriptionTextView = (TextView) layout.findViewById(R.id.textViewRowDescriptionn);
            TextView stockTextView = (TextView) layout.findViewById(R.id.textViewRowStock);

            nameTextView.setText(pagerItems.get(position).getCondoms().getNombre());
            descriptionTextView.setText(pagerItems.get(position).getCondoms().getDescripcion());
            stockTextView.setText(pagerItems.get(position).getCondoms().getDisponible());
        }
        else if( position == 1){
            layout = (LinearLayout) inflater.inflate(R.layout.row_condom_buy_options, null);
        }
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return pagerItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }
}