
package Formatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Poo2.ConexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class Gestion_de_Usuario extends javax.swing.JFrame {

    public Gestion_de_Usuario() {
        initComponents();
        //cargar();
    }
     void borrar_datos(){
        try {
            PreparedStatement psU= cn.prepareStatement("DELETE FROM usuarios WHERE idusuario='"+id_usuario.getText()+"'");
                    psU.executeUpdate();
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    void actualizar_datos(){
        String tip_de_usuario = String.valueOf(tipo_de_usuario.getSelectedItem());
        
        try {
            PreparedStatement psU= cn.prepareStatement("UPDATE usuarios SET idusuarios='"+id_usuario.getText()+"',Nombre_usuario='"+usuario.getText()+"',"
                    + "personal='"+nom_empleado.getText()+"',tipo_usuario='"+tip_de_usuario+"',password='"+contraseña.getText()+"',phone='"+phone.getText()+"'"
                            + "WHERE idusuarios='"+id_usuario.getText()+"'");
                    psU.executeUpdate();
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
     void cargar_filtro(String valor){
    DefaultTableModel model2 = (DefaultTableModel)t_usuario.getModel();
    model2.getDataVector().clear();
    
    String[] registros = new String[8];
    String sql = "SELECT idusuarios,Nombre_usuario,personal,password,tipo_usuario,phone FROM usuarios where CONCAT (idusuarios,'',Nombre_usuario,'',personal,'',phone)LIKE '%"+valor+"%'";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);        
        

        while (rs.next()) {
            registros[0] = rs.getString("idusuarios");
            registros[1] = rs.getString("Nombre_usuario");
            registros[3] = rs.getString("personal");
            registros[2] = rs.getString("password");
            registros[5] = rs.getString("tipo_usuario");
            registros[4] = rs.getString("phone");
            
           model2.addRow(registros);
        }
        
        t_usuario.setModel(model2);
       
    }  
    catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex);

    }
}

    void cargar(){
    DefaultTableModel model2 = (DefaultTableModel)t_usuario.getModel();
    model2.getDataVector().clear();
    
    String[] registros = new String[8];
    String sql = "SELECT idusuarios,Nombre_usuario,personal,password,tipo_usuario,phone FROM usuarios";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);        
        

        while (rs.next()) {
            registros[0] = rs.getString("idusuarios");
            registros[1] = rs.getString("Nombre_usuario");
            registros[2] = rs.getString("personal");
            registros[3] = rs.getString("password");
            registros[4] = rs.getString("tipo_usuario");
            registros[5] = rs.getString("phone");
            
           model2.addRow(registros);
        }
        
        t_usuario.setModel(model2);
       
    }  
    catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex);

    }
}
    
      void limpiar(){
          id_usuario.setText("");
          usuario.setText("");
          contraseña.setText("");
          nom_empleado.setText("");
          phone.setText("");
          tipo_de_usuario.setSelectedIndex(0);         
          id_usuario.requestFocus(true);
      }
      void cargar_datos(){
    String[] registros = new String[7];
    String sql = "SELECT Nombre_usuario,personal,password,tipo_usuario,phone FROM usuarios WHERE idusuarios = '"+id_usuario.getText()+"'";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);        
        

        while (rs.next()) {
            registros[0] = rs.getString("Nombre_usuario");
            registros[1] = rs.getString("personal");
            registros[2] = rs.getString("password");
            registros[3] = rs.getString("tipo_usuario");
            registros[4] = rs.getString("phone");
           
           
        }
        
        usuario.setText(registros[0]);
        contraseña.setText(registros[2]);
        nom_empleado.setText(registros[1]);
        phone.setText(registros[4]);       
        tipo_de_usuario.setSelectedItem(registros[3]);
        

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex);

    }
}

    void guardar(){
        
        String tip_de_usuario = String.valueOf(tipo_de_usuario.getSelectedItem());
    
        try {
            String sqlguardar="";
            
            
            sqlguardar = "INSERT INTO usuarios VALUES (DEFAULT,'"+usuario.getText()+"','"+nom_empleado.getText()+"','"+contraseña.getText()+"',"
            + " '"+tip_de_usuario+"','"+phone.getText()+"')";
            PreparedStatement sql =   cn.prepareStatement(sqlguardar);
            
            int x;
            x = sql.executeUpdate();
            if(x>0){
            
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            
            
        }
          JOptionPane.showMessageDialog(null, "DATOS GUARDADOS");
            cargar();
    
    
    
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id_usuario = new javax.swing.JTextField();
        usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        contraseña = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nom_empleado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_usuario = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buscar_nombre = new javax.swing.JTextField();
        mod_datos = new javax.swing.JButton();
        eliminar_datos = new javax.swing.JButton();
        tipo_de_usuario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO DE CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 0))); // NOI18N

        jLabel1.setText("ID USUARIO");

        id_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_usuarioActionPerformed(evt);
            }
        });
        id_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_usuarioKeyReleased(evt);
            }
        });

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jLabel2.setText("USUARIO");

        jLabel3.setText("CONTRASEÑA");

        jLabel4.setText("NOM.EMPLEADO");

        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        jLabel5.setText("TELEFONO");

        jLabel6.setText("TIPO DE USUARIO");

        save.setText("GUARDAR");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jButton1.setText("EXIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("LIMPIAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        t_usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "USUARIO", "CONTRASEÑA", "NOM.EMPLEADO", "TELEFONO", "TIPO DE USUARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_usuario.setSelectionForeground(new java.awt.Color(0, 0, 0));
        t_usuario.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                t_usuarioAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        t_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_usuarioMouseClicked(evt);
            }
        });
        t_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_usuarioKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(t_usuario);
        if (t_usuario.getColumnModel().getColumnCount() > 0) {
            t_usuario.getColumnModel().getColumn(0).setMinWidth(80);
            t_usuario.getColumnModel().getColumn(0).setMaxWidth(80);
            t_usuario.getColumnModel().getColumn(1).setMinWidth(100);
            t_usuario.getColumnModel().getColumn(1).setMaxWidth(100);
            t_usuario.getColumnModel().getColumn(2).setMinWidth(120);
            t_usuario.getColumnModel().getColumn(2).setMaxWidth(120);
            t_usuario.getColumnModel().getColumn(3).setMinWidth(250);
            t_usuario.getColumnModel().getColumn(3).setMaxWidth(250);
            t_usuario.getColumnModel().getColumn(4).setMinWidth(90);
            t_usuario.getColumnModel().getColumn(4).setMaxWidth(90);
            t_usuario.getColumnModel().getColumn(5).setMinWidth(150);
            t_usuario.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        jLabel7.setText("BUSCAR");

        buscar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_nombreActionPerformed(evt);
            }
        });
        buscar_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar_nombreKeyReleased(evt);
            }
        });

        mod_datos.setText("MODIFICAR DATOS");
        mod_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_datosActionPerformed(evt);
            }
        });

        eliminar_datos.setText("ELIMINAR DATOS");
        eliminar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_datosActionPerformed(evt);
            }
        });

        tipo_de_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de usuario", "Usuario", "Admin" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mod_datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar_nombre))
                    .addComponent(eliminar_datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phone)
                            .addComponent(nom_empleado)
                            .addComponent(id_usuario)
                            .addComponent(usuario)
                            .addComponent(contraseña)
                            .addComponent(tipo_de_usuario, 0, 190, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nom_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tipo_de_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(7, 7, 7)
                .addComponent(mod_datos)
                .addGap(4, 4, 4)
                .addComponent(eliminar_datos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(buscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed

    private void id_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_usuarioActionPerformed
        cargar_filtro(buscar_nombre.getText());   
        cargar_datos();
    }//GEN-LAST:event_id_usuarioActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    if (usuario.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO CLIENTE ESTA BACIO");
       usuario.requestFocus(true);
       return;
      } 
    if (contraseña.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       contraseña.requestFocus(true);
       return;
      }
      if (nom_empleado.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       nom_empleado.requestFocus(true);
       return;  
      }
      
      if (phone.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       phone.requestFocus(true);
       return;  
      }
      if (tipo_de_usuario.getSelectedItem().equals("Tipo de usuario")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE USUARIO NO AH SIDO SELECCIONADO");
       tipo_de_usuario.requestFocus(true);
       return;  
      }
      
        guardar();
        limpiar();
        cargar();
        
    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buscar_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_nombreKeyReleased
        cargar_filtro(buscar_nombre.getText());
    }//GEN-LAST:event_buscar_nombreKeyReleased

    private void mod_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_datosActionPerformed
        if (usuario.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO CLIENTE ESTA BACIO");
       usuario.requestFocus(true);
       return;
      } 
    if (contraseña.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       contraseña.requestFocus(true);
       return;
      }
      if (nom_empleado.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       nom_empleado.requestFocus(true);
       return;  
      }
      
      if (phone.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       phone.requestFocus(true);
       return;  
      }
      if (tipo_de_usuario.getSelectedItem().equals("Tipo de usuario")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE USUARIO NO AH SIDO SELECCIONADO");
       tipo_de_usuario.requestFocus(true);
       return;  
      }
       
        actualizar_datos();
        limpiar();
        cargar();
    }//GEN-LAST:event_mod_datosActionPerformed

    private void eliminar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_datosActionPerformed
        if (usuario.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO CLIENTE ESTA BACIO");
       usuario.requestFocus(true);
       return;
      } 
    if (contraseña.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       contraseña.requestFocus(true);
       return;
      }
      if (nom_empleado.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       nom_empleado.requestFocus(true);
       return;  
      }
      
      if (phone.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       phone.requestFocus(true);
       return;  
      }
      if (tipo_de_usuario.getSelectedItem().equals("Tipo de usuario")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE USUARIO NO AH SIDO SELECCIONADO");
       tipo_de_usuario.requestFocus(true);
       return;  
      }
        borrar_datos();
        limpiar();
        cargar();
        
    }//GEN-LAST:event_eliminar_datosActionPerformed

    private void buscar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_nombreActionPerformed

    private void id_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_usuarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_id_usuarioKeyReleased

    private void t_usuarioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_t_usuarioAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_t_usuarioAncestorAdded

    private void t_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_usuarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t_usuarioKeyReleased

    private void t_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_usuarioMouseClicked
        int fila = t_usuario.getSelectedRow();
        if (fila>=0){
            id_usuario.setText(t_usuario.getValueAt(fila,0).toString());
            usuario.setText(t_usuario.getValueAt(fila,1).toString());
            contraseña.setText(t_usuario.getValueAt(fila,2).toString());
            nom_empleado.setText(t_usuario.getValueAt(fila,3).toString());
            phone.setText(t_usuario.getValueAt(fila,4).toString());
            tipo_de_usuario.setSelectedItem(t_usuario.getValueAt(fila,5).toString());            
            usuario.requestFocus(true);
        }
    }//GEN-LAST:event_t_usuarioMouseClicked

   
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
            java.util.logging.Logger.getLogger(Gestion_de_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_de_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_de_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_de_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_de_Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_nombre;
    private javax.swing.JTextField contraseña;
    private javax.swing.JButton eliminar_datos;
    private javax.swing.JTextField id_usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mod_datos;
    private javax.swing.JTextField nom_empleado;
    private javax.swing.JTextField phone;
    private javax.swing.JButton save;
    private javax.swing.JTable t_usuario;
    private javax.swing.JComboBox<String> tipo_de_usuario;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables

        ConexionDB cc= new ConexionDB();
        Connection cn = cc.conectar();
}

