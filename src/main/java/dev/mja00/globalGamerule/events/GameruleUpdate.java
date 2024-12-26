package dev.mja00.globalGamerule.events;

import dev.mja00.globalGamerule.GlobalGamerule;
import io.papermc.paper.event.world.WorldGameRuleChangeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.java.JavaPlugin;

public class GameruleUpdate implements Listener {

    JavaPlugin plugin = GlobalGamerule.getPlugin(GlobalGamerule.class);

    @EventHandler
    public void onGameruleChange(WorldGameRuleChangeEvent event) {
        GameRule<?> gameRule = event.getGameRule();
        CommandSender sender = event.getCommandSender();
        String newValue = event.getValue();
        // If the sender is null, just ignore it
        // Most likely the event was triggered by a plugin
        if (sender == null) {
            return;
        }
        Component senderName = sender.name();

        plugin.getComponentLogger().info("Gamerule {} changed to {} by {}", gameRule.getName(), newValue, senderName);
        // Get all loaded worlds for the server and update the game rule for each world
        for (String worldName : GlobalGamerule.getPlugin(GlobalGamerule.class).getServer().getWorlds().stream().map(WorldInfo::getName).toList()) {
            plugin.getComponentLogger().info("Updating gamerule {} for world {}", gameRule.getName(), worldName);
            // Get the gamerule type and cast it to the correct type
            World world = plugin.getServer().getWorld(worldName);
            if (world == null) {
                plugin.getComponentLogger().warn("World {} not found, skipping update", worldName);
                continue;
            }
            world.setGameRuleValue(gameRule.getName(), newValue);
        }
    }
}