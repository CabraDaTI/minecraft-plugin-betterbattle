package org.cabradati.betterbattle.sistemas.nocaute.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.nocaute.NocauteConsts
import org.cabradati.betterbattle.sistemas.utils.SistemaEvent
import kotlin.random.Random

class NocauteEvent(
    diContainer: DIContainer
) : Listener, SistemaEvent<EntityDamageByEntityEvent> {

    private val parametroChance = diContainer.config.getInt(NocauteConsts.CHANCE)
    private val parametroMultiplicador = diContainer.config.getInt(NocauteConsts.MULTIPLICADOR_DE_DANO)

    @EventHandler(priority = EventPriority.NORMAL)
    override fun on(event: EntityDamageByEntityEvent) {
        if (event.damager !is Player && event.entity !is Player) return

        val probabilidadeDeOcorrer = Random.nextInt(1, 100) <= parametroChance

        if (probabilidadeDeOcorrer) {
            event.damage = event.damage * parametroMultiplicador
        }

    }

}