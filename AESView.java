// AESView.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AESView extends JFrame{

    private JButton Encryption_Button = new JButton("Encryption");
    private JButton Decryption_Button = new JButton("Decryption");
    private JLabel Input_Label = new JLabel("Input:");
    private JTextField Input_Field = new JTextField(20);
    
    private JButton EncryptionSaveFile_Button = new JButton("SaveFile");
    private JButton EncryptionCopyToInput_Button = new JButton("CopyToInput");
    private JLabel EncryptionText_Label = new JLabel("密文:");
    private JTextField EncryptionText_Field = new JTextField(20);
    
    private JButton DecryptionSaveFile_Button = new JButton("SaveFile");
    private JButton DecryptionCopyToInput_Button = new JButton("CopyToInput");
    private JLabel DecryptionText_Label = new JLabel("明文:");
    private JTextField DecryptionText_Field = new JTextField(20);
    
    private JButton FileLoad_Button = new JButton("Load");
    private JLabel FileNameText_Label = new JLabel("檔案名稱:", 0);
    private JTextField FileNameText_Field = new JTextField(20);
    
    private JButton ClearAll_Button = new JButton("Clear All");
    private JLabel KeyText_Label = new JLabel("key:", 0);
    private JTextField KeyText_Field = new JTextField(20);
    
    private JLabel StatusText_Label = new JLabel("歡迎使用AES加密工具!!", 0);
    
    
    AESView() {
    
        JPanel AESPanel = new JPanel();
        this.setTitle("AES加密工具");    // 標題
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //setDefaultCloseOperation 方法，這個方法是設定視窗標題列的關閉按鈕結束程式執行，提供 JFrame 類別的 staic 屬性 (field) EXIT_ON_CLOSE 當參數 (parameter)
        this.setSize(642, 225);
        this.setResizable(false);    // 視窗不可調整
        this.setLocationRelativeTo(null);    // 視窗初始位置在螢幕中心
        AESPanel.setLayout(null);
        
        
        Encryption_Button.setBounds(10,10,105,25);
        AESPanel.add(Encryption_Button);
        Decryption_Button.setBounds(120,10,105,25);
        AESPanel.add(Decryption_Button);
        Input_Label.setBounds(230,10,40,20);
        AESPanel.add(Input_Label);
        Input_Field.setBounds(267,10,350,25);
        AESPanel.add(Input_Field);
        
        
        EncryptionSaveFile_Button.setBounds(10,40,105,25);
        AESPanel.add(EncryptionSaveFile_Button);
        EncryptionCopyToInput_Button.setBounds(120,40,105,25);
        AESPanel.add(EncryptionCopyToInput_Button);
        EncryptionText_Label.setBounds(230,40,40,20);
        AESPanel.add(EncryptionText_Label);
        EncryptionText_Field.setBounds(267,40,350,25);
        AESPanel.add(EncryptionText_Field);
        
        
        DecryptionSaveFile_Button.setBounds(10,70,105,25);
        AESPanel.add(DecryptionSaveFile_Button);
        DecryptionCopyToInput_Button.setBounds(120,70,105,25);
        AESPanel.add(DecryptionCopyToInput_Button);
        DecryptionText_Label.setBounds(230,70,40,20);
        AESPanel.add(DecryptionText_Label);
        DecryptionText_Field.setBounds(267,70,350,25);
        AESPanel.add(DecryptionText_Field);
        
        
        FileLoad_Button.setBounds(10,100,105,25);
        AESPanel.add(FileLoad_Button);
        FileNameText_Label.setBounds(120,100,105,25);
        AESPanel.add(FileNameText_Label);
        FileNameText_Field.setBounds(230,100,387,25);
        AESPanel.add(FileNameText_Field);
        
        
        ClearAll_Button.setBounds(10,130,105,25);
        AESPanel.add(ClearAll_Button);
        KeyText_Label.setBounds(120,130,105,25);
        AESPanel.add(KeyText_Label);
        KeyText_Field.setBounds(230,130,387,25);
        AESPanel.add(KeyText_Field);
        
        
        StatusText_Label.setBounds(10,160,617,25);
        AESPanel.add(StatusText_Label);
        
        
        this.add(AESPanel);
        Input_Field.setText("請輸入您要加密的文字00");
        KeyText_Field.setText("8877665544332211");
        FileNameText_Label.setHorizontalAlignment(0);
        KeyText_Label.setHorizontalAlignment(0);
        //System.out.printf("FileNameText_Label: " + FileNameText_Label.getHorizontalAlignment());
        //System.out.printf("KeyText_Label: " + KeyText_Label.getHorizontalAlignment());
    }
    
    
    
    // Input 欄位 取得字串
	public String getInput_Field() {
		return Input_Field.getText();
	}
    
    // Input 欄位 輸出字串
	public void setInput_Field(String str) {
		Input_Field.setText(str);
	}
    
    
    
    // 密文 欄位 取得字串
	public String getEncryptionText_Field() {
		return EncryptionText_Field.getText();
	}
    
    // 密文 欄位 輸出字串
	public void setEncryptionText_Field(String ciphertext) {
		EncryptionText_Field.setText(ciphertext);
	}
    
    
    
    // 明文 欄位 取得字串
	public String getDecryptionText_Field() {
		return DecryptionText_Field.getText();
	}
    
    // 明文 欄位 輸出字串
	public void setDecryptionText_Field(String plaintext) {
		DecryptionText_Field.setText(plaintext);
	}
    
    
    
    // 檔案名稱 欄位 取得字串
	public String getFileNameText_Field() {
		return FileNameText_Field.getText();
	}
    
    // 檔案名稱 欄位 輸出字串
	public void setFileNameText_Field(String str) {
		FileNameText_Field.setText(str);
	}
    
    
    
    // key 欄位 取得字串
	public String getKeyText_Field() {
		return KeyText_Field.getText();
	}

    // key 欄位 輸出字串
	public void setKeyText_Field(String str) {
		KeyText_Field.setText(str);
	}
    
    
    
    // status Label 輸出字串
	public void setStatusText_Label(String status){
		StatusText_Label.setText(status);
	}
    
    
    
   /**
    * 事件處理
    * 利用.addActionListener()讓使用者在點擊按鈕後處理對應的事件
    * 如某按鈕被點擊後進行讀取檔案的動作
    */
    
    // Encryption 按鈕
    void Listener_Encryption_Button(ActionListener listen_En_Bu) {
        Encryption_Button.addActionListener(listen_En_Bu);
    }
    // Decryption 按鈕
    void Listener_Decryption_Button(ActionListener listen_De_Bu) {
        Decryption_Button.addActionListener(listen_De_Bu);
    }
    

    // Encryption SaveFile 按鈕
    void Listener_EncryptionSaveFile_Button(ActionListener listen_ESF_Bu) {
        EncryptionSaveFile_Button.addActionListener(listen_ESF_Bu);
    }
    // Encryption CopyToInput 按鈕
    void Listener_EncryptionCopyToInput_Button(ActionListener listen_ECTI_Bu) {
        EncryptionCopyToInput_Button.addActionListener(listen_ECTI_Bu);
    }
    

    // Decryption SaveFile 按鈕
    void Listener_DecryptionSaveFile_Button(ActionListener listen_DSV_Bu) {
        DecryptionSaveFile_Button.addActionListener(listen_DSV_Bu);
    }
    // Decryption CopyToInput 按鈕
    void Listener_DecryptionCopyToInput_Button(ActionListener listen_DCTI_Bu) {
        DecryptionCopyToInput_Button.addActionListener(listen_DCTI_Bu);
    }
    
    
    // File Load 按鈕
    void Listener_FileLoad_Button(ActionListener listen_Fi_Bu) {
        FileLoad_Button.addActionListener(listen_Fi_Bu);
    }
    
    
    // Clear All 按鈕
    void Listener_ClearAll_Button(ActionListener listen_CA_Bu) {
        ClearAll_Button.addActionListener(listen_CA_Bu);
    }



}