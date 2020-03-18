/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            Connection con = Coneccion.conectar();
            String SQL = "select * from usuario where id_usuario='111' and clave_acceso='111';";
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();
 while (resultado.next()) {
                System.out.print(resultado.getString("id_Usuario"));
                System.out.print(resultado.getString("clave_acceso"));
                System.out.print(resultado.getInt("rol"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
