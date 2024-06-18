package likia.likianetwork.essentials.commmand;

import likia.likianetwork.essentials.api.connect.ConnectionClient;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandPingMSC implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp()){
            try {
                List<String> arr = ConnectionClient.createSocketConnection("Ping",new String[]{System.currentTimeMillis()+""});
                if(arr.get(0).equals("Status=Fine")){
                    sender.sendMessage("§a"+arr.get(1)+"ms");
                }else{
                    sender.sendMessage("§c"+arr.get(1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
