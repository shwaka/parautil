Simple utility library for parallel computation in kotlin.
For example, this library provides `pmap`, which is a parallel version of `map`.
Note that parallelization only works for JVM currently.
In other targets, `pmap` is simply an alias for `map`.

## Usage
Published at [shwaka/maven](https://github.com/shwaka/maven)

```kotlin
// build.gradle.kts
repositories {
    maven(url = "https://shwaka.github.io/maven/")
}

dependencies {
    // Any other condition can be used here:
    if (System.getProperty("parallel") == null) {
        implementation("com.github.shwaka.parautil:parautil-nonparallel-jvm:0.1")
    } else {
        implementation("com.github.shwaka.parautil:parautil-parallel-jvm:0.1")
    }
}
```

```bash
./gradlew run
./gradlew run -Dparallel
```
