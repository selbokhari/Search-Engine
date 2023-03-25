/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp.partie1_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PC
 */
public class normalisation {
    
    public static  List<String> stopWordsAr=new ArrayList<String>();
    public static String projectPath ="";
    
     public List<String> Stopword(File file) throws FileNotFoundException, IOException {
     
        String line;
         List<String> stp =new ArrayList<>();
        InputStream flux = new FileInputStream(file);
        InputStreamReader lecture = new InputStreamReader(flux);
        BufferedReader buff = new BufferedReader(lecture);
     
        while ((line = buff.readLine()) != null) {

           stp.add(line);

        }

        return stp;
    }
 
    
   public String  Nettoyer(String str ) throws IOException {
       File file =new File(projectPath+"stopwords.txt");
       
       stopWordsAr=Stopword(file);
        
            
         Pattern p = Pattern.compile("[\\p{P}\\p{Mn}]");
         Matcher m = p.matcher(str);
         m.reset();
         String n =m.replaceAll("").replaceAll("[a-zA-Z]", "").replaceAll("[0-9]","").replaceAll("\\p{Punct}", "");
         String nostopwords=new String();
         String tab[]= n.trim().split(" ");
        for (String tab1 : tab) {
            if (!stopWordsAr.contains(tab1.trim())) {
                nostopwords += " " + tab1.trim();
            }
        }
        return nostopwords.toLowerCase().trim();
    }
    static public String normalisation(String s) throws IOException {
          
        String normalized =new normalisation().Nettoyer(s);
        
        return normalized;
    }
     
    


}
