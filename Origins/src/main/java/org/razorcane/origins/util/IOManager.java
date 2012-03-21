package org.razorcane.origins.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.razorcane.origins.Origins;

public class IOManager {
    public static String dir = "plugins/Origins";
    
    public IOManager(String directory){
        dir = directory;
    }
    
    public static Map<String, Object> load(String target){
        return load(target, "");
    }
    
    public static Map<String, Object> load(String target, String destination){
        /*
         * The string "destination" is appended to whatever the string
         * "dir" is appended to. So for example:
         * 
         * dir = plugins/Origins
         * destination = /data
         * 
         * The appended string becomes plugins/Origins/data.
         */
        Map<String, Object> map = new HashMap();
        map.clear();
        
        File f = new File(dir + destination + "/" + target + ".properties");
        FileInputStream fis = null;
        
        try{
            if(f.exists()){
                Properties props = new Properties();
                fis = new FileInputStream(f);
                props.load(fis);
                
                for(Map.Entry<Object, Object> entry : props.entrySet()){
                    String key = entry.getKey().toString();
                    
                    try{
                        if(entry.getValue().toString().contains(".") || Double.parseDouble(entry.getValue().toString()) > 2147483647 || Double.parseDouble(entry.getValue().toString()) < -2147483648){
                            double doub = Double.parseDouble(entry.getValue().toString());
                            map.put(key, doub);
                        }
                        else{
                            int i = Integer.parseInt(entry.getValue().toString());
                            map.put(key, i);
                        }
                    }
                    catch(Exception e){
                        if(entry.getValue().toString().equals(Boolean.TRUE.toString()) || entry.getValue().toString().equals(Boolean.FALSE.toString())){
                            boolean bool = Boolean.parseBoolean(entry.getValue().toString());
                            map.put(key, bool);
                            continue;
                        }
                        else{
                            map.put(key, entry.getValue().toString());
                            continue;
                        }
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            Origins.log("No file to match:" + f.getAbsolutePath(), 2);
        }
        catch(IOException e){
            Origins.log("Unable to load file: " + f.getAbsolutePath(), 2);
        }
        finally {
            try{
                if(fis != null){
                    fis.close();
                }
            }
            catch(IOException e){
                Origins.log("ERROR - Unable to store data in file: " + f.getAbsolutePath(), 2);
            }
        }
        
        return map;
    }
    
    public static void save(String target, Map<String, Object> map){
        save(target, "", map);
    }
    
    public static void save(String target, String destination, Map<String, Object> map){
        /*
         * The string "destination" is appended to whatever the string
         * "dir" is appended to. So for example:
         * 
         * dir = plugins/Origins
         * destination = /data
         * 
         * The appended string becomes plugins/Origins/data.
         */
        
        File f = new File(dir + destination + "/" + target + ".properties");
        FileOutputStream fos = null;
        
        try{
            if(!f.exists()){
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            
            fos = new FileOutputStream(f);
            Properties props = new Properties();
            
            for(Map.Entry<String, Object> entry : map.entrySet()){
                String key = entry.getKey();
                
                props.setProperty(key, entry.getValue().toString());
            }
            
            props.store(fos, null);
        }
        catch(IOException e){
            Origins.log("Unable to create file: " + f.getAbsolutePath(), 2);
        }
        finally {
            try{
                if(fos != null){
                    fos.close();
                }
            }
            catch(IOException e){
                Origins.log("ERROR - Unable to store data in file: " + f.getAbsolutePath(), 2);
            }
        }
    }
}
