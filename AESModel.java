// AESModel.java
// AES ECB mode PKCS5Padding填充不足的Block
// key長度有128、192、256 bits，不足以0x00補滿


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class AESModel{


    private String plaintext_String = "";
    private byte[] plaintext_Byte;
    private String ciphertext_String = "";
    private byte[] ciphertext_Byte;
    private String IV;
    private byte[] key_byte;
    private byte[] msg_byte;
    private String status;
    
    
    
    // AES加密
    public void Encrypt(String key, String msg) throws Exception {
    
        this.key_byte = key.getBytes("UTF-8");
        
        // Input欄位中的字串不能為空、key<=32 bytes
        if(msg.length() != 0 && this.key_byte.length <= 32) {
            this.key_byte = DecideKeyLength(this.key_byte);    // 判斷key的長度
            this.msg_byte = msg.getBytes("UTF-8");
            SecretKeySpec spec = new SecretKeySpec(key_byte, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // PKCS5Padding, NoPadding(無填充)
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            this.ciphertext_Byte = cipher.doFinal(msg_byte);
            this.ciphertext_String = Ciphertext_ByteArray2String();
            this.status = "AES加密完成!";
        }
        else {
            this.status = "欲加密明文或key長度有誤，請再次確認...";
        }
        
    }
    
    
    
    // AES解密
    public void Decrypt(String key, String msg) throws Exception {
    
        this.msg_byte = Plaintext_String2ByteArray(msg);
        this.key_byte = key.getBytes("UTF-8");
        
        // Plaintext_String2ByteArray()沒有錯誤訊息、this.msg_byte長度為16的倍數(區塊)、欲解密密文不為空
        if(this.status.compareTo("")==0 && this.msg_byte.length%16 == 0 && msg.length() != 0 && this.key_byte.length <= 32) {
            this.key_byte = DecideKeyLength(this.key_byte);    // 判斷key的長度
            SecretKeySpec spec = new SecretKeySpec(key_byte, "AES");
            Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
            cipher.init( Cipher.DECRYPT_MODE, spec);
            this.plaintext_Byte = cipher.doFinal(msg_byte);
            this.plaintext_String = new String(this.plaintext_Byte, "UTF-8");
            this.status = "AES解密完成!";
        }
        else {
            this.status = "欲解密密文或key長度有誤，請再次確認...";
        }
        
    }
    
    
    
    // 將16進制Binary格式的Ciphertext資料由byte[]轉為String
    private String Ciphertext_ByteArray2String() {
        return DatatypeConverter.printHexBinary(this.ciphertext_Byte);
    }
    
    
    
    // 將16進制Binary格式的Plaintext資料由String轉為byte[]
    public byte[] Plaintext_String2ByteArray(String ciphertext) {
        this.status = "";
        byte[] temp_Byte = new byte[0];
        try {
            temp_Byte = DatatypeConverter.parseHexBinary(ciphertext);
        }   
        catch(Exception e) {
            this.status = "欲解密密文有誤，請再次確認...";
        }
        return temp_Byte;
    }
    
    
    
    // 讀取檔案
    public String ReadFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String fileStr = "";
        while(br.ready()) {
            fileStr = fileStr + br.readLine() + "\r\n";    // 逐行讀取檔案內容但不包含換行(\r\n)，因此需要自行添加
        }
        fileStr = fileStr.substring(0, fileStr.length()-2);    // 刪除讀取檔案內容時最後一次的"\r\n"
        return fileStr;
    }
    
    
    
    // 寫入檔案
    public void WriteFile(String fileString, String saveText, String fileName) throws IOException {
        try {
            if(saveText == "plaintext") {
                fileString = RestoreByte(fileString);
            }
            FileWriter fw = new FileWriter(fileName);
            fw.write(fileString);
            fw.flush();
            fw.close();
            this.status = "寫入完畢!";
        }
        catch(Exception e) {
            this.status = "寫入失敗，請再次確認要寫入的資料或檔案路徑...";
        }
    }
    
    
    
    // 更正Byte，將0x0d,0x20更正為0x0d,0x0a
    // 在讀取檔案(ReadFile())內容時會在每行後加"\r\n"，但是從Input欄位取下時會變成"\r "，因此必須更正回"\r\n"
    // \r: 0x0d
    // \n: 0x0a
    // space: 0x20
    private String RestoreByte(String fileString) throws Exception {
        byte[] a = fileString.getBytes("UTF-8");
        byte b;
        byte c;
        
        for(int i=0; i<a.length-1; i++) {
            b = a[i];
            c = a[i+1];
            if(b == 0x0d && c == 0x20) {
                a[i+1] = 0x0a;
            }
        }
        
        return new String(a, "UTF-8");    // byte[] to String
    }
    
    
    
    // key長度有128、192、256bit，不足以0x00補滿
    public byte[] DecideKeyLength( byte[] key_original ) throws Exception {
        byte[] key = new byte[0];
        if( key_original.length <= 16 ) {
           byte[] key_128bits = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
           System.arraycopy(key_original, 0, key_128bits, 0, key_original.length );
           key = key_128bits;
        }
        else if( key_original.length >= 17 || key_original.length <= 24) {
           byte[] key_192bits = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			                      0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
           System.arraycopy(key_original, 0, key_192bits, 0, key_original.length );
           key = key_192bits;
        }
        else if( key_original.length >= 25 || key_original.length <= 32) {
           byte[] key_256bits = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			                      0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
           System.arraycopy(key_original, 0, key_256bits, 0, key_original.length );
           key = key_256bits;
        }
        else {
           System.out.println( "key length error");
        }
        return key;
    } 
    
    
    
    
    public String getCiphertext_String() {
        return this.ciphertext_String;
    }
    
    
    public String getPlaintext_String() {
        return this.plaintext_String;
    }


    public String getStatus() {
        return this.status;
    }



}