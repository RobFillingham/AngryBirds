/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProyectoFinal;

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
import menus.menuNIveles;
import org.dyn4j.samples.framework.SimulationFrame;

/**
 *
 * @author famfi
 */
public class EndGame extends javax.swing.JFrame {

    /**
     * Creates new form EndGame
     */
    private AudioInputStream ab;
    private Clip clip ;
    private SimulationFrame daddy;
    private int level;
    
    public EndGame(int score, int stars, int level, int result, SimulationFrame daddy) {
        this.daddy =  daddy;
        this.setVisible(true);
        this.level = level;
        initComponents();
        if(result == -1){
            lost(); //disables stars, and does not call write on the file
        }else if(result == 1){
            writeFile(score, stars, level-1);
        }
    }
    
    public EndGame(int score, int stars, int level, int result) {
        
        initComponents();
        if(result == -1){
            lost(); //disables stars, and does not call write on the file
        }else if(result == 1){
            writeFile(score, stars, level);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        star1 = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        OKAY = new javax.swing.JButton();
        pig = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        score1 = new javax.swing.JLabel();
        OKAY1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(600, 130));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        star1.setIcon(new ImageIcon("left-star.png"));
        getContentPane().add(star1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 110, 110));

        result.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        result.setForeground(new java.awt.Color(255, 255, 255));
        result.setText("LEVEL PASSED! ");

        getContentPane().add(result, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        score.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        score.setForeground(new java.awt.Color(255, 255, 255));
        score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score.setText("Score:");
        score.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 260, -1));

        OKAY.setBackground(new java.awt.Color(0, 0, 0));
        OKAY.setForeground(new java.awt.Color(0, 0, 0));
        OKAY.setIcon(new ImageIcon("Retry.png"));
        OKAY.setBorder(null);
        OKAY.setBorderPainted(false);
        OKAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKAYActionPerformed(evt);
            }
        });
        getContentPane().add(OKAY, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 90, 100));

        pig.setIcon(new ImageIcon("Pig2A.png"));
        getContentPane().add(pig, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 180, 140));

        star3.setIcon(new ImageIcon("right-star.png"));
        getContentPane().add(star3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 110, 110));

        star2.setIcon(new ImageIcon("center-star.png"));
        getContentPane().add(star2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 120, 100));

        score1.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        score1.setForeground(new java.awt.Color(255, 255, 255));
        score1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score1.setText("1000000000");
        score1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(score1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 260, -1));

        OKAY1.setBackground(new java.awt.Color(0, 0, 0));
        OKAY1.setForeground(new java.awt.Color(0, 0, 0));
        OKAY1.setIcon(new ImageIcon("Return-Button.png"));
        OKAY1.setBorder(null);
        OKAY1.setBorderPainted(false);
        OKAY1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKAY1ActionPerformed(evt);
            }
        });
        getContentPane().add(OKAY1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 501, 90, 80));

        jLabel1.setIcon(new ImageIcon("black.jpg"));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKAYActionPerformed
        // TODO add your handling code here:
          switch(level){
            case 1:
                new Nivel1().run();
                clip.stop();
                break;
                
            case 2:
                new Nivel2().run();
                clip.stop();
                break;
            
            case 3:
                new Nivel3().run();
                clip.stop();
                break;
            case 4:
                new Nivel4().run();
                clip.stop();
                break;
            case 5:
                new Nivel5().run();
                clip.stop();
                break;
        }
        this.dispose();
        
        
    }//GEN-LAST:event_OKAYActionPerformed

    private void OKAY1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKAY1ActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        clip.stop();
        new menuNIveles().setVisible(true);
        
      
    }//GEN-LAST:event_OKAY1ActionPerformed
    
    private void lost(){
        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        pig.setVisible(true);
        result.setText("Level Failed!");
        score1.setText("0");
        new Thread() {
            public void run() {
                try {
                    ab = AudioSystem.getAudioInputStream(new File("failedLevel.wav"));
                    clip = AudioSystem.getClip();

                    clip.open(ab);
                    clip.start();

                    // Wait for the clip to finish playing
                    Thread.sleep(clip.getMicrosecondLength() / 1000);

                    clip.stop();
                    clip.close();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                    // Proper exception handling/logging here
                    e.printStackTrace();
                }
               }
        }.start();
        
        
    
    }
    
    private void writeFile(int score, int stars, int level){
        pig.setVisible(false);
        if(stars==1){
            star2.setVisible(false);
            star1.setVisible(false);
            
        }else if(stars==2){
            star1.setVisible(false);
        }
        
        this.score1.setText(score+"");
        
        FileInputStream archFIS = null;
        ObjectInputStream is;
        Score reference = null;
        
        FileOutputStream archFOS = null;
        ObjectOutputStream os;
        
        
        try{
           archFIS = new FileInputStream("Score.angryBirds");
           is = new ObjectInputStream(archFIS);
           
           reference = (Score)is.readObject();
           System.out.println(reference.levels.get(0).available);
           reference.levels.get(level).completed = true;
           reference.levels.get(level).score = score;
           reference.levels.get(level).stars = stars;
           reference.levels.get(level+1).available= true;
         
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                if(archFIS != null){ archFIS.close(); }
               
            } catch(IOException e){ System.out.println(e.getMessage());}
        }
        
        try{
           archFOS = new FileOutputStream("Score.angryBirds");
           os = new ObjectOutputStream(archFOS);
           
           os.writeObject(reference);
         
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(archFIS != null){ archFIS.close(); }
               
            } catch(IOException e){ System.out.println(e.getMessage());}
        }
        
        new Thread() {
            public void run() {
                
                try {
                    ab = AudioSystem.getAudioInputStream(new File("Angry-Birds-Theme.wav"));
                    clip = AudioSystem.getClip();

                    clip.open(ab);
                    clip.start();

                    // Wait for the clip to finish playing
                    Thread.sleep(clip.getMicrosecondLength() / 1000);

                    clip.stop();
                    clip.close();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                    // Proper exception handling/logging here
                    e.printStackTrace();
                }
            }
        }.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
  
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EndGame(2000, 0, 1, -1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKAY;
    private javax.swing.JButton OKAY1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel pig;
    private javax.swing.JLabel result;
    private javax.swing.JLabel score;
    private javax.swing.JLabel score1;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    // End of variables declaration//GEN-END:variables
}
