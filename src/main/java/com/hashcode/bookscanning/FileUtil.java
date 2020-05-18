package com.hashcode.bookscanning;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtil {

    public Pair init(String fileName){
        Pair pair = null;
        TimelineLookup timeline;
        Library[] libraries;
        String[] line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            line = bf.readLine().split(" ");
            libraries = new Library[Integer.parseInt(line[1])];
            timeline = new TimelineLookup(Integer.parseInt(line[2]), Integer.parseInt(line[0]));
            line = bf.readLine().split(" ");
            for (String val: line) {
                timeline.addScore(Integer.parseInt(val));
            }
            for(int i = 0 ; i<libraries.length ; i++){
                line = bf.readLine().split(" ");
                Library library = new Library(Integer.parseInt(line[0]),
                                              Integer.parseInt(line[1]),
                                              Integer.parseInt(line[2]),i)  ;
                library.fillLibrary(bf.readLine().split(" "));
                libraries[i] = library;
            }
            pair = new Pair(libraries,timeline);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pair;
    }
}
