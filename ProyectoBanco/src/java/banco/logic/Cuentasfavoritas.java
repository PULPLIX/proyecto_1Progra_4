/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logic;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "cuentasfavoritas")
@NamedQueries({
    @NamedQuery(name = "Cuentasfavoritas.findAll", query = "SELECT c FROM Cuentasfavoritas c")})
public class Cuentasfavoritas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuentasfavoritasPK cuentasfavoritasPK;
    @JoinColumns({
        @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente", insertable = false, updatable = false)
        , @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Cliente cliente;
    @JoinColumns({
        @JoinColumn(name = "cuenta_idcuenta", referencedColumnName = "idcuenta", insertable = false, updatable = false)
        , @JoinColumn(name = "cuenta_idcuenta", referencedColumnName = "idcuenta", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Cuenta cuenta;

    public Cuentasfavoritas() {
    }

    public Cuentasfavoritas(CuentasfavoritasPK cuentasfavoritasPK) {
        this.cuentasfavoritasPK = cuentasfavoritasPK;
    }

    public Cuentasfavoritas(int clienteidCliente, int cuentaIdcuenta) {
        this.cuentasfavoritasPK = new CuentasfavoritasPK(clienteidCliente, cuentaIdcuenta);
    }

    public CuentasfavoritasPK getCuentasfavoritasPK() {
        return cuentasfavoritasPK;
    }

    public void setCuentasfavoritasPK(CuentasfavoritasPK cuentasfavoritasPK) {
        this.cuentasfavoritasPK = cuentasfavoritasPK;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentasfavoritasPK != null ? cuentasfavoritasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentasfavoritas)) {
            return false;
        }
        Cuentasfavoritas other = (Cuentasfavoritas) object;
        if ((this.cuentasfavoritasPK == null && other.cuentasfavoritasPK != null) || (this.cuentasfavoritasPK != null && !this.cuentasfavoritasPK.equals(other.cuentasfavoritasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.Cuentasfavoritas[ cuentasfavoritasPK=" + cuentasfavoritasPK + " ]";
    }
    
}
