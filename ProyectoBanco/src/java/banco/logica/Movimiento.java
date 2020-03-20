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
    , @NamedQuery(name = "Movimiento.findByMonto", query = "SELECT m FROM Movimiento m WHERE m.monto = :monto")
    , @NamedQuery(name = "Movimiento.findByFecha", query = "SELECT m FROM Movimiento m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Movimiento.findByAplicado", query = "SELECT m FROM Movimiento m WHERE m.aplicado = :aplicado")
    , @NamedQuery(name = "Movimiento.findByMovimientocol", query = "SELECT m FROM Movimiento m WHERE m.movimientocol = :movimientocol")
    , @NamedQuery(name = "Movimiento.findByCuentaNumCuenta", query = "SELECT m FROM Movimiento m WHERE m.movimientoPK.cuentaNumCuenta = :cuentaNumCuenta")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
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
    private int id_movimiento;

    public Movimiento() {
        this.id_movimiento = 0;
        this.monto = 0.0;
        this.fecha = null;
        this.aplicado = 1;
        
    }

    public Movimiento(int movimiento, double monto, Date fecha, short aplicado) {
        this.id_movimiento = movimiento;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;

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

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }
    
}
