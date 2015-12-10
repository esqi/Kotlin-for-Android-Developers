package net.esqiaktiadi.weatherapp.domain.commands

interface Command<T> {
    fun execute(): T
}