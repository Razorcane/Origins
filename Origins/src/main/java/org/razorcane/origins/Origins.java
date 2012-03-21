package org.razorcane.origins;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Origins extends JavaPlugin{
    public static final Logger log = Logger.getLogger("Minecraft");
    
    @Override
    public void onEnable(){
        log("[Origins] Version 2.0 now enabled.");
    }
    
    @Override
    public void onDisable(){
        log("[Origins] Disabling Origins.");
    }
    
    public static void log(String str){
        log(str, 0);
    }
    
    public static void log(String str, int priority){
        switch(priority){
            case 0:
                log.info("[Origins] " + str);
            case 1:
                log.warning("[Origins] " + str);
            case 2:
                log.severe("[Origins] " + str);
            default:
                log.info("[Origins] " + str);
        }
    }
}
