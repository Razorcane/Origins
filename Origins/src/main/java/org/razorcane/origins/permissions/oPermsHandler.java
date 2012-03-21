package org.razorcane.origins.permissions;

import java.util.List;
import org.bukkit.entity.Player;

public interface oPermsHandler {
    String getGroup(Player player);
    List<String> getGroups(Player player);
    boolean inGroup(Player player, String group);
    boolean hasPermission(Player player, String permissionNode);
    String getPrefix(Player player);
    String getSuffix(Player player);
}
