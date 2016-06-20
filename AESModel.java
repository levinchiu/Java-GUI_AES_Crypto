// AESModel.java
// AES ECB mode PKCS5Padding��R������Block
// key���צ�128�B192�B256 bits�A�����H0x00�ɺ�


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
    
    
    
    // AES�[�K
    public void Encrypt(String key, String msg) throws Exception {
    
        this.key_byte = key.getBytes("UTF-8");
        
        // Input��줤���r�ꤣ�ର�šBkey<=32 bytes
        if(msg.length() != 0 && this.key_byte.length <= 32) {
            this.key_byte = DecideKeyLength(this.key_byte);    // �P�_key������
            this.msg_byte = msg.getBytes("UTF-8");
            SecretKeySpec spec = new SecretKeySpec(key_byte, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // PKCS5Padding, NoPadding(�L��R)
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            this.ciphertext_Byte = cipher.doFinal(msg_byte);
            this.ciphertext_String = Ciphertext_ByteArray2String();
            this.status = "AES�[�K����!";
        }
        else {
            this.status = "���[�K�����key���צ��~�A�ЦA���T�{...";
        }
        
    }
    
    
    
    // AES�ѱK
    public void Decrypt(String key, String msg) throws Exception {
    
        this.msg_byte = Plaintext_String2ByteArray(msg);
        this.key_byte = key.getBytes("UTF-8");
        
        // Plaintext_String2ByteArray()�S�����~�T���Bthis.msg_byte���׬�16������(�϶�)�B���ѱK�K�夣����
        if(this.status.compareTo("")==0 && this.msg_byte.length%16 == 0 && msg.length() != 0 && this.key_byte.length <= 32) {
            this.key_byte = DecideKeyLength(this.key_byte);    // �P�_key������
            SecretKeySpec spec = new SecretKeySpec(key_byte, "AES");
            Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
            cipher.init( Cipher.DECRYPT_MODE, spec);
            this.plaintext_Byte = cipher.doFinal(msg_byte);
            this.plaintext_String = new String(this.plaintext_Byte, "UTF-8");
            this.status = "AES�ѱK����!";
        }
        else {
            this.status = "���ѱK�K���key���צ��~�A�ЦA���T�{...";
        }
        
    }
    
    
    
    // �N16�i��Binary�榡��Ciphertext��ƥ�byte[]�ରString
    private String Ciphertext_ByteArray2String() {
        return DatatypeConverter.printHexBinary(this.ciphertext_Byte);
    }
    
    
    
    // �N16�i��Binary�榡��Plaintext��ƥ�String�ରbyte[]
    public byte[] Plaintext_String2ByteArray(String ciphertext) {
        this.status = "";
        byte[] temp_Byte = new byte[0];
        try {
            temp_Byte = DatatypeConverter.parseHexBinary(ciphertext);
        }   
        catch(Exception e) {
            this.status = "���ѱK�K�妳�~�A�ЦA���T�{...";
        }
        return temp_Byte;
    }
    
    
    
    // Ū���ɮ�
    public String ReadFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String fileStr = "";
        while(br.ready()) {
            fileStr = fileStr + br.readLine() + "\r\n";    // �v��Ū���ɮפ��e�����]�t����(\r\n)�A�]���ݭn�ۦ�K�[
        }
        fileStr = fileStr.substring(0, fileStr.length()-2);    // �R��Ū���ɮפ��e�ɳ̫�@����"\r\n"
        return fileStr;
    }
    
    
    
    // �g�J�ɮ�
    public void WriteFile(String fileString, String saveText, String fileName) throws IOException {
        try {
            if(saveText == "plaintext") {
                fileString = RestoreByte(fileString);
            }
            FileWriter fw = new FileWriter(fileName);
            fw.write(fileString);
            fw.flush();
            fw.close();
            this.status = "�g�J����!";
        }
        catch(Exception e) {
            this.status = "�g�J���ѡA�ЦA���T�{�n�g�J����Ʃ��ɮ׸��|...";
        }
    }
    
    
    
    // ��Byte�A�N0x0d,0x20�󥿬�0x0d,0x0a
    // �bŪ���ɮ�(ReadFile())���e�ɷ|�b�C���["\r\n"�A���O�qInput�����U�ɷ|�ܦ�"\r "�A�]�������󥿦^"\r\n"
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
    
    
    
    // key���צ�128�B192�B256bit�A�����H0x00�ɺ�
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