/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author david
 */
@Embeddable
public class MovimientoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_movimiento")
    private int idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cuenta_num_cuenta")
    private String cuentaNumCuenta;

    public MovimientoPK() {
    }

    public MovimientoPK(int idMovimiento, String cuentaNumCuenta) {
        this.idMovimiento = idMovimiento;
        this.cuentaNumCuenta = cuentaNumCuenta;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getCuentaNumCuenta() {
        return cuentaNumCuenta;
    }

    public void setCuentaNumCuenta(String cuentaNumCuenta) {
        this.cuentaNumCuenta = cuentaNumCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovimiento;
        hash += (cuentaNumCuenta != null ? cuentaNumCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoPK)) {
            return false;
        }
        MovimientoPK other = (MovimientoPK) object;
        if (this.idMovimiento != other.idMovimiento) {
            return false;
        }
        if ((this.cuentaNumCuenta == null && other.cuentaNumCuenta != null) || (this.cuentaNumCuenta != null && !this.cuentaNumCuenta.equals(other.cuentaNumCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.MovimientoPK[ idMovimiento=" + idMovimiento + ", cuentaNumCuenta=" + cuentaNumCuenta + " ]";
    }
    
}
