package likia.likianetwork.essentials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class essentialListener implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        if(event.getCause() == WeatherChangeEvent.Cause.NATURAL || event.getCause() == WeatherChangeEvent.Cause.SLEEP){
            event.setCancelled(true);
        }
    }
}
