# Crypto Challenge

## Introduction

Thank you for participating in the Android Apprenticeship Program!
Here, you'll find instructions for completing the challenge.

## The Challenge

The purpose of the challenge is for you to demonstrate your Android skills. This is your chance to show off everything you've learned during the course!!

You will build and deliver a whole Android application on your own. We don't want to limit you by providing some fill-in-the-blanks exercises, but instead request you to build it from scratch.
We hope you find this exercise challenging and engaging.

The goal is to build an application that will use the Bitso REST API which must include:

- A screen that shows all available currencies.
- A detail screen for every currency.
- Testing coverage.
- CI/CD.
- Persistence.

## Requirements

These are the main requirements we will evaluate:

- Use all that you've learned in the course:
  - Best practices
  - Api design
  - UI design
  - Design patterns
  - Testing

## Getting Started

To get started, follow these steps:

1. Fork this project
1. Make your project private
1. Grant your mentor access to the project
1. Commit periodically
1. Apply changes according to the mentor's comments
1. Have fun!

## Deliverables

We provide the delivery dates so you can plan accordingly; please take this challenge seriously and try to make progress constantly.

It’s worth mentioning that you’ll ONLY get feedback from the review team for your first deliverable, so you will have a chance to fix or improve the code based on our suggestions.

For the final deliverable, we will provide some feedback, but there is no extra review date. If you are struggling with something, contact your mentor and peers to get help on time. Feel free to use the slack channel available.

## First Deliverable (Pending...)

Based on the self-study material and mentorship covered until this deliverable, we suggest you perform the following:

- Create a network client in order to consume the next services and use the gson adapter to get easily the parsed data classes. 
  - https://bitso.com/api_info#available-books
  - https://bitso.com/api_info#ticker
  - https://bitso.com/api_info#order-book
  
- Search fo images that represent the currencies and add the image in the list of currencies.
- Work with MVVM and LiveData in order to design the application.
  - Create a screen that will show the list of currencies by using the available-books service
  - Create a detail screen that will show the price and also add the last order of the specific currency by using the ticker and order-book services
- Use best practices

> Note: what’s listed in this deliverable is just for guidance and to help you distribute your workload; you can deliver more or fewer items if necessary. However, if you deliver fewer items at this point, you have to cover the remaining tasks in the next deliverable.

## Second Deliverable (Pending...)

Based on the self-study material and mentorship covered until this deliverable, we suggest you perform the following:

- Add persistence with room in order to use the application when the user is offline.
- Add the okhttp library in order to use the  HttpLoggingInterceptor for all network events and also add a header with the user agent.
- Implement some function types, lambdas and extension functions.
- Add some unit tests and ui tests.
- Do UI refactor, update you layouts by using constraintLayout.
- Use best practices.

> Note: what’s listed in this deliverable is just for guidance and to help you distribute your workload; you can deliver more or fewer items if necessary. However, if you deliver fewer items at this point, you have to cover the remaining tasks in the next deliverable.

## Final Deliverable (Pending...)

- Set up CI/CD pipeline by using CircleCI or Bitrise.
- Add a linter in order to check your code.
- Use some advanced kotlin features like "Inline Functions, Tail Recursive, Delegated Properties and Collection Operators"
- Implement co-routines and use suspend functions with retrofit.
- Implement hilt as a DI framework.
- Implement the navigation component to navigate among the screens.
- Implement RxAndroid, wrap the retrofit response and create an observable that needs to be observed or change the retrofot response to Observable.
- Use best practices.

> Important: this is the final deliverable, so all the requirements must be included. We will give you feedback and you will have 3 days more to apply changes. On the third day, we will stop receiving changes at 11:00 am.

## Submitting the deliverables

For submitting your work, you should follow these steps:

1. Create a pull request with your code, targeting the master branch of the [repository android-bootcamp-2021](https://github.com/wizelineacademy/android-bootcamp-capstone-project-2021).
2. Fill this [form](pending...) including the PR’s url
3. Stay tune for feedback
4. Do the changes according to your mentor's comments

## Documentation

### Self-Study Material

- [Retrofit](https://square.github.io/retrofit/)
- [GSON](https://github.com/google/gson)
- [Data Classes](https://kotlinlang.org/docs/reference/data-classes.html)
- [Picasso](https://square.github.io/picasso/)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
- [Parallelism and Concurrency](https://android.jlelse.eu/kotlin-coroutines-threads-concurrency-and-parallelism-101-78a56e09d373)
- [Function type](https://kotlinlang.org/docs/reference/lambdas.html#instantiating-a-function-type)
- [Lambdas](https://kotlinlang.org/docs/reference/lambdas.html#lambda-expressions-and-anonymous-functions)
- [Extension functions](https://kotlinlang.org/docs/reference/extensions.html)
- [Generics](https://kotlinlang.org/docs/reference/generics.html#generics)
- [Room](https://developer.android.com/training/data-storage/room)
- [OkHttp](https://square.github.io/okhttp/)
- [junit](https://developer.android.com/training/testing/junit-rules)
- [mockito](https://www.vogella.com/tutorials/Mockito/article.html)
- [espresso](https://developer.android.com/training/testing/espresso)
- [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout)
- [Setup CI/CD](https://firebase.google.com/docs/test-lab/android/continuous)
- [CircleCI](https://circleci.com/docs/2.0/language-android/)
- [Bitrise](https://devcenter.bitrise.io/getting-started/getting-started-with-android-apps/)
- [Linters](https://ktlint.github.io/)
- [Co-routines](https://developer.android.com/kotlin/coroutines)
- [Suspend Functions](https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html)
- [DI](https://developer.android.com/training/dependency-injection/manual)
- [DI Framework](https://developer.android.com/training/dependency-injection/hilt-android)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
