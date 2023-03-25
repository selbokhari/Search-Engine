/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp.partie1_1;

import AlKhalil2.morphology.result.model.Result;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import static mr_mp.partie1_1.Lemme.getLemma;

/**
 *
 * @author PC
 */
public class Qst1 {

    public Qst1() {
    }

    private int Niveau;
    private int Mots_sans_repetition;
    private int Nombre_de_lemmes;
    private int Nouveaux_lemmes;
    public static List<Qst1> donnees = new ArrayList<Qst1>();
    static String file = "donnees1\\";
    static String Slash = "\\";

    public int getNiveau() {
        return Niveau;
    }

    public void setNiveau(int Niveau) {
        this.Niveau = Niveau;
    }

    public int getMots_sans_repetition() {
        return Mots_sans_repetition;
    }

    public void setMots_sans_repetition(int Mots_sans_repetition) {
        this.Mots_sans_repetition = Mots_sans_repetition;
    }

    public int getNombre_de_lemmes() {
        return Nombre_de_lemmes;
    }

    public void setNombre_de_lemmes(int Nombre_de_lemmes) {
        this.Nombre_de_lemmes = Nombre_de_lemmes;
    }

    public int getNouveaux_lemmes() {
        return Nouveaux_lemmes;
    }

    public void setNouveaux_lemmes(int Nouveaux_lemmes) {
        this.Nouveaux_lemmes = Nouveaux_lemmes;
    }

    public Qst1(int Niveau, int Mots_sans_repetition, int Nombre_de_lemmes, int Nouveaux_lemmes) {
        this.Niveau = Niveau;
        this.Mots_sans_repetition = Mots_sans_repetition;
        this.Nombre_de_lemmes = Nombre_de_lemmes;
        this.Nouveaux_lemmes = Nouveaux_lemmes;
    }

    static public void traitement(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        List<List<String>> lemmes_NR_ancien = new ArrayList<>();
        file =path+file;
        for (int ii = 0; ii < 7; ii++) {

            File rep = new File(file + ii);
            String liste[] = rep.list();

            String line;

            ArrayList<String> Mots = new ArrayList<>();
            ArrayList<String> Mots_sans_repet = new ArrayList<>();
            List<Result> lemmes = null;
            ArrayList<String> lemmes_NR = new ArrayList<>();

            int Nombre_de_lemmes_NR = 0;

            ArrayList<String> NvlistLam = new ArrayList<>();
            for (String liste1 : liste) {
                File rep1 = new File(file + ii + Slash + liste1);
                String liste0[] = rep1.list();
                if (liste0 != null) {
                    //
                    for (String liste01 : liste0) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(file + ii + Slash + liste1 + Slash + liste01), "utf8"));
                        while ((line = bufferedReader.readLine()) != null) {
                            line = normalisation.normalisation(line);

                            List<String> text = new ArrayList<>();
                            text = tokn.TokenizerChaine(line);
                            for (String s : text) {
                                if (!s.isEmpty()) {
                                    Mots.add(s);
                                }
                            }

                        }
                    }
                }
            }
            ////////////////////////////////////////////////////////////
            for (int t = 0; t < Mots.size(); t++) {

                Lemme.Sans_Repetition(Mots.get(t), Mots_sans_repet);
            }
            for (int s = 0; s < Mots_sans_repet.size(); s++) {
                lemmes = getLemma(Mots_sans_repet.get(s));
                for (int d = 0; d < lemmes.size(); d++) {
                    lemmes_NR.add(lemmes.get(d).getLemma());
                }
                Nombre_de_lemmes_NR = Nombre_de_lemmes_NR + lemmes.size();
            }
            NvlistLam = (ArrayList<String>) lemmes_NR.clone();
            lemmes_NR_ancien.add(lemmes_NR);
            Qst1 Nv;
            if (ii == 0) {
                Nv = new Qst1(ii, Mots_sans_repet.size(), Nombre_de_lemmes_NR, 0);

            } else {

                for (int t = 0; t < lemmes_NR_ancien.get(ii - 1).size(); t++) {

                    Lemme.NV_Lemme(lemmes_NR_ancien.get(ii - 1).get(t), NvlistLam);
                }

                Nv = new Qst1(ii, Mots_sans_repet.size(), Nombre_de_lemmes_NR, NvlistLam.size());
            }
            donnees.add(Nv);
            System.out.println("Niveau " + ii + " est bien traité");
        }

    }

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {

        traitement("");
        String leftAlignFormat = "| %-10s | %-6d |  %-19d |  %-19d |%n";

        System.out.format("+------------+--------+----------------------+----------------------+%n");
        System.out.format("| Niveau     | Mots   | Nombre de lemmes     | Nouveaux lemmes      |%n");
        System.out.format("+------------+--------+----------------------+----------------------+%n");
        for (Qst1 d : donnees) {
            System.out.format(leftAlignFormat, d.getNiveau(), d.getMots_sans_repetition(), d.getNombre_de_lemmes(), d.getNouveaux_lemmes());
            System.out.format("+------------+--------+----------------------+----------------------+%n");

        }
    }

}
