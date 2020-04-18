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
                + "(?,(select now()),?,?,?);";
        try {
            Connection con = Coneccion.conectar();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, transferencia.getMonto());
            st.setInt(2, transferencia.getAplicado());
            st.setInt(3, transferencia.getCuenta_Destino().getNumCuenta());
            st.setInt(4, transferencia.getCuenta().getNumCuenta());
            return st.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } 
    }

}
