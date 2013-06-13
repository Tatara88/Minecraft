# Klaxon

> This mod adds a customizable klaxon for each player.

With this mod, a new item called `Klaxon` is added to the game. A player can right-click while holding it to make a sound audible by all the players within a certain range. The range is the same as the one for sounds made by mods or items. Each player can use a custom `.ogg` file as its klaxon.

## In-Depth

Klaxons are shared via P2P between the players and are not stored on the server. There are two events making a player download the klaxon of another player:
* When a player A connects to a server, he automatically uploads its klaxon and sends a packet to notify the server that a klaxon has been uploaded. Then, the server sends the packet to all the players (including player A). Each player receiving the packet deletes the old klaxon for player A if any and downloads the new one.
* When a player A uses its klaxon, the server sends a packet indicating to all the players near player A that a klaxon has been used. Each player receiving the packet downloads the klaxon for player A if he doesn't already have it.

A klaxon can be heard ingame only if it has been downloaded because of one of those two events. It means that a player won't hear its own klaxon if it has not been uploaded and downloaded before. It can be used as a way to detect if the P2P is working or not.

Once a klaxon has been downloaded, it is saved in the `.minecraft/config/klaxons` folder client-side and in the `yourserver/config/klaxons` folder server-side. You can easily check if the P2P is working by looking at these folders. Finally, it is added to the Minecraft sound manager. The klaxon can now be heard ingame.

## How to craft it

![Crafting](/resources/crafting.png "Crafting")

> The klaxon is a little bag filled with air that makes an annoying noise when pressed.

It can be crafted by putting one stick at each side of the grid and one leather at the center.

## How to install it

### Client

1. Make sure to have [Forge](http://www.minecraftforge.net/wiki/Installation/Universal) installed.
2. Installs the [P2P](https://github.com/Nauja/Minecraft/tree/master/P2P) and [AutoP2P](https://github.com/Nauja/Minecraft/tree/master/AutoP2P) mods.
3. Downloads the latest `Klaxon-x.x.x-x.x.x.zip` and put it into `.minecraft/mods`.
4. It's done.

### Server

1. Make sure to have [Forge](http://www.minecraftforge.net/wiki/Installation/Universal) installed.
2. Installs the [P2P](https://github.com/Nauja/Minecraft/tree/master/P2P) and [AutoP2P](https://github.com/Nauja/Minecraft/tree/master/AutoP2P) mods.
3. Downloads the latest `Klaxon-x.x.x-x.x.x.zip` and put it into `yourserver/mods`.
4. It's done.

## Configuration

### Client

The configuration file `Klaxon.cfg` can be found in the `.minecraft/config` folder. By default, it contains the following:

```
# Configuration file

####################
# general
####################

general {
    S:Klaxon=
}


####################
# item
####################

item {
    I:Klaxon=5000
}
```

* general
    * Klaxon: absolute path to your klaxon (`.ogg` file).
* item
    * Klaxon: item identifier.

### Server

The configuration file `Klaxon.cfg` can be found in the `yourserver/config` folder. By default, it contains the following:

```
# Configuration file

####################
# item
####################

item {
    I:Klaxon=5000
}
```

* item
    * Klaxon: item identifier.

## Bugs

Feel free to report any bug [here](https://github.com/Nauja/Minecraft/issues).

* Downloaded klaxons are saved on disk and added to the Minecraft sound manager. When a player reconnects with a new klaxon, you will download it again and have the new version in the `klaxons` folder. However you will not hear this new version ingame as the sound manager will not update it. Without changing the Minecraft class, it is impossible to refresh an existing sound without adding it with a new name and keeping the old version in memory.
