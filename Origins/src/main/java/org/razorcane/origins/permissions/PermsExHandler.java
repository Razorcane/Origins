package org.razorcane.origins.permissions;

import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermsExHandler implements oPermsHandler{
    private PermissionManager manager;
    
    public PermsExHandler(){
        manager = PermissionsEx.getPermissionManager();
    }

    @Override
    public String getGroup(Player player) {
        PermissionUser user = manager.getUser(player);
        
        if(user == null){
            return null;
        }
        
        return user.getGroupsNames()[0];
    }

    @Override
    public List<String> getGroups(Player player) {
        PermissionUser user = manager.getUser(player);
        
        if(user == null){
            return null;
        }
        
        return Arrays.asList(user.getGroupsNames());
    }

    @Override
    public boolean inGroup(Player player, String group) {
        PermissionUser user = manager.getUser(player);
        
        if(user == null){
            return false;
        }
        
        return user.inGroup(group);
    }

    @Override
    public boolean hasPermission(Player player, String permissionNode) {
        return manager.has(player, permissionNode);
    }

    @Override
    public String getPrefix(Player player) {
        PermissionUser user = manager.getUser(player);
        
        if(user == null){
            return null;
        }
        
        return user.getPrefix(player.getWorld().getName());
    }

    @Override
    public String getSuffix(Player player) {
        PermissionUser user = manager.getUser(player);
        
        if(user == null){
            return null;
        }
        
        return user.getSuffix(player.getWorld().getName());
    }
}
