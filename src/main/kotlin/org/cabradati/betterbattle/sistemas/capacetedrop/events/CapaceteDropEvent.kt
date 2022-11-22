package org.cabradati.betterbattle.sistemas.capacetedrop.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.capacetedrop.CapaceteDropConsts
import kotlin.random.Random


class CapaceteDropEvent(private val diContainer: DIContainer) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onCapaceteDropEvent(event: EntityDamageByEntityEvent) {

        if (event.damager !is Player && event.entity !is Player) return
        if ((event.entity as Player).equipment.helmet?.amount == 0) return

        val chance = diContainer.config.getInt(CapaceteDropConsts.CHANCE)
        val probabilidadeDeOcorrer = Random.nextInt(1,100) <= chance

        diContainer.debug("chance: $chance, resultado: $probabilidadeDeOcorrer")

        if (probabilidadeDeOcorrer) {
            val capacete = (event.entity as Player).equipment.helmet
            (event.entity as Player).equipment.helmet = null
            (event.entity as Player).inventory.addItem(capacete)
        }

    }

}