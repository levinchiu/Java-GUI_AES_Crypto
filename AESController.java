// AESController.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;    // 用於implements ActionListener，需實作actionPerformed()
import java.io.File;
import javax.swing.JFileChooser;
import java.io.IOException;

public class AESController{
    
    private AESView theView;
    private AESModel theModel;

    AESController(AESView theView, AESModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        
        
        // Encryption 按鈕
        this.theView.Listener_Encryption_Button(new Encryption_Button_Listener());
        
        
        // Decryption 按鈕
        this.theView.Listener_Decryption_Button(new Decryption_Button_Listener());
        
        
        // Encryption CopyToInput 按鈕
        this.theView.Listener_EncryptionCopyToInput_Button(new EncryptionCopyToInput_Button_Listener());
        
        
        // Decryption CopyToInput 按鈕
        this.theView.Listener_DecryptionCopyToInput_Button(new DecryptionCopyToInput_Button_Listener());
        
        
        // Clear All 按鈕
        this.theView.Listener_ClearAll_Button(new ClearAll_Button_Listener());
        
        
        // File Load 按鈕
        this.theView.Listener_FileLoad_Button(new FileLoad_Button_Listener());
        
        
        // Encryption SaveFile 按鈕
        this.theView.Listener_EncryptionSaveFile_Button(new EncryptionSaveFile_Button_Listener());
        
        
        // Decryption SaveFile 按鈕
        this.theView.Listener_DecryptionSaveFile_Button(new DecryptionSaveFile_Button_Listener());

    }
    
    
    
    // Encryption 按鈕 - 動作_將Input欄位的資料加密後置放到密文欄位
    class Encryption_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String key;
            String msg;
            
            try {
                key = theView.getKeyText_Field();
                msg = theView.getInput_Field();
            
                theModel.Encrypt(key, msg);
                theView.setStatusText_Label("AES加密中...");             
                theView.setEncryptionText_Field(theModel.getCiphertext_String());
                theView.setStatusText_Label(theModel.getStatus());
            }
            catch(Exception ex) {
            }
        }
    }
    
    
    
    // Decryption 按鈕 - 動作_將Input欄位的資料解密後置放到明文欄位
    class Decryption_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String key;
            String msg;
            
            try {
                key = theView.getKeyText_Field();
                msg = theView.getInput_Field();
                theView.setStatusText_Label("AES解密中...");
                theModel.Decrypt(key, msg);
                theView.setDecryptionText_Field(theModel.getPlaintext_String());
                theView.setStatusText_Label(theModel.getStatus());
            }
            catch(Exception ex) {
            }
        }
    }
    
    
    
    // Encryption CopyToInput 按鈕 - 動作_將密文欄位的資料置放到Input欄位
    class EncryptionCopyToInput_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.setInput_Field(theView.getEncryptionText_Field());
        }
    
    }
    
    
    
    // Decryption CopyToInput 按鈕 - 動作_將明文欄位的資料置放到Input欄位
    class DecryptionCopyToInput_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.setInput_Field(theView.getDecryptionText_Field());
        }
    
    }
    
    
    
    // Clear All 按鈕 - 動作_清除所有欄位
    class ClearAll_Button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.setInput_Field("");
            theView.setEncryptionText_Field("");
            theView.setDecryptionText_Field("");
            theView.setFileNameText_Field("");
            theView.setKeyText_Field("");
        }
    }
    
    
    
    // File Load 按鈕 - 動作_讀取檔案
    class FileLoad_Button_Listener implements ActionListener {
        String fileStr = "";
        public void actionPerformed(ActionEvent e) {
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));    // 設定讀取當前檔案的目錄

            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File selsectedFile = fileChooser.getSelectedFile();
                theView.setFileNameText_Field(selsectedFile.getAbsolutePath());
                
                try {
                    fileStr = theModel.ReadFile(selsectedFile.getAbsolutePath());
                    theView.setInput_Field(fileStr);
                    theView.setStatusText_Label("讀取完畢!");
                }
                catch(Exception ex) {
                    theView.setStatusText_Label("檔案有問題，請再次確認...");
                }
            }
        }
    }
    
    
    
    // Encryption SaveFile 按鈕 - 動作_將密文欄位的資料寫入檔案
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
    
    
    
    // Decryption SaveFile 按鈕 - 動作_將名文欄位的資料寫入檔案
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