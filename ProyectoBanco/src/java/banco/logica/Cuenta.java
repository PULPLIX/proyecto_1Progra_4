/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logica;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByNumCuenta", query = "SELECT c FROM Cuenta c WHERE c.numCuenta = :numCuenta")
    , @NamedQuery(name = "Cuenta.findByFechaCreacion", query = "SELECT c FROM Cuenta c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Cuenta.findByLimiteTransferenciaDiaria", query = "SELECT c FROM Cuenta c WHERE c.limiteTransferenciaDiaria = :limiteTransferenciaDiaria")
    , @NamedQuery(name = "Cuenta.findByActiva", query = "SELECT c FROM Cuenta c WHERE c.activa = :activa")
    , @NamedQuery(name = "Cuenta.findBySaldoInicial", query = "SELECT c FROM Cuenta c WHERE c.saldoInicial = :saldoInicial")
    , @NamedQuery(name = "Cuenta.findByFechaUltimaAplicacion", query = "SELECT c FROM Cuenta c WHERE c.fechaUltimaAplicacion = :fechaUltimaAplicacion")
    , @NamedQuery(name = "Cuenta.findBySaldoFinal", query = "SELECT c FROM Cuenta c WHERE c.saldoFinal = :saldoFinal")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_cuenta")
    private Integer numCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limite_transferencia_diaria")
    private double limiteTransferenciaDiaria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activa")
    private short activa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_inicial")
    private double saldoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ultima_aplicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaAplicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_final")
    private double saldoFinal;
    @ManyToMany(mappedBy = "cuentaCollection")
    private Collection<Cliente> clienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private Collection<Transferencia> transferenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta1")
    private Collection<Transferencia> transferenciaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private Collection<Movimiento> movimientoCollection;
    @JoinColumn(name = "cliente_id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdCliente;
    @JoinColumn(name = "moneda_nombre", referencedColumnName = "nombre")
    @ManyToOne(optional = false)
    private Moneda monedaNombre;
    @JoinColumn(name = "idTipoCuenta", referencedColumnName = "id_tipo_cuenta")
    @ManyToOne(optional = false)
    private TipoCuenta idTipoCuenta;

    public Cuenta() {
    }

    public Cuenta(Integer numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Cuenta(Integer numCuenta, Date fechaCreacion, double limiteTransferenciaDiaria, short activa, double saldoInicial, Date fechaUltimaAplicacion, double saldoFinal) {
        this.numCuenta = numCuenta;
        this.fechaCreacion = fechaCreacion;
        this.limiteTransferenciaDiaria = limiteTransferenciaDiaria;
        this.activa = activa;
        this.saldoInicial = saldoInicial;
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
        this.saldoFinal = saldoFinal;
    }

    public Integer getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(Integer numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getLimiteTransferenciaDiaria() {
        return limiteTransferenciaDiaria;
    }

    public void setLimiteTransferenciaDiaria(double limiteTransferenciaDiaria) {
        this.limiteTransferenciaDiaria = limiteTransferenciaDiaria;
    }

    public short getActiva() {
        return activa;
    }

    public void setActiva(short activa) {
        this.activa = activa;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Date getFechaUltimaAplicacion() {
        return fechaUltimaAplicacion;
    }

    public void setFechaUltimaAplicacion(Date fechaUltimaAplicacion) {
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Transferencia> getTransferenciaCollection() {
        return transferenciaCollection;
    }

    public void setTransferenciaCollection(Collection<Transferencia> transferenciaCollection) {
        this.transferenciaCollection = transferenciaCollection;
    }

    @XmlTransient
    public Collection<Transferencia> getTransferenciaCollection1() {
        return transferenciaCollection1;
    }

    public void setTransferenciaCollection1(Collection<Transferencia> transferenciaCollection1) {
        this.transferenciaCollection1 = transferenciaCollection1;
    }

    @XmlTransient
    public Collection<Movimiento> getMovimientoCollection() {
        return movimientoCollection;
    }

    public void setMovimientoCollection(Collection<Movimiento> movimientoCollection) {
        this.movimientoCollection = movimientoCollection;
    }

    public Cliente getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(Cliente clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public Moneda getMonedaNombre() {
        return monedaNombre;
    }

    public void setMonedaNombre(Moneda monedaNombre) {
        this.monedaNombre = monedaNombre;
    }

    public TipoCuenta getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(TipoCuenta idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCuenta != null ? numCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.numCuenta == null && other.numCuenta != null) || (this.numCuenta != null && !this.numCuenta.equals(other.numCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.Cuenta[ numCuenta=" + numCuenta + " ]";
    }
    
}
