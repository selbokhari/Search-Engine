package solr;

import AlKhalil2.morphology.analyzer.AnalyzerTokens;
import AlKhalil2.morphology.result.model.Result;
import java.util.ArrayList;
import java.util.List;

public class AlKhalil {
    
    
   	 public List <String> getLemme(String str){
        List <String> liLemme = new ArrayList<>();
        try{
        AlKhalil2.text.tokenization.Tokenization tokens = new AlKhalil2.text.tokenization.Tokenization();
        tokens.setTokenizationString(str);
        //+-------------------------------------------+
        int nbNoAnalyzedWord = 0;
        int nbAnalyzedWord = 0;
        int i=0;
        //+-------------------------------------------+
        java.util.List l = new java.util.ArrayList();
        l.addAll(tokens.getTokens());
        java.util.Collections.sort(l);
        //+-------------------------------------------+
        java.util.Iterator<String> it_normalized = l.iterator();
        AnalyzerTokens at = new AnalyzerTokens();
        List result = new ArrayList();
        //+-------------------------------------------+
        while( it_normalized.hasNext() ){
            String normalizedWord = it_normalized.next();
            //+-------------------------------------------+
            result = at.analyzerToken(normalizedWord);
            
            //+-------------------------------------------+
            if(result.isEmpty()){
                nbNoAnalyzedWord += tokens.getTokensRepeat().get(normalizedWord);
            }
            else{
                nbAnalyzedWord += tokens.getTokensRepeat().get(normalizedWord);
            }
            //+-------------------------------------------+
            i++;
            //+-------------------------------------------+
        }
        //System.out.println(result.get(0).toString()+"----------------------------------------");
        String [] temp ;
        List <String> liStem = new ArrayList<>();
        
        String st ;
        for(i=0;i<result.size();i++){
            temp = result.get(i).toString().split(",");
            for (String temp1 : temp) {
                if(temp1.contains("stem")){
                    String [] t = temp1.split("=");
                    st = t[1].replaceAll("[ًٌٍَُِّْ]", "");
                    if(!liStem.toString().contains(st))
                        liStem.add(st);
                }
                else if(temp1.contains("lemme=")){
                    String [] t = temp1.split("=");
                    st = t[1].replaceAll("[ًٌٍَُِّْ]", "");
                    if(!liLemme.toString().contains(st))
                        liLemme.add(st);
                }
            }
        }
        /*System.out.println("Stem : "+liStem.toString());
        System.out.println("Lemme : "+liLemme.toString());*/
        
         }
        catch(Exception EX){
           EX.printStackTrace();
        }
        return liLemme;
    }
    
}
