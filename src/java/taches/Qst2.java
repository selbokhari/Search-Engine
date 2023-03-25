package taches;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mr_mp.partie1_1.MotFrequence;


/**
 *
 * @author pc
 */
@WebServlet(name = "Qst2", urlPatterns = {"/Qst2"})
public class Qst2 extends HttpServlet {

    public List<String> stopWordsAr = new ArrayList<String>();
    public String file = "\\donnees\\";
    public String Slash = "\\";
    static public ArrayList<MotFrequence> liste100Mots;
    public static String projectPath;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */

        Qst2 qst2 = new Qst2();
        ServletContext context = getServletContext();
        projectPath = Paths.get(context.getRealPath("")).getParent().getParent() + "";
        
        if (liste100Mots == null) {
            liste100Mots = run(context, qst2);
        } else if (liste100Mots.isEmpty()) {
            liste100Mots = run(context, qst2);
        }

//        for (int k = 0; k < 100; k++) {
//            out.println(liste100Mots.get(k).mot + " , " + liste100Mots.get(k).frequence + "( " + k + ")<br>");
//        }
          RequestDispatcher requestDispatcher = (getServletContext().getRequestDispatcher("/Qst2.jsp"));
        requestDispatcher.forward(request, response);
     

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public ArrayList<MotFrequence> run(ServletContext context, Qst2 qst2) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        ArrayList<MotFrequence> Liste_Mot_freq = new ArrayList<>();
        ArrayList<String> mot = new ArrayList<>();
        String line;
        qst2.file = qst2.projectPath + qst2.file;
        for (int ii = 0; ii < 7; ii++) {
            File rep = new File(qst2.file + ii);
            String liste[] = rep.list();
            for (String liste1 : liste) {

                File rep1 = new File(qst2.file + ii + qst2.Slash + liste1);
                String liste0[] = rep1.list();
                if (liste0 != null) {
                    for (String liste01 : liste0) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(qst2.file + ii + qst2.Slash + liste1 + qst2.Slash + liste01), "utf8"));
                        while ((line = bufferedReader.readLine()) != null) {
                            line = normalisation(line, context, qst2);
                            List<String> text = TokenizerChaine(line, qst2);
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

          Collections.sort(Liste_Mot_freq);
        return Liste_Mot_freq;

    }

    public List<String> TokenizerChaine(String str, Qst2 qst2) throws IOException {
        // String str = "I am sample string and will be tokenized on space";
        String normalized = Nettoyer(str, qst2);
        List<String> tokens = new ArrayList<>();

        StringTokenizer defaultTokenizer = new StringTokenizer(normalized);

        while (defaultTokenizer.hasMoreTokens()) {
            tokens.add(defaultTokenizer.nextToken());
        }

        return tokens;
    }

    public List<String> Stopword(BufferedReader reader) throws FileNotFoundException, IOException {

        String line;
        List<String> stp = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            stp.add(line);
        }
        return stp;
    }

    public String Nettoyer(String str, Qst2 qst2) throws IOException {

        // First get the file InputStream using ServletContext.getResourceAsStream()
        // method.
//        InputStream is = context.getResourceAsStream(filename);
        String fileName = qst2.projectPath + "\\stopwords.txt";

        qst2.stopWordsAr = Files.readAllLines(Paths.get(fileName));

        Pattern p = Pattern.compile("[\\p{P}\\p{Mn}]");
        Matcher m = p.matcher(str);
        m.reset();
        String n = m.replaceAll("").replaceAll("[a-zA-Z]", "").replaceAll("[0-9]", "").replaceAll("\\p{Punct}", "");
        String nostopwords = new String();
        String tab[] = n.trim().split(" ");
        for (String tab1 : tab) {
            if (!qst2.stopWordsAr.contains(tab1.trim())) {
                nostopwords += " " + tab1.trim();
            }
        }
        return nostopwords.toLowerCase().trim();
    }

    static public String normalisation(String s, ServletContext context, Qst2 qst2) throws IOException {

        String normalized = qst2.Nettoyer(s, qst2);

        return normalized;
    }

    public void Position(String string, ArrayList<MotFrequence> mf) {
        int compt = 1;

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

//    public ArrayList<MotFrequence> triage(ArrayList<MotFrequence> mots_Frequence) {
//        int compt = 0;
//        ArrayList<MotFrequence> Freq_Trier = new ArrayList<>();
//        MotFrequence trie;
//        ArrayList<Integer> frequence = new ArrayList<>();
//
//        for (int i = 0; i < mots_Frequence.size(); i++) {
//            frequence.add(mots_Frequence.get(i).frequence);
//        }
//        sort(frequence);
//        for (int j = frequence.size() - 1; j >= 0; j--) {
//            for (int f = 0; f < mots_Frequence.size(); f++) {
//                if (mots_Frequence.get(f).frequence == frequence.get(j)) {
//                    trie = new MotFrequence(mots_Frequence.get(f).mot, mots_Frequence.get(f).frequence);
//                    Freq_Trier.add(trie);
//                    mots_Frequence.remove(f);
//                    compt++;
//                    if (compt == 100) {
//                        j = 0;
//                        f = mots_Frequence.size();
//                    }
//                }
//
//            }
//        }
//        return Freq_Trier;
//    }

}
