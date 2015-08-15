/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bazziss
 */
public class ImageUtil {
    
    public static void saveImageToFile(String fullPath, InputStream filecontent) {

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(fullPath));
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (IOException ex) {
            Logger.getLogger(ImageUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
                filecontent.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static byte[] readImageFileToBytes(String fullPath) throws FileNotFoundException{
        FileInputStream  fis=null;
        byte[] imageData = null;
        try {
            
            fis=new FileInputStream(fullPath);
            imageData = new byte[fis.available()];
            fis.read(imageData);
            
        } catch (Exception e) {
            
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return imageData ;
    
    }

    // f

    public static void deleteImage(String realPath , String numeDir, String fileName) {
     String path  = realPath+File.separator+numeDir+File.separator+fileName;
        File f=new File(path);
        if(f.exists()){
       
           boolean deleted = f.delete();
           if(deleted){
             LOG.info("Image deleted  "+fileName);
           }
       
        }
                 
    }
    private static final Logger LOG = Logger.getLogger(ImageUtil.class.getName());
    
    
}
