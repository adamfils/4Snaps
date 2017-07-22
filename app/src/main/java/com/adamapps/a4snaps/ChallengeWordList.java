package com.adamapps.a4snaps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ChallengeWordList extends AppCompatActivity {
    RecyclerView challengeList;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference challenge_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_word_list);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.showOverflowMenu();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        challenge_ref = firebaseDatabase.getReference().child("word");


        challengeList = (RecyclerView) findViewById(R.id.challenge_list);
        challenge_ref.keepSynced(true);

        firebaseAdapter();

    }
    public void firebaseAdapter(){
        FirebaseRecyclerAdapter<ChallengeWordListModel,ChallengeHolder> adapter
                = new FirebaseRecyclerAdapter<ChallengeWordListModel, ChallengeHolder>(
                        ChallengeWordListModel.class,R.layout.single_challenge_list_item,
                ChallengeHolder.class,challenge_ref) {
            @Override
            protected void populateViewHolder(ChallengeHolder viewHolder, ChallengeWordListModel model, int position) {
                final String word_key = getRef(position).getKey();
                viewHolder.setImage(ChallengeWordList.this,model.getImage());
                viewHolder.setWord(model.getWord());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(ChallengeWordList.this,ChallengeBuilder.class);
                        i.putExtra("key",word_key);
                        startActivity(i);
                    }
                });
            }
        };
        challengeList.setLayoutManager(new LinearLayoutManager(this));
        challengeList.setAdapter(adapter);
    }
    public static class ChallengeHolder extends RecyclerView.ViewHolder{
        View mView;
        public ChallengeHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        void setImage(Context c, String image){
            ImageView pic = (ImageView ) mView.findViewById(R.id.suggestedImages);
            Picasso.with(c).load(image).error(R.drawable.ic_backspace_dark).into(pic);
        }
        void setWord(String word){
            TextView text = (TextView) mView.findViewById(R.id.suggestedWord);
            text.setText(word);
        }
    }
}
