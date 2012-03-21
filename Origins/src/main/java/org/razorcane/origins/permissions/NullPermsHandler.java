package org.razorcane.origins.permissions;

import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Player;

public class NullPermsHandler implements oPermsHandler {
    
    @Override
    public String getGroup(final Player player) {
        return null;
    }

    @Override
    public List<String> getGroups(Player player) {
        return Collections.emptyList();
    }

    @Override
    public boolean inGroup(Player player, String group) {
        return false;
    }

    @Override
    public boolean hasPermission(Player player, String permissionNode) {
        return false;
    }

    @Override
    public String getPrefix(Player player) {
        return null;
    }

    @Override
    public String getSuffix(Player player) {
        return null;
    }
}
