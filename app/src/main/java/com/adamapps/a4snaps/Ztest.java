package com.adamapps.a4snaps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by user on 28-Jun-17.
 */

public class Ztest extends AppCompatActivity {
    Button word1, word2, word3, word4, word5;
    Button word6, word7, word8, word9, word10;
    Button word11, word12;
    Boolean isCorrect = false;
    int len_word;
    TextView hintText;
    SweetAlertDialog dialog;
    LinearLayout linearLayout;
    private static final String answer = "DIRTY";
    Button btn1, btn2, btn3, btn4, btn5, btn6;
    Button btn7, btn8, btn9, btn10, btn11, btn12;
    GridLayout choice;
    int count;
    String gotten_build;
    private boolean shouldAdd = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        len_word = answer.length();
        hintText = (TextView) findViewById(R.id.answer_test);
        hintText.setText(len_word + "Words");

        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);

        linearLayout = (LinearLayout) findViewById(R.id.answer_layout_test);

        choice = (GridLayout) findViewById(R.id.choiceGrid);


        count = linearLayout.getChildCount();
        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();

        instaciateWords();
        //NewButtons();

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

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, 70);
        params.gravity = Gravity.CENTER;
        params.leftMargin = 10;
        // final int len = answer.length();
        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word1.getText().toString();

                if (!isCorrect) {
                    Button word1Btn = new Button(Ztest.this);

                        word1Btn.setText(value);
                        word1Btn.setTextSize(12);
                        word1Btn.setId(R.id.btn1);
                        word1Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                        word1Btn.setLayoutParams(params);
                    if (shouldAdd) {
                        linearLayout.addView(word1Btn);
                    }

                        final int countIT = linearLayout.getChildCount();


                        btn1 = (Button) findViewById(R.id.btn1);
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                linearLayout.removeView(btn1);
                                word1.setVisibility(View.VISIBLE);

                                if (countIT >= len_word || countIT == 9) {
                                    disableButtons(choice);
                                    Toast.makeText(Ztest.this, "one test", Toast.LENGTH_SHORT).show();
                                } else {
                                    enableButton(choice);
                                    Toast.makeText(Ztest.this, "two test", Toast.LENGTH_SHORT).show();

                            }
                        });

                        word1.setVisibility(View.INVISIBLE);
                        if (countIT >= len_word || countIT == 9) {

                            disableButtons(choice);
                        } else if (countIT < len_word) {
                            enableButton(choice);
                        }
                    }
                }else{
                    final int countIT = linearLayout.getChildCount();
                    if(countIT<len_word){
                        enableButton(choice);
                    }else{
                        Toast.makeText(Ztest.this, "Can't Continue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word2.getText().toString();

                Button word2Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word2Btn.setText(value);
                    word2Btn.setTextSize(12);
                    word2Btn.setId(R.id.btn2);
                    word2Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word2Btn.setLayoutParams(params);
                    linearLayout.addView(word2Btn);

                    final int countIT = linearLayout.getChildCount();
                    displayButtonAnswer(linearLayout);

                    btn2 = (Button) findViewById(R.id.btn2);
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn2);
                            word2.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT == 9) {

                                disableButtons(choice);
                            } else {
                                enableButton(choice);
                            }
                        }
                    });

                    word2.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT == 9) {

                        disableButtons(choice);
                    } else if (countIT < len_word) {
                        enableButton(choice);
                    }
                }
            }
        });

        word3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word3.getText().toString();

                Button word3Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word3Btn.setText(value);
                    word3Btn.setTextSize(12);
                    word3Btn.setId(R.id.btn3);
                    word3Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word3Btn.setLayoutParams(params);
                    linearLayout.addView(word3Btn);

                    final int countIT = linearLayout.getChildCount();
                    displayButtonAnswer(linearLayout);

                    btn3 = (Button) findViewById(R.id.btn3);
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn3);
                            word3.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT == 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });


                    word3.setVisibility(View.INVISIBLE);
                    if (countIT >= len_word || countIT == 9) {

                        disableButtons(choice);
                    } else if (countIT < len_word) {
                        enableButton(choice);
                    }
                }
            }
        });
        word4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word4.getText().toString();

                Button word4Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word4Btn.setText(value);
                    word4Btn.setTextSize(12);
                    word4Btn.setId(R.id.btn4);
                    word4Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word4Btn.setLayoutParams(params);
                    linearLayout.addView(word4Btn);

                    final int countIT = linearLayout.getChildCount();
                    displayButtonAnswer(linearLayout);

                    btn4 = (Button) findViewById(R.id.btn4);
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn4);
                            word4.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });

                    word4.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }
                }
            }
        });
        word5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word5.getText().toString();

                Button word5Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word5Btn.setText(value);
                    word5Btn.setTextSize(12);
                    word5Btn.setId(R.id.btn5);
                    word5Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word5Btn.setLayoutParams(params);
                    linearLayout.addView(word5Btn);

                    final int countIT = linearLayout.getChildCount();
                    displayButtonAnswer(linearLayout);

                    btn5 = (Button) findViewById(R.id.btn5);
                    btn5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn5);
                            word5.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });

                    word5.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }

                }
            }
        });
        word6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word6.getText().toString();

                Button word6Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word6Btn.setText(value);
                    word6Btn.setTextSize(12);
                    word6Btn.setId(R.id.btn6);
                    word6Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word6Btn.setLayoutParams(params);
                    linearLayout.addView(word6Btn);

                    final int countIT = linearLayout.getChildCount();
                    displayButtonAnswer(linearLayout);

                    btn6 = (Button) findViewById(R.id.btn6);
                    btn6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn6);
                            word6.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });


                    word6.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }

                }
            }
        });
        word7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word7.getText().toString();

                Button word7Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word7Btn.setText(value);
                    word7Btn.setTextSize(12);
                    word7Btn.setId(R.id.btn7);
                    word7Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word7Btn.setLayoutParams(params);
                    linearLayout.addView(word7Btn);

                    final int countIT = linearLayout.getChildCount();
                    displayButtonAnswer(linearLayout);

                    btn7 = (Button) findViewById(R.id.btn7);
                    btn7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn7);
                            word7.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });

                    word7.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }

                }
            }
        });
        word8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word8.getText().toString();

                Button word8Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word8Btn.setText(value);
                    word8Btn.setTextSize(12);
                    word8Btn.setId(R.id.btn8);
                    word8Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word8Btn.setLayoutParams(params);
                    linearLayout.addView(word8Btn);

                    final int countIT = linearLayout.getChildCount();

                    btn8 = (Button) findViewById(R.id.btn8);
                    btn8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn8);
                            word8.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });


                    word8.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT == 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }

                }
            }
        });
        word9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word9.getText().toString();

                Button word9Btn = new Button(Ztest.this);
                if (shouldAdd) {
                    word9Btn.setText(value);
                    word9Btn.setTextSize(12);
                    word9Btn.setId(R.id.btn9);
                    word9Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word9Btn.setLayoutParams(params);
                    linearLayout.addView(word9Btn);

                    final int countIT = linearLayout.getChildCount();

                    btn9 = (Button) findViewById(R.id.btn9);
                    btn9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn9);
                            word9.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });


                    word9.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }
                }
            }
        });
        word10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word10.getText().toString();

                Button word10Btn = new Button(Ztest.this);
                if(shouldAdd) {
                    word10Btn.setText(value);
                    word10Btn.setTextSize(12);
                    word10Btn.setId(R.id.btn10);
                    word10Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word10Btn.setLayoutParams(params);
                    linearLayout.addView(word10Btn);

                    final int countIT = linearLayout.getChildCount();

                    btn10 = (Button) findViewById(R.id.btn10);
                    btn10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn10);
                            word10.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });


                    word10.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }
                }
            }
        });
        word11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word11.getText().toString();

                Button word11Btn = new Button(Ztest.this);
                if(shouldAdd) {
                    word11Btn.setText(value);
                    word11Btn.setTextSize(12);
                    word11Btn.setId(R.id.btn11);
                    word11Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word11Btn.setLayoutParams(params);
                    linearLayout.addView(word11Btn);

                    final int countIT = linearLayout.getChildCount();

                    btn11 = (Button) findViewById(R.id.btn11);
                    btn11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn11);
                            word11.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });


                    word11.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }
                }
            }
        });
        word12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = word12.getText().toString();

                Button word12Btn = new Button(Ztest.this);
                if(shouldAdd) {
                    word12Btn.setText(value);
                    word12Btn.setTextSize(12);
                    word12Btn.setId(R.id.btn12);
                    word12Btn.setBackground(getResources().getDrawable(R.drawable.special_edittext_bg));
                    word12Btn.setLayoutParams(params);
                    linearLayout.addView(word12Btn);

                    final int countIT = linearLayout.getChildCount();

                    btn12 = (Button) findViewById(R.id.btn12);
                    btn12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(btn12);
                            word12.setVisibility(View.VISIBLE);

                            if (countIT >= len_word || countIT >= 9) {
                                choice.setEnabled(false);
                            } else {
                                choice.setEnabled(true);
                            }
                        }
                    });

                    word12.setVisibility(View.INVISIBLE);

                    if (countIT >= len_word || countIT >= 9) {
                        choice.setEnabled(false);
                    } else {
                        choice.setEnabled(true);
                    }
                }
            }
        });
    }

    private void disableButtons(GridLayout layout) {
        ArrayList<View> layoutButtons = layout.getTouchables();
        for (View v : layoutButtons) {
            if (v instanceof Button) {
                //v.setEnabled(false);
                YoYo.with(Techniques.Shake).duration(500).playOn(v);
                shouldAdd=false;
                /*int count = linearLayout.getChildCount();
                linearLayout.removeView(linearLayout.getChildAt(count-1));*/
            }
        }
    }

    private void enableButton(GridLayout layout) {
        ArrayList<View> layoutButtons = layout.getTouchables();
        for (View v : layoutButtons) {
            if (v instanceof Button) {

                v.setEnabled(true);
                shouldAdd=true;

            }
        }
    }


    private void displayButtonAnswer(LinearLayout layout) {
        ArrayList<View> layoutButtons = layout.getTouchables();
        StringBuilder build = new StringBuilder();
        for (View v : layoutButtons) {
            if (v instanceof Button) {

                String answer = ((Button) v).getText().toString();
                build.append(answer);

            }
        }
        gotten_build = build.toString();
        Toast.makeText(this, String.valueOf(build.toString()), Toast.LENGTH_SHORT).show();
    }

}
