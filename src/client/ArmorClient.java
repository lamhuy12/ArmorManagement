/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import armor.ArmorDTO;
import armor.ArmorInterface;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ArmorClient extends javax.swing.JFrame {

    String serviceName = "rmi://127.0.0.1:9999/ArmorInterface";
    ArmorInterface stub = null;
    Vector header = new Vector();
    //List<ArmorDTO> data = new ArrayList();
    Vector armor = new Vector();
    List<ArmorDTO> listArmor = new ArrayList<>();
    boolean addNew = false;
    int position = -1;
    DefaultTableModel model;

    public ArmorClient() {
        initComponents();
        header.add("ID");
        header.add("classification");
        header.add("TimeOfCreate");
        header.add("Defense");

        model = (DefaultTableModel) (tblArmor.getModel());
        loadData();
        txtTimeOfCreate.setEditable(false);

        txtDescription.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtStatus.requestFocus();
                    e.consume();
                }
                // compiled code
            }
        });
    }

    void loadData() {
        try {
            stub = (ArmorInterface) Naming.lookup(serviceName);
            listArmor = stub.findAllArmor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot connect to server");
            System.exit(0);
        }

        model.setRowCount(0);
        for (int i = 0; i < listArmor.size(); i++) {
            Vector v = new Vector();
            v.add(listArmor.get(i).getArmorID());
            v.add(listArmor.get(i).getClassification());
            v.add(listArmor.get(i).getTimeOfCreate());
            v.add(listArmor.get(i).getDefense());
            armor.add(v);
        }

        model.setDataVector(armor, header);
    }

    private String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    private void setField(ArmorDTO armor) {
        txtArmorID.setText(armor.getArmorID());
        txtClassfication.setText(armor.getClassification());
        txtTimeOfCreate.setText(convertDateToString(armor.getTimeOfCreate()));
        txtDefense.setText("" + armor.getDefense());
        txtDescription.setText(armor.getDescription());
        txtStatus.setText(armor.getStatus());
    }

    private void setNone() {
        txtArmorID.setText("");
        txtArmorID.setEnabled(true);
        txtClassfication.setText("");
        txtTimeOfCreate.setText("");
        txtDefense.setText("");
        txtDescription.setText("");
        txtStatus.setText("");
        position = -1;
    }

    private boolean validData() {
        //check id
        String armorID = txtArmorID.getText().trim();
        if (armorID.equals("")) {
            lbArmorID.setText("ID Cannot blank");
            return false;
        } else if (armorID.length() > 10 || armorID.length() < 0) {
            lbArmorID.setText("Length must be in range 1-10");
            return false;
        } else if (!armorID.matches("^[^@#$]*$")) {
            lbArmorID.setText("Armor ID is not contains special characters (@, #, $)");
            return false;
        } else if (armorID.contains("`")) {
            lbArmorID.setText("Cannot use character: `");
            return false;
        } else {
            lbArmorID.setText("");
            lbClassification.setText("");
            lbDefense.setText("");
            lbDescription.setText("");
            lbStatus.setText("");
        }

        //check classification
        String classification = txtClassfication.getText().trim();
        if (classification.equals("")) {
            lbClassification.setText("Classification cannot blank");
            return false;
        } else if (classification.length() < 0 || classification.length() > 30) {
            lbClassification.setText("Length must be in range 1-30");
            return false;
        } else if (classification.contains("`")) {
            lbClassification.setText("Cannot use this character: `");
            return false;
        } else {
            lbArmorID.setText("");
            lbClassification.setText("");
            lbDefense.setText("");
            lbDescription.setText("");
            lbStatus.setText("");
        }

        //check description
        String description = txtDescription.getText().trim();
        if (description.equals("")) {
            lbDescription.setText("Description cannot blank");
            return false;
        } else if (description.length() < 0 || description.length() > 300) {
            lbDescription.setText("Length must be in range 1-300");
            return false;
        } else if (description.contains("`")) {
            lbDescription.setText("Cannot use this character: `");
            return false;
        } else {
            lbArmorID.setText("");
            lbClassification.setText("");
            lbDefense.setText("");
            lbDescription.setText("");
            lbStatus.setText("");
        }

        //check defense
        try {
            int defense = Integer.parseInt(txtDefense.getText().trim());
        } catch (NumberFormatException e) {
            lbDefense.setText("Must be integer number and cannot not blank");
            return false;
        }

        if (Integer.parseInt(txtDefense.getText().trim()) < 0) {
            lbDefense.setText("Must be >0");
            return false;
        } else {
            lbArmorID.setText("");
            lbClassification.setText("");
            lbDefense.setText("");
            lbDescription.setText("");
            lbStatus.setText("");
        }

        //check status
        String status = txtStatus.getText().trim();
        if (status.equals("")) {
            lbStatus.setText("Status cannot blank");
            return false;
        } else if (status.contains("`")) {
            lbStatus.setText("Cannot use this character: `");
            return false;
        } else {
            lbArmorID.setText("");
            lbClassification.setText("");
            lbDefense.setText("");
            lbDescription.setText("");
            lbStatus.setText("");
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblArmor = new javax.swing.JTable();
        lblArmorClient = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblEmpID = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        txtArmorID = new javax.swing.JTextField();
        txtClassfication = new javax.swing.JTextField();
        txtTimeOfCreate = new javax.swing.JTextField();
        txtDefense = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        txtFindByID = new javax.swing.JButton();
        scrDescription = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnNew = new javax.swing.JButton();
        lbArmorID = new javax.swing.JLabel();
        lbClassification = new javax.swing.JLabel();
        lbDefense = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        btnGetAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblArmor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArmor.getTableHeader().setReorderingAllowed(false);
        tblArmor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArmorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblArmor);

        lblArmorClient.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblArmorClient.setForeground(new java.awt.Color(0, 0, 255));
        lblArmorClient.setText("Armor Client");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Armor's Detail"));
        jPanel1.setToolTipText("");
        jPanel1.setFocusable(false);
        jPanel1.setName(""); // NOI18N

        lblEmpID.setText("ArmorID:");

        lblFullname.setText("Classification:");

        lblPhone.setText("TimeOfCreate:");

        lblEmail.setText("Defense:");

        lblAddress.setText("Description:");

        lblDOB.setText("Status:");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        txtFindByID.setText("Find By ArmorID ");
        txtFindByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindByIDActionPerformed(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        scrDescription.setViewportView(txtDescription);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        lbArmorID.setForeground(new java.awt.Color(255, 0, 51));

        lbClassification.setForeground(new java.awt.Color(255, 0, 0));

        lbDefense.setForeground(new java.awt.Color(255, 0, 0));

        lbDescription.setForeground(new java.awt.Color(255, 0, 0));

        lbStatus.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFullname)
                            .addComponent(lblEmpID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbClassification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtClassfication, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(txtArmorID)
                            .addComponent(lbArmorID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDOB)
                            .addComponent(lblAddress)
                            .addComponent(lblPhone)
                            .addComponent(lblEmail)
                            .addComponent(btnNew))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(lbDefense, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCreate)
                                .addGap(46, 46, 46)
                                .addComponent(btnUpdate))
                            .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtDefense)
                            .addComponent(txtStatus)
                            .addComponent(scrDescription)
                            .addComponent(lbDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFindByID)
                    .addComponent(btnRemove)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpID)
                    .addComponent(txtArmorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFindByID))
                .addGap(2, 2, 2)
                .addComponent(lbArmorID, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFullname)
                    .addComponent(txtClassfication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lbClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addGap(4, 4, 4)
                .addComponent(lbDefense, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress)
                    .addComponent(scrDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDOB)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove)
                    .addComponent(btnNew))
                .addGap(25, 25, 25))
        );

        btnGetAll.setText("Get all");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(lblArmorClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(504, 504, 504))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btnGetAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblArmorClient)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGetAll)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblArmorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseClicked
        try {
            stub = (ArmorInterface) Naming.lookup(serviceName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server disconnected");
            System.exit(0);
        }

        this.addNew = false;
        position = tblArmor.getSelectedRow();
        txtArmorID.setEnabled(false);
        txtTimeOfCreate.setEditable(false);
        txtTimeOfCreate.setEnabled(true);
        String armorID = (String) tblArmor.getValueAt(position, 0);
        try {
            ArmorDTO armor = stub.findByArmorID(armorID);
            setField(armor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblArmorMouseClicked

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        try {
            stub = (ArmorInterface) Naming.lookup(serviceName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server disconnected");
            System.exit(0);
        }

        if (!validData()) {
            return;
        }

        String armorID = txtArmorID.getText().trim();
        String classification = txtClassfication.getText().trim();
        Date date = new Date();
        txtTimeOfCreate.setEditable(false);
        String description = txtDescription.getText().trim();
        String status = txtStatus.getText().trim();
        int defense = Integer.parseInt(txtDefense.getText().trim());
        ArmorDTO armor = new ArmorDTO(armorID, classification, description, status, date, defense);

        this.addNew = true;

        try {
            if (stub.findByArmorID(armorID) != null) {
                JOptionPane.showMessageDialog(this, "Duplicated ID");
                return;
            } else {
                addNew = stub.createArmor(armor);
                System.out.println(armor.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (addNew) {
            JOptionPane.showMessageDialog(this, "Create succesful");
            loadData();
            setNone();
            addNew = false;
        } else {
            JOptionPane.showMessageDialog(this, "Armor already existed");
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (position != -1) {
            try {
                stub = (ArmorInterface) Naming.lookup(serviceName);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Server disconnected");
                System.exit(0);
            }

            if (!validData()) {
                return;
            }

            String armorID = txtArmorID.getText().trim();
            String classfication = txtClassfication.getText().trim();
            Date date = new Date();
            int defense = Integer.parseInt(txtDefense.getText().trim());
            String description = txtDescription.getText().trim();
            String status = txtStatus.getText().trim();

            ArmorDTO armor = new ArmorDTO(armorID, classfication, description, status, date, defense);
            boolean isUpdated = false;
            try {
                isUpdated = stub.updateArmor(armor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Update successful");
                setNone();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Udpate fail");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Choose armor to update");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if (position != -1) {
            try {
                stub = (ArmorInterface) Naming.lookup(serviceName);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Server disconnected");
                System.exit(0);
            }

            String armorID = txtArmorID.getText();
            boolean isRemoved = false;
            if (JOptionPane.showConfirmDialog(this, "Do you want to remove?")
                    == JOptionPane.OK_OPTION) {

                try {
                    isRemoved = stub.removeArmor(armorID);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isRemoved) {
                    JOptionPane.showMessageDialog(this, "Remove successful");
                    setNone();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Remove fail");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Choose armor to remove");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        try {
            stub = (ArmorInterface) Naming.lookup(serviceName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "server disconnect");
            System.exit(0);
        }

        this.addNew = true;
        this.txtArmorID.setText("");
        this.txtArmorID.setEnabled(true);
        this.txtArmorID.requestFocus();
        this.txtClassfication.setText("");
        this.txtDefense.setText("");
        this.txtDescription.setText("");
        this.txtStatus.setText("");
        this.txtTimeOfCreate.setText("");
        this.txtTimeOfCreate.setEnabled(false);

        lbArmorID.setText("");
        lbClassification.setText("");
        lbDefense.setText("");
        lbDescription.setText("");
        lbStatus.setText("");
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtFindByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindByIDActionPerformed
        // TODO add your handling code here:
        try {
            stub = (ArmorInterface) Naming.lookup(serviceName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server disconnected");
            System.exit(0);
        }

        String armorID = txtArmorID.getText().trim();
        txtTimeOfCreate.setEnabled(true);
        txtTimeOfCreate.setEditable(false);
        ArmorDTO armor = null;
        
        try {
            armor = stub.findByArmorID(armorID);
            for (int i = 0; i < listArmor.size(); i++) {
                // System.out.println(listArmor.get(i).getArmorID());
                if (armorID.equals(listArmor.get(i).getArmorID())) {
                    tblArmor.setRowSelectionInterval(i, i);
                    tblArmor.updateUI();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (armor != null) {
            setField(armor);
        } else {
            JOptionPane.showMessageDialog(this, "Armor ID not founded");
        }
    }//GEN-LAST:event_txtFindByIDActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit? ", "exit", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            return;
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArmorClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbArmorID;
    private javax.swing.JLabel lbClassification;
    private javax.swing.JLabel lbDefense;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblArmorClient;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmpID;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JScrollPane scrDescription;
    private javax.swing.JTable tblArmor;
    private javax.swing.JTextField txtArmorID;
    private javax.swing.JTextField txtClassfication;
    private javax.swing.JTextField txtDefense;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JButton txtFindByID;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTimeOfCreate;
    // End of variables declaration//GEN-END:variables
}
