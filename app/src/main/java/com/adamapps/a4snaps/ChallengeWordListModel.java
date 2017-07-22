package com.adamapps.a4snaps;

/**
 * Created by user on 30-May-17.
 */

public class ChallengeWordListModel {
    private String image;
    private String word;

    public ChallengeWordListModel(){
        //Default Constructor
    }

    public ChallengeWordListModel(String image, String word) {
        this.image = image;
        this.word = word;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
