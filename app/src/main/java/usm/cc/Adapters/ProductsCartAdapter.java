package usm.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import usm.cc.Model.Product;
import usm.cc.R;

/**
 * Created by niko on 28/06/2016.
 */
public class ProductsCartAdapter extends ArrayAdapter<Product> {
    ArrayList<Product> products;
    Context context;

    public ProductsCartAdapter(Context context, ArrayList<Product> objects) {
        super(context, -1, objects);
        this.products = objects;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_carrito,parent,false);
        TextView productBrandCircle = (TextView) v.findViewById(R.id.product_brand_circle);
        TextView productUnits = (TextView) v.findViewById(R.id.units);
        TextView productName = (TextView) v.findViewById(R.id.nameProduct);
        productName.setText(products.get(position).getName());
        productBrandCircle.setText(products.get(position).getBrand().substring(0, 1).toUpperCase());
        return v;
    }
}
