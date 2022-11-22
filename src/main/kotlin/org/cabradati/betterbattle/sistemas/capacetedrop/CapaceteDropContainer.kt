package org.cabradati.betterbattle.sistemas.capacetedrop

import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.capacetedrop.events.CapaceteDropEvent
import org.cabradati.betterbattle.sistemas.utils.SistemaContainer

class CapaceteDropContainer(private val diContainer: DIContainer) : SistemaContainer {

    override fun registerConfig() {

        val config = diContainer.config
        val plugin = diContainer.plugin

        diContainer.log("sistema - capacete drop - registrando configurações")
        config.addDefault(CapaceteDropConsts.ATIVAR_SISTEMA, true)
        config.addDefault(CapaceteDropConsts.CHANCE, 5)
        config.options().copyDefaults(true)
        plugin.saveConfig()

    }

    override fun registerEvents() {

        val config = diContainer.config
        val plugin = diContainer.plugin
        val server = diContainer.server

        if (config.getBoolean(CapaceteDropConsts.ATIVAR_SISTEMA)) {

            diContainer.log("sistema - capacete drop - registrando eventos")
            server.pluginManager.registerEvents(
                CapaceteDropEvent(diContainer),
                plugin
            )

        } else {
            diContainer.log("sistema - capacete drop - eventos estão desabilitados")
        }

    }

    override fun registerSchedulers() {
    }

}