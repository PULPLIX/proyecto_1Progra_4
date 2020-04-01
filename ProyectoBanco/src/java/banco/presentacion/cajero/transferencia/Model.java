/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cajero.transferencia;

import banco.logica.Cliente;
import banco.logica.Cuenta;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Escinf
 */
public class Model {

    Cliente cliente;
    Cliente clienteBuscar;
    ArrayList<Cuenta> cuentas;
    Cuenta seleccionada;

    Cliente clienteBuscarDestino;
    ArrayList<Cuenta> cuentasDestino;
    Cuenta seleccionadaDestino;

    double monto;

    public Model() {
        this.reset();
    }

    private void reset() {
        cliente = null;
        clienteBuscar = null;
        cuentas = null;
        seleccionada = null;
        clienteBuscarDestino = null;
        cuentasDestino = null;
        seleccionadaDestino = null;
        this.monto = 0;
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

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Cuenta seleccionada) {
        this.seleccionada = seleccionada;
    }

    public Cliente getClienteBuscarDestino() {
        return clienteBuscarDestino;
    }

    public void setClienteBuscarDestino(Cliente clienteBuscarDestino) {
        this.clienteBuscarDestino = clienteBuscarDestino;
    }

    public ArrayList<Cuenta> getCuentasDestino() {
        return cuentasDestino;
    }

    public void setCuentasDestino(ArrayList<Cuenta> cuentasDestino) {
        this.cuentasDestino = cuentasDestino;
    }

    public Cuenta getSeleccionadaDestino() {
        return seleccionadaDestino;
    }

    public void setSeleccionadaDestino(Cuenta seleccionadaDestino) {
        this.seleccionadaDestino = seleccionadaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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
        final banco.presentacion.cajero.depositos.Model other = (banco.presentacion.cajero.depositos.Model) obj;
        return true;
    }

}
