
package wekaapp;
import com.sun.xml.internal.ws.api.message.Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import weka.core.Attribute;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;



/**
 *
 * @author sgolebiewski
 */
public class Main extends javax.swing.JFrame 
{

    Instances data = null;
    
    public Main()
    {
        initComponents();
        CenterFrame();
    }
    
    private void CenterFrame()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSource = new javax.swing.JTextField();
        lblInfo = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        lblSource = new javax.swing.JLabel();
        btnGenerateTree = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        oSourceTable = new javax.swing.JTable();
        chkUnprunedTree = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSource.setName("txtPath");

        lblInfo.setText("Plik arff lub csv.");

        btnBrowse.setText("Przeglądaj");
        btnBrowse.setName("btnBrowse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        lblSource.setText("Zródło:");

        btnGenerateTree.setText("Generuj drzewo");
        btnGenerateTree.setName("btnBrowse");
        btnGenerateTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateTreeActionPerformed(evt);
            }
        });

        oSourceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(oSourceTable);

        chkUnprunedTree.setText("Nie przycinaj drzewa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(chkUnprunedTree)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGenerateTree))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblSource)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSource)
                    .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerateTree)
                    .addComponent(chkUnprunedTree))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        
        FileDialog fd =new FileDialog(this,"Wczytaj",FileDialog.LOAD);
   
        fd.setVisible(true);
        String sDirectory=fd.getDirectory();
        String sFile=fd.getFile();
        
        this.txtSource.setText(sDirectory + "\\" + sFile);
        
        //wczytanie pliku
        LoadData(this.txtSource.getText());
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void LoadData(String Path)
    {
     
        this.data = MyWeka.LoadFile(Path);
        
        int countAtr = data.numAttributes();
        int countRows = data.numInstances();
        
        String [] tblColumnNames = new String[countAtr];
        String [][] tblRows = new String[countRows][countAtr];
        
        for(int i=0;i<countAtr;i++)
        {
           tblColumnNames[i] = data.attribute(i).name();
        }
        
        for(int i=0;i<countRows;i++)
        {
           for(int j=0;j<countAtr;j++)
           {
                tblRows[i][j] = data.instance(i).toString(j);
           }
        }
 
        oSourceTable.setModel(new DefaultTableModel(tblRows, tblColumnNames));
    }
    
    private void btnGenerateTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateTreeActionPerformed
        
        ArrayList<String> lsOptions = null;
        
        if(this.chkUnprunedTree.isSelected())
        {
            if(lsOptions == null)
            {
                lsOptions = new ArrayList<String>();
            }
            lsOptions.add("N");
        }
        
        String[] aOptions = new String[lsOptions.size()];
        lsOptions.toArray(aOptions); // fill the array
        
        MyWeka.GenerateDecisiveTree(data, aOptions);
    }//GEN-LAST:event_btnGenerateTreeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnGenerateTree;
    private javax.swing.JCheckBox chkUnprunedTree;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblSource;
    private javax.swing.JTable oSourceTable;
    private javax.swing.JTextField txtSource;
    // End of variables declaration//GEN-END:variables
}
