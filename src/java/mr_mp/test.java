/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp;

/**
 *
 * @author pc
 */
public class test {
   public static void main(String[] args){
   
   String path = "C:\\solr-8.1.1\\example\\exampledocs\\CorpusMR\\3\\مرشدي في اللغة العربية\\ألعب بحذر.txt";
   System.out.println(path.substring(path.lastIndexOf("\\")+1, path.length()));
   
   
   }
}
