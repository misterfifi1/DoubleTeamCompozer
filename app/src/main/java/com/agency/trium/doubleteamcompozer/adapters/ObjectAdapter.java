package com.agency.trium.doubleteamcompozer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.agency.trium.doubleteamcompozer.views.cell.ViewCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by florentchampigny on 20/04/2014.
 */
public class ObjectAdapter<OBJECT> extends BaseAdapter {

    ObjectAdapterLoadMore listener;
    public boolean afficherFooter = false;
    Context context;
    int layoutId;
    List<OBJECT> objects = new ArrayList<OBJECT>();
    Class cellClass;
    int count = 0;

    Object[] params;

    public ObjectAdapter(Context context, List<OBJECT> objets, int layoutId, Class<? extends ViewCell> cellClass) {
        this.context = context;
        this.objects = objets;
        this.layoutId = layoutId;
        this.cellClass = cellClass;

        //this.cells = new ViewCell[this.objects.size()];
    }

    public ObjectAdapter(Context context, List<OBJECT> objets, int layoutId, Class<? extends ViewCell> cellClass, Object... params) {
        this(context, objets, layoutId, cellClass);
        this.params = params;
    }

    @Override
    public int getCount() {
        if (objects == null)
            return 0;
        else {
            if (afficherFooter)
                return objects.size() + 1;
            else
                return objects.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (i < objects.size())
            return objects.get(i);
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewCell cell = null;

        if (view == null || (view.getTag() != null && view.getTag().equals("FOOTER")))
            view = View.inflate(context, layoutId, null);

        cell = (ViewCell) view.getTag();

        if (cell != null) {
            cell.construireObject((OBJECT) getItem(i), i);
        } else {
            try {
                cell = (ViewCell) Class.forName(cellClass.getName()).newInstance();
                cell.setParams(params);

                cell.construire(context, view, (OBJECT) getItem(i), i);
                view.setTag(cell);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int apparition = cell.apparaitre();

        if (apparition >= 1 && listener != null && getCount() > 4 && i >= (getCount() - 3)) {
            listener.loadMore();
        }

        return view;
    }

    public void setListener(ObjectAdapterLoadMore listener) {
        this.listener = listener;
    }

    public interface ObjectAdapterLoadMore {
        public void loadMore();
    }
}
