package io.lethinh.github.mantle.loader;

import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;

import io.lethinh.github.mantle.Mantle;
import io.lethinh.github.mantle.command.MantleAdminCommands;

/**
 * Created by Le Thinh
 */
public class CommandLoader implements ILoader {

	public CommandLoader() {
		preLoad();
	}

	@Override
	public void load(Mantle plugin) throws Exception {
		Logger logger = plugin.getLogger();
		logger.info("Registering commands...");

		registerCommand(plugin, "mantle", new MantleAdminCommands());

		logger.info("Registered commands!");
	}

	private static void registerCommand(Mantle plugin, String name, CommandExecutor executor) {
		plugin.getCommand(name).setExecutor(executor);
	}

}
