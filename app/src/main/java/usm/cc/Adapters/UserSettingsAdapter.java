package usm.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import usm.cc.Model.Info;
import usm.cc.R;

/**
 * Created by niko on 25/05/2016.
 */
public class UserSettingsAdapter extends ArrayAdapter<Info>{

    private Info[] info;
    private Context context;

    public UserSettingsAdapter(Context context, Info[] info) {
        super(context, -1 , info);
        this.context=context;
        this.info = info;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_user_settings, parent, false);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.Text);
        TextView valueTextView = (TextView) rowView.findViewById(R.id.value);
        nameTextView.setText(info[position].getText() + ":");
        valueTextView.setText(info[position].getValue());


        return rowView;
    }
}
