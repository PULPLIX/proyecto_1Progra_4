/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cliente;
import banco.logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Oscar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        try {
            
            //Cliente cliente = ClienteDao.find("111");
            
            
//            Cliente cliente = ClienteDao.find("222");
//            System.out.print(cliente.getFavoritasCollection().toString());
            
                    
//           ArrayList<Cliente> list = ClienteDao.listar();
           
//           System.out.print(list.get(0).getApellidos());
//           System.out.print(list.get(0).getNombre());
//           System.out.print(list.get(0).getUsuarioIdUsuario().getIdUsuario());
//           System.out.print(list.get(0).getTelefono());



            //FavoritaDao.agregarFavorita(3, 7);



           
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
