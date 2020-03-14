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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "tipomoneda")
@NamedQueries({
    @NamedQuery(name = "Tipomoneda.findAll", query = "SELECT t FROM Tipomoneda t")})
public class Tipomoneda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoMoneda")
    private Integer idTipoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentajeInteres")
    private int porcentajeInteres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tipomoneda")
    private Tipocambio tipocambio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipomoneda")
    private Collection<Cuenta> cuentaCollection;

    public Tipomoneda() {
    }

    public Tipomoneda(Integer idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public Tipomoneda(Integer idTipoMoneda, int porcentajeInteres, String descripcion) {
        this.idTipoMoneda = idTipoMoneda;
        this.porcentajeInteres = porcentajeInteres;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(Integer idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public int getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(int porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipocambio getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(Tipocambio tipocambio) {
        this.tipocambio = tipocambio;
    }

    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMoneda != null ? idTipoMoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomoneda)) {
            return false;
        }
        Tipomoneda other = (Tipomoneda) object;
        if ((this.idTipoMoneda == null && other.idTipoMoneda != null) || (this.idTipoMoneda != null && !this.idTipoMoneda.equals(other.idTipoMoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.Tipomoneda[ idTipoMoneda=" + idTipoMoneda + " ]";
    }
    
}
