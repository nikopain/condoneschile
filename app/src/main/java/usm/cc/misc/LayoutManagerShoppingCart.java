package usm.cc.misc;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class LayoutManagerShoppingCart extends LinearLayoutManager {
    public LayoutManagerShoppingCart(Context context) {
        super(context);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }
}