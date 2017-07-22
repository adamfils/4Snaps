package com.adamapps.a4snaps;

/**
 * Created by user on 30-May-17.
 */

public class SendChallengeModel {
    String pic1;
    String pic2;
    String pic3;
    String pic4;
    String hint;
    String answer;

    public SendChallengeModel(){

    }

    public SendChallengeModel(String pic1, String pic2, String pic3, String pic4, String hint, String answer) {
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.pic4 = pic4;
        this.hint = hint;
        this.answer = answer;
    }
}
