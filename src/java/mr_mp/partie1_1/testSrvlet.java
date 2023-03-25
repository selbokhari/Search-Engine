/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp.partie1_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class testSrvlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet testSrvlet</title>");
        out.println("</head>");
        out.println("<body>");
        String fileName = "C:\\Users\\pc\\Desktop\\S2\\Moteurs de recherche\\TPs\\Travail\\MiniProjet(MR)\\stopwords.txt";
        
            ServletContext cntxt = getServletContext();
            String fName = "/WEB-INF/stopwords.txt";
//            String p = cntxt.getRealPath(fName);
            InputStream ins = cntxt.getResourceAsStream(fName);
                
                BufferedReader br = new BufferedReader((new InputStreamReader(ins , "UTF-8")));
                
                String word;
                while ((word = br.readLine()) != null) {
                    out.println(word + "<br>");
                }
             out.println("-------------------------------------------------------------------------------------------</br>");
        
        List<String> lignes = Files.readAllLines(Paths.get(fileName));
        for(String l : lignes)
            out.println(l+"<br>");
            out.println(Paths.get(cntxt.getRealPath("")).getParent().getParent()+"<BR>");
        out.println("</body>");
        out.println("</html>");

    }
//    public static List<String> readFile(String fileName) throws Exception{
//      
//    
//    }
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

}
