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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "cajero")
@NamedQueries({
    @NamedQuery(name = "Cajero.findAll", query = "SELECT c FROM Cajero c")})
public class Cajero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCajero")
    private Integer idCajero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numCedula")
    private String numCedula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario")
    private int salario;
    @Column(name = "cubiculoAsig")
    private Integer cubiculoAsig;
    @JoinColumns({
        @JoinColumn(name = "idUsuario_C", referencedColumnName = "idusuario")
        , @JoinColumn(name = "idUsuario_C", referencedColumnName = "idusuario")})
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Cajero() {
    }

    public Cajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public Cajero(Integer idCajero, String nombre, Date fechaNacimiento, String numCedula, int salario) {
        this.idCajero = idCajero;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.numCedula = numCedula;
        this.salario = salario;
    }

    public Integer getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumCedula() {
        return numCedula;
    }

    public void setNumCedula(String numCedula) {
        this.numCedula = numCedula;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Integer getCubiculoAsig() {
        return cubiculoAsig;
    }

    public void setCubiculoAsig(Integer cubiculoAsig) {
        this.cubiculoAsig = cubiculoAsig;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCajero != null ? idCajero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajero)) {
            return false;
        }
        Cajero other = (Cajero) object;
        if ((this.idCajero == null && other.idCajero != null) || (this.idCajero != null && !this.idCajero.equals(other.idCajero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.Cajero[ idCajero=" + idCajero + " ]";
    }
    
}
