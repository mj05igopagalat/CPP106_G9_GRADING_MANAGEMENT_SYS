/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package code.zennnith;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
public class view_list extends javax.swing.JInternalFrame {
    
    //edit student//
    public void editStudent() {
        //Selection of row//
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.");
            return;
        }

        //editing the previous added output//
        String oldStudentNumber = table.getValueAt(selectedRow, 3).toString();
        String name = JOptionPane.showInputDialog(this, "Enter new name:", table.getValueAt(selectedRow, 0));
        String yearSection = JOptionPane.showInputDialog(this, "Enter new year & section:", table.getValueAt(selectedRow, 1));
        String age = JOptionPane.showInputDialog(this, "Enter new age:", table.getValueAt(selectedRow, 2));
        String newStudentNumber = JOptionPane.showInputDialog(this, "Enter new student number:", oldStudentNumber);

        //updating the table//
        if (name != null && yearSection != null && age != null && newStudentNumber != null) {
            updateStudentInfo(oldStudentNumber, name, yearSection, age, newStudentNumber);
            loadStudentData(); // Refresh the table
        }
    }

    //Update Student Info method//
    private void updateStudentInfo(String oldStudentNumber, String name, String yearSection, String age, String newStudentNumber) {
        File inputFile = new File("students.txt");
        File tempFile = new File("temp.txt");
        //error handling//
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            //read the line in the file txt//
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[3].equals(oldStudentNumber)) {
                    //Update the student information including the new student number//
                    writer.write(name + "," + yearSection + "," + age + "," + newStudentNumber + "," + data[4]);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating student information: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Error renaming temporary file.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error deleting original file.");
        }
    }   
    
    //Show the List of the Student//
    public String getSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return table.getValueAt(selectedRow, 3).toString();
        }
        return null;
    }
//Deleting Student in the List//
public void deleteStudent(String studentNumber) {
    File inputFile = new File("students.txt");
    File tempFile = new File("temp.txt");
    
    //Read the txt file//
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;
        boolean found = false;

        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.contains(studentNumber)) {
                writer.write(currentLine);
                writer.newLine();
            } else {
                found = true;
            }
        }
        //Error if student not found//
        if (!found) {
            JOptionPane.showMessageDialog(this, "Student not found.");
            return;
        }

    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading/writing file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (inputFile.delete()) {
        if (tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(this, "Student deleted successfully.");
            loadStudentData(); //Refresh the table//
        } else {
            JOptionPane.showMessageDialog(this, "Error renaming temporary file.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Error deleting original file.");
    }

}
    
    private void loadStudentData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); //Clear existing rows//

        //show the list of the student with grade in the table//
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    model.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading student data: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

}
   public view_list() {
    initComponents();
    loadStudentData();
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("CPP106 GRADING SYSTEM");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student Name", "Year & Section", "Age", "Student Number", "Standing"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setToolTipText("");
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
            
