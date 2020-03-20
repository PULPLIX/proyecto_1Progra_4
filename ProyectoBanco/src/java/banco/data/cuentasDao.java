/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.Cliente;
import banco.logica.Cuenta;
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
public class cuentasDao {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */

    /**
     *
     * @author david
     */
    public static boolean registrar(Cuenta cuenta) throws Exception {
        String SQL = "insert into cuenta (fecha_creacion,limite_transferencia_diaria,activa,saldo_inicial,fecha_ultima_aplicacion,saldo_final,moneda_nombre,cliente_id_cliente, idTipoCuenta)"
                + "values (?,?,?,?,?,?,?,?,?);";
        try {
            //Se crea la coneccion y luego se prepara el query que se ejecutará en la BD
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            //Se sustituyen los valores que existían en el String.

            st.setInt(1, cuenta.getNumCuenta());
            st.setDate(2, new java.sql.Date(cuenta.getFechaCreacion().getDate()));
            st.setDouble(3, cuenta.getLimiteTransferenciaDiaria());
            st.setInt(4, cuenta.getActiva());
            st.setDouble(5, cuenta.getSaldoInicial());
            st.setDate(6, cuenta.getFechaUltimaAplicacion());
            st.setDouble(7, cuenta.getSaldoFinal());
//            
//            st.setDouble(8, cuenta.getMonedaNombre());
//            st.setInt(9, cuenta.getClienteIdCliente());
//            st.setInt(10, cuenta.getIdTipoCuenta());


            return st.executeUpdate() != 0;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static Usuario creaUsuario(ResultSet resultado) throws SQLException {
        Usuario usu = new Usuario();

        usu.setIdUsuario(resultado.getString("id_Usuario"));
        usu.setClaveAcceso(resultado.getString("clave_acceso"));
        usu.setRol(resultado.getInt("rol"));

        return usu;
    }

    public static ArrayList<Cliente> listar() throws Exception {
        String SQL = "select*from cliente inner join usuario on usuario_id_usuario = id_usuario;";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet resultado = st.executeQuery();

            ArrayList<Cliente> lista = new ArrayList<>();
            Cliente cli;

            while (resultado.next()) {
                cli = new Cliente();
                cli.setIdCliente(resultado.getInt("id_cliente"));
                cli.setUsuarioIdUsuario(creaUsuario(resultado));
                cli.setApellidos(resultado.getString("apellidos"));
                cli.setNombre(resultado.getString("nombre"));
                cli.setTelefono(resultado.getString("telefono"));

                lista.add(cli);
            }
            con.close();
            st.close();
            resultado.close();
            return lista;

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }

}
