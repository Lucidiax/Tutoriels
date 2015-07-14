package fr.lucidiax.episode1;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onSneak(PlayerJoinEvent event){
		Player player = event.getPlayer();
		event.setJoinMessage("§e" + player.getName() + " §9à rejoint le serveur.");
		ItemStack tete = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta sm = (SkullMeta) tete.getItemMeta();
		sm.setDisplayName("§6Ma tête");
		sm.setLore(Arrays.asList("§bVoici ma", "§6tête§b !"));
		sm.setOwner(player.getName());
		tete.setItemMeta(sm);
		player.getInventory().addItem(tete);
	}

}
