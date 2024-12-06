/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatKezeles;

import alapOsztalyok.Jatekos;
import modellcsomag.RendezhetoListModel;

/**
 *
 * @author nbx
 */
public interface AdatBevitel {

    /**
     *
     * @return
     * @throws Exception
     */
    public RendezhetoListModel<Jatekos> jatekosModelBevitel() throws Exception;
}
