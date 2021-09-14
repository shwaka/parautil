package com.github.shwaka.parautil

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

public actual fun <T, R> Iterable<T>.pmap(transform: (T) -> R): List<R> = runBlocking {
    this@pmap.map {
        async(Dispatchers.Default) { transform(it) }
    }.map { it.await() }
}
public actual fun <T, R : Any> Iterable<T>.pmapNotNull(transform: (T) -> R?): List<R> = runBlocking {
    this@pmapNotNull.map {
        async(Dispatchers.Default) { transform(it) }
    }.mapNotNull { it.await() }
}

public actual fun <T, R> Iterable<T>.pmapIndexed(transform: (Int, T) -> R): List<R> = runBlocking {
    this@pmapIndexed.mapIndexed { i, t ->
        async(Dispatchers.Default) { transform(i, t) }
    }.map { it.await() }
}

public actual fun <T, R : Any> Iterable<T>.pmapIndexedNotNull(transform: (Int, T) -> R?): List<R> = runBlocking {
    this@pmapIndexedNotNull.mapIndexed { i, t ->
        async(Dispatchers.Default) { transform(i, t) }
    }.mapNotNull { it.await() }
}

public actual fun <T> Iterable<T>.pforEach(action: (T) -> Unit): Unit = runBlocking {
    this@pforEach.map {
        async(Dispatchers.Default) { action(it) }
    }.forEach { it.await() }
}
