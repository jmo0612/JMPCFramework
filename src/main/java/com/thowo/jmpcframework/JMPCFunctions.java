package com.thowo.jmpcframework;

import com.thowo.jmjavaframework.JMFunctions;
import java.io.File;




//import static android.content.ContentValues.TAG;

/**
 * Created by jimi on 8/2/2017.
 */

public class JMPCFunctions{
    //private static String tes;
    
    public static void init(File languageExcelFile){
        JMFunctions.init(languageExcelFile);
    }
    
    public static String test(){
        return JMFunctions.getStringMessage("DB_NO_CONNECTION");
        //return "TEST";
    }

}
