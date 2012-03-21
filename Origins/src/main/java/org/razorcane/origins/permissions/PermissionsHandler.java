package org.razorcane.origins.permissions;

import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.razorcane.origins.Origins;

public class PermissionsHandler implements oPermsHandler{
    private transient oPermsHandler handler = new NullPermsHandler();
    private String defaultGroup = "default";
    private final Plugin plugin;
    private boolean superPerms = false;
    
    public PermissionsHandler(Plugin plugin){
        this.plugin = plugin;
    }
    
    public PermissionsHandler(Plugin plugin, boolean superPerms){
        this.plugin = plugin;
        this.superPerms = superPerms;
    }
    
    public PermissionsHandler(Plugin plugin, String defaultGroup){
        this.plugin = plugin;
        this.defaultGroup = defaultGroup;
    }
    
    @Override
    public String getGroup(Player player){
        String group = handler.getGroup(player);
        
        if(group == null){
            group = defaultGroup;
        }
        
        return group;
    }
    
    @Override
    public List<String> getGroups(Player player){
        List<String> groups = handler.getGroups(player);
        
        if(groups == null || groups.isEmpty()){
            groups = Collections.singletonList(defaultGroup);
        }
        
        return groups;
    }
    
    @Override
    public boolean inGroup(Player player, String group){
        return handler.inGroup(player, group);
    }
    
    @Override
    public boolean hasPermission(Player player, String permissionNode){
        return handler.hasPermission(player, permissionNode);
    }
    
    @Override
    public String getPrefix(Player player){
        String prefix = handler.getPrefix(player);
        
        if(prefix == null){
            prefix = "";
        }
        
        return prefix;
    }
    
    @Override
    public String getSuffix(Player player){
        String suffix = handler.getSuffix(player);
        
        if(suffix == null){
            suffix = "";
        }
        
        return suffix;
    }
    
    public void checkPermissions(){
        PluginManager pm = plugin.getServer().getPluginManager();
        
        //bPermissions Integration
        Plugin bPerms = pm.getPlugin("bPermissions");
        if(bPerms != null && bPerms.isEnabled()){
            if(!(handler instanceof BPermissionsHandler)){
                handler = new BPermissionsHandler();
                Origins.log("bPermissions detected.");
            }
            return;
        }
        
        //PermissionsEx Integration
        Plugin permsEx = pm.getPlugin("PermissionsEx");
        if(permsEx != null && permsEx.isEnabled()){
            if(!(handler instanceof PermsExHandler)){
                handler = new PermsExHandler();
                Origins.log("PermissionsEx detected.");
            }
            return;
        }
        
        //GroupManager Integration
        Plugin grpMngr = pm.getPlugin("GroupManager");
        if(grpMngr != null && grpMngr.isEnabled()){
            if(!(handler instanceof GroupManagerHandler)){
                handler = new GroupManagerHandler();
                Origins.log("GroupManager detected.");
            }
            return;
        }
        
        if(superPerms){
            if(!(handler instanceof SuperpermsHandler)){
                handler = new SuperpermsHandler();
                Origins.log("Enabling SuperPerms.");
            }
            return;
        }
    }
}
