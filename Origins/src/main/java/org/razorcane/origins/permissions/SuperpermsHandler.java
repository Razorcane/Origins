package org.razorcane.origins.permissions;

import java.util.List;
import org.bukkit.entity.Player;

public class SuperpermsHandler implements oPermsHandler{
    
    @Override
    public String getGroup(Player player){
        return null;
    }
    
    @Override
    public List<String> getGroups(Player player){
        return null;
    }
    
    @Override
    public boolean hasPermission(Player player, String permissionNode){
        if(player.hasPermission("-" + permissionNode)){
            return false;
        }
        
        final String[] parts = permissionNode.split("//.");
        final StringBuilder sb = new StringBuilder(permissionNode.length());
        
        for(String part : parts){
            sb.append('*');
            
            if(player.hasPermission(sb.toString())){
                return true;
            }
            
            sb.deleteCharAt(sb.length() -1);
            sb.append(part).append('.');
        }
        
        return player.hasPermission(permissionNode);
    }
    
    @Override
    public boolean inGroup(Player player, String group){
        return hasPermission(player, "group." + group);
    }
    
    @Override
    public String getPrefix(Player player){
        return null;
    }
    
    @Override
    public String getSuffix(Player player){
        return null;
    }
}
