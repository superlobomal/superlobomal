
package Formatos;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Calculos_Matatematicos extends javax.swing.JFrame {

    String op= "";
    
    public Calculos_Matatematicos() {
        initComponents();
        sumar_num.setSelected(true);
       
       Num1.requestFocus(true);
        
    }
    
    void limpiar_d(){
        Num1.setText("");
        Num2.setText("");
        Total.setText("");
        sumar_num.setSelected(true);
        Num1.requestFocus(true);
    }
        void sumar_numeros(){
      float a=Float.parseFloat(Num1.getText());
      float b=Float.parseFloat(Num2.getText());
      float resultado=a+b;
      Total.setText(String.valueOf(resultado));
        }
      void restar_numeros(){
      float a=Float.parseFloat(Num1.getText());
      float b=Float.parseFloat(Num2.getText());
      float resultado=a-b;      
      Total.setText(String.valueOf(resultado));    
      }
        void multiplicar_numeros(){
      float a=Float.parseFloat(Num1.getText());
      float b=Float.parseFloat(Num2.getText());
      float resultado=a*b;      
      Total.setText(String.valueOf(resultado));    
      }
        void dividir_numeros(){
      float a=Float.parseFloat(Num1.getText());
      float b=Float.parseFloat(Num2.getText());
      float resultado=a/b;      
      Total.setText(String.valueOf(resultado));    
      }
        void agregar_datost(){
            DefaultTableModel model2 = (DefaultTableModel)t_numero.getModel();
            String[] registros = new String[4]; 
            
            registros[0] = Num1.getText();
            registros[1] = Num2.getText();
            registros[2] = op;
            registros[3] = Total.getText();
            model2.addRow(registros);
            t_numero.setModel(model2);
            
       
            
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Num1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Num2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        sumar_num = new javax.swing.JRadioButton();
        restar_num = new javax.swing.JRadioButton();
        multiplicar_num = new javax.swing.JRadioButton();
        dividir_num = new javax.swing.JRadioButton();
        Procesar = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_numero = new javax.swing.JTable();
        Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CALCULOS MATEMATICOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 0))); // NOI18N

        Num1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Num1ActionPerformed(evt);
            }
        });

        jLabel1.setText("NUMERO1");

        Num2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Num2ActionPerformed(evt);
            }
        });

        jLabel2.setText("NUMERO2");

        jLabel3.setText("TOTAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(Num2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(Num1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Num1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Num2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        buttonGroup1.add(sumar_num);
        sumar_num.setText("SUMAR");
        sumar_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumar_numActionPerformed(evt);
            }
        });

        buttonGroup1.add(restar_num);
        restar_num.setText("RESTAR");
        restar_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restar_numActionPerformed(evt);
            }
        });

        buttonGroup1.add(multiplicar_num);
        multiplicar_num.setText("MULTIPLICAR");
        multiplicar_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplicar_numActionPerformed(evt);
            }
        });

        buttonGroup1.add(dividir_num);
        dividir_num.setText("DIVIDIR");
        dividir_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dividir_numActionPerformed(evt);
            }
        });

        Procesar.setText("PROCESAR");
        Procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcesarActionPerformed(evt);
            }
        });

        Exit.setText("EXIT");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        t_numero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NUMERO1", "NUMERO2", "OPERACION", "TOTAL"
            }
        ));
        t_numero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_numeroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_numero);

        Limpiar.setText("LIMPIAR");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sumar_num)
                    .addComponent(dividir_num)
                    .addComponent(multiplicar_num)
                    .addComponent(restar_num))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Procesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(300, 300, 300))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sumar_num)
                            .addComponent(Procesar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(restar_num)
                            .addComponent(Exit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(multiplicar_num)
                            .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dividir_num)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
      this.dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void ProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcesarActionPerformed
      if (Num1.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO NUMERO 1 ESTA EN BLANCO");
       Num1.requestFocus(true);
       return;
      }         
      if (Num2.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO NUMERO 2 ESTA EN BLANCO");
       Num2.requestFocus(true);
       return;      
      }
      if (sumar_num.isSelected()){
     sumar_numeros(); 
     op="SUMA";
      }
      if (restar_num.isSelected()){
     restar_numeros(); 
     op="RESTA";
      }
      if (multiplicar_num.isSelected()){
     multiplicar_numeros();
     op="MULTIPLICACION";
      }
     if (dividir_num.isSelected()){
     dividir_numeros();   
     op="DIVICION";
      }
     agregar_datost();
    }//GEN-LAST:event_ProcesarActionPerformed

    private void restar_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restar_numActionPerformed
        Procesar.doClick();
    }//GEN-LAST:event_restar_numActionPerformed

    private void Num1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Num1ActionPerformed
              if (Num1.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO NUMERO 1 ESTA EN BLANCO");
       Num1.requestFocus(true);
       return;
      }
        Num2.requestFocus(true);
        Num2.setBackground(Color.yellow);
        
    }//GEN-LAST:event_Num1ActionPerformed

    private void Num2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Num2ActionPerformed
        if (Num1.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO NUMERO 2 ESTA EN BLANCO");
       Num1.requestFocus(true);
       return;      
      }
        if (Num2.getText().equals("")){
       JOptionPane.showMessageDialog(null,"EL CAMPO NUMERO 2 ESTA EN BLANCO");
       Num2.requestFocus(true);
       return;      
      }
         if (sumar_num.isSelected()){
     sumar_numeros();   
      }
      if (sumar_num.isSelected()){
     sumar_numeros(); 
     op="SUMA";
      }
      if (restar_num.isSelected()){
     restar_numeros(); 
     op="RESTA";
      }
      if (multiplicar_num.isSelected()){
     multiplicar_numeros();
     op="MULTIPLICACION";
      }
     if (dividir_num.isSelected()){
     dividir_numeros();   
     op="DIVICION";
      }
     agregar_datost();
    }//GEN-LAST:event_Num2ActionPerformed

    private void sumar_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumar_numActionPerformed
        Procesar.doClick();
    }//GEN-LAST:event_sumar_numActionPerformed

    private void multiplicar_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplicar_numActionPerformed
        Procesar.doClick();
    }//GEN-LAST:event_multiplicar_numActionPerformed

    private void dividir_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dividir_numActionPerformed
        Procesar.doClick();
    }//GEN-LAST:event_dividir_numActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiar_d();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void t_numeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_numeroMouseClicked
        int fila = t_numero.getSelectedRow();
         if (fila>=0){
             Num1.setText(t_numero.getValueAt(fila,0).toString());
             Num2.setText(t_numero.getValueAt(fila,1).toString());
             Total.setText(t_numero.getValueAt(fila,3).toString());             
              op=(t_numero.getValueAt(fila,2).toString());  
              
              if (op.equals("SUMA")){
                  sumar_num.setSelected(true);       
              }
               if (op.equals("RESTA")){
                  restar_num.setSelected(true);                
              }
                if (op.equals("MULTIPLICACION")){
                  multiplicar_num.setSelected(true);
              }
                 if (op.equals("DIVICION")){
                  dividir_num.setSelected(true);                
              }
         }
    }//GEN-LAST:event_t_numeroMouseClicked

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
            java.util.logging.Logger.getLogger(Calculos_Matatematicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculos_Matatematicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculos_Matatematicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculos_Matatematicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculos_Matatematicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTextField Num1;
    private javax.swing.JTextField Num2;
    private javax.swing.JButton Procesar;
    private javax.swing.JTextField Total;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dividir_num;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton multiplicar_num;
    private javax.swing.JRadioButton restar_num;
    private javax.swing.JRadioButton sumar_num;
    private javax.swing.JTable t_numero;
    // End of variables declaration//GEN-END:variables
}
