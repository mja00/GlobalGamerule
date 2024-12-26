package dev.mja00.globalGamerule;

import dev.mja00.globalGamerule.events.GameruleUpdate;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class GlobalGamerule extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getComponentLogger().info(Component.text("GlobalGamerule plugin enabled"));
        getServer().getPluginManager().registerEvents(new GameruleUpdate(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getComponentLogger().info(Component.text("GlobalGamerule plugin disabled"));
    }
}
