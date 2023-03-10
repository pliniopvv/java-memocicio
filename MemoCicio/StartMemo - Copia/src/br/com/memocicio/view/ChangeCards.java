/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.view;

import br.com.memocicio.db.Card;
import br.com.memocicio.db.Conjunto;
import br.com.memocicio.db.DBFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author GanGss
 */
public class ChangeCards extends java.awt.Dialog {

    /**
     * Creates new form ChangeCards
     */
    public ChangeCards() {
        super(null, true);
        initComponents();
        listaConjuntos = DBFactory.getConjuntoController().findConjuntoEntities();
        listaCards = DBFactory.getCardsController().findCardEntities();
        buildCbs();
        setModal(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cb1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        btnTdPra1 = new javax.swing.JButton();
        btnSPara1 = new javax.swing.JButton();
        btnTdPra2 = new javax.swing.JButton();
        btnSPara2 = new javax.swing.JButton();
        btnTdPra3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cb2 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista2 = new javax.swing.JList<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb1ItemStateChanged(evt);
            }
        });
        jPanel1.add(cb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, -1));

        jScrollPane1.setViewportView(lista1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 270, 390));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTdPra1.setText("<<");
        btnTdPra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTdPra1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnTdPra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 50, 50));

        btnSPara1.setText("<");
        btnSPara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPara1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSPara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 50, 50));

        btnTdPra2.setText(">>");
        btnTdPra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTdPra2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnTdPra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 50, 50));

        btnSPara2.setText(">");
        btnSPara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPara2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSPara2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 50, 50));

        btnTdPra3.setText("V");
        btnTdPra3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTdPra3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnTdPra3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 50, 50));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cb2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb2ItemStateChanged(evt);
            }
        });
        jPanel3.add(cb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, -1));

        jScrollPane2.setViewportView(lista2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void cb1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb1ItemStateChanged
        sc1 = mapC.get(cb1.getModel().getSelectedItem());
        selectConjunto(dlm1, getCards(sc1));
    }//GEN-LAST:event_cb1ItemStateChanged

    private void cb2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb2ItemStateChanged
        sc2 = mapC.get(cb2.getModel().getSelectedItem());
        selectConjunto(dlm2, getCards(sc2));
    }//GEN-LAST:event_cb2ItemStateChanged

    private void btnTdPra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTdPra1ActionPerformed
        List<Card> l = getCards(sc2);
        for (Card c : l) {
            c.setConjuntoFk(sc1);
            listaCardsEditadas.add(c);
        }
        resetLists();
    }//GEN-LAST:event_btnTdPra1ActionPerformed

    private void btnTdPra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTdPra2ActionPerformed
        List<Card> l = getCards(sc1);
        for (Card c : l) {
            c.setConjuntoFk(sc2);
            listaCardsEditadas.add(c);
        }
        resetLists();
    }//GEN-LAST:event_btnTdPra2ActionPerformed

    private void btnSPara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPara1ActionPerformed
        List<Card> s = lista2.getSelectedValuesList();
        s.forEach(c -> {
            c.setConjuntoFk(sc1);
            listaCardsEditadas.add(c);
        });
        resetLists();
    }//GEN-LAST:event_btnSPara1ActionPerformed

    private void btnSPara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPara2ActionPerformed
        List<Card> s = lista1.getSelectedValuesList();
        s.forEach(c -> {
            c.setConjuntoFk(sc2);
            listaCardsEditadas.add(c);
        });
        resetLists();
    }//GEN-LAST:event_btnSPara2ActionPerformed

    private void btnTdPra3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTdPra3ActionPerformed
        try {
            DBFactory.getCardsController().editAll(listaCardsEditadas.stream().collect(Collectors.toList()));
            JOptionPane.showMessageDialog(null, "Cards movidos alterados permanentemente.", "SUCESSO!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTdPra3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChangeCards dialog = new ChangeCards();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSPara1;
    private javax.swing.JButton btnSPara2;
    private javax.swing.JButton btnTdPra1;
    private javax.swing.JButton btnTdPra2;
    private javax.swing.JButton btnTdPra3;
    private javax.swing.JComboBox cb1;
    private javax.swing.JComboBox cb2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Card> lista1;
    private javax.swing.JList<Card> lista2;
    // End of variables declaration//GEN-END:variables
    DefaultComboBoxModel cb1m = new DefaultComboBoxModel<>();
    DefaultComboBoxModel cb2m = new DefaultComboBoxModel<>();
    DefaultListModel<Card> dlm1 = new DefaultListModel<>();
    DefaultListModel<Card> dlm2 = new DefaultListModel<>();
    HashMap<String, Conjunto> mapC = new HashMap<>();
    Conjunto sc1;
    Conjunto sc2;
    List<Conjunto> listaConjuntos;
    List<Card> listaCards;
    Set<Card> listaCardsEditadas = new HashSet<>();

    private void buildCbs() {
        if (listaConjuntos.size() == 0) {
            semConjuntos();
            return;
        }

        mapC.clear();
        cb1m.removeAllElements();
        cb2m.removeAllElements();

        listaConjuntos.forEach(c -> {
            mapC.put(c.getNome(), c);
            cb1m.addElement(c.getNome());
            cb2m.addElement(c.getNome());
        });

        cb1.setModel(cb1m);
        cb2.setModel(cb2m);
        lista1.setModel(dlm1);
        lista2.setModel(dlm2);
        sc1 = listaConjuntos.get(0);
        sc2 = listaConjuntos.get(0);
        selectConjunto(dlm1, sc1.getCardList());
        selectConjunto(dlm2, sc2.getCardList());
    }

    private void selectConjunto(DefaultListModel lm, List<Card> conteudo) {
        lm.clear();
        lm.addAll(conteudo);
    }

    private void resetLists() {
        dlm1.clear();
        dlm2.clear();
        List<Card> c1 = listaCards.stream().filter(c -> c.getConjuntoFk().getId() == sc1.getId()).collect(Collectors.toList());
        List<Card> c2 = listaCards.stream().filter(c -> c.getConjuntoFk().getId() == sc2.getId()).collect(Collectors.toList());
        dlm1.addAll(c1);
        dlm2.addAll(c2);
    }

    private void semConjuntos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Card> getCards(Conjunto cnj) {
        return listaCards.stream().filter(c -> c.getConjuntoFk().getId() == cnj.getId()).collect(Collectors.toList());
    }
}
