# PopularPeople

A simple app to hit the Popular People API and show a list of (artists, directories ..etc), that shows details when items on the list are tapped (a typical master/detail app), also user able to browse/add people to favorite list that implements MVVM architecture using Clean Architecture, Dagger2, Retrofit, Coroutines, LiveData, RoomDatabase, Database Debugging, DataBinding, Navigation Component and Unit Testing.
<br>

## The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Dagger2.
3. **domain**: It contains dto classes and repositories.
4. **presentation**: View classes along with their corresponding Presenters.
5. **utils**: Utility classes.
#### Classes have been designed in such a way that it could be inherited and maximize the code reuse.
<br>

## Library reference resources:
1. Coroutines: https://codelabs.developers.google.com/codelabs/kotlin-coroutines/
2. Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3. Retrofit: https://square.github.io/retrofit/
4. Room: https://developer.android.com/topic/libraries/architecture/room.html
5. AndroidDebugDatabase: https://github.com/amitshekhariitbhu/Android-Debug-Database
6. DataBinding: https://developer.android.com/topic/libraries/data-binding
7. Navigation Component: https://developer.android.com/guide/navigation/navigation-getting-started
8. Unit Testing: https://www.pluralsight.com/courses/android-unit-testing-junit-mockito-using-kotlin
<br>

## License
```
   Copyright (C) 2020 Amr Abdelhameed

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```