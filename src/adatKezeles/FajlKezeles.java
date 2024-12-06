/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatKezeles;

import alapOsztalyok.Global;
import alapOsztalyok.Jatekos;
import java.io.InputStream;
import java.util.Scanner;
import modellcsomag.RendezhetoListModel;

/**
 *
 * @author nbx
 */
public class FajlKezeles implements AdatBevitel{
    
    private RendezhetoListModel<Jatekos> jatekosok = new RendezhetoListModel<>();

    /**
     *Visszaadja az elkészített modellt.
     * @return
     * @throws Exception
     */
    @Override
    public RendezhetoListModel<Jatekos> jatekosModelBevitel() throws Exception {
        fajlbol(Global.FAJLUT);
        return jatekosok;
    }
    
    /**
     *Beolvassa az adott fájl következő sorát.
     * @param fajlut
     * @throws Exception
     */
    private void fajlbol(String fajlut) throws Exception{
        try (InputStream ins = this.getClass().getResourceAsStream(fajlut);
                Scanner fajlScanner = new Scanner(ins, Global.CHAR_SET)){
            String sor;
            while (fajlScanner.hasNextLine()) {                
                sor = fajlScanner.nextLine();
                if (!sor.isEmpty()) {
                    fedolgoz(sor);
                }
            }
        }
    }

    /**
     *Feldolgozza az adott sort.
     * @param sor
     * @throws Exception
     */
    private void fedolgoz(String sor) throws Exception{
        String[] adatok = sor.split(";");
        jatekosok.addElement(new Jatekos(adatok[0], Integer.parseInt(adatok[1])), true);
    }    
}
