package com.hashcode.bookscanning;

import java.io.*;
import java.util.*;

public class Pair {
    Library[] libraries;
    TimelineLookup timelineLookup;

    public Pair(Library[] libraries, TimelineLookup timelineLookup) {
        this.libraries = libraries;
        this.timelineLookup = timelineLookup;
    }

    public void schedule(){
        Queue<Library> queue = new LinkedList<>();
        while(!timelineLookup.isZero()){
            int index = findEffectiveOrder() ;
            libraries[index].setBooks(timelineLookup.reschedule(libraries[index].books));
            libraries[index].scheduled = true ;
            queue.add(libraries[index]);
        }
        if(queue.size() < libraries.length){
            for (Library lib: libraries) {
                if(!lib.scheduled){
                    queue.add(lib);
                }
            }
        }
        test(queue);
    }

    public int scheduleAndWrite(String fileName){
        BufferedWriter bw = null;
        BufferedWriter bwx = null;
        int libsScheduled = 0 ;
        try {
            bw = new BufferedWriter(new FileWriter("[output] "+fileName, true));
            while(!timelineLookup.isZero()){

                int index = findEffectiveOrder();
                if(index == -1){
                    break;
                }
                libraries[index].setBooks(timelineLookup.reschedule(libraries[index].books));
                libraries[index].scheduled = true ;
                bw.newLine();
                bw.write(index +" "+ libraries[index].books.length);
                bw.newLine();
                for (int book : libraries[index].books) {
                    bw.write(String.valueOf(book)+" ");
                }
                libsScheduled++;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
            }catch(Exception e){

            }

        }
    return libsScheduled;
    }

    public void test(Queue<Library> queue){
        System.out.println(queue.size());
        while(!queue.isEmpty()){
            Library lib = queue.poll();
            System.out.println(lib.index+" "+lib.books.length);
            for (int book: lib.books) {
                System.out.print(book+" ");
            }
            System.out.println();
        }
    }

    public int findHighestUnScanned(){
        int max = 0;
        int idx = -1;
        for(int i = 0 ; i<libraries.length ;i++){
            int UnScannedBooks = timelineLookup.checkUnScannedBooks(libraries[i].books);
            if(UnScannedBooks > max){
                max = UnScannedBooks;
                idx = i ;
            }else if((UnScannedBooks == max) && !libraries[i].scheduled){
                idx = i ;
            }
        }
        return idx;
    }

    public int findEffectiveOrder(){
        int idx = -1;
        int mostEffective = 0;
        int threshold = 0;
        for (int i = 0 ; i < libraries.length ; i++) {
            if(!libraries[i].scheduled){
                int availableTime = timelineLookup.days - libraries[i].signUp;
                int maximumBooksCanBeScanned = availableTime*libraries[i].scanningThroughput;
                libraries[i].setBooks(timelineLookup.reschedule(libraries[i].books));
                int mostEffectiveComparer = timelineLookup.calculateScore(libraries[i].books, maximumBooksCanBeScanned);;
                if(mostEffective < mostEffectiveComparer){
                    mostEffective = mostEffectiveComparer;
                    idx = i ;
                    threshold = maximumBooksCanBeScanned;
                }
            }
        }
        if(idx != -1)
            timelineLookup.scanBooks(libraries[idx].books, threshold);

        return idx;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "libraries=" + Arrays.toString(libraries) +
                ", timelineLookup=" + timelineLookup +
                '}';
    }
}
