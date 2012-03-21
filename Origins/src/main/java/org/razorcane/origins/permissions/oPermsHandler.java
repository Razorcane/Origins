package org.razorcane.origins.permissions;

import java.util.List;
import org.bukkit.entity.Player;

public interface oPermsHandler {
    String getGroup(Player base);
    List<String> getGroups(Player base);
    boolean inGroup(Player base, String group);
    boolean hasPermission(Player base, String node);
    String getPrefix(Player base);
    String getSuffix(Player base);
}
