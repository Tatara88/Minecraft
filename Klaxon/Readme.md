# Klaxon

This mod adds a customizable klaxon for each player.

Klaxons are shared via P2P between the players and are not stored on the server. There are two events making a player download the klaxon of another player:
* When a player A connects to a server, he automatically uploads its klaxon and sends a packet to notify the server that a klaxon has been uploaded. Then, the server sends the packet to all the players (including player A). Each player receiving the packet deletes the old klaxon for player A if any and downloads the new one.
* When a player A uses its klaxon, the server sends a packet indicating to all the players near player A that a klaxon has been used. Each player receiving the packet downloads the klaxon for player A if he doesn't already have it.

## How to craft it

![Crafting](/resources/crafting.png "Crafting")
