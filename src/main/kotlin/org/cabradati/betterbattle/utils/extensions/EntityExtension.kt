package org.cabradati.betterbattle.utils.extensions

import org.bukkit.entity.Entity
import org.bukkit.entity.Player

fun Entity.isPlayer(): Boolean {
    return this is Player
}