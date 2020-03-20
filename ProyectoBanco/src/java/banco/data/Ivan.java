package banco.data;

import banco.logica.Moneda;
import banco.logica.TipoCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ivan {

    public static void main(String[] args) {

        try {

//            Moneda moneda = new Moneda(4, "Pepe", "P", 123, 123);
//            monedaDao.registrar(moneda);

//            Moneda moneda = monedaDao.find(1);
//            System.out.print("Moneda: " + moneda.getSimbolo() + " " + moneda.getDescripcion() + " "
//                    + moneda.getNombre() + " " + moneda.getTipoCambioCompra() + " " + moneda.getTipoCambioVenta() + "\n");
            
//            ArrayList<Moneda> lista = monedaDao.listar();
//            System.out.print(lista.toString());


//            TipoCuenta tipoCuenta = new TipoCuenta(2, "hola", 2);
//            TipoCuentaDao.registrar(tipoCuenta);

//            TipoCuenta tipoCuenta = TipoCuentaDao.find(3);
//            System.out.print("Tipo de cuenta: " + tipoCuenta.getIdTipoCuenta()+ " " + tipoCuenta.getDescripción()+ " "
//                    + tipoCuenta.getTasaInterés()+ "\n");
           
//            ArrayList<TipoCuenta> lista = TipoCuentaDao.listar();
//            System.out.print(lista.toString());


        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
