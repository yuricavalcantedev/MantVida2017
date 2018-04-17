package com.heavendevelopment.mantvida20182.Dominio;

/**
 * Created by Yuri on 21/12/2016.
 */

public class Versículo {

    private int verse;
    private int chapter;
    private int book_id;
    private String text;

    public Versículo() {
    }

    public Versículo(int verse, int chapter, int book_id, String text) {
        this.verse = verse;
        this.chapter = chapter;
        this.book_id = book_id;
        this.text = text;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
