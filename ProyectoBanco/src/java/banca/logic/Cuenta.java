/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.logic;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "cuenta")
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuenta")
    private Integer idcuenta;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "telefono")
    private Integer telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private Collection<Movimiento> movimientoCollection;
    @JoinColumns({
        @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente")
        , @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente")})
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumns({
        @JoinColumn(name = "idTipoMoneda", referencedColumnName = "idTipoMoneda")
        , @JoinColumn(name = "idTipoMoneda", referencedColumnName = "idTipoMoneda")})
    @ManyToOne(optional = false)
    private Tipomoneda tipomoneda;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private Cuentasfavoritas cuentasfavoritas;

    public Cuenta() {
    }

    public Cuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Collection<Movimiento> getMovimientoCollection() {
        return movimientoCollection;
    }

    public void setMovimientoCollection(Collection<Movimiento> movimientoCollection) {
        this.movimientoCollection = movimientoCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tipomoneda getTipomoneda() {
        return tipomoneda;
    }

    public void setTipomoneda(Tipomoneda tipomoneda) {
        this.tipomoneda = tipomoneda;
    }

    public Cuentasfavoritas getCuentasfavoritas() {
        return cuentasfavoritas;
    }

    public void setCuentasfavoritas(Cuentasfavoritas cuentasfavoritas) {
        this.cuentasfavoritas = cuentasfavoritas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuenta != null ? idcuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idcuenta == null && other.idcuenta != null) || (this.idcuenta != null && !this.idcuenta.equals(other.idcuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.Cuenta[ idcuenta=" + idcuenta + " ]";
    }
    
}
