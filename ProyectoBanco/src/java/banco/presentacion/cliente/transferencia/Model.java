/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.transferencia;

import banco.logica.Cuenta;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Model {

    List<Cuenta> cuentas;
    Cuenta seleccionado;
    Cuenta aTransferir;
    ArrayList<Cuenta> favoritas;

    public Model() {
        this.reset();
    }

    public void reset() {
        List<Cuenta> rows = new ArrayList<>();
        seleccionado = null;
        aTransferir = null;
        this.setCuentas(rows);
        this.favoritas = new ArrayList<>();
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Cuenta getaTransferir() {
        return aTransferir;
    }

    public void setaTransferir(Cuenta aTransferir) {
        this.aTransferir = aTransferir;
    }

    public ArrayList<Cuenta> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(ArrayList<Cuenta> favoritas) {
        this.favoritas = favoritas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.cuentas);
        hash = 23 * hash + Objects.hashCode(this.seleccionado);
        hash = 23 * hash + Objects.hashCode(this.aTransferir);
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
        final Model other = (Model) obj;
        return true;
    }

}
