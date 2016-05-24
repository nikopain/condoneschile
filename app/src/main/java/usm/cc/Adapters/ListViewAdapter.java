package usm.cc.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import usm.cc.Model.Condom;
import usm.cc.R;
import usm.cc.View.ListViewItem;
import usm.cc.View.ViewPagerAdapterWithView;

public class ListViewAdapter extends ArrayAdapter<Condom> {
    private ArrayList<Condom> mItems;
    private Context mContext;
    private LayoutInflater inflater;


    public ListViewAdapter(FragmentActivity fragmentActivity, ArrayList<Condom> items) {
        super(fragmentActivity, 0, items);
        mContext = fragmentActivity;
        mItems = items;
        inflater = fragmentActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Condom getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list_product_view, null);
            viewHolder = new Holder();
            viewHolder.viewPager = (ViewPager) convertView.findViewById(R.id.pager);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        ViewPager viewPager = viewHolder.viewPager;
        ViewPagerAdapterWithView tempMyFriendPagerAdapter = new ViewPagerAdapterWithView(mContext, mItems.get(position));
        viewPager.setAdapter(tempMyFriendPagerAdapter);
        return convertView;
    }

    class Holder {
        ViewPager viewPager;
    }
}


