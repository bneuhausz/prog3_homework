/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vezerles;

import adatKezeles.AdatBevitel;
import adatKezeles.FajlKezeles;
import alapOsztalyok.Global;
import alapOsztalyok.Jatekos;
import alapOsztalyok.Repulo;
import alapOsztalyok.Sarkany;
import feluletek.HangFrame;
import feluletek.JatekPanel;
import feluletek.JatekosPanel;
import feluletek.KijelzoPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modellcsomag.RendezhetoListModel;

/**
 *
 * @author nbx
 */
public class SzalVezerlo extends Thread {

    private JatekPanel jatekPanel;
    private KijelzoPanel kijelzoPanel;
    private JatekosPanel jatekosPanel;

    private List<Sarkany> sarkanyok = new CopyOnWriteArrayList<>();
    private List<Repulo> repulok = new CopyOnWriteArrayList<>();
    private RendezhetoListModel<Jatekos> jatekosok = new RendezhetoListModel<>();
    private HangFrame hangFrame = new HangFrame();

    private Jatekos jatekos;
    private boolean repuloTalalat;

    /**
     *
     * @param jatekPanel
     * @param jatekosPanel
     * @param kijelzoPanel
     */
    public SzalVezerlo(JatekPanel jatekPanel, JatekosPanel jatekosPanel, KijelzoPanel kijelzoPanel) {
        this.jatekPanel = jatekPanel;
        this.kijelzoPanel = kijelzoPanel;
        this.jatekosPanel = jatekosPanel;
    }

    /**
     *Kirajzol egy képet.
     * @param g
     */
    public void rajzol(Graphics g) {
        for (Sarkany sarkany : sarkanyok) {
            sarkany.rajzol(g);
        }
        for (Repulo repulo : repulok) {
            repulo.rajzol(g);
        }
    }

    /**
     *Megvizsgálja, hogy eltaláltak-e egy célpontot.
     * @param x
     * @param y
     */
    public void kattintasVizsgalat(int x, int y) {
        for (Sarkany sarkany : sarkanyok) {
            if (sarkany.talalat(x, y)) {
                jatekos.pontotKap();
                hangFrame.sarkanyHang();
                frissit();
            }
        }
        kijelzoPanel.pontotKiir(jatekos.getPontSzam());
        for (Repulo repulo : repulok) {
            if (repulo.talalat(x, y)) {
                frissit();
            }
        }
    }

    /**
     *Elindítja a szálat.
     */
    public void indit() {
        String nev = jatekosPanel.getNev();
        if (nev.isEmpty()) {
            JOptionPane.showMessageDialog(jatekosPanel, "Nem adott meg nevet!");
        } else {
            jatekosPanel.gombotAllit();
            jatekos = new Jatekos(nev);
            hangFrame.zeneStart();
            start();
            frissit();
        }
    }

    /**
     *Töröl a képernyőről.
     * @param sarkany
     */
    public void sarkanyTorol(Sarkany sarkany) {
        sarkanyok.remove(sarkany);
        sarkany = null;
    }
    
    /**
     *Töröl a képernyőről.
     * @param repulo
     */
    public void repuloTorol(Repulo repulo) {
        repulok.remove(repulo);
        repulo = null;
    }

    /**
     *Meghívja a panel repaint metódusát.
     */
    public void frissit() {
        jatekPanel.repaint();
    }

    public void run() {
        long aktIdo = Global.JATEK_IDO_SEC * 1000;
        while (aktIdo >= 0 && !repuloTalalat) {
            try {
                kijelzoPanel.idoKiir(aktIdo / 1000);
                if (jatekosPanel.isNehez()) {
                    double r = Math.random();
                    if (r > 0.5) {
                        repuloInditas();
                    } else {
                        sarkanyInditas();
                    }
                } else {
                    sarkanyInditas();
                }
                Thread.sleep(Global.KELETKEZESI_IDO_MSEC);
                aktIdo -= Global.KELETKEZESI_IDO_MSEC;
            } catch (InterruptedException ex) {
                Logger.getLogger(SzalVezerlo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!repuloTalalat) {
            leallit();
        }
    }

    /**
     *Elindítja a repülőket.
     */
    private void repuloInditas() {
        int kx = 0;
        int ky = (int) (Math.random() * (Global.HATTER_MAGASSAG-Global.REPULO_MAGASSAG));
        Image kep = new ImageIcon(this.getClass().getResource(Global.REPULO_KEPUT)).getImage();
        Repulo repulo = new Repulo(kx, ky, Global.REPULO_SZELESSEG, Global.REPULO_MAGASSAG, kep, false, Global.HATTER_SZELESSEG, Global.HATTER_MAGASSAG, this, Global.MOZGAS_IDO_NEHEZ_MSEC);
        repulok.add(repulo);
        repulo.start();
    }

    /**
     *Elindítja a sárkányokat.
     */
    private void sarkanyInditas() {
        int kx = 0;
        int ky = (int) (Math.random() * (Global.HATTER_MAGASSAG-Global.SARKANY_MAGASSAG));
        Image kep = new ImageIcon(this.getClass().getResource(Global.SARKANY_KEPUT)).getImage();
        long ido;

        if (jatekosPanel.isNehez()) {
            ido = Global.MOZGAS_IDO_NEHEZ_MSEC;
        } else {
            ido = Global.MOZGAS_IDO_NORMAL_MSEC;
        }
        Sarkany sarkany = new Sarkany(kx, ky, Global.SARKANY_SZELESSEG, Global.SARKANY_MAGASSAG, kep, false, Global.HATTER_SZELESSEG, Global.HATTER_MAGASSAG, this, ido);
        sarkanyok.add(sarkany);
        sarkany.start();
    }

    /**
     *Leállítja a játékot, ha nem repülő lelövésével ér véget.
     */
    private void leallit() {
        hangFrame.zeneStop();
        eltuntet();
        if (jatekosPanel.isNehez()) {
            if (jatekosok.getElementAt(Global.RANGLISTA_UTOLSO).getPontSzam() < jatekos.getPontSzam()) {
                FileWriter ki = null;
                try {
                    jatekosok.addElement(jatekos, true);
                    jatekosok.removeElement(jatekosok.getElementAt(Global.RANGLISTA_UTOLSO+1));
                    ki = new FileWriter(System.getProperty("user.dir") + "/src/adatok/ranglista.txt", false);
                    for (int i = 0; i < jatekosok.getSize(); i++) {
                        String adat = jatekosok.getElementAt(i).getNev() + ";" + jatekosok.getElementAt(i).getPontSzam() + "\n";
                        ki.write(adat + "\r\n");
                        ki.flush();
                    }
                    JOptionPane.showMessageDialog(jatekPanel, "Gratulálok, nyertél nehéz nehézségi szinten és felkerültél a ranglistára!");
                    jatekPanel.fomenu();
                } catch (IOException ex) {
                    Logger.getLogger(SzalVezerlo.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        ki.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SzalVezerlo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                int minimumPont = jatekosok.getElementAt(Global.RANGLISTA_UTOLSO).getPontSzam() + 1;
                JOptionPane.showMessageDialog(jatekPanel, "Az elért pontszám: " + jatekos.getPontSzam() + ". Sajnos nem érted el a szükséges " + minimumPont +" pontot, így nem kerültél fel a ranglistára.");
                jatekPanel.fomenu();
            }
        } else {
            JOptionPane.showMessageDialog(jatekPanel, "Gratulálok, nyertél normál nehézségi szinten!");
            jatekPanel.fomenu();
        }
    }

    /**
     *Leállítja a játékot, ha repülő lelövésével ér véget.
     */
    public void vege() {
        eltuntet();
        hangFrame.zeneStop();
        repuloTalalat = true;
        JOptionPane.showMessageDialog(jatekPanel, "GAME OVER!");
        jatekPanel.fomenu();
    }

    /**
     *Eltünteti a képernyőről a célpontokat.
     */
    private void eltuntet() {
        for (Sarkany sarkany : sarkanyok) {
            sarkanyok.remove(sarkany);
            sarkany = null;
        }
        for (Repulo repulo : repulok) {
            repulok.remove(repulo);
            repulo = null;
        }
    }

    /**
     *Elindítja a fájlból olvasást.
     */
    public void beallit() {
        try {
            AdatBevitel adatBevitel = new FajlKezeles();
            jatekosok = adatBevitel.jatekosModelBevitel();
        } catch (Exception e) {
            Logger.getLogger(SzalVezerlo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *Kilép a főmenübe.
     */
    public void fomenubeLep() {
        eltuntet();
        hangFrame.zeneStop();
    }
}
