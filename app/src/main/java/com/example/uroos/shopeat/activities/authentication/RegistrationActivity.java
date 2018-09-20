package com.example.uroos.shopeat.activities.authentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uroos.shopeat.R;
import com.example.uroos.shopeat.activities.BaseActivity;
import com.example.uroos.shopeat.activities.profile.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;

    private ImageView userProfilePic;
    String email, name, age, password;

    private static int PICK_IMAGE = 123;
    Uri imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();


//        userProfilePic.setOnClickListener(this);
        regButton.setOnClickListener(this);
        userLogin.setOnClickListener(this);

    }


    /**
     * setting views
     */
    private void setupUIViews() {
        userName = (EditText) findViewById(R.id.etUserName);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);
        userAge = (EditText) findViewById(R.id.etAge);
        //userProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRegister: {

                sendUserDataToFirebaseServer();

          /*

                if (validate()) {
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                //sendEmailVerification();
                                uploadingImage();
                                showToast(activity, "Successfully Registered, Upload complete!");
                                logoutfromFireBase();


                            } else {
                                showToast(activity, ""+task.getException().getMessage());
                            }

                        }
                    });
                }*/

                break;
            }
            //register button case close


            case R.id.tvUserLogin: {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                break;
            }
            //user login case close

     /*       case R.id.ivProfile:{

                Intent intent = new Intent();
                intent.setType("images/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);

                break;
            }*/


        }

    }
    //onClick case close

    /**
     * setting validation of
     * fields
     *
     * @return
     */
    private Boolean validate() {
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();


        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()) {
            showToast(activity, "Please enter all the details");
        } else {
            result = true;
        }

        return result;
    }


    /**
     * setting send email verification code
     * to user registered email
     */
    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        uploadingImage();
                        showToast(activity, "Successfully Registered, Verification mail sent!");
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    } else {
                        showToast(activity, "Verification mail has'nt been sent!");
                    }
                }
            });
        }
    }

    /**
     * send image data to
     * fire base server
     */
    DatabaseReference myRef;

    private void uploadingImage() {

        myRef = firebaseDatabase.getReference();

        /*StorageReference imageReference = storageReference
                .child(firebaseAuth.getUid())
                .child("Images").child(UUID.randomUUID().toString());  //User id/Images/Profile Pic.jpg


        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast(activity, "Upload failed!");
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                showToast(activity, "Upload successful!");
            }
        });*/

        sendUserDataToFirebaseServer();

    }

    /**
     * setting function for sending user
     * data to firebase server
     */
    public void sendUserDataToFirebaseServer() {

        UserProfile userProfile = new UserProfile();
        userProfile.setUdid(FirebaseInstanceId.getInstance().getToken());//firebaseAuth.getUid());
        userProfile.setUserAge(age);
        userProfile.setUserEmail(email);
        userProfile.setUserName(name);

        final Map<String, Object> childUpdates = new HashMap<>();

        //making hash map data for sending fire base server
        Map<String, Object> result = userProfile.toMap();

        //making new user in users table
        childUpdates.put("/users"+firebaseAuth.getUid(), result);
        myRef.updateChildren(childUpdates);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                userProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}