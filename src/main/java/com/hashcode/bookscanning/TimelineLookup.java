package com.hashcode.bookscanning;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.IntStream;




public class TimelineLookup {

    class sortByScore implements Comparator<Integer>{

        public int compare(Integer a, Integer b)
        {
            return a - b;
        }
    }

    int days;
    int[] scores;
    int counter;
    int zg;

    public TimelineLookup(int days, int numberOfBooks) {
        this.days = days;
        this.counter = 0;
        scores = new int[numberOfBooks];
        zg = 0 ;
    }

    public void updateDays(int days) { this.days = days; }

    public void addScore(int score){
        scores[counter++] = score;
    }

    public void updateScore(int index){
        scores[index] = 0;
    }

    public int[] reschedule(int[] books){
        Integer[] boxedBooks = ArrayUtils.toObject(books);
        Arrays.sort(boxedBooks, new sortByScore());
        ArrayUtils.reverse(boxedBooks);
        return ArrayUtils.toPrimitive(boxedBooks);
    }

    public int calculateScore(int[] books, int index){
        int score = 0 ;
        int[] scoresClone = scores.clone();
        for(int i = 0 ; i < index; i++){
            if(i == books.length)
                break;
            score = score + scoresClone[books[i]];
            scoresClone[books[i]] = 0;
        }
      return score;
    }

    public void scanBooks(int[] books, int toIndex){
        for(int i = 0 ; i < toIndex; i++){
            if(i == books.length)
                break;
            updateScore(books[i]);
        }

    }

    public boolean isZero(){
        zg = Arrays.stream(scores).sum();
        return zg == 0 ? true : false ;
    }

    public int checkUnScannedBooks(int[] books){
        int numberOfUnScannedBooks = 0;
        for (int book: books) {
            if(scores[book] != 0)
                numberOfUnScannedBooks++;
        }
        return numberOfUnScannedBooks;
    }

    @Override
    public String toString() {
        return "TimelineLookup{" +
                "days=" + days +
                ", scores=" + Arrays.toString(scores) +
                ", counter=" + counter +
                '}';
    }
}
