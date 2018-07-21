package io.lethinh.github.mantle.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.lethinh.github.mantle.Mantle;
import io.lethinh.github.mantle.utils.ItemStackFactory;

/**
 * Created by Le Thinh
 */
public class BlockBlockPlacer extends BlockMachine {

	// Placing helper thingy
	private BlockFace face = BlockFace.NORTH;

	public BlockBlockPlacer(Block block) {
		super(block, 45, "Block Placer");

		// Inventory
		for (int i = 27; i < 36; ++i) {
			inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1));
		}

		inventory.setItem(36, new ItemStackFactory(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 2))
				.setLocalizedName("North").build());
		inventory.setItem(37, new ItemStackFactory(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3))
				.setLocalizedName("South").build());
		inventory.setItem(38, new ItemStackFactory(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4))
				.setLocalizedName("East").build());
		inventory.setItem(39, new ItemStackFactory(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5))
				.setLocalizedName("West").build());
		inventory.setItem(40, new ItemStackFactory(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 6))
				.setLocalizedName("Up").build());
		inventory.setItem(41, new ItemStackFactory(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7))
				.setLocalizedName("Down").build());
	}

	@Override
	public void handleUpdate(Mantle plugin) {
		(subThread = new BukkitRunnable() {
			@Override
			public void run() {
				Block surround = block.getRelative(face);

				for (int i = 0; i < 27; ++i) {
					ItemStack content = inventory.getItem(i);

					if (!surround.isEmpty() || content == null || content.getAmount() == 0) {
						return;
					}

					surround.setType(content.getType());
					content.setAmount(content.getAmount() - 1);
					inventory.setItem(0, content);
				}
			}
		}).runTaskTimer(plugin, DEFAULT_DELAY, DEFAULT_PERIOD);
	}

	/* Event */
	@EventHandler
	public void onInventoryClicked(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();

		if (!this.inventory.getName().equals(inventory.getName())) {
			return;
		}

		ItemStack curStack = event.getCurrentItem();

		if (curStack == null || curStack.getAmount() == 0 || !Material.STAINED_GLASS_PANE.equals(curStack.getType())) {
			return;
		}

		if (event.getSlot() < 27) {
			return;
		}

		switch (curStack.getDurability()) {
		case 1:
			event.setCancelled(true);
			break;
		case 2:
			face = BlockFace.NORTH;
			event.setCancelled(true);
			break;
		case 3:
			face = BlockFace.SOUTH;
			event.setCancelled(true);
			break;
		case 4:
			face = BlockFace.EAST;
			event.setCancelled(true);
			break;
		case 5:
			face = BlockFace.WEST;
			event.setCancelled(true);
			break;
		case 6:
			face = BlockFace.UP;
			event.setCancelled(true);
			break;
		case 7:
			face = BlockFace.DOWN;
			event.setCancelled(true);
			break;
		default:
			break;
		}
	}

}