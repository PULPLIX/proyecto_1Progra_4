/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findByIdMovimiento", query = "SELECT m FROM Movimiento m WHERE m.movimientoPK.idMovimiento = :idMovimiento")
    , @NamedQuery(name = "Movimiento.findByCuentaNumCuenta", query = "SELECT m FROM Movimiento m WHERE m.movimientoPK.cuentaNumCuenta = :cuentaNumCuenta")
    , @NamedQuery(name = "Movimiento.findByMonto", query = "SELECT m FROM Movimiento m WHERE m.monto = :monto")
    , @NamedQuery(name = "Movimiento.findByFecha", query = "SELECT m FROM Movimiento m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Movimiento.findByAplicado", query = "SELECT m FROM Movimiento m WHERE m.aplicado = :aplicado")
    , @NamedQuery(name = "Movimiento.findByMovimientocol", query = "SELECT m FROM Movimiento m WHERE m.movimientocol = :movimientocol")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovimientoPK movimientoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplicado")
    private short aplicado;
    @Size(max = 45)
    @Column(name = "movimientocol")
    private String movimientocol;
    @JoinColumn(name = "cuenta_num_cuenta", referencedColumnName = "num_cuenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public Movimiento() {
    }

    public Movimiento(MovimientoPK movimientoPK) {
        this.movimientoPK = movimientoPK;
    }

    public Movimiento(MovimientoPK movimientoPK, double monto, Date fecha, short aplicado) {
        this.movimientoPK = movimientoPK;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
    }

    public Movimiento(int idMovimiento, String cuentaNumCuenta) {
        this.movimientoPK = new MovimientoPK(idMovimiento, cuentaNumCuenta);
    }

    public MovimientoPK getMovimientoPK() {
        return movimientoPK;
    }

    public void setMovimientoPK(MovimientoPK movimientoPK) {
        this.movimientoPK = movimientoPK;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getAplicado() {
        return aplicado;
    }

    public void setAplicado(short aplicado) {
        this.aplicado = aplicado;
    }

    public String getMovimientocol() {
        return movimientocol;
    }

    public void setMovimientocol(String movimientocol) {
        this.movimientocol = movimientocol;
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
        hash += (movimientoPK != null ? movimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.movimientoPK == null && other.movimientoPK != null) || (this.movimientoPK != null && !this.movimientoPK.equals(other.movimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.Movimiento[ movimientoPK=" + movimientoPK + " ]";
    }
    
}
