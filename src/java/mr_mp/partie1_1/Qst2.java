/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp.partie1_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;


/**
 *
 * @author PC
 */
public class Qst2 {

 
    static String file = "donnees/";
    static String Slash = "/";
   public static ArrayList<MotFrequence> liste100Mots ;


   

    static public ArrayList<MotFrequence> run() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        ArrayList<MotFrequence> Liste_Mot_freq = new ArrayList<>();
        ArrayList<String> mot = new ArrayList<>();
        String line;
        for (int ii = 0; ii < 7; ii++) {
            File rep = new File(file + ii);
            String liste[] = rep.list();
            for (String liste1 : liste) {
                File rep1 = new File(file + ii + Slash + liste1);
                String liste0[] = rep1.list();
                if (liste0 != null) {
                    for (String liste01 : liste0) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(file + ii + Slash + liste1 + Slash + liste01), "utf8"));
                        while ((line = bufferedReader.readLine()) != null) {
                            line = normalisation.normalisation(line);
                            List<String> text = tokn.TokenizerChaine(line);
                            for (String s : text) {
                                if (!s.isEmpty()) {
                                    mot.add(s);
                                }
                            }
                        }
                    }
                }
            }

        }
        for (int i = 0; i < mot.size(); i++) {
            Position(mot.get(i), Liste_Mot_freq);
        }
        ArrayList<MotFrequence> M_F_Trier = triage(Liste_Mot_freq);
        return M_F_Trier;

    }

    static public void Position(String string, ArrayList<MotFrequence> mf) {
        int compt = 1;
        int cont = 0;
        MotFrequence motfq;

        for (int i = 0; i < mf.size(); i++) {
            if (mf.get(i).mot.equals(string)) {
                compt = mf.get(i).frequence + 1;
                mf.remove(i);
            }
        }
        motfq = new MotFrequence(string, compt);
        mf.add(motfq);
    }

    public static ArrayList<MotFrequence> triage(ArrayList<MotFrequence> mots_Frequence) {
        int compt = 0;
        ArrayList<MotFrequence> Freq_Trier = new ArrayList<>();
        MotFrequence trie;
        ArrayList<Integer> frequence = new ArrayList<>();

        for (int i = 0; i < mots_Frequence.size(); i++) {
            frequence.add(mots_Frequence.get(i).frequence);
        }
        sort(frequence);
        for (int j = frequence.size() - 1; j >= 0; j--) {
            for (int f = 0; f < mots_Frequence.size(); f++) {
                if (mots_Frequence.get(f).frequence == frequence.get(j)) {
                    trie = new MotFrequence(mots_Frequence.get(f).mot, mots_Frequence.get(f).frequence);
                    Freq_Trier.add(trie);
                    mots_Frequence.remove(f);
                    compt++;
                    if (compt == 100) {
                        j = 0;
                        f = mots_Frequence.size();
                    }
                }

            }
        }
        return Freq_Trier;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {

       liste100Mots = run();
        for (int k = 0; k < liste100Mots.size(); k++) {
            System.out.println(liste100Mots.get(k).mot + " , " + liste100Mots.get(k).frequence);

        }
        
        

    }

}
