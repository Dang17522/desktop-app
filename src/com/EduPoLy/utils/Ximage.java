/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduPoLy.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.shape.Path;
import javax.swing.ImageIcon;

/**
 *
 * @author DUY PHUONG
 */
//XU LY HINH ANH
public class Ximage {
    public static Image getAppIcon(){
     URL url=  Ximage.class.getResource("/com/EduPoLy/icon/logo.PNG");
    return new ImageIcon(url).getImage();
    }
    //COPY FILE ANH BO VAO THU MUC
    public static void save(File src){
        File dst=new File("logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();//Tao thu muc logos neu chua ton tai
        }
        try{
            java.nio.file.Path from =Paths.get(src.getAbsolutePath());
            java.nio.file.Path to =Paths.get(dst.getAbsolutePath());
            Files.copy(from,to,StandardCopyOption.REPLACE_EXISTING);//Copy file vao thu muc logos
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    //doc file anh
    public static ImageIcon read(String fileName){
        File path=new File("logos",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}