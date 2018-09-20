package com.example.uroos.shopeat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uroos.shopeat.R;
import com.example.uroos.shopeat.activities.authentication.LoginActivity;
import com.example.uroos.shopeat.activities.profile.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BaseActivity extends AppCompatActivity {

    protected FirebaseAuth firebaseAuth;
    protected StorageReference storageReference;
    protected FirebaseStorage firebaseStorage;
    protected FirebaseDatabase firebaseDatabase;
    protected Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        settingFireBaseAuth();



    }
    //onCreate close


    /** setting fire base authentication
     * and fire base storage references
     */
    private void settingFireBaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }


    /** setting logout from
     * firebase
     */
    protected void logoutfromFireBase(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(BaseActivity.this, LoginActivity.class));
    }


    Toast toast = null;
    /** setting toast function
     *
     * @param activity
     * @param msg
     */
    public void showToast(Activity activity, String msg) {

        if (activity == null)
            return;

        if (toast == null || toast.getView().getWindowVisibility() != View.VISIBLE) {
            toast = Toast.makeText(activity, "" + msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (toast == null || toast.getView().getWindowVisibility() == View.VISIBLE) {
                toast.cancel();
                toast = Toast.makeText(activity, "" + msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}