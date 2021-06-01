/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

/**
 *
 * @author jagh_
 */
public class Pedido {

   public static String nombre;
   public static int id;
    public static void main(String[] args) {
       Conexion cn= new Conexion();
       cn.conector();
       login ln = new login();
       ln.setVisible(true);// TODO code application logic here
    }
}
