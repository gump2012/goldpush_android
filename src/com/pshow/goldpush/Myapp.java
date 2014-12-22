package com.pshow.goldpush;

import android.app.Application;  

public class Myapp extends Application{  
    public String name;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }   
    
    private static Myapp instance = null;  
    
    /* 私有构造方法，防止被实例化 */  
    private Myapp() {  
    }  
  
    /* 1:懒汉式，静态工程方法，创建实例 */  
    public static Myapp getInstance() {  
        if (instance == null) {  
            instance = new Myapp();  
        }  
        return instance;  
    }  
      
}  
