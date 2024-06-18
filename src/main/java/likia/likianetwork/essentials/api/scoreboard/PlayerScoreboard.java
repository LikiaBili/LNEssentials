package likia.likianetwork.essentials.api.scoreboard;

import likia.likianetwork.essentials.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerScoreboard {
    private String boardDisplayName = "Sample Scoreboard";
    private UUID boardUUID = null;
    private List<String> scoreboardLines = new ArrayList<>();

    protected String getBoardDisplayName(){
        return boardDisplayName;
    }

    protected List<String> getScoreboardLines(){
        return scoreboardLines;
    }

    public PlayerScoreboard(UUID uuid) {
        boardUUID = uuid;
    }

    public UUID getBoardUUID(){
        return boardUUID;
    }

    public void resetBoard(boolean areYouSerious){
        if(areYouSerious){
            scoreboardLines = new ArrayList<>();
        }
    }

    public void setBoardDisplayName(String newName){
        boardDisplayName = newName;
    }

    public void setLine(int line,String message){
        if(line >= scoreboardLines.size()){
            int range = scoreboardLines.size();
            while (true){
                if(range >= line - 1){break;}
                scoreboardLines.add(" ");
                range++;
            }
            scoreboardLines.add(message);
        }else {
            scoreboardLines.set(line,message);
        }
    }

    public void addLine(String message){
        scoreboardLines.add(message);
    }

    private boolean tracking = false;

    public void trackScoreboardForPlayer(Player player, Long refreshTime){
        tracking = true;
        String playername = player.getName();
        org.bukkit.scoreboard.ScoreboardManager sbManager = Bukkit.getScoreboardManager();
        new BukkitRunnable(){
            Scoreboard sb = sbManager.getNewScoreboard();
            Long refresh = 2L;
            boolean firstRun = true;
            Objective lastObjective = sb.registerNewObjective("1","dummy",boardDisplayName);
            @Override
            public void run() {
                Objective currentObjective = null;
                if(sb.getObjective(refresh+"") != null){
                    currentObjective = sb.getObjective(refresh+"");
                }else {
                    currentObjective = sb.registerNewObjective(refresh+"","dummy",boardDisplayName);
                }

                int range = 0;
                List<String> lines = scoreboardLines;
                int len = lines.size();
                while (true){
                    if(range >= len){break;}

                    Score score = currentObjective.getScore(lines.get(range));
                    score.setScore(len-range);

                    range++;
                }
                currentObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
                player.setScoreboard(currentObjective.getScoreboard());

                lastObjective.setDisplaySlot(null);
                lastObjective.unregister();
                lastObjective = currentObjective;
                firstRun = false;

                if(!tracking || Bukkit.getPlayer(playername) == null){
                    this.cancel();
                }
                refresh = refresh + 1L;
            }
        }.runTaskTimerAsynchronously(main.getProvidingPlugin(likia.likianetwork.essentials.main.class),0,refreshTime);
    }

    public void unTrackAll(){
        tracking = false;
    }
}
