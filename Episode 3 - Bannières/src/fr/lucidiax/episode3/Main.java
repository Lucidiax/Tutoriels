package fr.lucidiax.episode3;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {
	
	public Inventory i;
	
	public void onEnable(){
		i = Bukkit.createInventory(null, 54, "§aMon inventaire");
		i.setItem(0, makeItem(Material.ANVIL, 1, "§5Item stylé !", "Bienvenue sur", "la description de cet item"));
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	private ItemStack makeItem(Material material, int number, String name, String... lore){
		ItemStack stack = new ItemStack(material, number);
		ItemMeta meta = stack.getItemMeta();
		if(name != null)
			meta.setDisplayName(name);
		if(lore.length != 0){
			List<String> desc = new ArrayList<String>();
			for(String s : lore)
				desc.add(s);
			meta.setLore(desc);
		}
		stack.setItemMeta(meta);
		return stack;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if(event.getMessage().contains("test")){
			event.setCancelled(true);
			event.getPlayer().openInventory(i);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Inventory inventory = event.getInventory();
		if(inventory.getName().equals(i.getName())){
			event.setCancelled(true);
			Player player = (Player) event.getWhoClicked();
			ItemStack item = event.getCurrentItem();
			if(item == null || !item.hasItemMeta())
				return;
			if(item.getItemMeta().getDisplayName().equals("§5Item stylé !")){
				player.closeInventory();
				player.sendMessage("§aBravo à toi !");
				player.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(60*20, 2));
			}
		}
	}
	
}
