/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jagh_
 */
public class formpedido extends javax.swing.JFrame {
    Pedido pd = new Pedido();
    public String nombre = pd.nombre ;
    public String nombreusuario;
    public int codigo;
    
    public formpedido() {
        initComponents();
        llenartabla();
        llenarcombo();
        numpedido();
        obtenerfecha();
        this.setResizable(false);
        setLocationRelativeTo(null);
        usuario.setText(nombre);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
    }
    
    Conexion cn = new Conexion();
    String id,fecha,fechadb,producto;
    
    void numpedido(){
        Conexion cn = new Conexion();
        Connection conexion = cn.conector();
        String sql = "SELECT NUM_PEDIDO FROM `bodega`.`pedidos` order by pedidos.NO desc limit 1;";
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
            String id = result.getString("NUM_PEDIDO");
            this.codigo = Integer.parseInt(id);
   }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
    }
    public void bloquear(){
        txtcantidad.setText(null);
        txtproducto.setText(null);
        txtuso.setText(null);
        txtautorizado.setText(null);
        combobox.setSelectedItem(null);     
    } 
    public void buscar(){
    Conexion cn = new Conexion();
    Connection conexion = cn.conector();
    String buscar = busqueda.getText();
      String sql = "SELECT * FROM `bodega`.`producto` where DESCRIPCION like '%"+buscar+"%';";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Código");
      model.addColumn("Descripción");
      model.addColumn("Categoría");
      table.setModel(model);
      String[] dato = new String[3];

     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(formpedido.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No tiene permisos para consultar la información, contacte con el administrador.");
        }finally{
            cn.cierraConexion();
        }
      
     
  }
    public void fecha(){
       /*try {
        Date  fecha = txtfecha.getDate();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String fecha2 = f.format(fecha);
        this.fecha = fecha2;
       
    } catch (Exception e) {
    }*/
    }
    public void llenarcombo(){
    Connection conexion = cn.conector();
    String sql = "SELECT NOMBRE FROM departamento;";
    Statement st;
    combobox.addItem(" ");
    try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
            combobox.addItem(result.getString("NOMBRE"));
   }
        } catch (SQLException ex) {
            Logger.getLogger(formpedido.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
    }
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`producto`;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Código");
      model.addColumn("Descripción");
      model.addColumn("Categoría");
      table.setModel(model);
      String[] dato = new String[3];
     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(formpedido.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
  }
    public void obtenernombre(){
        Connection conexion = cn.conector();
        String sql = "select usuarios.NOMBRE, usuarios.APELLIDO from usuarios where (usuarios.USUARIO = '"+nombre+"');";
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
              String nombre = result.getString(1);
              String apellido = result.getString(2);
              nombreusuario = nombre +" "+apellido;
            }
        } catch (SQLException ex) {
            Logger.getLogger(formpedido.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
            
        }
    }  
    public void obtenerfecha(){
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        this.fecha = dia + "/" + (mes+1) + "/" + anio;
        this.fechadb = anio+"-"+(mes+1)+"-"+dia;
        txtfecha.setText(this.fecha);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        busqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        a1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        a3 = new javax.swing.JLabel();
        a4 = new javax.swing.JLabel();
        combobox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btn1 = new rscomponentshade.RSButtonShade();
        rSToggleButtonShade1 = new rscomponentshade.RSToggleButtonShade();
        jLabel10 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtproducto = new javax.swing.JLabel();
        txtcantidad = new rscomponentshade.RSTextFieldShade();
        txtuso = new rscomponentshade.RSTextFieldShade();
        txtautorizado = new rscomponentshade.RSTextFieldShade();
        txtfecha = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pedidos de Producto a Bodega");
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setColorBackgoundHead(new java.awt.Color(26, 129, 135));
        table.setColorFilasForeground1(new java.awt.Color(0, 153, 153));
        table.setColorFilasForeground2(new java.awt.Color(0, 153, 153));
        table.setColorSelBackgound(new java.awt.Color(0, 153, 153));
        table.setFuenteFilas(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(26, 129, 135));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 108, 606, 560));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 70, 454, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 129, 135));
        jLabel1.setText("Seleccione el producto ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 31, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 66, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Información del pedido");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 168, -1, -1));

        a1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        a1.setForeground(new java.awt.Color(26, 129, 135));
        a1.setText("Fecha del pedido:");
        jPanel1.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Cantidad:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, -1, -1));

        a2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        a2.setForeground(new java.awt.Color(26, 129, 135));
        a2.setText("Uso:");
        jPanel1.add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 369, -1, -1));

        a3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        a3.setForeground(new java.awt.Color(26, 129, 135));
        a3.setText("Departamento:");
        jPanel1.add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, -1, -1));

        a4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        a4.setForeground(new java.awt.Color(26, 129, 135));
        a4.setText("Autorizado por:");
        jPanel1.add(a4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, -1, -1));

        jPanel1.add(combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 420, 240, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        btn1.setBackground(new java.awt.Color(0, 153, 153));
        btn1.setBorder(null);
        btn1.setText("Realizar Pedido");
        btn1.setBgHover(new java.awt.Color(0, 153, 153));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(791, 531, -1, -1));

        rSToggleButtonShade1.setBackground(new java.awt.Color(0, 153, 153));
        rSToggleButtonShade1.setBorder(null);
        rSToggleButtonShade1.setForeground(new java.awt.Color(0, 153, 153));
        rSToggleButtonShade1.setText("Cerrar Sesion");
        rSToggleButtonShade1.setBgHover(new java.awt.Color(0, 153, 153));
        rSToggleButtonShade1.setBorderPainted(false);
        rSToggleButtonShade1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rSToggleButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSToggleButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSToggleButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, 95, 27));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(26, 129, 135));
        jLabel10.setText("Usuario:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 30, -1, -1));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        usuario.setForeground(new java.awt.Color(0, 204, 0));
        usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 119, 21));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(26, 129, 135));
        jLabel11.setText("Producto:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, -1, -1));

        txtproducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 333, 25));

        txtcantidad.setPlaceholder("Cantidad");
        jPanel1.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, 100, -1));

        txtuso.setPlaceholder("Uso");
        jPanel1.add(txtuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 380, -1));

        txtautorizado.setPlaceholder("Autorizado por");
        jPanel1.add(txtautorizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 460, 330, -1));
        jPanel1.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 190, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    
    private void rSToggleButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSToggleButtonShade1ActionPerformed
        this.setVisible(false);
        login ln = new login();
        ln.setVisible(true);
    }//GEN-LAST:event_rSToggleButtonShade1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String codigo=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String descrip = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        String categoria = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
        this.id = codigo;
        this.producto = descrip;
        //txtfecha.setEnabled(true);
        txtcantidad.setEditable(true);
        txtuso.setEditable(true);
        combobox.setEnabled(true);
        txtautorizado.setEnabled(true);
        btn1.setEnabled(true);
        txtproducto.setText(descrip);
    }//GEN-LAST:event_tableMouseClicked
    
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
    obtenernombre();
    //fecha();
    Connection conexion = cn.conector();
    String codigo = this.id;
    int num_pedido = this.codigo+1;
    String fecha = this.fechadb;
    String cantidad = txtcantidad.getText();
    String uso = txtuso.getText();
    String departamento = (String)combobox.getSelectedItem();
    String solicita = this.nombreusuario;
    String autoriza = txtautorizado.getText();
    String estado = "PENDIENTE";
    String sql = "INSERT INTO `bodega`.`pedidos` (`CODIGO`, `NUM_PEDIDO`, `FECHA`, `CANTIDAD`, `USO`, `DEPARTAMENTO`, `QUIEN_SOLICITA`"
            + ", `AUTORIZA`, `ESTADO`) VALUES ("+codigo+", "+num_pedido+", '"
            +fecha+"', "+cantidad+", '"+uso+"', '"+departamento+"', '"+solicita+"', '"+autoriza+"', '"+estado+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido realizado");
            int decision = JOptionPane.showConfirmDialog(null, "¿Desea pedir otro producto?"," ",JOptionPane.YES_NO_OPTION);
            if(decision == 0){
                bloquear();
            }
            else{
                this.setVisible(false);
                login ln = new login();
                ln.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(formpedido.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
    }//GEN-LAST:event_busquedaKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formpedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formpedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formpedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formpedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formpedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a2;
    private javax.swing.JLabel a3;
    private javax.swing.JLabel a4;
    private rscomponentshade.RSButtonShade btn1;
    private javax.swing.JTextField busqueda;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSToggleButtonShade rSToggleButtonShade1;
    private rojerusan.RSTableMetro table;
    private rscomponentshade.RSTextFieldShade txtautorizado;
    private rscomponentshade.RSTextFieldShade txtcantidad;
    private javax.swing.JLabel txtfecha;
    private javax.swing.JLabel txtproducto;
    private rscomponentshade.RSTextFieldShade txtuso;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
