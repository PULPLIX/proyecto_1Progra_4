/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logica;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "moneda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m")
    , @NamedQuery(name = "Moneda.findByNombre", query = "SELECT m FROM Moneda m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Moneda.findByDescripcion", query = "SELECT m FROM Moneda m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Moneda.findBySimbolo", query = "SELECT m FROM Moneda m WHERE m.simbolo = :simbolo")
    , @NamedQuery(name = "Moneda.findByTipoCambioCompra", query = "SELECT m FROM Moneda m WHERE m.tipoCambioCompra = :tipoCambioCompra")
    , @NamedQuery(name = "Moneda.findByTipoCambioVenta", query = "SELECT m FROM Moneda m WHERE m.tipoCambioVenta = :tipoCambioVenta")})
public class Moneda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nombre")
    private Integer nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "simbolo")
    private String simbolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_cambio_compra")
    private double tipoCambioCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_cambio_venta")
    private double tipoCambioVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monedaNombre")
    private Collection<Cuenta> cuentaCollection;

    public Moneda() {
        this.nombre = 0;
        this.descripcion = "";
        this.simbolo = "";
        this.tipoCambioCompra = 0;
        this.tipoCambioVenta = 0;
    }

    public Moneda(Integer nombre) {
        this.nombre = nombre;
    }

    public Moneda(Integer nombre, String descripcion, String simbolo, double tipoCambioCompra, double tipoCambioVenta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
        this.tipoCambioCompra = tipoCambioCompra;
        this.tipoCambioVenta = tipoCambioVenta;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getTipoCambioCompra() {
        return tipoCambioCompra;
    }

    public void setTipoCambioCompra(double tipoCambioCompra) {
        this.tipoCambioCompra = tipoCambioCompra;
    }

    public double getTipoCambioVenta() {
        return tipoCambioVenta;
    }

    public void setTipoCambioVenta(double tipoCambioVenta) {
        this.tipoCambioVenta = tipoCambioVenta;
    }

    @XmlTransient
    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.Moneda[ nombre=" + nombre + " ]";
    }

}
