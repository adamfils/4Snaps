package com.adamapps.a4snaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ChallengeBuilder extends AppCompatActivity {

    ImageView image1, image2, image3, image4;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser user;
    DatabaseReference cha;
    FirebaseStorage storage;
    StorageReference challenge_pics;
    String key;
    ProgressDialog progressDialog;
    String one = null, two = null, three = null, four = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_builder);

        progressDialog = new ProgressDialog(this);
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.showOverflowMenu();
        Intent i = getIntent();
        key = i.getStringExtra("key");

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        Button hint = (Button) findViewById(R.id.selectedWord);
        hint.setText("Selected Word: "+key.toUpperCase());

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        cha = database.getReference();
        challenge_pics = storage.getReference();


    }

    public void image1(View v) {
        Intent pic = new Intent();
        pic.setAction(Intent.ACTION_GET_CONTENT);
        pic.setType("image/*");
        startActivityForResult(pic, 100);
    }

    public void image2(View v) {
        Intent pic = new Intent();
        pic.setAction(Intent.ACTION_GET_CONTENT);
        pic.setType("image/*");
        startActivityForResult(pic, 200);
    }

    public void image3(View v) {
        Intent pic = new Intent();
        pic.setAction(Intent.ACTION_GET_CONTENT);
        pic.setType("image/*");
        startActivityForResult(pic, 300);
    }

    public void image4(View v) {
        Intent pic = new Intent();
        pic.setAction(Intent.ACTION_GET_CONTENT);
        pic.setType("image/*");
        startActivityForResult(pic, 400);
    }

    public void send(View v) {
        
        if(TextUtils.isEmpty(one)||TextUtils.isEmpty(two)||TextUtils.isEmpty(three)||TextUtils.isEmpty(four)) {
            Toast.makeText(this, "Upload 4 Images To Proceed", Toast.LENGTH_SHORT).show();
            return;

        }
            SendChallenge send = new SendChallenge(key, one, two, three, four, user.getUid());
        Toast.makeText(this, "Sending......", Toast.LENGTH_SHORT).show();
        cha.child("challenge").child(user.getUid())
                .child(key).setValue(send).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ChallengeBuilder.this, "Sent", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(ChallengeBuilder.this,ChallengeWordList.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChallengeBuilder.this, "Could Not Send", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void selectWord(View v) {
        finish();
        startActivity(new Intent(this, ChallengeWordList.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            final Uri picData = data.getData();

            UploadTask uploadTask = challenge_pics.child("challenge").child(user.getUid()).
                    child(key).child("image1").putFile(picData);
            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    long count = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setTitle(String.valueOf(count) + "% Uploaded");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri download = taskSnapshot.getDownloadUrl();
                    assert download != null;
                    one = download.toString();
                    image1.setImageURI(picData);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                }
            });
        }
        if (requestCode == 200 && resultCode == RESULT_OK) {
            final Uri picData2 = data.getData();

            final UploadTask uploadTask = challenge_pics.child("challenge").child(user.getUid()).
                    child(key).child("image2").putFile(picData2);
            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    long count = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setTitle(String.valueOf(count) + "% Uploaded");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri download = taskSnapshot.getDownloadUrl();
                    assert download != null;
                    two = download.toString();
                    image2.setImageURI(picData2);
                    uploadTask.cancel();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                }
            });
        }
        if (requestCode == 300 && resultCode == RESULT_OK) {
            final Uri picData3 = data.getData();

            UploadTask uploadTask = challenge_pics.child("challenge").child(user.getUid()).
                    child(key).child("image3").putFile(picData3);
            Toast.makeText(this, "Number 3", Toast.LENGTH_SHORT).show();

            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    long count = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setTitle(String.valueOf(count) + "% Uploaded");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri download = taskSnapshot.getDownloadUrl();
                    assert download != null;
                    three = download.toString();
                    image3.setImageURI(picData3);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                }
            });
        }
        if (requestCode == 400 && resultCode == RESULT_OK) {
            final Uri picData4 = data.getData();

            UploadTask uploadTask = challenge_pics.child("challenge").child(user.getUid()).
                    child(key).child("image4").putFile(picData4);
            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    long count = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setTitle(String.valueOf(count) + "% Uploaded");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri download = taskSnapshot.getDownloadUrl();
                    assert download != null;
                    four = download.toString();
                    image4.setImageURI(picData4);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                }
            });
        }
    }
}
