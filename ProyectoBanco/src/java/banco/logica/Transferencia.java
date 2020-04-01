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
@Table(name = "transferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t")
    , @NamedQuery(name = "Transferencia.findByIdTransferencia", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.idTransferencia = :idTransferencia")
    , @NamedQuery(name = "Transferencia.findByMonto", query = "SELECT t FROM Transferencia t WHERE t.monto = :monto")
    , @NamedQuery(name = "Transferencia.findByFecha", query = "SELECT t FROM Transferencia t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "Transferencia.findByAplicado", query = "SELECT t FROM Transferencia t WHERE t.aplicado = :aplicado")
    , @NamedQuery(name = "Transferencia.findByCuentaDestino", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.cuentaDestino = :cuentaDestino")
    , @NamedQuery(name = "Transferencia.findByCuentaOrigen", query = "SELECT t FROM Transferencia t WHERE t.transferenciaPK.cuentaOrigen = :cuentaOrigen")})
public class Transferencia implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "monto")
    private String monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplicado")
    private short aplicado;
    @JoinColumn(name = "cuenta_destino", referencedColumnName = "num_cuenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta_origen;
    @JoinColumn(name = "cuenta_origen", referencedColumnName = "num_cuenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta_Destino;
    
    private int id_transferencia;

    public Transferencia(String monto, Date fecha, short aplicado, Cuenta cuenta, Cuenta cuenta1, int id_transferencia) {
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
        this.cuenta_origen = cuenta;
        this.cuenta_Destino = cuenta1;
        this.id_transferencia = id_transferencia;
    }

    public Transferencia() {
        this.monto = "";
        this.fecha = null;
        this.aplicado = 1;
        this.cuenta_origen = null;
        this.cuenta_Destino = null;
        this.id_transferencia = 0;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
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

    public Cuenta getCuenta() {
        return cuenta_origen;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta_origen = cuenta;
    }

    public Cuenta getCuenta_Destino() {
        return cuenta_Destino;
    }

    public void setCuenta_Destino(Cuenta cuenta_Destino) {
        this.cuenta_Destino = cuenta_Destino;
    }

    public int getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(int id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

}
