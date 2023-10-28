package gui;

import DAO.NhanVienDAO;
import Entity.NhanVien;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.ImageIcon;
import utils.Auth;
import utils.MsgBox;
import utils.XFileReaderWritter;

public class ChatRoomJInternalFrame extends javax.swing.JInternalFrame {

    static String username = "["+Auth.user.getMaNhanVien()+"] " + Auth.user.getHovaTen();
    static Socket socket = null;
    static InputStream inputStream = null;
    static OutputStream outputStream = null;
    
    public ChatRoomJInternalFrame() {
        initComponents();
        ImageIcon icon = new ImageIcon("src\\Images\\Chat Room18.png");
        setFrameIcon(icon);
        username = "["+Auth.user.getMaNhanVien()+"] " + Auth.user.getHovaTen();
        lblSoLuongThanhVien.setText(""+ countNhanVien() +" Thành Viên");
        String chatlog = XFileReaderWritter.reader("logs\\chatlog.txt");
        jTextArea.setText(chatlog);
        //connect server
        try {
            socket = new Socket("localhost", 1234);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException ex) {
            MsgBox.alert(null, "Kết nối thất bại!");
            txtMessage.setEnabled(false);
            ex.printStackTrace();
        }
    }
    
    private int countNhanVien() {
        int count = 0;
        NhanVienDAO nvDAO = new NhanVienDAO();
        List<NhanVien> list = nvDAO.selectAll();
        for (NhanVien nhanVien : list) {
            count++;
        }
        return count;
    }
    
    
    private void sendTinNhan() {
        if(txtMessage.getText().isEmpty() || txtMessage.getText().trim().length() == 0) {
            return;
        }
        String message = txtMessage.getText();
        String oldMess = jTextArea.getText();
        String sendMess = username + ": " + message + "\n";
        jTextArea.setText(oldMess + sendMess);
        txtMessage.setText("");
        
        XFileReaderWritter.writter(oldMess + sendMess, "logs\\chatlog.txt");
        
        //gửi tn lên server
        try {
            if(inputStream != null && outputStream != null) {
                outputStream.write(sendMess.getBytes());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void getTinNhanfromOtherClient() {
        Thread messageReaderThread = new Thread(() -> {
           try {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    String message = new String(buffer, 0, bytesRead);
                    String oldMess = jTextArea.getText();
                    jTextArea.setText(oldMess + message);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
        });
        messageReaderThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblSoLuongThanhVien = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        txtMessage = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setTitle("EduSys - RoomChat");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Phòng Chat Nội Bộ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Meeting Room.png"))); // NOI18N

        lblSoLuongThanhVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuongThanhVien.setForeground(new java.awt.Color(51, 51, 51));
        lblSoLuongThanhVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Collaborator Male_dark.png"))); // NOI18N
        lblSoLuongThanhVien.setText("1 Thành Viên");

        jTextArea.setEditable(false);
        jTextArea.setBackground(new java.awt.Color(224, 232, 239));
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextArea.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea.setRows(5);
        jTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(jTextArea);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtMessage.setBackground(new java.awt.Color(255, 255, 255));
        txtMessage.setForeground(new java.awt.Color(0, 0, 0));
        txtMessage.setText("Nhập tin nhắn....");
        txtMessage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtMessage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMessageFocusLost(evt);
            }
        });
        txtMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMessageMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtMessageMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtMessageMouseReleased(evt);
            }
        });
        txtMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMessageActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("+");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblSoLuongThanhVien))
                        .addGap(0, 857, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoLuongThanhVien))
                    .addComponent(jLabel2))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMessageActionPerformed
        sendTinNhan();
    }//GEN-LAST:event_txtMessageActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        getTinNhanfromOtherClient();
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtMessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMessageMouseClicked
        if(txtMessage.getText().equals("Nhập tin nhắn....")) {
            txtMessage.setText("");
            txtMessage.requestFocus();
        }
    }//GEN-LAST:event_txtMessageMouseClicked

    private void txtMessageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMessageMouseExited
        
    }//GEN-LAST:event_txtMessageMouseExited

    private void txtMessageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMessageMouseReleased
        
    }//GEN-LAST:event_txtMessageMouseReleased

    private void txtMessageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMessageFocusLost
        txtMessage.setText("Nhập tin nhắn....");
    }//GEN-LAST:event_txtMessageFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JLabel lblSoLuongThanhVien;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
