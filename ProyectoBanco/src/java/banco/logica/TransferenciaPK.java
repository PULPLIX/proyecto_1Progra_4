/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author david
 */
@Embeddable
public class TransferenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_transferencia")
    private int idTransferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cuenta_origen")
    private String cuentaOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cuenta_destino")
    private String cuentaDestino;

    public TransferenciaPK() {
    }

    public TransferenciaPK(int idTransferencia, String cuentaOrigen, String cuentaDestino) {
        this.idTransferencia = idTransferencia;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTransferencia;
        hash += (cuentaOrigen != null ? cuentaOrigen.hashCode() : 0);
        hash += (cuentaDestino != null ? cuentaDestino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciaPK)) {
            return false;
        }
        TransferenciaPK other = (TransferenciaPK) object;
        if (this.idTransferencia != other.idTransferencia) {
            return false;
        }
        if ((this.cuentaOrigen == null && other.cuentaOrigen != null) || (this.cuentaOrigen != null && !this.cuentaOrigen.equals(other.cuentaOrigen))) {
            return false;
        }
        if ((this.cuentaDestino == null && other.cuentaDestino != null) || (this.cuentaDestino != null && !this.cuentaDestino.equals(other.cuentaDestino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.TransferenciaPK[ idTransferencia=" + idTransferencia + ", cuentaOrigen=" + cuentaOrigen + ", cuentaDestino=" + cuentaDestino + " ]";
    }
    
}
