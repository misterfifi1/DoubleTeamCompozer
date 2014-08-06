package com.agency.trium.doubleteamcompozer.views.cell;

import android.content.Context;
import android.view.View;


/**
 * Created by florentchampigny on 20/04/2014.
 */
public abstract class ViewCell<OBJECT> {

    Context context;
    View view;
    OBJECT object;
    int position;
    int apparition = 0;
    Object[] params;

    public ViewCell() {

    }

    public int apparaitre() {
        apparition++;

        if (apparition == 1) {
            try {
                animer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return apparition;
    }

    public abstract void animer();

    public void construire(Context context, View view, OBJECT object, int position) {
        this.context = context;
        this.view = view;
        this.object = object;
        this.position = position;

        this.construire();
        this.construire(view);
    }

    public void construire(View v) {
        this.view = v;
        this.charger();
        this.remplir();
        this.ajouterListeners();
    }

    public void construireObject(OBJECT objet, int position){
        setObject(objet);
        this.position = position;
        remplir();
    }

    public View findViewById(int id){
        if(getView() != null)
            return getView().findViewById(id);
        else
            return null;
    }

    public abstract void construire();

    public abstract void charger();

    public abstract void remplir();

    public abstract void ajouterListeners();

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public OBJECT getObject() {
        return object;
    }

    public void setObject(OBJECT object) {
        this.object = object;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void onScroll(float yOffset) {
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }
}
