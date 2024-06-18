package likia.likianetwork.essentials.commmand;

import likia.likianetwork.essentials.api.connect.ConnectionClient;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CommandRedirect implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()) {
            try {
                ConnectionClient.createSocketConnection("Redirect", new String[]{args[0], args[1]});
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage("§cError : "+e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }
}
