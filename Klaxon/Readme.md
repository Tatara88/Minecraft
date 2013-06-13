# Klaxon

> This mod adds a customizable klaxon for each player.

## In-Depth

With this mod, a new item called `Klaxon` is added to the game. A player can right-click while holding it to make a sound audible by all the players within a certain range. The range is the same as the one for sounds made by mods or items. Each player can use a custom `.ogg` file as its klaxon.

Klaxons are shared via P2P between the players and are not stored on the server. There are two events making a player download the klaxon of another player:
* When a player A connects to a server, he automatically uploads its klaxon and sends a packet to notify the server that a klaxon has been uploaded. Then, the server sends the packet to all the players (including player A). Each player receiving the packet deletes the old klaxon for player A if any and downloads the new one.
* When a player A uses its klaxon, the server sends a packet indicating to all the players near player A that a klaxon has been used. Each player receiving the packet downloads the klaxon for player A if he doesn't already have it.

A klaxon can be heard ingame only if it has been downloaded because of one of those two events. It means that a player won't hear its own klaxon if it has not been uploaded and downloaded before. It can be used as a way to detect if the P2P is working or not.

Once a klaxon has been downloaded, it is saved in the `.minecraft/config/klaxons` folder client-side and in `yourserver/config/klaxons` folder server-side.

## How to craft it

![Crafting](/resources/crafting.png "Crafting")
