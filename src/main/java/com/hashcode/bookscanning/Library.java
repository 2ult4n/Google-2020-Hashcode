package com.hashcode.bookscanning;

import java.util.Arrays;

public class Library {

    int signUp;
    int[] books;
    int scanningThroughput;
    int counter;
    boolean scheduled;
    int index;

    public Library(int numberOfBooks, int signUp, int scanningThroughput, int index){
        this.signUp = signUp;
        this.scanningThroughput = scanningThroughput;
        books = new int[numberOfBooks];
        counter = 0;
        scheduled = false;
        this.index = index;
    }

    public void setBooks(int[] books) {
        this.books = books;
    }

    public void fillLibrary(String[] books){
        for(String val: books){
            this.books[counter++] = Integer.parseInt(val);
        }
    }


    @Override
    public String toString() {
        return "Library{" +
                "signUp=" + signUp +
                ", books=" + Arrays.toString(books) +
                ", scanningThroughput=" + scanningThroughput +
                ", counter=" + counter +
                '}';
    }
}
