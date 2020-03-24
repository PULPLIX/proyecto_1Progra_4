/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import banco.logica.TipoCuenta;
import banco.logica.Transferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class movimientosDao {

    public static boolean registrarTransferencia(Transferencia transferencia) throws Exception {
        String SQL = "INSERT INTO transferencia (monto,fecha,aplicado,cuenta_destino,cuenta_origen) values "
                + "(?,?,?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, transferencia.getMonto());
            st.setDate(2, new java.sql.Date(transferencia.getFecha().getDate()));
            st.setInt(3, transferencia.getAplicado());
            st.setInt(4, transferencia.getCuenta_Destino().getNumCuenta());
            st.setInt(5, transferencia.getCuenta().getNumCuenta());
            return st.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

//    public static Transferencia find(Integer id_tipo_cuenta) throws Exception {
//        String SQL = "select * from tipo_cuenta where id_tipo_cuenta=?;";
//        TipoCuenta tipoCuenta = new TipoCuenta();
//
//        try {
//            Connection con = Coneccion.conectar();
//            PreparedStatement st = con.prepareStatement(SQL);
//
//            st.setInt(1, id_tipo_cuenta);
//            ResultSet resultado = st.executeQuery();
//
//            while (resultado.next()) {
//
//                tipoCuenta.setIdTipoCuenta(resultado.getInt("id_tipo_cuenta"));
//                tipoCuenta.setDescripción(resultado.getString("descripción"));
//                tipoCuenta.setTasaInterés(resultado.getDouble("tasa_interés"));
//
//            }
//            con.close();
//            resultado.close();
//            st.close();
//            return tipoCuenta;
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//            return tipoCuenta;
//        }
//    }
//
//    public static TipoCuenta creaTipoCuenta(ResultSet resultado) throws SQLException {
//        TipoCuenta tipoCuenta = new TipoCuenta();
//
//        tipoCuenta.setIdTipoCuenta(resultado.getInt("id_tipo_cuenta"));
//        tipoCuenta.setDescripción(resultado.getString("descripción"));
//        tipoCuenta.setTasaInterés(resultado.getDouble("tasa_interés"));
//
//        return tipoCuenta;
//    }
//
//    public static ArrayList<TipoCuenta> listar() throws Exception {
//        String SQL = "select * from tipo_cuenta;";
//        try {
//            Connection con = Coneccion.conectar();
//            PreparedStatement st = con.prepareStatement(SQL);
//            ResultSet resultado = st.executeQuery();
//
//            ArrayList<TipoCuenta> lista = new ArrayList<TipoCuenta>();
//            TipoCuenta tipoCuenta;
//
//            while (resultado.next()) {
//                tipoCuenta = new TipoCuenta();
//
//                tipoCuenta.setIdTipoCuenta(resultado.getInt("id_tipo_cuenta"));
//                tipoCuenta.setDescripción(resultado.getString("descripción"));
//                tipoCuenta.setTasaInterés(resultado.getDouble("tasa_interés"));
//
//                lista.add(tipoCuenta);
//            }
//            return lista;
//
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//            return null;
//        }
//
//    }
}
