package likia.likianetwork.essentials.api.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class UserMenu {
    public abstract int getRows();
    public abstract int getUpdateTicks();
    public abstract MenuOperation onClick(int location, ClickType clickType);
    public abstract ItemStack[] getMenuDisplay(Long update);
    public abstract @NotNull String getTitle();
    public abstract void setPlayer(Player p);
}
