/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.cuentas;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import java.util.ArrayList;
import java.util.List;


public class Model {

    ArrayList<Cuenta> cuentas;
    Cuenta seleccionado;
    Cliente current;

    public Model() {
        this.reset();
    }

    public void reset() {
        cuentas = new ArrayList<>();
        seleccionado = null;
        current = null;
    }

    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.current = current;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }
}
