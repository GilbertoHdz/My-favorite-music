# My Favorite Music

A soundtrack guide application

## Getting Started

1. Clone the project to your local machine.
2. Open the project using Android Studio.

### Installation

Download the project and Run on physical device or virtual device with API level 21 / Android 5.0 and above.

## Project Structure Approach
    # Presentation Module
    1. Using MVI/MVRx approach as architecture on the ui layer to manage unidirectional data flow.
        - Using this should be easy to migrate jetpack compose since the architecture is similar to recommended layer from Google.
        - Fast development since we are using `viewbinding` instead of kotlin synthetics
        - State management architecture approach
    2. Create Base classes to reduce boiler plate code and override the MV* structure in the UIs
    3. Using `databinding` in order to manage a reactive UI from `[class]ViewData` with MutableLiveData as observer.
    [TODO] Include jetpack compose widget as improvement

    # Domain Module
    1. Creating `interactor` to provide the use cases that will interact with the API.
    2. Creating `repository` with interface as access layer to transform the request from :data: module API
    3. Creating `models` for the UI to manage the :data:api:model requests to handle the :presentation: Ui
    [TODO] Create Preferences interfaces to local persistence.

    # Data Module
    1. Creating `api` to register the ApiServices and implement each endpoint with retrofit.
    2. Creating `api.models` to creating the models with contains the objects provided by the API request.
    3. Create `repository` class implementations to manage the request API on :data layer
    [TODO] Finish Database implementations to store favorite soundtrack

## Built With
* [Dagger Hilt](https://dagger.dev/) - A pragmatic dependency injection framework for Java, Kotlin, and Android.
* [Jetpack](https://developer.android.com/jetpack) Using `ViewModel` to manage the state and `Lifecycle` to manage the lifecycle
* [Glide](https://github.com/bumptech/glide) - A efficient open source media management and image loading framework for Android.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
* [OkHttp](https://square.github.io/okhttp/) - Using a modern HTTP to handle the interceptors and configure the providers.
* [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Using Kotlin flow to manage ui state after each changes
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments

## TODO List
* Implement Persistence data to store favorite soundtrack.
* Implement ExoPlayer to reproduce short song or preview movie.
* Improve UIDesign and use compose toolbar.

## Meta
Gilberto Hernández G. – [gilbertohdz.dev](https://gilbertohdz.dev/) - [@_GilbertoHdz_](https://twitter.com/_GilbertoHdz_) – ghernandez.9002@gmail.com

## License
[MIT](https://choosealicense.com/licenses/mit/)
