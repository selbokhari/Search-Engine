/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp.partie1_1;

/**
 *
 * @author pc
 */
public class MotFrequence  implements Comparable<MotFrequence> {
  public  String mot ; 
  public   int frequence ;
    public MotFrequence(String mot, int frequence) {
        this.mot = mot;
        this.frequence = frequence;
    }

      @Override
    public int compareTo(MotFrequence r) {
//        return this.support > r.support ? -1 : (this.support == r.support ? 0 : 1);
        if (this.frequence > r.frequence) {
            return -1;
        } else if (this.frequence < r.frequence) {
            return 1;
        } else {

            return 0;

        }
    }
}
