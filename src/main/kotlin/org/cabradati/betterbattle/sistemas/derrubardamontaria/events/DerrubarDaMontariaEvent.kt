package org.cabradati.betterbattle.sistemas.derrubardamontaria.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.derrubardamontaria.DerrubarDaMontariaConsts
import org.cabradati.betterbattle.sistemas.utils.SistemaEvent
import kotlin.random.Random

class DerrubarDaMontariaEvent(
    diContainer: DIContainer
) : Listener, SistemaEvent<EntityDamageByEntityEvent> {

    private val parametroChance = diContainer.config.getInt(DerrubarDaMontariaConsts.CHANCE)

    @EventHandler(priority = EventPriority.LOW)
    override fun on(event: EntityDamageByEntityEvent) {

        if (!(event.damager is Player && event.entity is Player)) return
        if ((event.entity as Player).vehicle == null) return

        val probabilidadeDeOcorrer = Random.nextInt(1, 100) <= parametroChance

        if (probabilidadeDeOcorrer) {
            event.entity.leaveVehicle()
        }

    }

}