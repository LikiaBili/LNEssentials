package likia.likianetwork.essentials.api.menu;

import likia.likianetwork.essentials.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class MenuAPI {
    private static List<MenuClick> pendingClicks = new ArrayList<>();

    public static void openMenuForPlayer(Player player,UserMenu menu){
        menu.setPlayer(player);
        final int rows = menu.getRows();
        if(menu.getMenuDisplay(-1L).length%9 == 0 && menu.getMenuDisplay(-1L).length/9 == rows && rows >= 0 && menu.getUpdateTicks() > 0){ //detect if menu is legal
            //setup
            Inventory inventory = Bukkit.createInventory(new MenuHolder(),rows*9,menu.getTitle());
            inventory.setContents(menu.getMenuDisplay(0L));
            player.openInventory(inventory);
            String playername = player.getName();
            UUID uuid = ((MenuHolder)inventory.getHolder()).getUuid();
            System.out.println("create uuid:"+uuid);
            new BukkitRunnable(){
                Long update = 1L;
                @Override
                public void run() {
                    UserMenu menui = menu;
                    try {
                        MenuHolder inventoryHolder = (MenuHolder) player.getOpenInventory().getTopInventory().getHolder();
                        if (!inventoryHolder.getClass().getName().equals("Player")) {
                            if (inventoryHolder.getUuid() != uuid || Bukkit.getPlayer(playername) == null) {//end if quit
                                this.cancel();
                            }
                        }
                    }catch (Exception e){
                        this.cancel();
                    }
                    for (int i = 0; i < pendingClicks.size(); i++) {
                       // System.out.println(pendingClicks.get(i).getUuid().toString()+"  |  "+uuid.toString());
                        if(pendingClicks.get(i).getUuid().toString().equals(uuid.toString())){
                            //System.out.println("AC");
                            MenuOperation mop = menui.onClick(pendingClicks.get(i).getSlot(),pendingClicks.get(i).getClick());
                            pendingClicks.remove(i);
                            if(mop != null) {
                                switch (mop) {
                                    case QUIT:
                                        player.getOpenInventory().close();
                                        this.cancel();
                                        break;
                                }
                            }
                        }
                    }

                    if(menui.getMenuDisplay(update).length%9 == 0 && menui.getMenuDisplay(update).length/9 == rows && menui.getUpdateTicks() > 0) { //detect if menu STILL is legal
                        inventory.setContents(menui.getMenuDisplay(update));
                    }
                    update++;
                }
            }.runTaskTimer(main.getProvidingPlugin(likia.likianetwork.essentials.main.class),0,menu.getUpdateTicks());
        }
    }

    public static void addPendingClick(MenuClick menuClick){
        pendingClicks.add(menuClick);
    }
}
