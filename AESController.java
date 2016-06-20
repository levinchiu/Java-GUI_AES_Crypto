// AESController.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;    // �Ω�implements ActionListener�A�ݹ�@actionPerformed()
import java.io.File;
import javax.swing.JFileChooser;
import java.io.IOException;

public class AESController{
    
    private AESView theView;
    private AESModel theModel;

    AESController(AESView theView, AESModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        
        
        // Encryption ���s
        this.theView.Listener_Encryption_Button(new Encryption_Button_Listener());
        
        
        // Decryption ���s
        this.theView.Listener_Decryption_Button(new Decryption_Button_Listener());
        
        
        // Encryption CopyToInput ���s
        this.theView.Listener_EncryptionCopyToInput_Button(new EncryptionCopyToInput_Button_Listener());
        
        
        // Decryption CopyToInput ���s
        this.theView.Listener_DecryptionCopyToInput_Button(new DecryptionCopyToInput_Button_Listener());
        
        
        // Clear All ���s
        this.theView.Listener_ClearAll_Button(new ClearAll_Button_Listener());
        
        
        // File Load ���s
        this.theView.Listener_FileLoad_Button(new FileLoad_Button_Listener());
        
        
        // Encryption SaveFile ���s
        this.theView.Listener_EncryptionSaveFile_Button(new EncryptionSaveFile_Button_Listener());
        
        
        // Decryption SaveFile ���s
        this.theView.Listener_DecryptionSaveFile_Button(new DecryptionSaveFile_Button_Listener());

    }
    
    
    
    // Encryption ���s - �ʧ@_�NInput��쪺��ƥ[�K��m���K�����
    class Encryption_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String key;
            String msg;
            
            try {
                key = theView.getKeyText_Field();
                msg = theView.getInput_Field();
            
                theModel.Encrypt(key, msg);
                theView.setStatusText_Label("AES�[�K��...");             
                theView.setEncryptionText_Field(theModel.getCiphertext_String());
                theView.setStatusText_Label(theModel.getStatus());
            }
            catch(Exception ex) {
            }
        }
    }
    
    
    
    // Decryption ���s - �ʧ@_�NInput��쪺��ƸѱK��m���������
    class Decryption_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String key;
            String msg;
            
            try {
                key = theView.getKeyText_Field();
                msg = theView.getInput_Field();
                theView.setStatusText_Label("AES�ѱK��...");
                theModel.Decrypt(key, msg);
                theView.setDecryptionText_Field(theModel.getPlaintext_String());
                theView.setStatusText_Label(theModel.getStatus());
            }
            catch(Exception ex) {
            }
        }
    }
    
    
    
    // Encryption CopyToInput ���s - �ʧ@_�N�K����쪺��Ƹm���Input���
    class EncryptionCopyToInput_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.setInput_Field(theView.getEncryptionText_Field());
        }
    
    }
    
    
    
    // Decryption CopyToInput ���s - �ʧ@_�N������쪺��Ƹm���Input���
    class DecryptionCopyToInput_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.setInput_Field(theView.getDecryptionText_Field());
        }
    
    }
    
    
    
    // Clear All ���s - �ʧ@_�M���Ҧ����
    class ClearAll_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.setInput_Field("");
            theView.setEncryptionText_Field("");
            theView.setDecryptionText_Field("");
            theView.setFileNameText_Field("");
            theView.setKeyText_Field("");
        }
    }
    
    
    
    // File Load ���s - �ʧ@_Ū���ɮ�
    class FileLoad_Button_Listener implements ActionListener {
        String fileStr = "";
        public void actionPerformed(ActionEvent e) {
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));    // �]�wŪ����e�ɮת��ؿ�

            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File selsectedFile = fileChooser.getSelectedFile();
                theView.setFileNameText_Field(selsectedFile.getAbsolutePath());
                
                try {
                    fileStr = theModel.ReadFile(selsectedFile.getAbsolutePath());
                    theView.setInput_Field(fileStr);
                    theView.setStatusText_Label("Ū������!");
                }
                catch(Exception ex) {
                    theView.setStatusText_Label("�ɮצ����D�A�ЦA���T�{...");
                }
            }
        }
    }
    
    
    
    // Encryption SaveFile ���s - �ʧ@_�N�K����쪺��Ƽg�J�ɮ�
    class EncryptionSaveFile_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //theView.setFileNameText_Field(theView.getFileNameText_Field());
                
            try {
                theModel.WriteFile(theView.getEncryptionText_Field(), "ciphertext", theView.getFileNameText_Field());
                theView.setStatusText_Label(theModel.getStatus());
            }
            catch(Exception ex) {
                theView.setStatusText_Label(theModel.getStatus());
            }
        }
    }
    
    
    
    // Decryption SaveFile ���s - �ʧ@_�N�W����쪺��Ƽg�J�ɮ�
    class DecryptionSaveFile_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //theView.setFileNameText_Field(theView.getFileNameText_Field());
                
            try {
                theModel.WriteFile(theView.getDecryptionText_Field(), "plaintext", theView.getFileNameText_Field());
                theView.setStatusText_Label(theModel.getStatus());
            }
            catch(Exception ex) {
                theView.setStatusText_Label(theModel.getStatus());
            }
        }
    }


}