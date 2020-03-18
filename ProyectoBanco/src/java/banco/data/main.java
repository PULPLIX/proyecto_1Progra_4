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
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        try {
           ArrayList<Cliente> list = clienteDao.listar();
           
           System.out.print(list.get(0).getApellidos());
           System.out.print(list.get(0).getNombre());
           System.out.print(list.get(0).getUsuarioIdUsuario().getIdUsuario());
           System.out.print(list.get(0).getTelefono());

           
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
