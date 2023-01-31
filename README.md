# Java-Assignment

## General architecture overview and reasoning behind main technical choices

#### Model-View-ViewModel (MVVM):
This pattern is used as the architectural pattern, which helps to separate the business logic and presentation layer, making the code easier to maintain and test. 
It increases code reusability and modularity.

#### Activity/Fragment:
This component acts as the View in MVVM, responsible for presenting information to the user and receiving input from the user through views.

#### ViewModel:
This component holds the state and behavior of the data and communicates with the Model to retrieve and update the data. 
The ViewModel communicates with the View through data bindings and provides data for the View to display.

#### Repository:
This pattern is used as a single source of truth for accessing data from multiple sources (such as local Room database and remote server through Retrofit).
It makes code easier to maintain and test.

#### Room database:
This component stores the favorites data.
It provides a simple and efficient way to persist data.

#### Retrofit:
This component communicates with remote APIs to retrieve data.
It simplifies and streamlines the process of making HTTP requests.
It provides a clean and efficient way to handle requests, parse responses, and manage error handling.

#### Hilt:
This component is used for dependency injection, which helps to manage dependencies effectively and reduces code clutter.

#### RxJava:
This component is used for managing asynchronous data streams and processing data with reactive streams.

#### Navigation component:
This is used for managing the navigation between fragments, which helps to create smooth and seamless navigation experiences.

#### Glide:
This component is used for image loading and caching, which enhances the user experience by reducing image loading time.



## Features you didn't implement.
1. Infinite scrolling(Paging library)
2. UI that is better suitable for tablets
3. Search
4. No internet connection



## Improvements

#### Improve UI

#### Use a multi-module project structure
It helps to break down a project into smaller and more manageable modules. 
This makes it easier to maintain, test, and manage the project's codebase, as well as improve build times, increase code reusability, and reduce coupling between modules. 

#### Add unit tests
It increases the confidence in the code by verifying that individual units of code work as intended, independently of the rest of the code. 
Unit tests also make it easier to catch bugs early in the development process, reducing the time and cost of fixing them.

#### Use MVI instead of MVVM
It simplifies and streamlines the process of managing the state and behavior of a user interface. 
MVI provides a clear and concise way to handle user interactions and updates to the UI, making the code easier to understand and maintain. 
Additionally, MVI can improve testability, reduce bugs and improve the overall stability of the code.
