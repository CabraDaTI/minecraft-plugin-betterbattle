package org.cabradati.betterbattle.sistemas.derrubardamontaria

import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.derrubardamontaria.events.DerrubarDaMontariaEvent
import org.cabradati.betterbattle.sistemas.utils.SistemaContainer

class DerrubarDaMontariaContainer(private val diContainer: DIContainer) : SistemaContainer {

    private val parametroAtivarSistema = diContainer.config.getBoolean(DerrubarDaMontariaConsts.ATIVAR_SISTEMA)

    override fun registerConfig() {

        val config = diContainer.config
        val plugin = diContainer.plugin

        diContainer.log("sistema - derrubar da montaria - registrando configurações")
        config.addDefault(DerrubarDaMontariaConsts.ATIVAR_SISTEMA, true)
        config.addDefault(DerrubarDaMontariaConsts.CHANCE, 40)
        config.options().copyDefaults(true)
        plugin.saveConfig()

    }

    override fun registerEvents() {

        val plugin = diContainer.plugin
        val server = diContainer.server

        if (parametroAtivarSistema) {

            diContainer.log("sistema - derrubar da montaria - registrando eventos")
            server.pluginManager.registerEvents(
                DerrubarDaMontariaEvent(diContainer),
                plugin
            )

        } else {
            diContainer.log("sistema - derrubar da montaria - eventos estão desabilitados")
        }

    }

    override fun registerSchedulers() {
        return
    }

}