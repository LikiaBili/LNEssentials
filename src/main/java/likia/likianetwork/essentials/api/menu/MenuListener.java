package likia.likianetwork.essentials.api.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import likia.likianetwork.essentials.api.menu.MenuHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        try {
            UUID uuid = ((MenuHolder)event.getClickedInventory().getHolder()).getUuid();
            if(uuid != null){
                System.out.println("slot:"+event.getRawSlot()+" uuid:"+uuid);
                MenuAPI.addPendingClick(new MenuClick(event.getClick(),event.getRawSlot(),uuid));
                event.setCancelled(true);
            }
        }catch (Exception e){}
    }
}
