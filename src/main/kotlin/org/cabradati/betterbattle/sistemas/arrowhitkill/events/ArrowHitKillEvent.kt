package org.cabradati.betterbattle.sistemas.arrowhitkill.events

import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.arrowhitkill.ArrowHitKillConsts
import org.cabradati.betterbattle.sistemas.arrowhitkill.utils.DistanceCalculatorUtil


class ArrowHitKillEvent(diContainer: DIContainer) : Listener {

    private val parametroDistancia = diContainer.config.getInt(ArrowHitKillConsts.DISTANCE)
    private val parametroDistancePelaMetade = diContainer.config.getBoolean(ArrowHitKillConsts.DANO_PELA_METADE)

    @EventHandler(priority = EventPriority.NORMAL)
    fun onArrowHitKill(event: EntityDamageByEntityEvent) {
        if (!(event.damager is Arrow && event.entity is Player)) return

        val flecha = event.damager as Arrow
        val atirador = flecha.shooter as Player
        val alvo = event.entity as Player

        val dist = DistanceCalculatorUtil.calculate(
            atirador.location,
            flecha.location
        )

        if (dist >= parametroDistancia) {
            alvo.health = 0.0
        } else if (dist >= (parametroDistancia / 2) && parametroDistancePelaMetade) {
            event.damage = alvo.health / 2
        }

    }

}