/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Coneccion {
    public static Connection conectar() throws Exception{
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "root");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        
    }

}