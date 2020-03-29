/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.depositos;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Iván
 */
public class Model {

    Cliente cliente;
    Cliente clienteBuscar;
    List<Cuenta> cuentas;
    Cuenta seleccionada;

    public Model() {
        this.reset();
    }

    private void reset() {
        cliente = null;
        clienteBuscar = null;
        cuentas = null;
        seleccionada = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getClienteBuscar() {
        return clienteBuscar;
    }

    public void setClienteBuscar(Cliente clienteBuscar) {
        this.clienteBuscar = clienteBuscar;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Cuenta seleccionada) {
        this.seleccionada = seleccionada;
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
        final banco.presentacion.cajero.infoPersonal.Model other = (banco.presentacion.cajero.infoPersonal.Model) obj;
        return true;
    }

}

