package fr.lucidiax.episode5;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	Map<String, Integer> stats = new HashMap<String, Integer>();
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		int i = 1;
		if(stats.containsKey(player.getName()))
			i = stats.get(player.getName()) + 1;
		stats.put(player.getName(), i);
		TitleManager.sendTitle(player, "§6Félicitations !", "§7Vous venez de casser §c" + i + " §7blocks !", 100);
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event){
		Player player = event.getPlayer();
		TitleManager.sendActionBar(player, "§7Vous venez " + (event.isSneaking() ? "d'§aactiver" : "de §cdésactiver") + " §7votre sneak !");
	}
	
}
