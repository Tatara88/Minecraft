# AutoP2P

> This mod allows for automatic set up of a P2P network.

On server-side, it automatically set up the P2P network. On client-side, it makes players automatically join the network.

## In-Depth

For the P2P network to work, it requires a master peer known by all the others peers wanting to join the network. Here, the master peer is the Minecraft server and the others are the players.

On server-side, this mod hides all the process of starting the master peer. To make it easier for the players to join the network, it also takes care of sending some informations as the `ip address` and the `port` to players connecting to the Minecraft server so that they don't have to configure it by themselves. The master peer can be configured via the `AutoP2P.cfg` described below.

On client-side, this mod hides all the process of starting a peer and joining the P2P network when informations from the master peer have been received. This way, players don't have to make a separate configuration for each server and don't have to search for these informations.


