# Dependency Injection in Android with Dagger 2

Tutorial application for the [course about Dependency Injection in Android](https://www.udemy.com/dependency-injection-in-android-with-dagger).

_Source Github [link](https://github.com/techyourchance/dependency-injection-in-android-course)_

---

## What one will learn

* Theory and Fundamentals of Dependency Injection
* Dependency Injection Architectural Pattern
* Implemetation of Pure Dependency Injection without using 3rd party frameworks
* Integration of Dagger 2 dependency injection in the simplest and cleanest way
* Dependency injection approaches for ViewModels using Dagger 2

---

## Dependencies used

* Android Support library
* **[Retrofit](https://square.github.io/retrofit/)** for communicating with **[Stackoverflow API](https://api.stackexchange.com/docs)**
* **[Glide](https://bumptech.github.io/glide/)** to load user profile images
* **ViewModel** for maintaining the data of the App and to show the dependency injection approaches for the same
* **[Dagger2](https://google.github.io/dagger/)** for Dependency Injection

---

## Branches in this Repository

* **[PureDI](https://github.com/kaushiknsanji/dependency-injection-in-android-course/tree/PureDI)**
	* Covers the implementation of Dependency Injection Architectural Pattern without the use of 3rd party frameworks.
* **[Dagger2](https://github.com/kaushiknsanji/dependency-injection-in-android-course/tree/Dagger2)**
	* Covers the implementation of Dependency Injection using Dagger 2.
* **[ViewModel](https://github.com/kaushiknsanji/dependency-injection-in-android-course/tree/ViewModel)**
	* Covers the Dependency injection approaches for ViewModels using Dagger 2.
* **[FactoryCtorArgFix](https://github.com/kaushiknsanji/dependency-injection-in-android-course/tree/FactoryCtorArgFix)**
	* Covers the correction required for ViewModel instances injected AS-IS into the ViewModels' Factory implementation.
	* Shows the importance of injecting Provider wrapped services into any Factory implementations. 
* **[master](https://github.com/kaushiknsanji/dependency-injection-in-android-course)**
	* This is the main/default branch that covers all the above.
	
---

## Udemy Certificate

<a href="https://www.udemy.com/certificate/UC-2GLLBTSE/">
<img alt="Udemy Certificate" src="https://udemy-certificate.s3.amazonaws.com/image/UC-2GLLBTSE.jpg?l=en_US" width="50%">
</a>
