/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peminjaman.buku;

import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author deffi
 */
public class Perpanjang extends javax.swing.JInternalFrame {

    /**
     * Creates new form Perpanjang
     */
    int baris;
    DefaultTableModel dtm;
    Connection koneksi;
    public Perpanjang() {
        initComponents();
        
        koneksi = Koneksi.getKoneksi("localhost","3306","root","","db_peminjaman");
        setVisible(true);
        BasicInternalFrameUI bifUI = ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI());
            for(MouseListener listener : bifUI.getNorthPane().getMouseListeners() ){
                bifUI.getNorthPane().removeMouseListener(listener);
            }
        
        daftarPeminjam();
        ClearData();
    }
    
    private void daftarPeminjam(){
         String[] kolom = {"KODE BUKU", "NAMA BUKU", "NAMA PEMINJAM", "TANGGAL PINJAM", "TANGGAL KEMBALI"};
        
        dtm = new DefaultTableModel (null, kolom);
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM tbl_peminjaman;" ;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                //ngambil dari database
                String kode         = rs.getString("kode_buku");
                String nama         = rs.getString("nama_buku");                
                String peminjam     = rs.getString("nama_peminjam");
                String tglPinjam    = rs.getString("tanggal_pinjam");
                String tglKembali   = rs.getString("tanggal_balik");
                
                dtm.addRow(new String[]{kode, nama, peminjam, tglPinjam, tglKembali});
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        tblPerpanjang.setModel(dtm);
    }
    
    private void ClearData(){
        txtKode.setText("");
        txtDurasi.setText("");
    }
    
    private void Search(){
        String kode         = txtKode.getText();
        String durasi       = txtDurasi.getText();
        int lama = Integer.parseInt(durasi);
        
//        buat pencarian data pada tabel
//        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
//        tblPerpanjang.setRowSorter(tr);
//        tr.setRowFilter(RowFilter.regexFilter(kode));
        
        try{
            Statement stmt  = koneksi.createStatement();
            
            String query1 = "SELECT * FROM tbl_peminjaman WHERE kode_buku = '"+kode+"';" ;
            ResultSet rs = stmt.executeQuery(query1);
            if (rs.next()){
                //ngambil dari database
                String tgl_kembali = rs.getString("tanggal_balik");
                update(kode,tgl_kembali,lama);
                ClearData();
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            }
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
           ClearData();
        }
        
    }
    
    private void update(String kode, String tgl_kembali,int lama){
        try{
            Statement stmt  = koneksi.createStatement();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date tanggal_balik  = df.parse(tgl_kembali);
            Calendar calTambah = Calendar.getInstance();
            calTambah.setTime(tanggal_balik);
            calTambah.add(Calendar.DAY_OF_MONTH, lama);
            String query2    = "UPDATE tbl_peminjaman SET tanggal_balik = '"+df.format(calTambah.getTime())+"' WHERE kode_buku= '"+kode+"';";
            int success     = stmt.executeUpdate(query2);
            if(success == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Diperpanjang");
                daftarPeminjam();
                ClearData();
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diperpanjang");
            }
          }catch(Exception e){
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JPanel();
        lbSubmit = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDurasi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPerpanjang = new javax.swing.JTable();

        setTitle("Perpanjang");

        jPanel1.setBackground(new java.awt.Color(171, 171, 171));

        jPanel2.setBackground(new java.awt.Color(74, 73, 70));

        jPanel3.setBackground(new java.awt.Color(171, 171, 171));

        btnSubmit.setBackground(new java.awt.Color(74, 73, 70));

        lbSubmit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSubmit.setForeground(new java.awt.Color(255, 255, 255));
        lbSubmit.setText("PERPANJANG");
        lbSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSubmitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnSubmitLayout = new javax.swing.GroupLayout(btnSubmit);
        btnSubmit.setLayout(btnSubmitLayout);
        btnSubmitLayout.setHorizontalGroup(
            btnSubmitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSubmitLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbSubmit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnSubmitLayout.setVerticalGroup(
            btnSubmitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSubmitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSubmit)
                .addContainerGap())
        );

        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kode Buku");

        txtDurasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDurasiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Durasi Hari");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPerpanjang.setAutoCreateRowSorter(true);
        tblPerpanjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Buku", "Nama Buku", "Nama Peminjam", "Tanggal Pinjam", "Tanggal Pengembalian"
            }
        ));
        tblPerpanjang.setRowHeight(20);
        tblPerpanjang.setShowGrid(true);
        jScrollPane1.setViewportView(tblPerpanjang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSubmitMouseClicked
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_lbSubmitMouseClicked

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeActionPerformed

    private void txtDurasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDurasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDurasiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSubmit;
    private javax.swing.JTable tblPerpanjang;
    private javax.swing.JTextField txtDurasi;
    private javax.swing.JTextField txtKode;
    // End of variables declaration//GEN-END:variables
}
