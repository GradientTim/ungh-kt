# ungh-kt

Unofficial [Kotlin](https://kotlinlang.org/) driver for the [unjs/ungh](https://github.com/unjs/ungh) api

![kotlin](https://img.shields.io/badge/written%20in-Kotlin-f03c60?style=for-the-badge)
![license](https://img.shields.io/badge/license-MIT-f03c60?style=for-the-badge)
![version](https://img.shields.io/maven-central/v/dev.gradienttim/ungh-kt?style=for-the-badge&color=f03c60)

### Installation

Gradle (Kotlin)
```kotlin
dependencies {
    implementation("dev.gradienttim", "ungh-kt", "VERSION")
}
```

Gradle (Groovy)
```groovy
dependencies {
    implementation "dev.gradienttim:ungh-kt:VERSION"
}
```

Maven
```xml
<dependencies>
    <dependency>
        <groupId>dev.gradienttim</groupId>
        <artifactId>ungh-kt</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```

### Example usages

> These are just a few examples of how to use the library.  
> In the `Ungh` class you will find more functions to use all the features provided by the `unjs/ungh` api

#### Repository
```kotlin
val repository = Ungh.repository("unjs", "ungh")
if (repository == null) {
    println("unjs/ungh repository not found :/")
}
```

#### Organization
```kotlin
val organization = Ungh.organization("unjs")
organization?.let {
    println("Organization description: ${it.description}")
}
```

#### Stars
```kotlin
// val stars = Ungh.stars("GradientTim/ungh-kt") // single repo
val stars = Ungh.stars(listOf(
    "unjs/ungh",
    "Jetbrains/Kotlin",
    "GradientTim/ungh-kt",
))
stars?.let {
    println("Total stars: ${it.totalStars}")
    it.stars.forEach { (key, value) ->
        println("$key has $value stars")
    }
}
```

#### Users
```kotlin
val user = Ungh.user("GradientTim")
user?.let {
    println("Did you know that his name is ${it.name}?")
}
```

### License

This project is licensed under the [MIT](./LICENSE) license