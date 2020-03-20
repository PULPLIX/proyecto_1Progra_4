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

    public static void agregarFavorita(int clienteId, int cuentaId) throws Exception {
        String SQL = "insert into favorita (cliente_id, cuenta_id) values (?,?)";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, clienteId);
            st.setInt(2, cuentaId);
            st.executeUpdate();

        } catch (Exception e) {

        }
    }

}
