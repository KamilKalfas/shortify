### Setup & Info

Project can be imported to android studio
- target SDK 30
- build and tested on Android 11
- used Android Studio Chipmunk | 2021.2.1 Patch 1

### Android Studio details

```
Android Studio Chipmunk | 2021.2.1 Patch 1
Build #AI-212.5712.43.2112.8609683, built on May 18, 2022
Runtime version: 11.0.12+0-b1504.28-7817840 aarch64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 12.4
GC: G1 Young Generation, G1 Old Generation
Memory: 4096M
Cores: 8
Registry: external.system.auto.import.disabled=true, debugger.watches.in.variables=false, kotlin.experimental.new.j2k=false
Non-Bundled Plugins: org.toml.lang (0.2.155.4114-212), org.intellij.plugins.markdown (212.5457.16), org.jetbrains.kotlin (212-1.7.10-release-333-AS5457.46)

```

### Technical decisions

Project uses Kotlin 1.7 and follows best practises promoted by Google Android Developers.
As dependencies project relies on AndroidX libraries, Google Material, Google Hilt.
The only third party dependency in production code is Ktor which is made by JetBrains team
As for testing I've chose mockk as I've been using it for past 5 years and feel most comfortable with.

#### Structure

Project is organised with clean architecture structure and loosely separated in feature packages for easier modularisation if needed.
Project uses MVVM where :
- presentation layer contains VM and compose screen
- domain: with business logic - usecases
- data: implementation detail with repository, data sources, network and database layers

#### Presentation layer
I've decided to use compose library for UI as there was no complex designs that would be difficult to made with library.
Compose has great potential and offers a feels easy and straightforward compared to XML.
Because of size and scope of this project it won over "the old way".

#### Data layer
I've decide to use repository and data source patterns. Repository uses local and remote data sources depending on requirements. Remote data source uses API for network calls and saves data to DB with database abstraction layer, in local source we only communicate with last one.  
To store data I've decide to use android library Room as it is battle tested and very easy to implement. On top of it I've put adapter as a place of separation of data 
persistence implementation. Even tho project scope for data persistence is so small it could've be done in static Map or saved to shared prefs. It cost lest time to use Room with small drawback of code generation.

#### Network
To make network calls I've decided to go with Ktor as it is fully written in Kotlin, so uses coroutines and doesn't use annotation processor as Retrofit. I've been using it since 
January, and 
I'm impressed 
by it 
flexibility, ease of configuration and implementation.

#### Dependency Injection
For DI I've choose Hilt, which is easier version of Dagger2, rather standard approach in Android realm.

#### Asynchronous operations
Since project uses Kotlin, the obvious choice was to use coroutines. For populating history in reactive manner I've used Flow, other actions are just one shot suspend functions.
