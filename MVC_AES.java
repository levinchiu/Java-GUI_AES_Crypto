// MVC_AES.java

public class MVC_AES {
    
    public static void main(String[] args) {
    	
    	AESView theView = new AESView();
        
    	AESModel theModel = new AESModel();
        
        AESController theController = new AESController(theView,theModel);
        
        theView.setVisible(true);
        
    }
}