package usm.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import usm.cc.Model.Condom;
import usm.cc.R;

/**
 * Created by niko on 12/04/2016.
 */
public class CondomListAdapter extends ArrayAdapter<Condom> {
    private final Context context;
    private final Condom[] condoms;

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
        //El costo debe estar en la lista de productos
      //  TextView costTextView = (TextView) rowView.findViewById(R.id.textViewDescripcionCurso);

        nameTextView.setText(condoms[position].getName());
        descriptionTextView.setText(condoms[position].getDescription());
        stockTextView.setText(condoms[position].getStock());
      //  costTextView.setText(condoms[position].getCost());


        return rowView;
    }
}
