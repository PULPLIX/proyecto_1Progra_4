/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.movimientos;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import banco.logica.Movimiento;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Oscar
 */
public class Model {

    List<Cuenta> cuentas;
    List<Movimiento> movimientos;
    Cuenta seleccionado;
    Cliente current;

    public Model() {
        this.reset();
    }

    private void reset() {
        seleccionado = null;
        current = null;
        movimientos = new ArrayList<Movimiento>();
        cuentas = new ArrayList<Cuenta>();
    }

    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.current = current;
    }

    public void setMovimientos(List<Movimiento> Movimiento) {
        this.movimientos = movimientos;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }

    
       public void setCuentas(List<Cuenta> cuentas){
        this.cuentas =cuentas;    
    }

     public List<Cuenta> getCuentas() {
        return cuentas;
    } 
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.current);
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
        final banco.presentacion.cliente.movimientos.Model other = (banco.presentacion.cliente.movimientos.Model) obj;
        return true;
    }

}
