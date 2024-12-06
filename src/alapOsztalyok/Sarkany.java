/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alapOsztalyok;

import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import vezerles.SzalVezerlo;

/**
 *
 * @author nbx
 */
public class Sarkany extends Thread{

    private int kepX;
    private int kepY;
    private int kepSzelesseg, kepMagassag;
    private Image kep;
    private boolean eltalaltak;
    private int hatterSzelesseg;
    private int hatterMagassag;
    private SzalVezerlo szalVezerlo;
    private long ido;

    /**
     *
     * @param kepX
     * @param kepY
     * @param kepSzelesseg
     * @param kepMagassag
     * @param kep
     * @param eltalaltak
     * @param hatterSzelesseg
     * @param hatterMagassag
     * @param szalVezerlo
     * @param ido
     */
    public Sarkany(int kepX, int kepY, int kepSzelesseg, int kepMagassag, Image kep, boolean eltalaltak, int hatterSzelesseg, int hatterMagassag, SzalVezerlo szalVezerlo, long ido) {
        this.kepX = kepX;
        this.kepY = kepY;
        this.kepSzelesseg = kepSzelesseg;
        this.kepMagassag = kepMagassag;
        this.kep = kep;
        this.eltalaltak = eltalaltak;
        this.hatterSzelesseg = hatterSzelesseg;
        this.hatterMagassag = hatterMagassag;
        this.szalVezerlo = szalVezerlo;
        this.ido = ido;
    }
    
    /**
     *Kirajzol egy képet.
     * @param g
     */
    public void rajzol(Graphics g){
        g.drawImage(kep, kepX, kepY, kepSzelesseg, kepMagassag, null);
    }

    @Override
    public void run() {
        while (kepX < hatterSzelesseg && !eltalaltak) {
            mozdul();
            frissit();
            alszik();
        }
        if (eltalaltak || kepX >= hatterSzelesseg) {
            szalVezerlo.sarkanyTorol(this);
        }
    }
    
    /**
     *Jobbra mozgat.
     */
    private void mozdul() {
        kepX++;
    }
    
    /**
     *Meghívja a vezérlő frissít metódusát.
     */
    private void frissit() {
        szalVezerlo.frissit();
    }

    /**
     *Elaltatja a szálat adott időre.
     */
    private void alszik() {
        try {
            Thread.sleep(ido);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sarkany.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Megvizsgálja, hogy eltalálták-e a példányt.
     * @param x
     * @param y
     * @return
     */
    public boolean talalat(int x, int y){
        if (kepX <= x && x <= kepX + kepSzelesseg &&
                kepY <= y && y <= kepY + kepMagassag) {
            eltalaltak = true;
        }
        return eltalaltak;
    }    

    /**
     *
     * @return
     */
    public int getKepX() {
        return kepX;
    }

    /**
     *
     * @return
     */
    public int getKepY() {
        return kepY;
    }

    /**
     *
     * @return
     */
    public int getKepSzelesseg() {
        return kepSzelesseg;
    }

    /**
     *
     * @return
     */
    public int getKepMagassag() {
        return kepMagassag;
    }

    /**
     *
     * @return
     */
    public Image getKep() {
        return kep;
    }

    /**
     *
     * @return
     */
    public boolean isEltalaltak() {
        return eltalaltak;
    }

    /**
     *
     * @return
     */
    public int getHatterSzelesseg() {
        return hatterSzelesseg;
    }

    /**
     *
     * @return
     */
    public int getHatterMagassag() {
        return hatterMagassag;
    }

    /**
     *
     * @return
     */
    public SzalVezerlo getSzalVezerlo() {
        return szalVezerlo;
    }

    /**
     *
     * @return
     */
    public long getIdo() {
        return ido;
    }
    
}
