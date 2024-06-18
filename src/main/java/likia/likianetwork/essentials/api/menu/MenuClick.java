package likia.likianetwork.essentials.api.menu;

import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MenuClick {
    private UUID uuid;
    private ClickType click;
    private int slot;
    public MenuClick(@NotNull ClickType clickType, int clickSlot, @NotNull UUID uuid){
        slot = clickSlot;
        click = clickType;
        this.uuid =uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ClickType getClick() {
        return click;
    }

    public int getSlot() {
        return slot;
    }
}
