package com.uss.sample.javacertification.exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ThrowClause1 implements IntReader{



    public static void main(String []args) throws FileNotFoundException {

        try(MyAutoCloseable autoCloseable = new MyAutoCloseable()){
              autoCloseable.someMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String fileOperation(String path){

        try (BufferedReader brr = new BufferedReader(new FileReader(path))) {
            return brr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int readIntFromFile() throws FileNotFoundException, NoSuchElementException {
        return 0;
    }
}
