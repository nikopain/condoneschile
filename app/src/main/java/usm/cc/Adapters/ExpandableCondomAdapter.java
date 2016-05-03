package usm.cc.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import usm.cc.Model.Condom;
import usm.cc.R;

/**
 * Created by niko on 16/04/2016.
 */
public class ExpandableCondomAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private Activity context;
    private Map<String, List<String>> itemcollections;
    private final Condom[] condoms;
    private List<Condom> item;
    private TextView name;
    public ExpandableCondomAdapter(Activity context, Condom[] condoms, Map<String, List<String>> collections){
        this.context = context;
        this.condoms = condoms;
        this.itemcollections = collections;
    }
    @Override
    public Object getChild(int groupposition, int childposition) {
        // TODO Auto-generated method stub
        return itemcollections.get(item.get(groupposition)).get(childposition);
    }

    @Override
    public long getChildId(int groupposition, int childposition) {
        // TODO Auto-generated method stub
        return childposition;
    }

    @Override
    public View getChildView(int groupposition, int childpostion, boolean isLastchild, View convertview,
                             ViewGroup parent) {
        // TODO Auto-generated method stub
        final Condom childitem = (Condom) getChild(groupposition, childpostion);
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertview==null){
            convertview = inflater.inflate(R.layout.row_condom_carrito, null);
        }
        name = (TextView)convertview.findViewById(R.id.name);
        name.setText(childitem.getName());
        return convertview;
    }

    @Override
    public int getChildrenCount(int groupposition) {
        // TODO Auto-generated method stub
        return itemcollections.get(item.get(groupposition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return item.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return item.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        String itemname = (String) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater groupinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupinflater.inflate(R.layout.row_condom_carrito, null);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

}