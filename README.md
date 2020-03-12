# Campbell Morrison - Mobile Assessment
My submission for the Entersekt Technical Assessment for the position of Mobile Developer. Below is a quick summary on some of the design choices.

## SDK
### Instance management
There are many ways to instantiate and manage an SDK. If the SDK is complex and resource intensive, then I'd opt for a Bound Service. If the SDK is simple (like this one), then either the Singleton pattern or simple class instantiation is sufficient.

For this assessment I opted for the Singleton pattern due to its simplicity and developer convenience.

### Result Filtering
All filtering on the `AllCities` result is done in a single instance. You can definitely argue that this should be split further into `City`, `Mall`, and `Shop` related queries, but it felt excessive for the scope of this SDK.

### Result caching
Results are cached in `SharedPreferences` since the result set is small and simple. For large result sets that take long to deserialize, this approach might not be appropriate.

### Obfuscation
If the SDK contains proprietary information, the SDK should be obfuscated. In those cases all internal logic should be obfuscated as much as possible, while all user-facing interaction points should remain clean and readable. But for the purposes of this assessment there is no obfuscation.

## App
The main focus of the app is to showcase code style and SOLID principle. Little focus was placed on the aesthetics of the app, in the interest of limiting scope.

### Testing
The app is structured to facilitate automated tests. But SDK mocking should be added to facilitate hermetic testing, since hitting a remote server in automated tests introduces flakiness. But in the interest of limiting scope, the SDK mocking and tests were not added.