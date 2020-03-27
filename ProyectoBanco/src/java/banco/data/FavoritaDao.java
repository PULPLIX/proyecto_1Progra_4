/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Oscar
 */
public class FavoritaDao {

    public static void agregarFavorita(int cuentaId, String clienteId) throws Exception {
        String SQL = "insert into favorita values (?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, cuentaId);
            st.setString(2, clienteId);
            st.executeUpdate();
            con.close();
            st.close();
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void eliminar(int cuentaId){
    String SQL = "delete from favorita where cuenta_id = ?;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, cuentaId);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
