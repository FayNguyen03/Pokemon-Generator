# AND101 Project 5 - Choose Your Own API

Submitted by: **Khanh Tra Nguyen Tran**

Time spent: **2** hours spent in total

## Summary

**Choosing you Pokomon** is an android app that **randomly generates a Pokemon and returns the information about it or the user can choose their preferred Pokemon.**

If I had to describe this project in three (3) emojis, they would be: **👌🫡🐼**

## Application Features

The following REQUIRED features are completed:

- [X] Make an API call to an API of your choice using AsyncHTTPClient
- [X] Display at least three (3) pieces of data for each API entry retrieved
- [X] A working Button requests a new API entry and updates the data displayed

The following STRETCH features are implemented:

- [X] Add a query to the API request
  - The query I added is **https://pokeapi.co/api/v2/pokemon/$id/** When the user wants the app to randomly generate a Pokemon for them then the application will random a number from 0 to 1000. But if the user has their preferred pokemon, we will use the query **https://pokeapi.co/api/v2/pokemon/$name/**. 
- [X] Build a UI to allow users to add that query

The following EXTRA features are implemented:

- [ ] List anything else that you added to improve the app!

## API Choice

My chosen API for this project is **Pokémon API**.

## Video Demo

Here's a video / GIF that demos all of the app's implemented features:

<img src='Pikachu_Generator.gif' title='Video Demo' width='' alt='Video Demo' />

GIF created with **ScreenToGif**

<!-- Recommended tools:
- [Kap](https://getkap.co/) for macOS
- [ScreenToGif](https://www.screentogif.com/) for Windows
- [peek](https://github.com/phw/peek) for Linux. -->

## Notes

RestfulAPI is so fun! Gonna learn how to apply it to future projects!
## License

Copyright **2023** **Khanh Tra Nguyen Tran**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.