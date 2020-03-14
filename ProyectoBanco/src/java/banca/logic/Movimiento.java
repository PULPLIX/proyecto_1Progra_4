/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.logic;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "movimiento")
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimiento")
    private Integer idmovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_Usuario")
    private int idUsuario;
    @Column(name = "maxMov")
    private Integer maxMov;
    @Column(name = "tipo")
    private Integer tipo;
    @JoinColumns({
        @JoinColumn(name = "cuenta_idcuenta", referencedColumnName = "idcuenta")
        , @JoinColumn(name = "cuenta_idcuenta", referencedColumnName = "idcuenta")})
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public Movimiento() {
    }

    public Movimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Movimiento(Integer idmovimiento, int idUsuario) {
        this.idmovimiento = idmovimiento;
        this.idUsuario = idUsuario;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getMaxMov() {
        return maxMov;
    }

    public void setMaxMov(Integer maxMov) {
        this.maxMov = maxMov;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.Movimiento[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
