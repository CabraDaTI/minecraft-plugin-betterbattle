package org.cabradati.betterbattle.sistemas.nocaute.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.nocaute.NocauteConsts
import kotlin.random.Random

class NocauteEvent(private val diContainer: DIContainer): Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onNocaute(event: EntityDamageByEntityEvent) {

        if (event.damager !is Player && event.entity !is Player) return
        if ((event.entity as Player).equipment.helmet == null) return

        val chance = diContainer.config.getInt(NocauteConsts.CHANCE)
        val probabilidadeDeOcorrer = Random.nextInt(1, 100) <= chance

        diContainer.debug("chance: $chance, resultado: $probabilidadeDeOcorrer")

        if (probabilidadeDeOcorrer) {

            val player = event.entity as Player
            player.damage(diContainer.config.getDouble(NocauteConsts.DANO))

        }

    }

}