package mr_mp.partie1_1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 *
 * @author PC
 */
public class tokn {

    

    static public List<String> TokenizerChaine(String str) throws IOException {
        // String str = "I am sample string and will be tokenized on space";
//        String normalized = new normalisation().Nettoyer(str);
        List<String> tokens = new ArrayList<>();

        StringTokenizer defaultTokenizer = new StringTokenizer(str);

        while (defaultTokenizer.hasMoreTokens()) {
            tokens.add(defaultTokenizer.nextToken());
        }

        return tokens;
    }

   

}
