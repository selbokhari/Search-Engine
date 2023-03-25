<%-- 
    Document   : Qst1
    Created on : 3 juin 2020, 16:46:48
    Author     : pc
--%>

<%@page import="mr_mp.partie1_1.Qst1"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style1.css" rel="stylesheet" type="text/css"/>
        <title>Question 01</title>
    </head>

    <body>
    <center> 
        <div class="border_d"> 
            <div class="d smallWidth" >    
                <ul class="UL noneLI color1" >
                    <li>  <span>Niveau</span>         </li>
                </ul>
            </div>
            <div class="d smallWidth"> 
                <ul class="UL noneLI color1">
                    <li>  <span>Mots</span>               </li>
                </ul>
            </div>
            <div class="d bigWidth"> 
                <ul class="UL noneLI color1">
                    <li>  <span>      Nombre de lemmes </span>    </li>
                </ul>
            </div>
            <div class="d bigWidth"> 
                <ul class="UL noneLI color1">
                    <li>  <span>     Noveaux lemmes  </span>     </li>
                </ul>
            </div>
        </div>  <br>


        <%
            for (Qst1 qst1 : Qst1.donnees) {
        %>

        <div class="border_d marge"> 
            <div class="d smallWidth" >    
                <ul class="UL noneLI color2" >
                    <li>  <span>    <%=qst1.getNiveau()%>  </span>         </li>
                </ul>
            </div>
            <div class="d smallWidth"> 
                <ul class="UL noneLI color2">
                    <li>  <span>   <%=qst1.getMots_sans_repetition()%>  </span>               </li>
                </ul>
            </div>
            <div class="d bigWidth"> 
                <ul class="UL noneLI color2">
                    <li>    <span>   <%= qst1.getNombre_de_lemmes()%>   </span>           </li>
                </ul>
            </div>
            <div class="d bigWidth"> 
                <ul class="UL noneLI color2">
                    <li>    <span>  <%= qst1.getNouveaux_lemmes()%>  </span>             </li>
                </ul>
            </div>
        </div>  
        <%}%>

    </center>
</body>
</html>
