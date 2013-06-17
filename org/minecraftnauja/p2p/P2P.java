package org.minecraftnauja.p2p;

import java.util.HashMap;
import java.util.Map;

import org.minecraftnauja.p2p.exception.AlreadyRegisteredNameException;
import org.minecraftnauja.p2p.provider.IProvider;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * P2P API.
 */
@Mod(modid = "P2P", name = "P2P", version = "1.0.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class P2P {

	/**
	 * Mod instance.
	 */
	@Instance("P2P")
	public static P2P instance;

	/**
	 * Name of the server provider.
	 */
	public static final String SERVER_PROVIDER = "Server";

	/**
	 * Name of the client provider.
	 */
	public static final String CLIENT_PROVIDER = "Client";

	/**
	 * Registers given provider.
	 * 
	 * @param provider
	 *            provider to register.
	 * @throws AlreadyRegisteredNameException
	 *             trying to register two providers with the same name.
	 */
	public static void register(IProvider provider)
			throws AlreadyRegisteredNameException {
		String name = provider.getName();
		IProvider p = instance.providers.get(name);
		if (p != null) {
			throw new AlreadyRegisteredNameException(provider, name, p);
		} else {
			instance.providers.put(name, provider);
		}
	}

	/**
	 * Gets the provider with given name.
	 * 
	 * @param name
	 *            name of the provider.
	 * @return corresponding provider.
	 */
	public static IProvider get(String name) {
		return instance.providers.get(name);
	}

	/**
	 * Unregisters given provider.
	 * 
	 * @param provider
	 *            provider to unregister.
	 * @return the unregistered provider.
	 */
	public static IProvider unregister(IProvider provider) {
		return instance.providers.remove(provider.getName());
	}

	/**
	 * Registered providers.
	 */
	private Map<String, IProvider> providers;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		providers = new HashMap<String, IProvider>();
	}

}
