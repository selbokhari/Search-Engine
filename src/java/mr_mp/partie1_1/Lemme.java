package mr_mp.partie1_1;

import java.util.List;



import AlKhalil2.morphology.analyzer.AnalyzerTokens;
import AlKhalil2.morphology.result.model.Result;
import java.util.ArrayList;


public class Lemme {
	
	public static String getStemm(String string) {
		String stemm="";
		
			AnalyzerTokens  analyzerTokens = new AnalyzerTokens();
			List<Result> liste = analyzerTokens.analyzerToken(string);
                        if(liste.isEmpty()){
                            stemm=string;
                        }else{
			stemm = liste.get(0).getStem(); 
                        }
	
	
		return stemm;
	}
	
	public static List<Result> getLemma(String string) {
		String lemma;
		AnalyzerTokens  analyzerTokens = new AnalyzerTokens();
		List<Result> liste = analyzerTokens.analyzerToken(string);	
		//lemma = liste.get(0).getLemma().toString(); 
		return liste;
	}
        
        
		
        
        public static void  Sans_Repetition(String origine, ArrayList<String> mots) {

        for (int i = 0; i < mots.size(); i++) {
            if (mots.get(i).equals(origine)) {
                        
                 mots.remove(i); 
            }
        }
     
        mots.add(origine);
    }
        
        
                
        public static void  NV_Lemme(String origine, ArrayList<String> nouveau) {
                    
        for (int i = 0; i < nouveau.size(); i++) {
            if (nouveau.get(i).equals(origine)) {
                        
                nouveau.remove(i);
            }
        }
     
        
    }
                public static String getLemme(String mot){
			AlKhalil2.morphology.analyzer.AnalyzerTokens b=new AnalyzerTokens();
			List t = b.analyzerToken(mot);
			String r;
			try {
				AlKhalil2.morphology.result.model.Result x =  (Result) t.get(0);
				r = x.getLemma();
			} catch (Exception e) {

				r="";
			}
			return r;
		} 
   

}
