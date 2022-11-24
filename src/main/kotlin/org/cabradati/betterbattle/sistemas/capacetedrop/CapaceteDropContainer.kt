package org.cabradati.betterbattle.sistemas.capacetedrop

import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.capacetedrop.events.CapaceteDropEvent
import org.cabradati.betterbattle.sistemas.utils.SistemaContainer

class CapaceteDropContainer(private val diContainer: DIContainer) : SistemaContainer {

    private val parametroAtivarSistema = diContainer.config.getBoolean(CapaceteDropConsts.ATIVAR_SISTEMA)

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

        val plugin = diContainer.plugin
        val server = diContainer.server

        if (parametroAtivarSistema) {

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
        return
    }

}