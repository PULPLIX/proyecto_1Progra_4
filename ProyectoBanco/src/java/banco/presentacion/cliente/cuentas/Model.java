/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.cuentas;

import banco.logica.Cliente;
import banco.logica.Cuenta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
    List<Cuenta> cuentas;
    Cuenta seleccionado;
    Cliente current;

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Cuenta> rows = new ArrayList<>();    
        
        seleccionado=null;  
        current = null;
        this.setCuentas(rows);
    }

    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.current = current;
    }
    
    public void setCuentas(List<Cuenta> cuentas){
        this.cuentas =cuentas;    
    }

     public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }
}
