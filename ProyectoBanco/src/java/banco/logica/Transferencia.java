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

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransferenciaPK transferenciaPK;
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
    private Cuenta cuenta;
    @JoinColumn(name = "cuenta_origen", referencedColumnName = "num_cuenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta1;

    public Transferencia() {
    }

    public Transferencia(TransferenciaPK transferenciaPK) {
        this.transferenciaPK = transferenciaPK;
    }

    public Transferencia(TransferenciaPK transferenciaPK, String monto, Date fecha, short aplicado) {
        this.transferenciaPK = transferenciaPK;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
    }

    public Transferencia(int idTransferencia, int cuentaDestino, int cuentaOrigen) {
        this.transferenciaPK = new TransferenciaPK(idTransferencia, cuentaDestino, cuentaOrigen);
    }

    public TransferenciaPK getTransferenciaPK() {
        return transferenciaPK;
    }

    public void setTransferenciaPK(TransferenciaPK transferenciaPK) {
        this.transferenciaPK = transferenciaPK;
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
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta1() {
        return cuenta1;
    }

    public void setCuenta1(Cuenta cuenta1) {
        this.cuenta1 = cuenta1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferenciaPK != null ? transferenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.transferenciaPK == null && other.transferenciaPK != null) || (this.transferenciaPK != null && !this.transferenciaPK.equals(other.transferenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.Transferencia[ transferenciaPK=" + transferenciaPK + " ]";
    }
    
}
