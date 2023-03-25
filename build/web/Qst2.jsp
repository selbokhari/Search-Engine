<%--
    Document   : Qst2
    Created on : 3 juin 2020, 16:46:48
    Author     : pc
--%>
<%@page import="mr_mp.partie1_1.Qst2"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>
        <title>Question 02</title>
    </head>
    <body><center>
        <div class="border_d marge"> 
         
            <div class="d smallWidth" >    
                <ul class="UL noneLI color1" >
                    <li>  <span>Mot</span>         </li>
                </ul>
            </div>
            <div class="d smallWidth" >    
                <ul class="UL noneLI color1" >
                    <li>  <span>Frequence</span>         </li>
                </ul>
            </div>
        </div>  <br>
        <%
            for (int i = 0; i < 100; i++) {
        %>
        <div class="border_d marge"> 
     
            <div class="d smallWidth" >    
                <ul class="UL noneLI color2" >
                    <li>  <span><%= taches.Qst2.liste100Mots.get(i).mot%></span>         </li>
                </ul>
            </div>
            <div class="d smallWidth" >    
                <ul class="UL noneLI color2" >
                    <li>  <span><%= taches.Qst2.liste100Mots.get(i).frequence%></span>         </li>
                </ul>
            </div>
        </div>  
        <%
            }
        %>
    </center> </body>
</html>
