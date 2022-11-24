package org.cabradati.betterbattle.sistemas.utils

interface SistemaEvent<T> {

    fun on(event: T)

}