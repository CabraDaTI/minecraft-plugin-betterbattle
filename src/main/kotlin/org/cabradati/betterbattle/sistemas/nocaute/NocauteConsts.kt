package org.cabradati.betterbattle.sistemas.nocaute

import org.cabradati.betterbattle.App

class NocauteConsts {

    companion object {
        private const val PREFIX = App.SYSTEMAS_PREFIX + "nocaute."
        const val ATIVAR_SISTEMA = PREFIX + "enabled"
        const val CHANCE = PREFIX + "chance"
        const val MULTIPLICADOR_DE_DANO = PREFIX + "damage_multiply"
    }

}