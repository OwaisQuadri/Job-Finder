/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jobfinder;

import java.util.LinkedList;

/**
 *
 * @author Owais
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        promptLabel = new javax.swing.JLabel();
        whatField = new javax.swing.JTextField();
        goButton = new javax.swing.JButton();
        whereField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        skillsTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("the Right Job for YOU");
        getContentPane().setLayout(null);

        promptLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        promptLabel.setText("<html>\n<p>Click to Fetch Job titles and Descriptions on this page:</p>\n<p>(will output to console)</p>\n<html>");
        promptLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(promptLabel);
        promptLabel.setBounds(0, 0, 390, 50);
        getContentPane().add(whatField);
        whatField.setBounds(60, 60, 140, 22);

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        getContentPane().add(goButton);
        goButton.setBounds(170, 280, 230, 40);
        getContentPane().add(whereField);
        whereField.setBounds(260, 60, 140, 22);

        jLabel1.setText("What:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 70, 41, 16);

        jLabel2.setText("Where:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 70, 41, 16);

        skillsTextArea.setColumns(20);
        skillsTextArea.setRows(5);
        skillsTextArea.setText("skill - 5, skill2 - 3, Node.js - 5");
        jScrollPane1.setViewportView(skillsTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(168, 130, 240, 140);

        jLabel3.setText("<html> <p> Skills: </p> <p>(separated by commas in the format \"SKILL - SCORE\")</p>");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 140, 150, 110);

        setSize(new java.awt.Dimension(444, 368));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        JobScraper j = new JobScraper();
        String what = whatField.getText();
        String where = whereField.getText();
        String skills = skillsTextArea.getText();
        boolean noSkill=skills.isEmpty();
        if (noSkill) {
            System.out.println("please enter skills in proper textArea");
        }else{
            // information for all the jobs
            j.getJobInfo(what, where);
            //get job title, company and description
            LinkedList<String> jobTitle = j.getJobTitle();
            LinkedList<String> jobCompany = j.getJobCompany();
            LinkedList<String> jobDesc = j.getJobDesc();
            LinkedList<String> jobURL = j.getJobURL();
            //get skills info (skill name and score from 1-10) from GUI

            //split skills by ', '
            String[] skillAndScore = skills.split(", ");
            //split name and score by ' - '
            int numOfSkills = skillAndScore.length;
            String[] skillName = new String[numOfSkills];
            int[] skillScore = new int[numOfSkills];
            int count = 0;
            for (String s : skillAndScore) {
                String[] temp = s.split(" - ");
                skillName[count] = temp[0];
                skillScore[count] = Integer.parseInt(temp[1]);
                //if score >10, make it 10 and if score < 1, make it 1
                if (skillScore[count]>10){
                    skillScore[count]=10;
                }else if(skillScore[count]<1){
                    skillScore[count]=1;
                }
                count++;
            }
            //test array by outputting 
            for (int i = 0; i < numOfSkills; i++) {
                System.out.println("Mastery of "+skillName[i]+" with a rating of "+skillScore[i]+"/10");
            }
        }
        

        //create a dictionary/map 'jobScore'
        //for each job description if desc contains skill then add score onto jobScore for job index
        //sort job from highest jobScore to lowest
        //send jobTitle, jobScore, jobCompany and jobURL to a file and to the console

    }//GEN-LAST:event_goButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel promptLabel;
    private javax.swing.JTextArea skillsTextArea;
    private javax.swing.JTextField whatField;
    private javax.swing.JTextField whereField;
    // End of variables declaration//GEN-END:variables
}
