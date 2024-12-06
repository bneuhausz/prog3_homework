/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alapOsztalyok;

import java.util.Objects;

/**
 *
 * @author nbx
 */
public class Jatekos implements Comparable<Jatekos>{
    private String nev;
    private int pontSzam;

    /**
     *
     * @param nev
     */
    public Jatekos(String nev) {
        this.nev = nev;
    }

    /**
     *
     * @param nev
     * @param pontSzam
     */
    public Jatekos(String nev, int pontSzam) {
        this.nev = nev;
        this.pontSzam = pontSzam;
    }
    
    /**
     *A játékos pontszámát növeli.
     */
    public void pontotKap(){
        pontSzam++;
    }

    @Override
    public String toString() {
        return nev + " " + pontSzam + " pont";
    }

    /**
     *
     * @return
     */
    public String getNev() {
        return nev;
    }

    /**
     *
     * @return
     */
    public int getPontSzam() {
        return pontSzam;
    }    

    @Override
    public int compareTo(Jatekos t) {
        return t.pontSzam - this.pontSzam;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nev);
        hash = 47 * hash + this.pontSzam;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jatekos other = (Jatekos) obj;
        if (!Objects.equals(this.nev, other.nev)) {
            return false;
        }
        if (this.pontSzam != other.pontSzam) {
            return false;
        }
        return true;
    }
    
}
