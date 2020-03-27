/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import static banco.data.CuentaDao.creaCliente;
import static banco.data.CuentaDao.creaIdTipoCuenta;
import static banco.data.CuentaDao.creaMoneda;
import static banco.data.CuentaDao.llenarMovimientosCuenta;
import static banco.data.CuentaDao.llenarTransferencias;
import banco.logica.Cuenta;
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

    
    
//    public static ArrayList<Cuenta> getMovimientos(String id) throws Exception {
//        String SQL = "select * from cuenta c "
//                + "inner join moneda m on c.moneda_nombre = m.nombre "
//                + "inner join cliente cli on c.cliente_id_cliente = cli.usuario_id_usuario "
//                + "inner join usuario u on cli.usuario_id_usuario = u.id_usuario "
//                + "inner join tipo_cuenta tp on c.idTipoCuenta = tp.id_tipo_cuenta "
//                + "where cliente_id_cliente=?;";
//
//        try {
//            Connection con = Coneccion.conectar();
//            PreparedStatement st = con.prepareStatement(SQL);
//            st.setString(1, id);
//
//            ResultSet resultado = st.executeQuery();
//
//            ArrayList<Cuenta> lista = new ArrayList<>();
//            Cuenta cuenta;
//
//            while (resultado.next()) {
//                cuenta = new Cuenta();
//                cuenta.setNumCuenta(resultado.getInt("num_cuenta"));
//                cuenta.setFechaCreacion(resultado.getDate("fecha_creacion"));
//                cuenta.setLimiteTransferenciaDiaria(resultado.getDouble("limite_transferencia_diaria"));
//                cuenta.setActiva(resultado.getShort("activa"));
//                cuenta.setSaldoInicial(resultado.getDouble("saldo_inicial"));
//                cuenta.setFechaUltimaAplicacion(resultado.getDate("fecha_ultima_aplicacion"));
//                cuenta.setSaldoFinal(resultado.getFloat("saldo_final"));
//                cuenta.setMonedaNombre(creaMoneda(resultado));
//                cuenta.setClienteIdCliente(creaCliente(resultado));
//                cuenta.setIdTipoCuenta(creaIdTipoCuenta(resultado));
//                llenarMovimientosCuenta(cuenta);
//                llenarTransferencias(cuenta);
//
//                lista.add(cuenta);
//            }
//            con.close();
//            st.close();
//            resultado.close();
//            return lista;
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//            return null;
//        }
//    }
    
    
    
    
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
