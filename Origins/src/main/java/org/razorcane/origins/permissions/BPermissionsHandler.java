package org.razorcane.origins.permissions;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.util.CalculableType;
import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;

public class BPermissionsHandler extends SuperpermsHandler{

    @Override
    public String getGroup(Player player) {
        List<String> groups = getGroups(player);
        
        if(groups == null || groups.isEmpty()){
            return null;
        }
        
        return groups.get(0);
    }

    @Override
    public List<String> getGroups(Player player) {
        String[] groups = ApiLayer.getGroups(player.getWorld().getName(), CalculableType.USER, player.getName());
        
        return Arrays.asList(groups);
    }

    @Override
    public boolean inGroup(Player player, String group) {
        return ApiLayer.hasGroup(player.getWorld().getName(), CalculableType.USER, player.getName(), group);
    }

    @Override
    public String getPrefix(Player player) {
        return ApiLayer.getValue(player.getWorld().getName(), CalculableType.USER, player.getName(), "prefix");
    }

    @Override
    public String getSuffix(Player player) {
        return ApiLayer.getValue(player.getWorld().getName(), CalculableType.USER, player.getName(), "suffix");
    }
    
}
