package org.razorcane.origins.permissions;

import java.util.Arrays;
import java.util.List;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.worlds.WorldsHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GroupManagerHandler implements oPermsHandler{
    
    private GroupManager manager;
    
    public GroupManagerHandler(Plugin permissionsPlugin){
        this.manager = ((GroupManager)permissionsPlugin);
    }
    
    public AnjoPermissionsHandler getHandler(Player player){
        WorldsHolder wh = manager.getWorldsHolder();
        
        if(wh == null){
            return null;
        }
        
        return wh.getWorldPermissions(player);
    }

    @Override
    public String getGroup(Player player) {
        AnjoPermissionsHandler handler = getHandler(player);
        
        if(handler == null){
            return null;
        }
        
        return handler.getGroup(player.getName());
    }

    @Override
    public List<String> getGroups(Player player) {
        AnjoPermissionsHandler handler = getHandler(player);
        
        if(handler == null){
            return null;
        }
        
        return Arrays.asList(handler.getGroups(player.getName()));
    }

    @Override
    public boolean inGroup(Player player, String group) {
        AnjoPermissionsHandler handler = getHandler(player);
        
        if(handler == null){
            return false;
        }
        
        return handler.inGroup(player.getName(), group);
    }

    @Override
    public boolean hasPermission(Player player, String permissionNode) {
        AnjoPermissionsHandler handler = getHandler(player);
        
        if(handler == null){
            return false;
        }
        
        return handler.has(player, permissionNode);
    }

    @Override
    public String getPrefix(Player player) {
        AnjoPermissionsHandler handler = getHandler(player);
        
        if(handler == null){
            return null;
        }
        
        return handler.getUserPrefix(player.getName());
    }

    @Override
    public String getSuffix(Player player) {
        AnjoPermissionsHandler handler = getHandler(player);
        
        if(handler == null){
            return null;
        }
        
        return handler.getUserSuffix(player.getName());
    }
    
}
