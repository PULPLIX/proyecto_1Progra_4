/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cuenta;
import java.util.ArrayList;

/**
 *
 * @author Oscaresunmarica
 */
public class david {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ArrayList<Cuenta> cuenta= banco.data.cuentasDao.getCuentasCliente(1);
        
        for(int i = 0; i< cuenta.size(); i++){
            System.out.println(cuenta.get(i).getClienteIdCliente().getApellidos()+"\n");
            System.out.println(cuenta.get(i).getFechaUltimaAplicacion()+"\n");
            System.out.println(cuenta.get(i).getFechaCreacion()+"\n");
            System.out.println(cuenta.get(i).getIdTipoCuenta().getDescripción()+"\n");
            System.out.println(cuenta.get(i).getMonedaNombre().getSimbolo()+"\n");

        }


    }
    
}
