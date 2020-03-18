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
    @Column(name = "cuenta_num_cuenta")
    private int cuentaNumCuenta;

    public MovimientoPK() {
    }

    public MovimientoPK(int idMovimiento, int cuentaNumCuenta) {
        this.idMovimiento = idMovimiento;
        this.cuentaNumCuenta = cuentaNumCuenta;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getCuentaNumCuenta() {
        return cuentaNumCuenta;
    }

    public void setCuentaNumCuenta(int cuentaNumCuenta) {
        this.cuentaNumCuenta = cuentaNumCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovimiento;
        hash += (int) cuentaNumCuenta;
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
        if (this.cuentaNumCuenta != other.cuentaNumCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.MovimientoPK[ idMovimiento=" + idMovimiento + ", cuentaNumCuenta=" + cuentaNumCuenta + " ]";
    }
    
}
