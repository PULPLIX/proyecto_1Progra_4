/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Iván
 */
@Embeddable
public class CuentasfavoritasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cliente_idCliente")
    private int clienteidCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_idcuenta")
    private int cuentaIdcuenta;

    public CuentasfavoritasPK() {
    }

    public CuentasfavoritasPK(int clienteidCliente, int cuentaIdcuenta) {
        this.clienteidCliente = clienteidCliente;
        this.cuentaIdcuenta = cuentaIdcuenta;
    }

    public int getClienteidCliente() {
        return clienteidCliente;
    }

    public void setClienteidCliente(int clienteidCliente) {
        this.clienteidCliente = clienteidCliente;
    }

    public int getCuentaIdcuenta() {
        return cuentaIdcuenta;
    }

    public void setCuentaIdcuenta(int cuentaIdcuenta) {
        this.cuentaIdcuenta = cuentaIdcuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clienteidCliente;
        hash += (int) cuentaIdcuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasfavoritasPK)) {
            return false;
        }
        CuentasfavoritasPK other = (CuentasfavoritasPK) object;
        if (this.clienteidCliente != other.clienteidCliente) {
            return false;
        }
        if (this.cuentaIdcuenta != other.cuentaIdcuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.CuentasfavoritasPK[ clienteidCliente=" + clienteidCliente + ", cuentaIdcuenta=" + cuentaIdcuenta + " ]";
    }
    
}
