package com.agency.trium.doubleteamcompozer.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


/**
 * Permet de gérer la sauvegarde de données dans la session
 * Fonctionne comme une MAP, système CLE / VALEUR
 */
public class SessionManager {
/*
    private Context context; //le context de l'application

    //Les clés
    private static final String PREFS = "PREFS";
    private static final String TEAMS = "TEAMS";
    private static final String PLAYERS = "PLAYERS";


    public SessionManager(Context context){
        this.context = context;
    }

    public void setStatut(String statut){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(STATUT, statut);
        editor.commit();
    }
    public String getStatut(){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        return pref.getString(STATUT,null);
    }

    public void setTime(long time){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(TIME, time);
        editor.commit();
    }

    public long getTime(){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        return pref.getLong(TIME,0l);
    }

    public void setProfil(Whatter profil){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PROFIL, (new Gson()).toJson(profil));
        editor.commit();
    }

    public Whatter getProfil(){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        String profilJSON = pref.getString(PROFIL,null);
        if(profilJSON != null)
            return (new Gson()).fromJson(profilJSON,Whatter.class);
        else
            return null;
    }

    public void setToken(String token){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken(){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        return pref.getString(TOKEN,null);
    }

    public void setJeton(String jeton){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(JETON, jeton);
        editor.commit();
    }

    public String getJeton(){
        SharedPreferences pref = context.getSharedPreferences(PREFS, 0);
        return pref.getString(JETON,null);
    }*/
}
