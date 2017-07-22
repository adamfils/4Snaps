package com.adamapps.a4snaps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import main.java.com.maximeroussy.invitrode.RandomWord;
import main.java.com.maximeroussy.invitrode.WordLengthException;

public class QuizArena extends AppCompatActivity {

    private static String RANDOM = "";
    GridLayout choiceGrid;
    EditText answerText;
    Button word1, word2, word3, word4, word5;
    Button word6, word7, word8, word9, word10;
    Button word11, word12;
    Boolean clicked = false;
    Boolean isCorrect = false;
    FloatingActionButton fab;
    private static final String TAG = "ADAM";

    //Firebase
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser user;
    DatabaseReference word_ref;
    DatabaseReference answer_ref;

    public static final String answer = "BOY";
    int len_word;
    String answer_gotten = "";
    TextView hintText;
    SweetAlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_arena);

        dialog = new SweetAlertDialog(QuizArena.this, SweetAlertDialog.SUCCESS_TYPE);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.showOverflowMenu();

        hintText = (TextView) findViewById(R.id.hintText);

        Intent i = getIntent();
        final String recieve_key = i.getStringExtra("key");


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        word_ref = database.getReference().child("word").child(recieve_key);

        instaciateWords();


        choiceGrid = (GridLayout) findViewById(R.id.choiceGrid);
        answerText = (EditText) findViewById(R.id.answerField);
        answerText.setCursorVisible(false);
        answerText.setInputType(InputType.TYPE_NULL);
        answerText.setTextIsSelectable(false);
        answer_ref = word_ref.child("word");

        word_ref.child("word").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                answer_gotten = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Restrict Input Size
        answer_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String response = dataSnapshot.getValue(String.class);

                if (response != null) {
                    int len = response.length();
                    hintText.setText(String.valueOf(response.length()) + " Words");
                    answerText.setFilters(new InputFilter[]{
                            new InputFilter.LengthFilter(len)});
                    int remainder = 12 - len;
                    try {
                        String generated = RandomWord.getNewWord(remainder);
                        String combine = generated + answer_gotten;

                        //Randomise Gotten Words
                        Random random = new Random();
                        StringBuilder s = new StringBuilder(combine);
                        StringBuilder shuffled = new StringBuilder();
                        while (s.length() != 0) {
                            int index = random.nextInt(s.length());
                            char c = s.charAt(index);
                            shuffled.append(c);
                            s.deleteCharAt(index);
                        }

                        String word = shuffled.toString();
                        char array[] = word.toCharArray();
                        String text1 = array[0] + "";
                        String text2 = array[1] + "";
                        String text3 = array[2] + "";
                        String text4 = array[3] + "";
                        String text5 = array[4] + "";
                        String text6 = array[5] + "";
                        String text7 = array[6] + "";
                        String text8 = array[7] + "";
                        String text9 = array[8] + "";
                        String text10 = array[9] + "";
                        String text11 = array[10] + "";
                        String text12 = array[11] + "";
                        word1.setText(text1);
                        word2.setText(text2);
                        word3.setText(text3);
                        word4.setText(text4);
                        word5.setText(text5);
                        word6.setText(text6);
                        word7.setText(text7);
                        word8.setText(text8);
                        word9.setText(text9);
                        word10.setText(text10);
                        word11.setText(text11);
                        word12.setText(text12);

                    } catch (WordLengthException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fab = (FloatingActionButton) findViewById(R.id.deleteButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = answerText.getText().length();
                if (length > 0) {
                    answerText.getText().delete(length - 1, length);
                }
            }
        });
        word_ref.child("word").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null)
                len_word = dataSnapshot.getValue(String.class).length();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        watchText();

    }

    public void instaciateWords() {
        word1 = (Button) findViewById(R.id.word1);
        word2 = (Button) findViewById(R.id.word2);
        word3 = (Button) findViewById(R.id.word3);
        word4 = (Button) findViewById(R.id.word4);
        word5 = (Button) findViewById(R.id.word5);
        word6 = (Button) findViewById(R.id.word6);
        word7 = (Button) findViewById(R.id.word7);
        word8 = (Button) findViewById(R.id.word8);
        word9 = (Button) findViewById(R.id.word9);
        word10 = (Button) findViewById(R.id.word10);
        word11 = (Button) findViewById(R.id.word11);
        word12 = (Button) findViewById(R.id.word12);

        // final int len = answer.length();
        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word1.getText().toString();

                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word1);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word1);
                }

            }
        });
        word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word2.getText().toString();

                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word2);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word2);
                }
            }
        });
        word3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word3.getText().toString();

                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word3);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word3);
                }
            }
        });
        word4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word4.getText().toString();

                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word4);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word4);
                }
            }
        });
        word5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word5.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word5);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word5);
                }
            }
        });
        word6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word6.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word6);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word6);
                }
            }
        });
        word7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word7.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word7);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word7);
                }
            }
        });
        word8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word8.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word8);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word8);
                }
            }
        });
        word9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word9.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word9);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word9);
                }
            }
        });
        word10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word10.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word10);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word10);
                }
            }
        });
        word11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word11.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word11);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {
                    YoYo.with(Techniques.Shake).duration(500).playOn(word11);                }
            }
        });
        word12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word12.getText().toString();
                if (!isCorrect) {
                    if (answerText.length() < len_word) {
                        answerText.append(value);
                    } else {
                        YoYo.with(Techniques.Shake).duration(500).playOn(word12);
                        YoYo.with(Techniques.Tada).duration(500).playOn(hintText);
                    }
                } else {

                    YoYo.with(Techniques.Shake).duration(500).playOn(word12);
                }
            }
        });
    }

    public void watchText() {

        answerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                word_ref.child("word").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String word = dataSnapshot.getValue(String.class);
                        assert word != null;
                        len_word = word.length();
                        String text = answerText.getText().toString();
                        if (text.equalsIgnoreCase(word)) {

                            dialog.setTitleText("CORRECT")
                                    .setContentText("You Just Score 3pts")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            dialog.dismiss();
                                            finish();
                                            startActivity(new Intent(QuizArena.this, WarmUpList.class));
                                        }
                                    }).show();

                            fab.setBackgroundColor(Color.BLACK);
                            fab.setEnabled(false);
                            isCorrect = true;
                        } else {
                            fab.setEnabled(true);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (dialog.isShowing()) {
            dialog.onBackPressed();
            finish();
            startActivity(new Intent(this, WarmUpList.class));
        } else {
            super.onBackPressed();
        }
    }
}