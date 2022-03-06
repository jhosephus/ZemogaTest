# Zemoga Tech Test

This project is an Android App to fullfill  the requeriments of Zemoga's tech test. It allows fetching "posts" from [JsonPlaceHolder](https://jsonplaceholder.typicode.com/), caching them on a local database. It also allows marking them as favourites and see their details.

## Installation

Use Android Studio to run/install the project on an emulator or a physical Android device.

## How to use it

When executed by the first time, it will look for posts in a local database, if no post was found, it will fetch them from the API, caching them in the database and show in the home screen. The home screen has two tabs: "All" and "Favourites". The first show all the posts, while the latter will show only the ones marked as favourites. When a post is marked as a favourite, it has a star-shaped icon on the left.

Is an item in the post list is touched/clicked, it will redirect to a detail screen, where more information is depicted: a description, the author information and all the comments related to the post. Author information and comments are also cached in local.

## Technologies used
<img src="https://4.bp.blogspot.com/-NnAkV5vpYuw/XNMYF4RtLvI/AAAAAAAAI70/kdgLm3cnTO4FB4rUC0v9smscN3zHJPlLgCLcBGAs/s1600/Jetpack_logo%2B%25282%2529.png" width="140" height="140" /> &nbsp; &nbsp; <img src="https://miro.medium.com/max/1400/1*o8Q_O-C6yGZQqW_2cdafoQ.png" width="200" height="140" /> &nbsp; &nbsp; <img src="https://3.bp.blogspot.com/-GOONRBOVwjo/WwWmZjvUb_I/AAAAAAAABqQ/IgFZ-m0CxFwiP5u1QxR5Hwzg3VkCwNK0QCLcBGAs/s1600/room.jpeg" width="240" height="140" /> &nbsp; &nbsp; <img src="https://miro.medium.com/max/1200/1*WMf1XcyKU98dOMlNnn-Agg.png" width="240" height="140" />

## Architecture

The project follows a MVVM architecture with Clean Architecture. It is divided in a core package and one package for feature (this is a single feature-case: posts). A feature has three layers: presentation, domain, data. All the componentes in the layers are decoupled.

### Presentation Layer

It contains the componentes related to the UI, like screen/fragments, and its state, stored in a ViewModel.

### Domain Layer

It contains the business logic of the app. It allows the interaction of the UI with any other components, in this case the data layer. It has interfaces that represent the repositories for all the types of dat that could be requested.

**UseCases**:
The business logic is encapsulated in UseCases, which are called by the ViewModel, and can call the repositories.

**Model**:
The model contain all the classes representing the data.

### Data Layer

It contains the components to access the information required to make use of the application.

**Local**:
This package contains the implementation of the local database in Room, caching all the incoming data from the API. It has the the Entities and DAOs.

**Remote**:
Contains all the classes and interfaces to interact  with the API using Retrofit.

**Repository**:
Here it is where the operations to fetch data take place, whether it comes from local or from a remote source.

