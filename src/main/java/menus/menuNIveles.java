package menus;


import ProyectoFinal.Nivel1;
import ProyectoFinal.Nivel2;
import ProyectoFinal.Nivel3;
import ProyectoFinal.Score;
import ProyectoFinal.Sqr;
import menus.Menu_1;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author César Reyes Torres
 */
public class menuNIveles extends javax.swing.JFrame {
    public Menu_1 daddy;
    private boolean diplvl1;
    private boolean diplvl2;
    private boolean diplvl3;
    private boolean diplvl4;
    private boolean diplvl5;
    private boolean diplvl6;
    
    /**
     * Creates new form menuNIveles
     */
    public menuNIveles(JFrame daddy) {
        this.daddy = (Menu_1)daddy;
        initComponents();
        transparent();
        readScoreFile();
    }
    
    public menuNIveles() {
        this.daddy = (Menu_1)daddy;
        initComponents();
        transparent();
        readScoreFile();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        star1lvl1 = new javax.swing.JLabel();
        star2lvl1 = new javax.swing.JLabel();
        star3lvl1 = new javax.swing.JLabel();
        star1lvl2 = new javax.swing.JLabel();
        star2lvl2 = new javax.swing.JLabel();
        star3lvl2 = new javax.swing.JLabel();
        star1lvl3 = new javax.swing.JLabel();
        star2lvl3 = new javax.swing.JLabel();
        star3lvl3 = new javax.swing.JLabel();
        star1lvl4 = new javax.swing.JLabel();
        star2lvl4 = new javax.swing.JLabel();
        star3lvl4 = new javax.swing.JLabel();
        star1lvl5 = new javax.swing.JLabel();
        star2lvl5 = new javax.swing.JLabel();
        star3lvl5 = new javax.swing.JLabel();
        star1lvl6 = new javax.swing.JLabel();
        star2lvl6 = new javax.swing.JLabel();
        star3lvl6 = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        lvl1button = new javax.swing.JButton();
        lvl2button = new javax.swing.JButton();
        lvl3button = new javax.swing.JButton();
        lvl4button = new javax.swing.JButton();
        lvl5button = new javax.swing.JButton();
        lvl6button = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Angry Birds - Menu");
        setResizable(false);

        panelPrincipal.setPreferredSize(new java.awt.Dimension(1024, 631));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        star1lvl1.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star1lvl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 40, 40));

        star2lvl1.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star2lvl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));

        star3lvl1.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star3lvl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        star1lvl2.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star1lvl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        star2lvl2.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star2lvl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        star3lvl2.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star3lvl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, -1, -1));

        star1lvl3.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star1lvl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, -1, -1));

        star2lvl3.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star2lvl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, -1, -1));

        star3lvl3.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star3lvl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, -1, -1));

        star1lvl4.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star1lvl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, 40, 40));

        star2lvl4.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star2lvl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, -1, -1));

        star3lvl4.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star3lvl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        star1lvl5.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star1lvl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, -1, -1));

        star2lvl5.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star2lvl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, -1, -1));

        star3lvl5.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star3lvl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, -1, -1));

        star1lvl6.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star1lvl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, -1, -1));

        star2lvl6.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star2lvl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 490, -1, -1));

        star3lvl6.setIcon(new javax.swing.ImageIcon("StarLevel.png"));
        panelPrincipal.add(star3lvl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 490, -1, -1));

        returnButton.setIcon(new javax.swing.ImageIcon("Return-Button.png"));
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });
        panelPrincipal.add(returnButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 535, 100, 90));

        lvl1button.setIcon(new javax.swing.ImageIcon("levelBlock-Front.png"));
        lvl1button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvl1buttonActionPerformed(evt);
            }
        });
        panelPrincipal.add(lvl1button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, 220));

        lvl2button.setIcon(new javax.swing.ImageIcon("levelBlock-Front.png"));
        lvl2button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvl2buttonActionPerformed(evt);
            }
        });
        panelPrincipal.add(lvl2button, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 160, 220));

        lvl3button.setIcon(new javax.swing.ImageIcon("levelBlock-Front.png"));
        lvl3button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvl3buttonActionPerformed(evt);
            }
        });
        panelPrincipal.add(lvl3button, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 160, 220));

        lvl4button.setIcon(new javax.swing.ImageIcon("levelBlock-Front.png"));
        lvl4button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvl4buttonActionPerformed(evt);
            }
        });
        panelPrincipal.add(lvl4button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 160, 220));

        lvl5button.setIcon(new javax.swing.ImageIcon("levelBlock-Front.png"));
        lvl5button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvl5buttonActionPerformed(evt);
            }
        });
        panelPrincipal.add(lvl5button, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 160, 220));

        lvl6button.setIcon(new javax.swing.ImageIcon("levelBlock-Front.png"));
        lvl6button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvl6buttonActionPerformed(evt);
            }
        });
        panelPrincipal.add(lvl6button, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 320, 160, 220));

        background.setIcon(new javax.swing.ImageIcon("Menu-Background.jpg"));
        panelPrincipal.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lvl2buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl2buttonActionPerformed
        // TODO add your handling code here:
        if(diplvl2 == true){
            daddy.clip.stop();
            
            Nivel2 lvl2 = new Nivel2();
            lvl2.setVisible(true);
            lvl2.run();
            dispose();
        }
    }//GEN-LAST:event_lvl2buttonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        // TODO add your handling code here:
        daddy.clip.stop();
        Menu_1 cF = new Menu_1();
        cF.setVisible(true);
        dispose();
    }//GEN-LAST:event_returnButtonActionPerformed

    private void lvl1buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl1buttonActionPerformed
        // TODO add your handling code here:
        if(diplvl1 == true){
            daddy.clip.stop();
            Nivel1 lvl1 = new Nivel1(this);
            lvl1.setVisible(true);
            lvl1.run();
            dispose();
        }
    }//GEN-LAST:event_lvl1buttonActionPerformed

    private void lvl3buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl3buttonActionPerformed
        // TODO add your handling code here:
        if(diplvl3 == true){
            daddy.clip.stop();
            
            Nivel3 lvl3 = new Nivel3();
            lvl3.setVisible(true);
            lvl3.run();
            dispose();
        }
    }//GEN-LAST:event_lvl3buttonActionPerformed

    private void lvl4buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl4buttonActionPerformed
        // TODO add your handling code here:
        if(diplvl4 == true){
            daddy.clip.stop();
            /*
            Nivel4 lvl4 = new Nivel4(this);
            lvl4.setVisible(true);
            lvl4.run();*/
            dispose();
        }
    }//GEN-LAST:event_lvl4buttonActionPerformed

    private void lvl5buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl5buttonActionPerformed
        // TODO add your handling code here:
        if(diplvl5 == true){
            daddy.clip.stop();
            /*
            Nivel4 lvl4 = new Nivel5(this);
            lvl5.setVisible(true);
            lvl5.run();*/
            dispose();
        }
    }//GEN-LAST:event_lvl5buttonActionPerformed

    private void lvl6buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl6buttonActionPerformed
        // TODO add your handling code here:
        if(diplvl6 == true){
            daddy.clip.stop();
            /*
            Nivel6 lvl6 = new Nivel6(this);
            lvl6.setVisible(true);
            lvl6.run();*/
            dispose();
        }
    }//GEN-LAST:event_lvl6buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *//*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuNIveles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuNIveles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuNIveles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuNIveles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        
        //new menuNIveles(this).setVisible(true);

        //Create and display the form 
        //java.awt.EventQueue.invokeLater(() -> {
        //    new menuNIveles().setVisible(true);
        //});
    }

    //FUNCIONES ---
    public void transparent(){
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        lvl1button.setOpaque(false);
        lvl1button.setContentAreaFilled(false);
        lvl1button.setBorderPainted(false);
        lvl2button.setOpaque(false);
        lvl2button.setContentAreaFilled(false);
        lvl2button.setBorderPainted(false);
        lvl3button.setOpaque(false);
        lvl3button.setContentAreaFilled(false);
        lvl3button.setBorderPainted(false);
        lvl4button.setOpaque(false);
        lvl4button.setContentAreaFilled(false);
        lvl4button.setBorderPainted(false);
        lvl5button.setOpaque(false);
        lvl5button.setContentAreaFilled(false);
        lvl5button.setBorderPainted(false);
        lvl6button.setOpaque(false);
        lvl6button.setContentAreaFilled(false);
        lvl6button.setBorderPainted(false);
    }
    
    public void readScoreFile(){
        FileInputStream archFIS;
        ObjectInputStream is;
        Score reference;
        
        try{
           archFIS = new FileInputStream("Score.angryBirds");
           is = new ObjectInputStream(archFIS);
           
           reference = (Score)is.readObject();
           //System.out.println(reference.levels.get(0).available);
            
           diplvl1 = arrangeLevel(lvl1button, star1lvl1, star2lvl1, star3lvl1, reference.levels.get(0).available, reference.levels.get(0).completed, reference.levels.get(0).stars, "level1-Front.png");
           diplvl2 = arrangeLevel(lvl2button, star1lvl2, star2lvl2, star3lvl2, reference.levels.get(1).available, reference.levels.get(1).completed, reference.levels.get(1).stars, "level2-Front.png");
           diplvl3 = arrangeLevel(lvl3button, star1lvl3, star2lvl3, star3lvl3, reference.levels.get(2).available, reference.levels.get(2).completed, reference.levels.get(2).stars, "level3-Front.png");
           diplvl4 = arrangeLevel(lvl4button, star1lvl4, star2lvl4, star3lvl4, reference.levels.get(3).available, reference.levels.get(3).completed, reference.levels.get(3).stars, "level4-Front.png");
           diplvl5 = arrangeLevel(lvl5button, star1lvl5, star2lvl5, star3lvl5, reference.levels.get(4).available, reference.levels.get(4).completed, reference.levels.get(4).stars, "level5-Front.png");
           diplvl6 = arrangeLevel(lvl6button, star1lvl6, star2lvl6, star3lvl6, reference.levels.get(5).available, reference.levels.get(5).completed, reference.levels.get(5).stars, "level6-Front.png");
           
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        
        //RESTART THE GAME LEVELS
        /*FileOutputStream archFOS;
                ObjectOutputStream os;
                
                Score a = new Score();
                a.levels.add(new Sqr(true, false, 0, 0));
                a.levels.add(new Sqr(false, false, 0, 0));
                a.levels.add(new Sqr(false, false, 0, 0));
                a.levels.add(new Sqr(false, false, 0, 0));
                a.levels.add(new Sqr(false, false, 0, 0));
                a.levels.add(new Sqr(false, false, 0, 0));
                        
                 
                
                try{
                    archFOS = new FileOutputStream("Score.angryBirds");
                    os = new ObjectOutputStream(archFOS);
                    
                    os.writeObject(a);
                }catch(IOException e){
                    e.printStackTrace();
                }
        */        
                
        
        
    }
    
    private boolean arrangeLevel(JButton b, JLabel star1, JLabel star2, JLabel star3, boolean available, boolean completed, 
            int stars, String image){
        
        if(completed){
            
            if(stars == 1){
                star1.setEnabled(true);
                star2.setEnabled(false);
                star3.setEnabled(false);
            }else if(stars == 2){
                star1.setEnabled(true);
                star2.setEnabled(true);
                star3.setEnabled(false);
            }else if(stars== 3){
                star1.setEnabled(true);
                star2.setEnabled(true);
                star3.setEnabled(true);
            }
        }else{
            star1.setEnabled(false);
            star2.setEnabled(false);
            star3.setEnabled(false);
        }
        if(available){
            b.setEnabled(true);
            b.setIcon(new ImageIcon(image));
            return true;
        }else{
            b.setIcon(new ImageIcon("levelBlock-Front.png"));
            return false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton lvl1button;
    private javax.swing.JButton lvl2button;
    private javax.swing.JButton lvl3button;
    private javax.swing.JButton lvl4button;
    private javax.swing.JButton lvl5button;
    private javax.swing.JButton lvl6button;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JButton returnButton;
    private javax.swing.JLabel star1lvl1;
    private javax.swing.JLabel star1lvl2;
    private javax.swing.JLabel star1lvl3;
    private javax.swing.JLabel star1lvl4;
    private javax.swing.JLabel star1lvl5;
    private javax.swing.JLabel star1lvl6;
    private javax.swing.JLabel star2lvl1;
    private javax.swing.JLabel star2lvl2;
    private javax.swing.JLabel star2lvl3;
    private javax.swing.JLabel star2lvl4;
    private javax.swing.JLabel star2lvl5;
    private javax.swing.JLabel star2lvl6;
    private javax.swing.JLabel star3lvl1;
    private javax.swing.JLabel star3lvl2;
    private javax.swing.JLabel star3lvl3;
    private javax.swing.JLabel star3lvl4;
    private javax.swing.JLabel star3lvl5;
    private javax.swing.JLabel star3lvl6;
    // End of variables declaration//GEN-END:variables
}
