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
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class UsuarioDao {

    public static boolean registrar(Usuario usu) throws Exception {
        String SQL = "INSERT INTO usuario (id_usuario, clave_acceso, rol) values (?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, usu.getIdUsuario());
            st.setString(1, usu.getClaveAcceso());
            st.setInt(1, usu.getRol());

            return st.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static Usuario find(String ced, String clave) throws Exception {
        String SQL = "select * from usuario where id_usuario=? and clave_acceso=?;";
        Usuario usu = null;

        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);

            st.setString(1, ced);
            st.setString(2, clave);

            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                usu = new Usuario();
                usu.setIdUsuario(resultado.getString("id_Usuario"));
                usu.setClaveAcceso(resultado.getString("clave_acceso"));
                usu.setRol(resultado.getInt("rol"));
                
            }
            con.close();
            resultado.close();
            st.close();
            return usu;

        } catch (SQLException ex) {
            System.out.println(ex);
            return usu;
        }
    }

    public static ArrayList<Usuario> listar() throws Exception {
        String SQL = "select * from usuario;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<Usuario> lista = null;
            Usuario usu = null;

            while (resultado.next()) {
                usu = new Usuario();
                usu.setIdUsuario(resultado.getString("id_Usuario"));
                usu.setClaveAcceso(resultado.getString("clave_acceso"));
                usu.setRol(resultado.getInt("rol"));
                lista.add(usu);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
}
