/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.presentacion.cliente.cuentas;

import banco.logica.Cliente;

/**
 *
 * @author ESCINF
 */
public class Model {
    Cliente current;

    public Model() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new Cliente());        
    }
    
    public Cliente getCurrent() {
        return current;
    }

    public void setCurrent(Cliente current) {
        this.current = current;
    }
   
}
