package usm.cc.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import usm.cc.Model.Condom;
import usm.cc.R;
import usm.cc.View.ViewPagerAdapterWithView;

/**
 * Created by niko on 12/04/2016.
 */
public class CondomListAdapter extends ArrayAdapter<Condom> {
    private Context context;
    private Condom[] condoms;

    public CondomListAdapter(Context context, Condom[] condoms) {
        super(context, -1, condoms);
        this.context=context;
        this.condoms=condoms;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_condom_carrito, parent, false);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.textViewRowName);
        TextView descriptionTextView = (TextView) rowView.findViewById(R.id.textViewRowDescriptionn);
        TextView stockTextView = (TextView) rowView.findViewById(R.id.textViewRowStock);

        nameTextView.setText(condoms[position].getNombre());
        descriptionTextView.setText(condoms[position].getDescripcion());
        stockTextView.setText(condoms[position].getDisponible());


        return rowView;
    }
}
