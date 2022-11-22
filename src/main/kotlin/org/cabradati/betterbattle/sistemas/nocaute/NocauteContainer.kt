package org.cabradati.betterbattle.sistemas.nocaute

import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.nocaute.events.NocauteEvent
import org.cabradati.betterbattle.sistemas.utils.SistemaContainer

class NocauteContainer(private val diContainer: DIContainer) : SistemaContainer {

    override fun registerConfig() {

        val config = diContainer.config
        val plugin = diContainer.plugin

        diContainer.log("sistema - nocaute - registrando configurações")
        config.addDefault(NocauteConsts.ATIVAR_SISTEMA, true)
        config.addDefault(NocauteConsts.CHANCE, 5)
        config.addDefault(NocauteConsts.DANO, 10)
        config.options().copyDefaults(true)
        plugin.saveConfig()

    }

    override fun registerEvents() {

        val config = diContainer.config
        val plugin = diContainer.plugin
        val server = diContainer.server

        if (config.getBoolean(NocauteConsts.ATIVAR_SISTEMA)) {

            diContainer.log("sistema - nocaute - registrando eventos")
            server.pluginManager.registerEvents(
                NocauteEvent(diContainer),
                plugin
            )

        } else {
            diContainer.log("sistema - nocaute - eventos estão desativados")
        }

    }

    override fun registerSchedulers() {
    }

}