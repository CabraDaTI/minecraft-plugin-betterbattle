package org.cabradati.betterbattle.sistemas.arrowhitkill.events

import net.kyori.adventure.text.Component
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.cabradati.betterbattle.App
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.arrowhitkill.ArrowHitKillConsts
import org.cabradati.betterbattle.sistemas.arrowhitkill.utils.DistanceCalculatorUtil


class ArrowHitKillEvent(
    private val diContainer: DIContainer
) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onArrowHitKill(event: EntityDamageByEntityEvent) {

        if (event.damager.type != EntityType.ARROW) return
        if (event.entity.type != EntityType.PLAYER) return

        val distancia = diContainer.config.getInt(ArrowHitKillConsts.DISTANCE)
        val danoPelaMetade = diContainer.config.getBoolean(ArrowHitKillConsts.DANO_PELA_METADE)
        val habilitarDebug = diContainer.config.getBoolean(App.ATIVAR_DEBUG)

        val flecha = event.damager as Arrow
        val atirador = (event.damager as Arrow).shooter as Player
        val alvo = event.entity as LivingEntity

        val dist = DistanceCalculatorUtil.calculate(atirador.location, flecha.location)


        if (dist >= distancia) {
            event.damage = alvo.health
        } else if (dist >= (distancia / 2) && danoPelaMetade) {
            event.damage = alvo.health / 2
        }

        if (habilitarDebug) {
            diContainer.server.broadcast(
                Component.text(
                    event.damager.type.toString() +
                            " acertou " +
                            event.entity.type.toString() +
                            " distancia = " +
                            dist +
                            " damage = " +
                            event.damage
                )
            )
        }

    }

}