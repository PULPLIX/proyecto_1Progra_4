/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.acreditarIntereses;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Moneda;
import banco.logica.TipoCuenta;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Oscar
 */
public class Model {

    Cliente cliente;
    private ArrayList<Moneda> listaMonedas;
    private ArrayList<Cuenta> cuentas;

    public Model(ArrayList<Moneda> tipoMoneda, ArrayList<Cuenta> tipoCuenta) {
        this.listaMonedas = tipoMoneda;
        this.cuentas = tipoCuenta;
    }

    public Model() {
        this.reset();
        this.listaMonedas = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }

    private void reset() {
        cliente = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Moneda> getListaMonedas() {
        return listaMonedas;
    }

    public void setListaMonedas(ArrayList<Moneda> tipoMoneda) {
        this.listaMonedas = tipoMoneda;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> tipoCuenta) {
        this.cuentas = tipoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.cliente);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final banco.presentacion.cajero.acreditarIntereses.Model other = (banco.presentacion.cajero.acreditarIntereses.Model) obj;
        return true;
    }

}
