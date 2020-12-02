# Poke

This is an Android application showing a list of Pokémon and some details about them.
The app uses https://pokeapi.co to retreive the Pokémon data.

I've made the application by these guidelines:
1.	:heavy_check_mark: Min. SDK 21 
2.	:heavy_check_mark: Use Kotlin language   
3.	:heavy_check_mark: Use Clean Architecture (Repository pattern) and MVI (e.g. Uniflow lib) 
4.	:heavy_check_mark: Use JetPack: (ViewModel, Room, Navigation) 
5.	:heavy_check_mark: Use Koin (DI)   
6.	:heavy_check_mark: Use Retrofit2 and OkHttp3   
7.	:heavy_check_mark: Use Coroutines + Flow 
8.	:heavy_check_mark: Use Moshi and Glide 
9.	:heavy_check_mark: Create pagination with Jetpack’s Paging library 
10.	:heavy_check_mark: Publish code in a github public repository

Bonus tasks:

1.	Make app work offline too

  - The app is partly working in offline mode. The list data is stored in the database, but the details not.

2.	Write Unit Tests

  - Some example unit tests written for the app, data and presentation module.

3.	Customize the project with something you believe could be useful for the app

  - I didn't.

Further improvements should be: 
1. The units tests should cover the application.
2. The details screen should contain a lot more information about the Pokémon because the responses from the pokeapi.co are way more bigger than the app shows.

List | Details
------------ | -------------
![Image of List](https://github.com/Frankitoo/poke/blob/master/screenshots/screenshot_list.png) | ![Image of List](https://github.com/Frankitoo/poke/blob/master/screenshots/screenshot_details.png)
