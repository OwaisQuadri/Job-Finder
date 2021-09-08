/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jobfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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
        jLabel4 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Find the Right Job for YOU");
        getContentPane().setLayout(null);

        promptLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        promptLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        promptLabel.setText("Job Finder");
        promptLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(promptLabel);
        promptLabel.setBounds(0, 10, 550, 50);
        getContentPane().add(whatField);
        whatField.setBounds(130, 110, 320, 22);

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        getContentPane().add(goButton);
        goButton.setBounds(160, 380, 230, 40);

        whereField.setText("Toronto");
        getContentPane().add(whereField);
        whereField.setBounds(130, 150, 320, 22);

        jLabel1.setText("What:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 100, 60, 40);

        jLabel2.setText("Where:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 140, 60, 40);

        skillsTextArea.setColumns(20);
        skillsTextArea.setLineWrap(true);
        skillsTextArea.setRows(5);
        skillsTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(skillsTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 220, 450, 140);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(" Skills (separated by commas in the format \"SKILL - SCORE\"):");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 190, 450, 80);

        jLabel4.setText("UserName:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 60, 80, 30);
        getContentPane().add(usernameField);
        usernameField.setBounds(110, 60, 240, 30);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(saveButton);
        saveButton.setBounds(360, 60, 70, 30);

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loadButton);
        loadButton.setBounds(450, 60, 80, 30);

        setSize(new java.awt.Dimension(567, 484));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        JobScraper j = new JobScraper();
        String what = whatField.getText();
        String where = whereField.getText();
        String skills = skillsTextArea.getText().toLowerCase();
        boolean noSkill = skills.isEmpty();
        if (noSkill) {
            System.out.println("please enter skills in proper textArea");
        } else {
            // information for all the jobs
            j.getJobInfo(what, where);
            //get number of jobs, job titles, companies, urls and descriptions
            LinkedList<String> jobTitle = j.getJobTitle();
            LinkedList<String> jobCompany = j.getJobCompany();
            LinkedList<String> jobDesc = j.getJobDesc();
            LinkedList<String> jobURL = j.getJobURL();
            int numOfJobs = j.getNumOfJobs();
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
                if (skillScore[count] > 10) {
                    skillScore[count] = 10;
                } else if (skillScore[count] < 1) {
                    skillScore[count] = 1;
                }
                count++;
            }
            //test array by outputting 
            /*for (int i = 0; i < numOfSkills; i++) {
                System.out.println("Mastery of " + skillName[i] + " with a rating of " + skillScore[i] + "/10");
            }*/
            //skillpoints
            int[] skillPoints = new int[numOfJobs];
            //initialize each skillcount to 0
            for (int init : skillPoints) {
                init = 0;
            }
            //define list of clutter to remove and speed up the program (marginally)
            String[] clutterList = {"a ", "and ", "the ", "of ", ",", "\'", "\"", "\n", "\r", "to ", "our ", "we "};
            //create a map to store job index in OG list as well as score 
            Map<Integer, Integer> jobIndexScore = new HashMap<>();
            // clean descsriptions and find iterations
            for (int i = 0; i < jobDesc.size(); i++) {
                //remove all clutter
                for (String clutter : clutterList) {
                    jobDesc.set(i, jobDesc.get(i).replace(clutter, ""));
                }
                //for each job description if desc contains skill then add score onto jobScore for job index
                for (int a = 0; a < numOfSkills; a++) {
                    if (jobDesc.get(i).contains(skillName[a])) {
                        skillPoints[i] += skillScore[a];
                    }
                }
                //TEST
                //output job titles then the points
//                System.out.println("Job " + i + ": " + skillPoints[i]);
                //if (i==0)System.out.println(jobDesc.get(i));

                jobIndexScore.put(i, skillPoints[i]);

            }
            //sort jobIndexScore from highest jobScore to lowest
            //get set of entries
            Set<Entry<Integer, Integer>> entrySet = jobIndexScore.entrySet();
            //get list (sortable collection) based on entries 
            List<Entry<Integer, Integer>> list = new ArrayList<>(entrySet);
            //sort
            Collections.sort(list, (Entry<Integer, Integer> a, Entry<Integer, Integer> b) -> b.getValue().compareTo(a.getValue()));
            //sorted
            //send sorted list of jobTitles, jobScores, jobCompanies and jobURL to the output
            System.out.println("\nJOBS IN ORDER FROM HIGHEST SCORE TO LOWEST:\n");
            for (var element : list) {
                int jobIndex = element.getKey();
                int jobScore = element.getValue();
                if (jobScore == 0) {
                    break;
                } else {
                    System.out.println("Job Title: " + jobTitle.get(jobIndex));
                    System.out.println("Company: " + jobCompany.get(jobIndex));
                    System.out.println("Score: " + jobScore);
                    System.out.println(jobURL.get(jobIndex) + "\n");
                }
            }

        }
    }//GEN-LAST:event_goButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // save skills to a file (later a database)
        String user = usernameField.getText();
        String filename = "./profiles/" + user + ".txt";
        if (user.isEmpty()) {
            System.out.println("Please enter a valid username");
        } else {
            try {
                //create file
                File saveFile = new File(filename);
                if (saveFile.createNewFile()) {
                    //file created
                    System.out.println("New User \"" + user + "\" created: " + saveFile.getName());
                } else {
                    //file exists
                    System.out.println("User \"" + user + "\" update in progress...");
                }
                //write to file
                FileWriter myWriter = new FileWriter(filename);
                myWriter.write(skillsTextArea.getText());
                myWriter.close();
                System.out.println("Successfully updated \"" + user + "\".");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        // load skills from a file (later a database)
        String skills = "";
        String user = usernameField.getText();
        String filename = "./profiles/" + user + ".txt";
        if (user.isEmpty()) {
            System.out.println("Please enter a valid username");
        } else {
            try {
                File loadFile = new File(filename);
                Scanner myReader = new Scanner(loadFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    skills = data;
                }
                myReader.close();
                System.out.println("User \"" + user + "\" loaded.");
            } catch (FileNotFoundException e) {
                System.out.println("That user does not exist,\nPlease enter a valid username");
            }
            skillsTextArea.setText(skills);
        }
    }//GEN-LAST:event_loadButtonActionPerformed

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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel promptLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextArea skillsTextArea;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField whatField;
    private javax.swing.JTextField whereField;
    // End of variables declaration//GEN-END:variables
}
