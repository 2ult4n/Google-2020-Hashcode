package com.hashcode.bookscanning;

import me.tongfei.progressbar.ProgressBar;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
        FileUtil util = new FileUtil();
        String[] fileNames = {"a_example.txt",
        "b_read_on.txt",
        "c_incunabula.txt",
        "d_tough_choices.txt",
        "e_so_many_books.txt",
        "f_libraries_of_the_world.txt"};

            Pair pair = util.init(fileNames[0]);
            int x = pair.scheduleAndWrite(fileNames[0]);
            BufferedWriter bwx = null ;
            try{
                bwx = new BufferedWriter(new FileWriter("[output] "+fileNames[0],true));
                bwx.write(String.valueOf(x));

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    bwx.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }



    }
}
