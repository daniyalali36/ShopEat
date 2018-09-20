package com.example.uroos.shopeat.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Talib on 8/28/2015.
 */

public class SaveInSharedPreference {


   public static SaveInSharedPreference savePrefs;


    public static SaveInSharedPreference getInstance() {
        if(savePrefs == null){
            savePrefs = new SaveInSharedPreference();
        }
        return savePrefs;
    }


    //saveBoolean method start
    public void saveBoolean(Activity act, String key, boolean data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }
    //saveBoolean method end

    //saveBoolean method start
    public void saveBoolean(Context act, String key, boolean data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }
    //saveBoolean method end

    //get getBoolean method start
    public static boolean getBoolean(Activity act, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        boolean value = prefs.getBoolean(key, false);
        return value;
    }
    //get getBoolean method end

    //get getBoolean method start
    public static boolean getBoolean(Context act, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        boolean value = prefs.getBoolean(key, false);
        return value;
    }
    //get getBoolean method end

    //saveData method start
    public void saveString(Activity act, String key, String data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, data);
        editor.commit();
    }
    //saveData method end

    //saveData method start
    public void saveString(Context act, String key, String data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, data);
        editor.commit();
    }
    //saveData method end

    //getString method start
    public String getString(Activity act, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        return prefs.getString(key, "");
    }
    //getString method end

    //getString method start
    public String getString(Context act, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
        return prefs.getString(key, "");
    }
    //getString method end


    //saveInt method start
    public void saveInt(Activity act, String key, int data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, data);
        editor.commit();
    }
    //saveInt method end

    //saveInt method start
    public void saveInt(Context act, String key, int data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, data);
        editor.commit();
    }
    //saveInt method end

    //getInt method start
    public int getInt(Context act, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(act);
            return prefs.getInt(key, -1);
    }
    //getInt method end

    //get clear key method start
    public void clearKey(Activity act, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    //get clear key method end
}
