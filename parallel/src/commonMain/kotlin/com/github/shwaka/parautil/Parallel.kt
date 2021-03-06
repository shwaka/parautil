package com.github.shwaka.parautil

// runBlocking, async, await do not exist on multiplatform project

public expect fun <T, R> Iterable<T>.pmap(transform: (T) -> R): List<R>
public expect fun <T, R : Any> Iterable<T>.pmapNotNull(transform: (T) -> R?): List<R>

public expect fun <T, R> Iterable<T>.pmapIndexed(transform: (Int, T) -> R): List<R>
public expect fun <T, R : Any> Iterable<T>.pmapIndexedNotNull(transform: (Int, T) -> R?): List<R>

public expect fun <T> Iterable<T>.pforEach(action: (T) -> Unit)
