/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Iván
 */
@Embeddable
public class TipocambioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idtipoCambio")
    private int idtipoCambio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dTipoMoneda")
    private int dTipoMoneda;

    public TipocambioPK() {
    }

    public TipocambioPK(int idtipoCambio, int dTipoMoneda) {
        this.idtipoCambio = idtipoCambio;
        this.dTipoMoneda = dTipoMoneda;
    }

    public int getIdtipoCambio() {
        return idtipoCambio;
    }

    public void setIdtipoCambio(int idtipoCambio) {
        this.idtipoCambio = idtipoCambio;
    }

    public int getDTipoMoneda() {
        return dTipoMoneda;
    }

    public void setDTipoMoneda(int dTipoMoneda) {
        this.dTipoMoneda = dTipoMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idtipoCambio;
        hash += (int) dTipoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipocambioPK)) {
            return false;
        }
        TipocambioPK other = (TipocambioPK) object;
        if (this.idtipoCambio != other.idtipoCambio) {
            return false;
        }
        if (this.dTipoMoneda != other.dTipoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banca.logic.TipocambioPK[ idtipoCambio=" + idtipoCambio + ", dTipoMoneda=" + dTipoMoneda + " ]";
    }
    
}
