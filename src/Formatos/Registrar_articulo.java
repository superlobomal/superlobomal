package Formatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Poo2.ConexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Registrar_articulo extends javax.swing.JFrame {

    public Registrar_articulo() {
        initComponents();
        //cargar();
    }

    void borrar_datos() {
        try {
            PreparedStatement psU = cn.prepareStatement("DELETE FROM articulo WHERE id_art='" + id_articulo.getText() + "'");
            psU.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void actualizar_datos() {
        
        String itbistring = String.valueOf(itbis.getSelectedItem());
        float itbisit = Float.valueOf(itbistring);
        

        String conteo = String.valueOf(tipo_de_conteo.getSelectedItem());
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE articulo SET des_art='"+des_art.getText()+"',codigo='"+codigo.getText() + "',amount_art='" + stock.getText() + "',price_buy='" + precio_compra.getText() + "',price_sell='" + precio_venta.getText() + "',reference='" + referencia.getText() + "',reorder='" + min_stock.getText() + "',container='" +conteo+"',itbis='" +itbisit+"'"
                    + "WHERE id_art='" + id_articulo.getText() + "'");
            psU.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void cargar_filtro(String valor) {
        DefaultTableModel model2 = (DefaultTableModel) t_art.getModel();
        model2.getDataVector().clear();

        String[] registros = new String[10];
        String sql = "SELECT id_art,codigo,des_art,amount_art,price_buy,price_sell,itbis,container,reference,reorder FROM articulo where CONCAT (des_art)LIKE '%" + valor + "%' limit 100";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("id_art");
                registros[1] = rs.getString("des_art");
                registros[2] = rs.getString("codigo");
                registros[3] = rs.getString("amount_art");
                registros[4] = rs.getString("price_buy");
                registros[5] = rs.getString("price_sell");
                registros[6] = rs.getString("itbis");
                registros[7] = rs.getString("container");
                registros[8] = rs.getString("reference");
                registros[9] = rs.getString("reorder");

                model2.addRow(registros);
            }

            t_art.setModel(model2);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void cargar() {
        DefaultTableModel model2 = (DefaultTableModel) t_art.getModel();
        model2.getDataVector().clear();

        String[] registros = new String[10];
        String sql = "SELECT id_art,codigo,des_art,amount_art,price_buy,price_sell,itbis,container,reference,reorder FROM articulo order by des_art asc limit 5";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("id_art");
                registros[1] = rs.getString("codigo");
                registros[2] = rs.getString("des_art");
                registros[3] = rs.getString("amount_art");
                registros[4] = rs.getString("price_buy");
                registros[5] = rs.getString("price_sell");
                registros[6] = rs.getString("itbis");
                registros[7] = rs.getString("container");
                registros[8] = rs.getString("reference");
                registros[9] = rs.getString("reorder");

                model2.addRow(registros);
            }

            t_art.setModel(model2);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void limpiar() {
        id_articulo.setText("");
        des_art.setText("");
        codigo.setText("");
        stock.setText("");
        precio_compra.setText("");
        precio_venta.setText("");
        referencia.setText("");
        min_stock.setText("");
        itbis.setSelectedIndex(0);
        tipo_de_conteo.setSelectedIndex(0);
        id_articulo.requestFocus(true);
    }

    void cargar_datos() {
        String[] registros = new String[9];
        String sql = "SELECT des_art,codigo,amount_art,price_buy,price_sell,itbis,container,reference,reorder FROM articulo WHERE id_art = '" + id_articulo.getText() + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
               
                registros[0] = rs.getString("codigo");
                registros[1] = rs.getString("des_art");
                registros[2] = rs.getString("amount_art");
                registros[3] = rs.getString("price_buy");
                registros[4] = rs.getString("price_sell");
                registros[5] = rs.getString("itbis");
                registros[6] = rs.getString("container");
                registros[7] = rs.getString("reference");
                registros[8] = rs.getString("reorder");  
              
               
            }
            
            
            
                 
            codigo.setText(registros[0]);
            des_art.setText(registros[1]);
            stock.setText(registros[2]);
            precio_compra.setText(registros[3]);
            precio_venta.setText(registros[4]);  
            
            if (registros[5].equals("0.0")) {
            itbis.setSelectedIndex(1);
            }
            if (registros[5].equals("2.0")) {
            itbis.setSelectedIndex(2);
            }
            if (registros[5].equals("8.0")) {
            itbis.setSelectedIndex(3);
            }
            if (registros[5].equals("9.0")) {
            itbis.setSelectedIndex(4);
            }
            if (registros[5].equals("16.0")) {
            itbis.setSelectedIndex(5);
            }
            if (registros[5].equals("18.0")) {
            itbis.setSelectedIndex(6);
            }
            if (registros[5].equals("28.0")) {
            itbis.setSelectedIndex(7);
            }
            
            tipo_de_conteo.setSelectedItem(registros[6]);
            referencia.setText(registros[7]);
            min_stock.setText(registros[8]);
            
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void guardar() {
        
        String itbist=String.valueOf(itbis.getSelectedItem().toString()); 
        float itbifl=Float.valueOf(itbist);      
        
        String conteo = String.valueOf(tipo_de_conteo.getSelectedItem());

        try {
            String sqlguardar = "";
            sqlguardar = "INSERT INTO articulo  VALUES (null,'" + codigo.getText() + "','" + des_art.getText() + "','" + stock.getText() + "',"
                    + "'" + precio_compra.getText() + "','" + precio_venta.getText() + "','"+itbifl+"','"+conteo+"',"
                    + "'" + referencia.getText() + "','" + min_stock.getText()+"')";

            PreparedStatement sql = cn.prepareStatement(sqlguardar);

            int x;
            x = sql.executeUpdate();
            if (x > 0) {

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

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
        id_articulo = new javax.swing.JTextField();
        des_art = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        precio_compra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        precio_venta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_art = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buscar_nombre = new javax.swing.JTextField();
        mod_datos = new javax.swing.JButton();
        eliminar_datos = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tipo_de_conteo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        referencia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        min_stock = new javax.swing.JTextField();
        itbis = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO DE ARTICULOS");
        setModalExclusionType(null);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO ARTICULO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 0))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID ARTICULO");

        id_articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_articuloActionPerformed(evt);
            }
        });
        id_articulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_articuloKeyReleased(evt);
            }
        });

        des_art.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                des_artActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 255));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("DES. DE ARTICULO");

        codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CODIGO");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("STOCK");

        precio_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precio_compraActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 51, 255));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("PRECIO DE COMPRA");

        precio_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precio_ventaActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 51, 255));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("PRECIO DE VENTA");

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

        t_art.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ARTICULO", "DES. DE ARTCULO", "CODIGO", "CANT.ARTK.STK", "PRECIO DE COMPRA", "PRECIO DE VENTA", "ITBIS", "TIPO DE CONTEO", "REFERENCIA", "CANTIDAD MIN EN STK"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_art.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(t_art);
        if (t_art.getColumnModel().getColumnCount() > 0) {
            t_art.getColumnModel().getColumn(0).setMinWidth(80);
            t_art.getColumnModel().getColumn(0).setMaxWidth(80);
            t_art.getColumnModel().getColumn(1).setMinWidth(120);
            t_art.getColumnModel().getColumn(1).setMaxWidth(120);
            t_art.getColumnModel().getColumn(2).setMinWidth(60);
            t_art.getColumnModel().getColumn(2).setMaxWidth(60);
            t_art.getColumnModel().getColumn(3).setMinWidth(110);
            t_art.getColumnModel().getColumn(3).setMaxWidth(110);
            t_art.getColumnModel().getColumn(4).setMinWidth(140);
            t_art.getColumnModel().getColumn(4).setMaxWidth(140);
            t_art.getColumnModel().getColumn(5).setMinWidth(140);
            t_art.getColumnModel().getColumn(5).setMaxWidth(140);
            t_art.getColumnModel().getColumn(6).setMaxWidth(50);
            t_art.getColumnModel().getColumn(7).setMinWidth(110);
            t_art.getColumnModel().getColumn(7).setMaxWidth(110);
            t_art.getColumnModel().getColumn(8).setMinWidth(90);
            t_art.getColumnModel().getColumn(8).setMaxWidth(90);
            t_art.getColumnModel().getColumn(9).setMinWidth(150);
            t_art.getColumnModel().getColumn(9).setMaxWidth(150);
        }

        jLabel7.setBackground(new java.awt.Color(51, 51, 255));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
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

        jLabel8.setBackground(new java.awt.Color(51, 51, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("ITBIS");

        jLabel9.setBackground(new java.awt.Color(51, 51, 255));
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("TIPO DE CONTEO ");

        tipo_de_conteo.setForeground(new java.awt.Color(0, 0, 0));
        tipo_de_conteo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione tipo de conteo", "UNIDAD", "LIBRA", "CAJA", "FARDO" }));
        tipo_de_conteo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_de_conteoActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(51, 51, 255));
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("REFERENCIA");

        jLabel11.setBackground(new java.awt.Color(51, 51, 255));
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("CANTIDAD MIN STK");

        min_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                min_stockActionPerformed(evt);
            }
        });

        itbis.setForeground(new java.awt.Color(0, 0, 0));
        itbis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione una tasa ITBIS", "0", "2", "8", "9", "16", "18", "28" }));
        itbis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itbisActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(id_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(des_art, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(precio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipo_de_conteo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itbis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(precio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(referencia, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(min_stock, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mod_datos)
                        .addGap(18, 18, 18)
                        .addComponent(eliminar_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 152, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(id_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(des_art, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(referencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(precio_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(min_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(precio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11)
                        .addComponent(itbis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tipo_de_conteo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eliminar_datos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mod_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void des_artActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_des_artActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_des_artActionPerformed

    private void id_articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_articuloActionPerformed
        cargar_filtro(buscar_nombre.getText());
        cargar_datos();
    }//GEN-LAST:event_id_articuloActionPerformed

    private void precio_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precio_compraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precio_compraActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        if (des_art.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO DES.DE ARTICULO NO PUEDE ESTAR EN BLANCO");
            des_art.requestFocus(true);
            return;
        }
        if (codigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CODIGO NO PUEDE ESTAR EN BLANCO");
            codigo.requestFocus(true);
            return;
        }
        if (stock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO STOCK NO PUEDE ESTAR EN BLANCO");
            stock.requestFocus(true);
            return;
        }
        if (precio_compra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO DE COMPRA NO PUEDE ESTAR EN BLANCO");
            precio_compra.requestFocus(true);
            return;
        }
        if (precio_venta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO DE VENTA NO PUDE ESTAR EN BLANCO");
            precio_venta.requestFocus(true);
            return;
        }
        if (itbis.getSelectedItem().equals("Selecione una tasa ITBIS")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECIONAR UNA OPCION EN ITBIS");
            itbis.requestFocus(true);
            return;
        }
        if (tipo_de_conteo.getSelectedItem().equals("Selecione tipo de conteo")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECIONAR UNA OPCION EN TIPO DE CONTEO");
            tipo_de_conteo.requestFocus(true);
            return;
        }
        if (referencia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO REFERENCIA NO PUEDE ESTAR BACIO");
            referencia.requestFocus(true);
            return;
        }
        if (min_stock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CANTIDAD MIN STK NO PUDE ESTAR BACIA");
            min_stock.requestFocus(true);
            return;
        }
        guardar();
        limpiar();
        cargar_datos();
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
        if (des_art.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO DES.DE ARTICULO NO PUEDE ESTAR EN BLANCO");
            des_art.requestFocus(true);
            return;
        }
        if (codigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CODIGO NO PUEDE ESTAR EN BLANCO");
            codigo.requestFocus(true);
            return;
        }
        if (stock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO STOCK NO PUEDE ESTAR EN BLANCO");
            stock.requestFocus(true);
            return;
        }
        if (precio_compra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO DE COMPRA NO PUEDE ESTAR EN BLANCO");
            precio_compra.requestFocus(true);
            return;
        }
        if (precio_venta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO DE VENTA NO PUDE ESTAR EN BLANCO");
            precio_venta.requestFocus(true);
            return;
        }
        if (itbis.getSelectedItem().equals("Selecione una tasa ITBIS")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECIONAR UNA OPCION EN ITBIS");
            itbis.requestFocus(true);
            return;
        }
        if (tipo_de_conteo.getSelectedItem().equals("Selecione tipo de conteo")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECIONAR UNA OPCION EN TIPO DE CONTEO");
            tipo_de_conteo.requestFocus(true);
            return;
        }
        if (referencia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO REFERENCIA NO PUEDE ESTAR BACIO");
            referencia.requestFocus(true);
            return;
        }
        if (min_stock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CANTIDAD MIN STK NO PUDE ESTAR BACIA");
            min_stock.requestFocus(true);
            return;
        }

        actualizar_datos();        
        cargar();
    }//GEN-LAST:event_mod_datosActionPerformed

    private void eliminar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_datosActionPerformed
        if (des_art.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO DES.DE ARTICULO NO PUEDE ESTAR EN BLANCO");
            des_art.requestFocus(true);
            return;
        }
        if (codigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CODIGO NO PUEDE ESTAR EN BLANCO");
            codigo.requestFocus(true);
            return;
        }
        if (stock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO STOCK NO PUEDE ESTAR EN BLANCO");
            stock.requestFocus(true);
            return;
        }
        if (precio_compra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO DE COMPRA NO PUEDE ESTAR EN BLANCO");
            precio_compra.requestFocus(true);
            return;
        }
        if (precio_venta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO DE VENTA NO PUDE ESTAR EN BLANCO");
            precio_venta.requestFocus(true);
            return;
        }
        if (itbis.getSelectedItem().equals("Selecione una tasa ITBIS")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECIONAR UNA OPCION EN ITBIS");
            itbis.requestFocus(true);
            return;
        }
        if (tipo_de_conteo.getSelectedItem().equals("Selecione tipo de conteo")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECIONAR UNA OPCION EN TIPO DE CONTEO");
            tipo_de_conteo.requestFocus(true);
            return;
        }
        if (referencia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO REFERENCIA NO PUEDE ESTAR BACIO");
            referencia.requestFocus(true);
            return;
        }
        if (min_stock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CANTIDAD MIN STK NO PUDE ESTAR BACIA");
            min_stock.requestFocus(true);
            return;
        }
        borrar_datos();
        limpiar();
        cargar();

    }//GEN-LAST:event_eliminar_datosActionPerformed

    private void buscar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_nombreActionPerformed

    private void id_articuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_articuloKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_id_articuloKeyReleased

    private void tipo_de_conteoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_de_conteoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_de_conteoActionPerformed

    private void min_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_min_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_min_stockActionPerformed

    private void itbisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itbisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itbisActionPerformed

    private void precio_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precio_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precio_ventaActionPerformed

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_articulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_nombre;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField des_art;
    private javax.swing.JButton eliminar_datos;
    private javax.swing.JTextField id_articulo;
    private javax.swing.JComboBox<String> itbis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField min_stock;
    private javax.swing.JButton mod_datos;
    private javax.swing.JTextField precio_compra;
    private javax.swing.JTextField precio_venta;
    private javax.swing.JTextField referencia;
    private javax.swing.JButton save;
    private javax.swing.JTextField stock;
    private javax.swing.JTable t_art;
    private javax.swing.JComboBox<String> tipo_de_conteo;
    // End of variables declaration//GEN-END:variables

    ConexionDB cc = new ConexionDB();
    Connection cn = cc.conectar();

    
}
