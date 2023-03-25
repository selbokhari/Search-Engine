/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solr;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class Afficher extends HttpServlet {

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
        try {
               String path = request.getParameter("path");
               String nom = path.substring(path.lastIndexOf("\\") + 1, path.length());
            out.println("<!DOCTYPE html>");

            out.println("<html>");
            out.println("<head>");
            out.println(" <link href=\"css/style3.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<title>"+nom.replace(".txt", "")+"</title>");
            out.println("</head>");
            out.println("<body><center>");

         
//           String path = "C:\\Users\\pc\\Desktop\\tp5ex5\\Donnees\\آداب العلماء والمتعلمين.txt";
            out.println("<div class=\"border_d\"> <font color=white size=+4>" + nom.replace(".txt", "") + "</font></div> <br>");
//           out.println("<div class=\"border_d\">");
            List<String> lignes = Files.readAllLines(Paths.get(path));
            for (String l : lignes) {
                out.println("<font color=white size=+2>" + l + "</font> <br>");
            }
            out.println("<br>");
            out.println("</center></body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println("<h1> Error :" + e.getMessage() + "</h1>");
        }
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

}
