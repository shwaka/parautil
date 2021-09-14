package com.github.shwaka.parautil

public actual fun <T, R> Iterable<T>.pmap(transform: (T) -> R): List<R> =
    this.map(transform)
public actual fun <T, R : Any> Iterable<T>.pmapNotNull(transform: (T) -> R?): List<R> =
    this.mapNotNull(transform)

public actual fun <T, R> Iterable<T>.pmapIndexed(transform: (Int, T) -> R): List<R> =
    this.mapIndexed(transform)
public actual fun <T, R : Any> Iterable<T>.pmapIndexedNotNull(transform: (Int, T) -> R?): List<R> =
    this.mapIndexedNotNull(transform)

public actual fun <T> Iterable<T>.pforEach(action: (T) -> Unit): Unit =
    this.forEach(action)
