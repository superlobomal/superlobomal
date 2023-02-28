
package Formatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Poo2.ConexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class Clientes extends javax.swing.JFrame {

    public Clientes() {
        initComponents();
        //cargar();
    }
     void borrar_datos(){
        try {
            PreparedStatement psU= cn.prepareStatement("DELETE FROM cliente WHERE id_cliente='"+id_client.getText()+"'");
                    psU.executeUpdate();
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    void actualizar_datos(){
        try {
            PreparedStatement psU= cn.prepareStatement("UPDATE cliente SET cliente='"+client.getText()+"',type_client='"+type_client.getText()+"',rnc='"+rnc.getText()+"',ncf='"+ncf.getText()+"',dir_cliente='"+dir_client.getText()+"',phone='"+phone.getText()+"',lim_cred='"+lim_cred.getText()+"'"
                            + "WHERE id_cliente='"+id_client.getText()+"'");
                    psU.executeUpdate();
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
     void cargar_filtro(String valor){
    DefaultTableModel model2 = (DefaultTableModel)t_cliente.getModel();
    model2.getDataVector().clear();
    
    String[] registros = new String[8];
    String sql = "SELECT id_cliente,cliente,type_client,dir_cliente,phone,lim_cred,rnc,ncf FROM cliente where CONCAT (cliente,'',id_cliente,'',phone,'',rnc)LIKE '%"+valor+"%'";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);        
        

        while (rs.next()) {
            registros[0] = rs.getString("id_cliente");
            registros[1] = rs.getString("cliente");
            registros[2] = rs.getString("type_client");
            registros[3] = rs.getString("dir_cliente");
            registros[4] = rs.getString("phone");
            registros[5] = rs.getString("lim_cred");
            registros[6] = rs.getString("rnc");
            registros[7] = rs.getString("ncf");
            
           model2.addRow(registros);
        }
        
        t_cliente.setModel(model2);
       
    }  
    catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex);

    }
}

    void cargar(){
    DefaultTableModel model2 = (DefaultTableModel)t_cliente.getModel();
    model2.getDataVector().clear();
    
    String[] registros = new String[8];
    String sql = "SELECT id_cliente,cliente,type_client,dir_cliente,phone,lim_cred,rnc,ncf FROM cliente";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);        
        

        while (rs.next()) {
            registros[0] = rs.getString("id_cliente");
            registros[1] = rs.getString("cliente");
            registros[2] = rs.getString("type_client");
            registros[3] = rs.getString("dir_cliente");
            registros[4] = rs.getString("phone");
            registros[5] = rs.getString("lim_cred");
            registros[6] = rs.getString("rnc");
            registros[7] = rs.getString("ncf");
           model2.addRow(registros);
        }
        
        t_cliente.setModel(model2);
       
    }  
    catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex);

    }
}
    
      void limpiar(){
          id_client.setText("");
          client.setText("");
          type_client.setText("");
          dir_client.setText("");
          phone.setText("");
          lim_cred.setText("");
          rnc.setText("");
          ncf.setText("");
          id_client.requestFocus(true);
      }
      void cargar_datos(){
    String[] registros = new String[7];
    String sql = "SELECT cliente,type_client,dir_cliente,phone,lim_cred,rnc,ncf FROM cliente WHERE id_cliente = '"+id_client.getText()+"'";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);        
        

        while (rs.next()) {
            registros[0] = rs.getString("cliente");
            registros[1] = rs.getString("type_client");
            registros[2] = rs.getString("dir_cliente");
            registros[3] = rs.getString("phone");
            registros[4] = rs.getString("lim_cred");
            registros[5] = rs.getString("rnc");
            registros[6] = rs.getString("ncf");
            
           
        }
        
        client.setText(registros[0]);
        type_client.setText(registros[1]);
        dir_client.setText(registros[2]);
        phone.setText(registros[3]);       
        lim_cred.setText(registros[4]);
        rnc.setText(registros[5]);
        ncf.setText(registros[6]);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex);

    }
}

    void guardar(){
    
        try {
            String sqlguardar="";
            
            
            sqlguardar = "INSERT INTO cliente VALUES (DEFAULT,'"+client.getText()+"','"+type_client.getText()+"','"+dir_client.getText()+"',"
            + " '"+phone.getText()+"','"+lim_cred.getText()+"','"+rnc.getText()+"','"+ncf.getText()+"')";
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
        id_client = new javax.swing.JTextField();
        client = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        type_client = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dir_client = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lim_cred = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_cliente = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buscar_nombre = new javax.swing.JTextField();
        mod_datos = new javax.swing.JButton();
        eliminar_datos = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        rnc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ncf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO DE CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 0))); // NOI18N

        jLabel1.setText("ID CLIENTE");

        id_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_clientActionPerformed(evt);
            }
        });
        id_client.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_clientKeyReleased(evt);
            }
        });

        client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientActionPerformed(evt);
            }
        });

        jLabel2.setText("CLIENTE");

        jLabel3.setText("TIPO DE CLIENTE");

        jLabel4.setText("DIRC DE CLIENTE");

        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        jLabel5.setText("TELEFONO");

        jLabel6.setText("LIMITE DE CREDITO");

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

        t_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "CLIENTE", "TIPO DE CLIENTE", "DIR DE CLIENTE", "TELEFONO", "LIMITE DE CREDITO", "RNC", "NCF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(t_cliente);
        if (t_cliente.getColumnModel().getColumnCount() > 0) {
            t_cliente.getColumnModel().getColumn(0).setMinWidth(80);
            t_cliente.getColumnModel().getColumn(0).setMaxWidth(80);
            t_cliente.getColumnModel().getColumn(1).setMinWidth(250);
            t_cliente.getColumnModel().getColumn(1).setMaxWidth(250);
            t_cliente.getColumnModel().getColumn(2).setMinWidth(100);
            t_cliente.getColumnModel().getColumn(2).setMaxWidth(100);
            t_cliente.getColumnModel().getColumn(3).setMinWidth(150);
            t_cliente.getColumnModel().getColumn(3).setMaxWidth(150);
            t_cliente.getColumnModel().getColumn(4).setMinWidth(100);
            t_cliente.getColumnModel().getColumn(4).setMaxWidth(100);
            t_cliente.getColumnModel().getColumn(5).setMinWidth(120);
            t_cliente.getColumnModel().getColumn(5).setMaxWidth(120);
            t_cliente.getColumnModel().getColumn(7).setMinWidth(80);
            t_cliente.getColumnModel().getColumn(7).setMaxWidth(80);
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

        jLabel8.setText("RNC");

        jLabel9.setText("NCF");

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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lim_cred, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dir_client, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_client, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(type_client, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rnc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ncf, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(type_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dir_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lim_cred, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rnc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ncf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(27, 27, 27)
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientActionPerformed

    private void id_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_clientActionPerformed
        cargar_filtro(buscar_nombre.getText());   
        cargar_datos();
    }//GEN-LAST:event_id_clientActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    if (client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO CLIENTE ESTA BACIO");
       client.requestFocus(true);
       return;
      } 
    if (type_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       type_client.requestFocus(true);
       return;
      }
      if (dir_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       dir_client.requestFocus(true);
       return;  
      }
      if (dir_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       dir_client.requestFocus(true);
       return;  
      }
      if (phone.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       phone.requestFocus(true);
       return;  
      }
      if (lim_cred.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       lim_cred.requestFocus(true);
       return;  
      }
      if (rnc.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       rnc.requestFocus(true);
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
        if (client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO CLIENTE ESTA BACIO");
       client.requestFocus(true);
       return;
      } 
    if (type_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       type_client.requestFocus(true);
       return;
      }
      if (dir_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       dir_client.requestFocus(true);
       return;  
      }
      if (dir_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       dir_client.requestFocus(true);
       return;  
      }
      if (phone.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       phone.requestFocus(true);
       return;  
      }
      if (lim_cred.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       lim_cred.requestFocus(true);
       return;  
      }
      if (rnc.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       rnc.requestFocus(true);
       return;  
      }
       
        actualizar_datos();
        limpiar();
        cargar();
    }//GEN-LAST:event_mod_datosActionPerformed

    private void eliminar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_datosActionPerformed
        if (client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO CLIENTE ESTA BACIO");
       client.requestFocus(true);
       return;
      } 
    if (type_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       type_client.requestFocus(true);
       return;
      }
      if (dir_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       dir_client.requestFocus(true);
       return;  
      }
      if (dir_client.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       dir_client.requestFocus(true);
       return;  
      }
      if (phone.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       phone.requestFocus(true);
       return;  
      }
      if (lim_cred.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       lim_cred.requestFocus(true);
       return;  
      }
      if (rnc.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO TIPO DE CLIENTE ESTA BACIO");
       rnc.requestFocus(true);
       return;  
      }
        borrar_datos();
        limpiar();
        cargar();
        
    }//GEN-LAST:event_eliminar_datosActionPerformed

    private void buscar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_nombreActionPerformed

    private void id_clientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_clientKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_id_clientKeyReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_nombre;
    private javax.swing.JTextField client;
    private javax.swing.JTextField dir_client;
    private javax.swing.JButton eliminar_datos;
    private javax.swing.JTextField id_client;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lim_cred;
    private javax.swing.JButton mod_datos;
    private javax.swing.JTextField ncf;
    private javax.swing.JTextField phone;
    private javax.swing.JTextField rnc;
    private javax.swing.JButton save;
    private javax.swing.JTable t_cliente;
    private javax.swing.JTextField type_client;
    // End of variables declaration//GEN-END:variables

        ConexionDB cc= new ConexionDB();
        Connection cn = cc.conectar();
}

