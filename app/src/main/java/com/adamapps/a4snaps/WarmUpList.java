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
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class WarmUpList extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference warm_ref;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_up_list);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.showOverflowMenu();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        warm_ref = database.getReference().child("word");
        warm_ref.keepSynced(true);
        recyclerView = (RecyclerView) findViewById(R.id.offline_list);

        recyclerView();
    }

    private void recyclerView() {
        FirebaseRecyclerAdapter<ChallengeWordListModel,WarmUpHolder> adapter =
                new FirebaseRecyclerAdapter<ChallengeWordListModel, WarmUpHolder>(
                        ChallengeWordListModel.class,R.layout.single_challenge_list_item,
                        WarmUpHolder.class,warm_ref) {
                    @Override
                    protected void populateViewHolder(WarmUpHolder viewHolder, ChallengeWordListModel model, int position) {
                        final String word_key = getRef(position).getKey();
                        viewHolder.setImage(WarmUpList.this,model.getImage());
                        viewHolder.setWord(model.getWord());
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(WarmUpList.this,QuizArena.class);
                                i.putExtra("key",word_key);
                                startActivity(i);
                            }
                        });
                    }
                };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    public static class WarmUpHolder extends RecyclerView.ViewHolder{

        View mView;
        public WarmUpHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        void setWord(String word){
            TextView text = (TextView) mView.findViewById(R.id.suggestedWord);
            text.setText(word);
        }

        void setImage(final Context c, final String image){
            final ImageView pic = (ImageView) mView.findViewById(R.id.suggestedImages);
            Picasso.with(c).load(image).error(R.drawable.ic_backspace_dark)
                    .networkPolicy(NetworkPolicy.OFFLINE).into(pic, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(c).load(image).error(R.drawable.ic_backspace_dark)
                            .into(pic);
                }
            });
        }
    }
}
