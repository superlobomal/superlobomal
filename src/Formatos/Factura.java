
package Formatos;

import Poo2.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Factura extends javax.swing.JFrame {

     Map<String,Float> productos=new HashMap<String,Float>();   
     
    public Factura() {
        initComponents();
        buscar.requestFocus(true);
        f_contado.setSelected(true);
        numero_factura();
        imprimir.setEnabled(false);
    }
    
    void numero_factura(){
        String id_factura="";
        
        try {
            Statement sq2= cn.createStatement();
            ResultSet rq2= sq2.executeQuery("SELECT id_factura FROM contador" );
            rq2.next();
            id_factura=rq2.getString("id_factura");
            
        } catch (SQLException e) {
        }
        
        int secuencia = Integer.parseInt(id_factura);
        secuencia= secuencia +1;
        num_factura.setText(String.valueOf(secuencia));
        
        
    }
   

    void eliminarDatosFactura() {
    DefaultTableModel model2 = (DefaultTableModel) t_factura.getModel();
    int[] filasSeleccionadas = t_factura.getSelectedRows();
    for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
        model2.removeRow(filasSeleccionadas[i]);
        String articulo=(String) new ArrayList(productos.keySet()).get(i);
        productos.remove(articulo);
        System.out.println(productos.size());
    }
    sumar_productos();
    
    
}

    
    
         
    void limpiar(){
         id_articulo.setText("");
            des_articulo.setText("");
            cantidad.setText("");
            precio_v.setText("");
            buscar.setText("");
            buscar.requestFocus(true);
    }
    
    void sumar_productos(){
        DecimalFormat formateador = new DecimalFormat("###,###,###,###.##");
        DecimalFormat formateador2 = new DecimalFormat("############.##");
        
        DefaultTableModel model2 = (DefaultTableModel) t_factura.getModel();
        
        float total_g = 0,total_g2 = 0;
        float itbis_g = 0, itbis_g2 = 0;
        float sub_total_g = 0, sub_total_g2 = 0;
        
        for (int i=0; i< t_factura.getRowCount(); i++){
            total_g = Float.parseFloat(t_factura.getValueAt(i, 6).toString());
            total_g2=total_g2+total_g;
            
            itbis_g = Float.parseFloat(t_factura.getValueAt(i, 4).toString());
            itbis_g2=itbis_g2+itbis_g;
            
            sub_total_g = Float.parseFloat(t_factura.getValueAt(i, 5).toString());
            sub_total_g2=sub_total_g2+sub_total_g;
            
        }
        total_general.setText(formateador.format(total_g2));
        itbis_general.setText(formateador.format(itbis_g2));
        sub_total_general.setText(formateador.format(sub_total_g2));
        
        sub_total_general2=(formateador2.format(total_g2));
        itbis_general2=(formateador2.format(itbis_g2));
        sub_total_general2=(formateador2.format(sub_total_g2));
        
    }
    
    void actualizar_cantidad_factura(String id_articulo, float cantidad) {
    try {
        // Consultar la cantidad actual del artículo en la base de datos
        String sql = "SELECT amount_art FROM articulo WHERE id_art = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, id_articulo);
        ResultSet rs = ps.executeQuery();
        float cantidad_actual = 0.0f;
        if (rs.next()) {
            cantidad_actual = rs.getFloat("amount_art");
        }
        
        // Actualizar la cantidad en la base de datos
        float cantidad_nueva = cantidad_actual - cantidad;
        sql = "UPDATE articulo SET amount_art = ? WHERE id_art = ?";
        ps = cn.prepareStatement(sql);
        ps.setFloat(1, cantidad_nueva);
        ps.setString(2, id_articulo);
        ps.executeUpdate();
        
        // Actualizar la cantidad en la tabla "t_factura"
        DefaultTableModel model = (DefaultTableModel) t_factura.getModel();
        int numRows = model.getRowCount();
        int idColumnIndex = 0; // La columna donde se encuentra el id_articulo
        int cantidadColumnIndex = 2; // La columna donde se encuentra la cantidad
        int rowIndex = -1;
        for (int i = 0; i < numRows; i++) {
            String id_articulo_factura = (String) model.getValueAt(i, idColumnIndex);
            if (id_articulo_factura.equals(id_articulo)) {
                rowIndex = i;
                break;
            }
        }
        if (rowIndex != -1) {
            float cantidad_actual_tabla = (float) model.getValueAt(rowIndex, cantidadColumnIndex);
            float cantidad_nueva_tabla = cantidad_actual_tabla - cantidad;
            model.setValueAt(cantidad_nueva_tabla, rowIndex, cantidadColumnIndex);
        }
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}






   void llenar_datoss(){
    DecimalFormat formateador = new DecimalFormat("###,###,###,###.##");
    DefaultTableModel model2 = (DefaultTableModel) t_factura.getModel();            
    String[] registros = new String[10];
    
    // Obtener el valor de ITBIS desde la base de datos
    float itbis_bd = 0.0f;
    try {
        String sql = "SELECT itbis FROM articulo WHERE id_art = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, id_articulo.getText());
        ResultSet rs = ps.executeQuery(); 
        if (rs.next()) {
            itbis_bd = rs.getFloat("itbis");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    
    // Establecer el valor de ITBIS a usar en los cálculos
    float itbis_para_calcular;
    if (itbis_bd == 0.0f) {
        itbis_para_calcular = 1.0f;
    } else if (itbis_bd == 2.0f) {
        itbis_para_calcular = 1.02f;
    } else if (itbis_bd == 8.0f) {
        itbis_para_calcular = 1.08f;
    } else if (itbis_bd == 9.0f) {
        itbis_para_calcular = 1.09f;
    } else if (itbis_bd == 16.0f) {
        itbis_para_calcular = 1.16f;
    } else if (itbis_bd == 18.0f) {
        itbis_para_calcular = 1.18f;
    } else if (itbis_bd == 28.0f) {
        itbis_para_calcular = 1.28f;
    } else {
        // Si no se encuentra el ITBIS en la lista de valores conocidos, se utiliza el valor por defecto de 0.0f
        itbis_para_calcular = 0.0f;
    }
    
    float precio2=Float.parseFloat(precio_v.getText());
    float cantidad2=Float.parseFloat(cantidad.getText());
    float total2=precio2*cantidad2;
    float sub_total2=total2/itbis_para_calcular;

    registros[0] = id_articulo.getText();
    registros[1] = des_articulo.getText();
    registros[2] = cantidad.getText();
    registros[3] = precio_v.getText();
    registros[4] = formateador.format(total2-sub_total2);
    registros[5] = formateador.format(sub_total2);
    registros[6] = formateador.format(total2);
    model2.addRow(registros);
    productos.put(id_articulo.getText(),cantidad2);
    t_factura.setModel(model2);
    
    
}


    void cargar_articulo(String valor) {
        

        String[] registros = new String[10];
        String sql = "SELECT id_art,des_art,amount_art,price_sell FROM articulo where id_art='"+valor+"' limit 100";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("id_art");
                registros[1] = rs.getString("des_art");
                registros[2] = rs.getString("amount_art");
                registros[3] = rs.getString("price_sell");               

                
            }
            id_articulo.setText(registros[0]);
            des_articulo.setText(registros[1]);
            precio_v.setText(registros[3]);
            cantidad.requestFocus();
            

            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
    
    void cargar_filtro(String valor) {
        DefaultTableModel model2 = (DefaultTableModel) t_articulos.getModel();
        model2.getDataVector().clear();

        String[] registros = new String[10];
        String sql = "SELECT id_art,des_art,amount_art,price_sell FROM articulo where CONCAT (id_art,'',des_art)LIKE '%" + valor + "%' limit 100";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("id_art");
                registros[1] = rs.getString("des_art");
                registros[2] = rs.getString("amount_art");
                registros[3] = rs.getString("price_sell");
                

                model2.addRow(registros);
            }

            t_articulos.setModel(model2);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        imprimir = new javax.swing.JButton();
        guardar_factura = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_articulos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id_cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nom_cliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rnc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ncf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        num_factura = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_factura = new javax.swing.JTable();
        id_articulo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        des_articulo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        precio_v = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        sub_total_general = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        itbis_general = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        total_general = new javax.swing.JTextField();
        f_credito = new javax.swing.JRadioButton();
        f_contado = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ELIMINAR DE LA FACTURA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        imprimir.setText("IMPRIMIR");
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });

        guardar_factura.setText("GUARDAR FACTURA");
        guardar_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_facturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardar_factura, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardar_factura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        t_articulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ARTICULO", "DESCRIPCION DEL ARTICULO", "CANT.", "PRECIO DE VENTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_articulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_articulosMouseClicked(evt);
            }
        });
        t_articulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_articulosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(t_articulos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("ID CLIENTE");

        id_cliente.setEditable(false);

        jLabel2.setText("CLIENTE");

        jLabel3.setText("RNC");

        rnc.setEditable(false);

        jLabel4.setText("NCF");

        ncf.setEditable(false);

        jLabel5.setText("BUSCAR");

        buscar.setBackground(new java.awt.Color(255, 255, 204));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        jLabel6.setText("#FACTURA");

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rnc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ncf, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nom_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(num_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(num_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nom_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rnc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ncf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        t_factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ARTICULO", "DESCRIPCION DEL ARTICULO", "CANTIDAD", "PRECIO V", "ITBIS", "IMPORTE", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(t_factura);

        id_articulo.setBackground(new java.awt.Color(255, 255, 204));

        jLabel7.setText("ID ARTICULO");

        des_articulo.setBackground(new java.awt.Color(255, 255, 204));

        jLabel8.setText("DESCRIPCION");

        jLabel9.setText("PRECIO V");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        jLabel10.setText("CANTIDAD");

        sub_total_general.setBackground(new java.awt.Color(255, 255, 204));

        jLabel11.setText("SUB TOTAL");

        jLabel12.setText("ITBIS");

        itbis_general.setBackground(new java.awt.Color(255, 255, 204));

        jLabel13.setText("TOTAL");

        total_general.setBackground(new java.awt.Color(0, 0, 0));
        total_general.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        total_general.setForeground(new java.awt.Color(255, 255, 0));

        buttonGroup1.add(f_credito);
        f_credito.setText("FACTURA A CREDITO");

        buttonGroup1.add(f_contado);
        f_contado.setText("FACTURA AL CONTADO");
        f_contado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_contadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(des_articulo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(precio_v, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(f_contado)
                            .addComponent(f_credito))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itbis_general, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sub_total_general, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(total_general, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(id_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(des_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(precio_v, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(sub_total_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(itbis_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(total_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(f_contado)
                        .addGap(9, 9, 9)
                        .addComponent(f_credito)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void t_articulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_articulosMouseClicked
        int fila = t_articulos.getSelectedRow();
        if (fila>=0){
            id_articulo.setText(t_articulos.getValueAt(fila,0).toString());
            des_articulo.setText(t_articulos.getValueAt(fila,1).toString());
            precio_v.setText(t_articulos.getValueAt(fila,3).toString());
            cantidad.requestFocus(true);
        }
    }//GEN-LAST:event_t_articulosMouseClicked

    private void t_articulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_articulosKeyReleased

    }//GEN-LAST:event_t_articulosKeyReleased

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        int fila = t_articulos.getSelectedRow();
        if (fila>=0){
            id_articulo.setText(t_articulos.getValueAt(fila,0).toString());
            des_articulo.setText(t_articulos.getValueAt(fila,1).toString());
            precio_v.setText(t_articulos.getValueAt(fila,3).toString());
            cantidad.requestFocus(true);
        }
        cargar_articulo(buscar.getText());
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        cargar_filtro(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        
        llenar_datoss();
        sumar_productos();
        limpiar();
    }//GEN-LAST:event_cantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        eliminarDatosFactura();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
    
                   
                    

    }//GEN-LAST:event_imprimirActionPerformed

    private void f_contadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_contadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_contadoActionPerformed

    private void guardar_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_facturaActionPerformed
        imprimir.setEnabled(true);   
        
         productos.forEach((t, u) -> {
                        actualizar_cantidad_factura(t,u);
                    });
        
        if (f_contado.isSelected()) {
            tipo_factura="CONTADO";
        }
        if (f_credito.isSelected()) {
            tipo_factura="CREDITO";
        }
    }//GEN-LAST:event_guardar_facturaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Clientes().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField buscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField des_articulo;
    private javax.swing.JRadioButton f_contado;
    private javax.swing.JRadioButton f_credito;
    private javax.swing.JButton guardar_factura;
    private javax.swing.JTextField id_articulo;
    public static javax.swing.JTextField id_cliente;
    private javax.swing.JButton imprimir;
    private javax.swing.JTextField itbis_general;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextField ncf;
    public static javax.swing.JTextField nom_cliente;
    private javax.swing.JTextField num_factura;
    private javax.swing.JTextField precio_v;
    public static javax.swing.JTextField rnc;
    private javax.swing.JTextField sub_total_general;
    private javax.swing.JTable t_articulos;
    private javax.swing.JTable t_factura;
    private javax.swing.JTextField total_general;
    // End of variables declaration//GEN-END:variables
    ConexionDB cc = new ConexionDB();
    Connection cn = cc.conectar();
    
    String total_general2="",itbis_general2="",sub_total_general2="";
    String tipo_factura="";
}
