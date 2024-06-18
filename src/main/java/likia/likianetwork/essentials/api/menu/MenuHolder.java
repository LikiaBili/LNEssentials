package likia.likianetwork.essentials.api.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MenuHolder implements InventoryHolder {
    private UUID uuid;

    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }

    public MenuHolder(){
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUuidString(){
        return uuid.toString();
    }
}
