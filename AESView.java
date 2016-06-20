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
    private JLabel EncryptionText_Label = new JLabel("�K��:");
    private JTextField EncryptionText_Field = new JTextField(20);
    
    private JButton DecryptionSaveFile_Button = new JButton("SaveFile");
    private JButton DecryptionCopyToInput_Button = new JButton("CopyToInput");
    private JLabel DecryptionText_Label = new JLabel("����:");
    private JTextField DecryptionText_Field = new JTextField(20);
    
    private JButton FileLoad_Button = new JButton("Load");
    private JLabel FileNameText_Label = new JLabel("�ɮצW��:", 0);
    private JTextField FileNameText_Field = new JTextField(20);
    
    private JButton ClearAll_Button = new JButton("Clear All");
    private JLabel KeyText_Label = new JLabel("key:", 0);
    private JTextField KeyText_Field = new JTextField(20);
    
    private JLabel StatusText_Label = new JLabel("�w��ϥ�AES�[�K�u��!!", 0);
    
    
    AESView() {
    
        JPanel AESPanel = new JPanel();
        this.setTitle("AES�[�K�u��");    // ���D
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //setDefaultCloseOperation ��k�A�o�Ӥ�k�O�]�w�������D�C���������s�����{������A���� JFrame ���O�� staic �ݩ� (field) EXIT_ON_CLOSE ��Ѽ� (parameter)
        this.setSize(642, 225);
        this.setResizable(false);    // �������i�վ�
        this.setLocationRelativeTo(null);    // ������l��m�b�ù�����
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
        Input_Field.setText("�п�J�z�n�[�K����r00");
        KeyText_Field.setText("8877665544332211");
        FileNameText_Label.setHorizontalAlignment(0);
        KeyText_Label.setHorizontalAlignment(0);
        //System.out.printf("FileNameText_Label: " + FileNameText_Label.getHorizontalAlignment());
        //System.out.printf("KeyText_Label: " + KeyText_Label.getHorizontalAlignment());
    }
    
    
    
    // Input ��� ���o�r��
	public String getInput_Field() {
		return Input_Field.getText();
	}
    
    // Input ��� ��X�r��
	public void setInput_Field(String str) {
		Input_Field.setText(str);
	}
    
    
    
    // �K�� ��� ���o�r��
	public String getEncryptionText_Field() {
		return EncryptionText_Field.getText();
	}
    
    // �K�� ��� ��X�r��
	public void setEncryptionText_Field(String ciphertext) {
		EncryptionText_Field.setText(ciphertext);
	}
    
    
    
    // ���� ��� ���o�r��
	public String getDecryptionText_Field() {
		return DecryptionText_Field.getText();
	}
    
    // ���� ��� ��X�r��
	public void setDecryptionText_Field(String plaintext) {
		DecryptionText_Field.setText(plaintext);
	}
    
    
    
    // �ɮצW�� ��� ���o�r��
	public String getFileNameText_Field() {
		return FileNameText_Field.getText();
	}
    
    // �ɮצW�� ��� ��X�r��
	public void setFileNameText_Field(String str) {
		FileNameText_Field.setText(str);
	}
    
    
    
    // key ��� ���o�r��
	public String getKeyText_Field() {
		return KeyText_Field.getText();
	}

    // key ��� ��X�r��
	public void setKeyText_Field(String str) {
		KeyText_Field.setText(str);
	}
    
    
    
    // status Label ��X�r��
	public void setStatusText_Label(String status){
		StatusText_Label.setText(status);
	}
    
    
    
   /**
    * �ƥ�B�z
    * �Q��.addActionListener()���ϥΪ̦b�I�����s��B�z�������ƥ�
    * �p�Y���s�Q�I����i��Ū���ɮת��ʧ@
    */
    
    // Encryption ���s
    void Listener_Encryption_Button(ActionListener listen_En_Bu) {
        Encryption_Button.addActionListener(listen_En_Bu);
    }
    // Decryption ���s
    void Listener_Decryption_Button(ActionListener listen_De_Bu) {
        Decryption_Button.addActionListener(listen_De_Bu);
    }
    

    // Encryption SaveFile ���s
    void Listener_EncryptionSaveFile_Button(ActionListener listen_ESF_Bu) {
        EncryptionSaveFile_Button.addActionListener(listen_ESF_Bu);
    }
    // Encryption CopyToInput ���s
    void Listener_EncryptionCopyToInput_Button(ActionListener listen_ECTI_Bu) {
        EncryptionCopyToInput_Button.addActionListener(listen_ECTI_Bu);
    }
    

    // Decryption SaveFile ���s
    void Listener_DecryptionSaveFile_Button(ActionListener listen_DSV_Bu) {
        DecryptionSaveFile_Button.addActionListener(listen_DSV_Bu);
    }
    // Decryption CopyToInput ���s
    void Listener_DecryptionCopyToInput_Button(ActionListener listen_DCTI_Bu) {
        DecryptionCopyToInput_Button.addActionListener(listen_DCTI_Bu);
    }
    
    
    // File Load ���s
    void Listener_FileLoad_Button(ActionListener listen_Fi_Bu) {
        FileLoad_Button.addActionListener(listen_Fi_Bu);
    }
    
    
    // Clear All ���s
    void Listener_ClearAll_Button(ActionListener listen_CA_Bu) {
        ClearAll_Button.addActionListener(listen_CA_Bu);
    }



}