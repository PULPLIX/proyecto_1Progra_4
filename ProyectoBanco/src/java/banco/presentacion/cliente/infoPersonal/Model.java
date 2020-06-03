/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.infoPersonal;

import banco.logica.Cliente;
import java.util.Objects;


public class Model {

    Cliente cliente;

    public Model() {
        this.reset();
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
        final banco.presentacion.cliente.infoPersonal.Model other = (banco.presentacion.cliente.infoPersonal.Model) obj;
        return true;
    }

}
