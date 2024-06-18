package likia.likianetwork.essentials;

import likia.likianetwork.essentials.api.menu.MenuListener;
import likia.likianetwork.essentials.commmand.CommandPingMSC;
import likia.likianetwork.essentials.commmand.CommandRedirect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    private boolean enableLNFeatures = false;

    public boolean getEnableLNFeatures(){
        return enableLNFeatures;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Loading Plugin!");
        Long starttime = System.currentTimeMillis();
        //register events
        Bukkit.getPluginManager().registerEvents(new essentialListener(),this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);
        //register commands
        Bukkit.getServer().getPluginCommand("pingmsc").setExecutor(new CommandPingMSC());
        Bukkit.getServer().getPluginCommand("redirect").setExecutor(new CommandRedirect());

        Long elapsed = System.currentTimeMillis() - starttime;
        System.out.println("Done! ("+elapsed+")");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Shutting down");
    }
}
