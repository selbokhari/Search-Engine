package mr_mp;

import mr_mp.partie1_1.tokn;
import AlKhalil2.morphology.result.model.Result;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import mr_mp.partie1_1.Lemme;
import static mr_mp.partie1_1.Lemme.getLemma;
import mr_mp.partie1_1.Qst1;
import mr_mp.partie1_1.normalisation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
public class Qst2 {

    private int point;
    private String mots;
    static String file = "donnees/";
    static String Slash = "/";
    static ArrayList<String> Mots_sans_repet = new ArrayList<>();

    public Qst2(int point, String mots) {
        this.point = point;
        this.mots = mots;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getMots() {
        return mots;
    }

    public void setMots(String mots) {
        this.mots = mots;
    }

    public Qst2() {
    }

    static public void Tout_mots() {
        ArrayList<String> Mots = new ArrayList<>();

        for (int ii = 0; ii < 7; ii++) {
            File rep = new File(file + ii);
            String liste[] = rep.list();
            String line;
            for (int k = 0; k < liste.length; k++) {
                File rep1 = new File(file + ii + Slash + liste[k]);
                String lst[] = rep1.list();
                if (lst != null) {
                    for (int i = 0; i < lst.length; i++) {
                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(file + ii + Slash + liste[k] + Slash + lst[i]), "utf8"))) {//

                            while ((line = bufferedReader.readLine()) != null) {
                                
                                List<String> text = new ArrayList<>();
                                   text=tokn.TokenizerChaine(line);
                                for (String s: text) {
                                    if (!s.isEmpty()) {
                                        Mots.add(s);
                                    }
                                }

                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (int t = 0; t < Mots.size(); t++) {

                            Lemme.Sans_Repetition(Mots.get(t), Mots_sans_repet);
                        }
                       // for (String string : Mots_sans_repet) {
          // System.out.println(string); }
                    }
                }
            }

        }

       
    }

    public static void main(String[] args) {
      Tout_mots();
        
       
        
    }

}
