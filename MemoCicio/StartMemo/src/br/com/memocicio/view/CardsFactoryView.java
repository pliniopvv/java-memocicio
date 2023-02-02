/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.view;

import br.com.memocicio.db.Card;
import br.com.memocicio.db.Conjunto;
import br.com.memocicio.db.DBFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author GanGss
 */
public class CardsFactoryView extends javax.swing.JFrame {

    /**
     * Creates new form CardsFactoryView
     */
    public CardsFactoryView() {
        initComponents();
        resetCombobox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        cmbbox_categorias = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        card_frente = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        card_tras = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tras_lin = new javax.swing.JLabel();
        frente_lin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jButton1.setText("Próximo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmbbox_categorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbbox_categorias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbbox_categoriasFocusLost(evt);
            }
        });

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        card_frente.setColumns(20);
        card_frente.setLineWrap(true);
        card_frente.setRows(5);
        card_frente.setWrapStyleWord(true);
        card_frente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                card_frenteFocusLost(evt);
            }
        });
        card_frente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                card_frenteKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(card_frente);

        jLabel1.setText("Frente");

        jLabel2.setText("Tras");

        card_tras.setColumns(20);
        card_tras.setLineWrap(true);
        card_tras.setRows(5);
        card_tras.setWrapStyleWord(true);
        card_tras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                card_trasFocusLost(evt);
            }
        });
        card_tras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                card_trasKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(card_tras);

        jButton3.setText("Fechar e Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("E");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Fechar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tras_lin.setText("0/1000");

        frente_lin.setText("0/1000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbbox_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(frente_lin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tras_lin)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(7, 7, 7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbbox_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(frente_lin))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tras_lin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nome = JOptionPane.showInputDialog(this, "Digite o nome da categoria.", "Adicionar Categoria.", JOptionPane.QUESTION_MESSAGE);
        if (nome == null) {
            return;
        }

        Conjunto c = new Conjunto();
        c.setNome(nome);
        try {
            DBFactory.getConjuntoController().create(c);
            resetCombobox();
            selectedConjunto = c;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buildCard();
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buildCard();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbbox_categoriasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbbox_categoriasFocusLost
        if (listaConjuntos.size() == 0) {
            return;
        }
        selectedConjunto = listaConjuntos.get(cmbbox_categorias.getModel().getSelectedItem());
    }//GEN-LAST:event_cmbbox_categoriasFocusLost

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        resetCombobox();
    }//GEN-LAST:event_formFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String nome = JOptionPane.showInputDialog(this, "Digite o novo nome do Conjunto:", "Editar Nome", JOptionPane.QUESTION_MESSAGE);
        if (nome.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nome curto/nulo. \nAção cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        selectedConjunto.setNome(nome);
        resetCombobox();
        try {
            DBFactory.getConjuntoController().edit(selectedConjunto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void card_frenteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_card_frenteFocusLost
    }//GEN-LAST:event_card_frenteFocusLost

    private void card_trasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_card_trasFocusLost
    }//GEN-LAST:event_card_trasFocusLost

    private void card_frenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_card_frenteKeyReleased
        frente_lin.setText(card_frente.getText().length() + "/1000");
    }//GEN-LAST:event_card_frenteKeyReleased

    private void card_trasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_card_trasKeyReleased
        tras_lin.setText(card_tras.getText().length() + "/1000");
    }//GEN-LAST:event_card_trasKeyReleased

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
            java.util.logging.Logger.getLogger(CardsFactoryView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardsFactoryView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardsFactoryView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardsFactoryView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CardsFactoryView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea card_frente;
    private javax.swing.JTextArea card_tras;
    private javax.swing.JComboBox<String> cmbbox_categorias;
    private javax.swing.JLabel frente_lin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel tras_lin;
    // End of variables declaration//GEN-END:variables
    private HashMap<String, Conjunto> listaConjuntos = new HashMap<>();
    private Conjunto selectedConjunto;

    private void resetCombobox() {
        List<Conjunto> conjunto = DBFactory.getConjuntoController().findConjuntoEntities();

        if (conjunto.size() == 0) {
            return;
        }

        listaConjuntos.clear();
        conjunto.forEach(_c -> {
            listaConjuntos.put(_c.getNome(), _c);
        });
        String[] categorias = listaConjuntos.keySet().toArray(new String[listaConjuntos.size()]);
        cmbbox_categorias.setModel(new DefaultComboBoxModel(categorias));
        if (selectedConjunto == null) {
            selectedConjunto = listaConjuntos.get(categorias[0]);
        } else {
            cmbbox_categorias.getModel().setSelectedItem(selectedConjunto.getNome());
        }
    }

    private void buildCard() {
        String frente = card_frente.getText();
        String verso = card_tras.getText();
        Card c = new Card();
        c.setFrente(frente);
        c.setTras(verso);
        c.setConjuntoFk(selectedConjunto);
        c.setCreateAt(new Date());

        try {
            DBFactory.getCardsController().create(c);
            card_frente.setText("");
            card_tras.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}