package www.pshow.goldpush;

import android.app.Application;  

public class Myapp extends Application{  
    public String name;  
    public static final String domain = "http://josnotice.nat123.net/jos/";
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }   
    
    private static Myapp instance = null;  
    
    /* ˽�й��췽������ֹ��ʵ���� */  
    private Myapp() {  
    }  
  
    /* 1:����ʽ����̬���̷���������ʵ�� */  
    public static Myapp getInstance() {  
        if (instance == null) {  
            instance = new Myapp();  
        }  
        return instance;  
    }  
      
}  