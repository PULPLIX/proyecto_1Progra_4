/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.creaCuenta;

import banco.logica.Moneda;
import banco.logica.TipoCuenta;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Model {
    private ArrayList<Moneda> tipoMoneda;
    private ArrayList<TipoCuenta> tipoCuenta;

    public Model(ArrayList<Moneda> tipoMoneda, ArrayList<TipoCuenta> tipoCuenta) {
        this.tipoMoneda = tipoMoneda;
        this.tipoCuenta = tipoCuenta;
    }

    public Model() {
        this.tipoMoneda = new ArrayList<>();
        this.tipoCuenta =  new ArrayList<>();
    }
    
    public ArrayList<Moneda> getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(ArrayList<Moneda> tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public ArrayList<TipoCuenta> getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(ArrayList<TipoCuenta> tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    
}
