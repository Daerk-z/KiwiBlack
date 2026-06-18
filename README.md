# KiwiBlack

![Version](https://img.shields.io/badge/Version-1.0.0-black)
![Spigot](https://img.shields.io/badge/Spigot-1.21.8-yellow)
![Java](https://img.shields.io/badge/Java-21-blue)

A Minecraft testing plugin with different uses such as items, commands, player events, menus, mining rewards, a basic economy system and basic database that stores the player's economy information.

## Features

- Provide plugin information using the main command "/kb"
- Give the virtual coin from the plugin
- Teleport request to another player
- Enable fly mode for you or for other players 
- Give experience levels for you or for other players
- Give a default item
- Open a custom menu

## Requeriments

- Minecraft version: 1.21.8
- Required Java: 21
- Compatible API: Spigot

## Installation 

- Go and download the latest release [here](https://github.com/Daerk-z/KiwiBlack/releases/tag/1.0.0)
- Download the `.jar` file
- Put the `.jar` file in the plugin folder of your server
- Restart your server to enable the plugin 

## Configuration

Information about the config file `config.yml`.

```yaml
#######################################################################
#  ████████▄     ▄████████    ▄████████    ▄████████    ▄█   ▄█▄      #
#  ███   ▀███   ███    ███   ███    ███   ███    ███   ███ ▄███▀      #
#  ███    ███   ███    ███   ███    █▀    ███    ███   ███▐██▀        #
#  ███    ███   ███    ███  ▄███▄▄▄      ▄███▄▄▄▄██▀  ▄█████▀         #
#  ███    ███ ▀███████████ ▀▀███▀▀▀       ███▀▀▀▀▀   ▀▀█████▄         #
#  ███    ███   ███    ███   ███    █▄   ▀██████████   ███▐██▄        #
#  ███   ▄███   ███    ███   ███    ███   ███    ███   ███ ▀███▄      #
#  ████████▀    ███    █▀    ██████████   ███    ███   ███   ▀█▀      #
#                                                                     #
#######################################################################

#
# Modificable prefix from the plugin
#
config:
  prefix: "&7[&a&lK&f&lB&7]"

#
# Custom welcome message when a player joins to the world
#
  welcome_message:
    enable: true
    message:
    - '&a&m                        '
    - '&e¡Bienvenido &a%player%&e!'
    - ''
    - '&e Mensaje del día: &aMiau'
    - '&a&m                        '

#
# Custom teleport when a player joins to the world
#
  teleport_on_join:
    enable: false
    Location:
    - 'world'      # Mundo
    - '268.5'      # Coordenada X
    - '-24.5'      # Coordenada Y
    - '119'        # Coordenada Z
    - '90'         # Yaw
    - '0'          # Pitch

#
# 
#
  block_break_rewards:
    enabled: true
    blocks:
      COAL_ORE:
        chance: 25 # 25%
        multiplier: 1.0
      DEEPSLATE_COAL_ORE:
        chance: 25 # 25%
        multiplier: 1.0  
      IRON_ORE:
        chance: 20 # 20%
        multiplier: 1.0
      DEEPSLATE_IRON_ORE:
        chance: 20 # 20%
        multiplier: 1.0
      GOLD_ORE:
        chance: 15 # 15%
        multiplier: 1.0
      DEEPSLATE_GOLD_ORE:
        chance: 15 # 15%
        multiplier: 1.0
      DIAMOND_ORE:
        chance: 10 # 10%
        multiplier: 1.0
      DEEPSLATE_DIAMOND_ORE:
        chance: 10 # 10%
        multiplier: 1.0
      EMERALD_ORE:
        chance: 5 # 5%
        multiplier: 1.0
      DEEPSLATE_EMERALD_ORE:
        chance: 5 # 5%
        multiplier: 1.0
      LAPIS_ORE:
        chance: 10 # 10%
        multiplier: 1.0
      DEEPSLATE_LAPIS_ORE:
        chance: 10 # 10%
        multiplier: 1.0
      NETHER_QUARTZ_ORE:
        chance: 20 # 20%
        multiplier: 1.0
      ANCIENT_DEBRIS:
        chance: 20 # 20%
        multiplier: 1.0

messages:
  prevent_block_break: " &c¡No puedes romper bloques aquí!"

  block_words_message: " &c¡No puedes escribir eso aquí!"
  ```

  ## Commands and uses

  -
  -
  -