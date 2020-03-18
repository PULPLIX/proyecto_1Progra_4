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
    @Column(name = "cuenta_destino")
    private int cuentaDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_origen")
    private int cuentaOrigen;

    public TransferenciaPK() {
    }

    public TransferenciaPK(int idTransferencia, int cuentaDestino, int cuentaOrigen) {
        this.idTransferencia = idTransferencia;
        this.cuentaDestino = cuentaDestino;
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTransferencia;
        hash += (int) cuentaDestino;
        hash += (int) cuentaOrigen;
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
        if (this.cuentaDestino != other.cuentaDestino) {
            return false;
        }
        if (this.cuentaOrigen != other.cuentaOrigen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.logica.TransferenciaPK[ idTransferencia=" + idTransferencia + ", cuentaDestino=" + cuentaDestino + ", cuentaOrigen=" + cuentaOrigen + " ]";
    }
    
}
