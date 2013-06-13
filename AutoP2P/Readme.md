# AutoP2P

> This mod allows for automatic set up of a P2P network.

On server-side, it automatically set up the P2P network. On client-side, it makes players automatically join the network.

## In-Depth

For the P2P network to work, it requires a master peer known by all the others peers wanting to join the network. Here, the master peer is the Minecraft server and the others are the players.

On server-side, this mod hides all the process of starting the master peer that can be easily configured via `AutoP2P.cgf`. To make it easier for the players to join the network, it also hide the process of sending some informations as the `ip address` and the `port`.
