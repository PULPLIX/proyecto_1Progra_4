/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logic;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "tipocambio")
@NamedQueries({
    @NamedQuery(name = "Tipocambio.findAll", query = "SELECT t FROM Tipocambio t")})
public class Tipocambio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipocambioPK tipocambioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumns({
        @JoinColumn(name = "dTipoMoneda", referencedColumnName = "idTipoMoneda", insertable = false, updatable = false)
        , @JoinColumn(name = "dTipoMoneda", referencedColumnName = "idTipoMoneda", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Tipomoneda tipomoneda;

    public Tipocambio() {
    }

    public Tipocambio(TipocambioPK tipocambioPK) {
        this.tipocambioPK = tipocambioPK;
    }

    public Tipocambio(TipocambioPK tipocambioPK, float monto, String descripcion) {
        this.tipocambioPK = tipocambioPK;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public Tipocambio(int idtipoCambio, int dTipoMoneda) {
        this.tipocambioPK = new TipocambioPK(idtipoCambio, dTipoMoneda);
    }

    public TipocambioPK getTipocambioPK() {
        return tipocambioPK;
    }

    public void setTipocambioPK(TipocambioPK tipocambioPK) {
        this.tipocambioPK = tipocambioPK;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Tipomoneda getTipomoneda() {
        return tipomoneda;
    }

    public void setTipomoneda(Tipomoneda tipomoneda) {
        this.tipomoneda = tipomoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipocambioPK != null ? tipocambioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocambio)) {
            return false;
        }
        Tipocambio other = (Tipocambio) object;
        if ((this.tipocambioPK == null && other.tipocambioPK != null) || (this.tipocambioPK != null && !this.tipocambioPK.equals(other.tipocambioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.Tipocambio[ tipocambioPK=" + tipocambioPK + " ]";
    }
    
}
