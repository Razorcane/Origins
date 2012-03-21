package org.razorcane.origins.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.razorcane.origins.Origins;

public class Configuration {
    private HashMap<String, Object> map = new HashMap();
    private String target;
    private String destination;
    private String dir = "plugins/Origins";
    
    public Configuration(String target){
        HashMap<String, Object> data = (HashMap<String, Object>) IOManager.load(target);
        
        if(data == null){
            map.clear();
        }
        else{
            map = data;
        }
        
        this.target = target;
        this.destination = "";
    }
    
    public Configuration(String target, String destination){
        HashMap<String, Object> data = (HashMap<String, Object>) IOManager.load(target, destination);
        
        if(data == null){
            map.clear();
        }
        else{
            map = data;
        }
        
        this.target = target;
        this.destination = destination;
    }
    
    public void load(){
        HashMap<String, Object> data = (HashMap<String, Object>) IOManager.load(target, destination);
        
        if(data != null){
            for(Map.Entry<String, Object> entry : data.entrySet()){
                if(entry.getValue() != null){
                    setEntry(entry.getKey(), entry.getValue());
                }
            }
        }
    }
    
    public void save(){
        if(map == null || map.isEmpty()){
            clear();
        }
        
        IOManager.save(target, destination, map);
    }
    
    public void clear(){
        if(!map.isEmpty()){
            map.clear();
        }
        
        File f = new File(dir + destination + "/" + target + ".properties");
        f.delete();
        
        try{
            f.createNewFile();
        }
        catch(IOException e){
            Origins.log("--- FATAL ERROR --- Unable to reinitialize config file.", 2);
        }
    }
    
    public void removeEntry(String key){
        map.remove(key);
    }
    
    public boolean hasEntry(String key){
        return map.containsKey(key);
    }
    
    /*
     * Configuration Set Methods
     * Used to define configuration variables to be written and read
     * from the main configuration file.
     */
    public void setEntry(String key, Object value){
        if(map == null){
            return;
        }
        else{
            map.put(key, value);
        }
    }
    
    public void setString(String key, String value){
        if(map == null){
            return;
        }
        else{
            map.put(key, value);
        }
    }
    
    public void setInt(String key, int value){
        if(map == null){
            return;
        }
        else{
            map.put(key, value);
        }
    }
    
    public void setBoolean(String key, boolean value){
        if(map == null){
            return;
        }
        else{
            map.put(key, value);
        }
    }
    
    public void setDouble(String key, double value){
        if(map == null){
            return;
        }
        else{
            map.put(key, value);
        }
    }
    
    /*
     * Configuration Get Methods
     * Used to read configuration variables that have been saved in the
     * main configuration file.
     */
    
    public Object getEntry(String key){
        if(!map.containsKey(key)){
            return null;
        }
        else{
            return map.get(key);
        }
    }
    
    public String getString(String key){
        if(!map.containsKey(key)){
            return null;
        }
        else if(!(map.get(key) instanceof String)){
            return null;
        }
        else{
            return map.get(key).toString();
        }
    }
    
    public int getInt(String key){
        if(!map.containsKey(key)){
            return -1;
        }
        else if(!(map.get(key) instanceof Integer)){
            return -1;
        }
        else{
            return ((Integer) map.get(key)).intValue();
        }
    }
    
    public Boolean getBoolean(String key){
        if(!map.containsKey(key)){
            return false;
        }
        else if(!(map.get(key) instanceof Boolean)){
            return false;
        }
        else{
            return ((Boolean) map.get(key)).booleanValue();
        }
    }
    
    public double getDouble(String key){
        if(!map.containsKey(key)){
            return -1;
        }
        else if(!(map.get(key) instanceof Double)){
            return -1;
        }
        else{
            return ((Double) map.get(key)).doubleValue();
        }
    }
}
