package org.cabradati.betterbattle.sistemas.capacetedrop.events

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.capacetedrop.CapaceteDropConsts
import org.cabradati.betterbattle.sistemas.utils.SistemaEvent
import kotlin.random.Random


class CapaceteDropEvent(private val diContainer: DIContainer) : Listener, SistemaEvent<EntityDamageByEntityEvent> {

    private val parametroChance = diContainer.config.getInt(CapaceteDropConsts.CHANCE)

    @EventHandler(priority = EventPriority.LOW)
    override fun on(event: EntityDamageByEntityEvent) {

        if (!(event.damager is Player && event.entity is Player)) return
        if ((event.entity as Player).equipment.helmet == null) return

        val probabilidadeDeOcorrer = Random.nextInt(1, 100) <= parametroChance

        if (probabilidadeDeOcorrer) {

            val vitima = event.entity as Player

            vitima.equipment.helmet = ItemStack(Material.AIR)

            diContainer.server
                .getWorld(vitima.location.world.uid)
                ?.dropItem(vitima.location, vitima.equipment.helmet)

        }

    }

}