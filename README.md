##P3 - Stock Hawk
***Included Udacity course: [Advanced Android App Development](https://www.udacity.com/course/advanced-android-app-development--ud855)***


During this project a basically working app should be enhanced in a way, so it is "ready for production", for publishment via the Google Play Store. Stock Hawk is an app, which provides data from the Yahoo Finance API. The user can search for stock symbols and add them to his watch list (stored in a local database). 

The project starts with the current sources of the Stock Hawk app, and a list of user reviews:

---------
**User Feedback for Stock Hawk:**

Hellen says:
"Right now I can't use this app with my screen reader. My friends love it, so I would love to download it, but the buttons don't tell my screen reader what they do."

Your boss says:
"We need to prepare Stock Hawk for the Egypt release. Make sure our translators know what to change and make sure the Arabic script will format nicely."

Adebowale says:
"Stock Hawk allows me to track the current price of stocks, but to track their prices over time, I need to use an external program. It would be wonderful if you could show more detail on a stock, including its price over time."

Gundega says:
"I use a lot of widgets on my Android device, and I would love to have a widget that displays my stock quotes on my home screen."

Jamal says:
"I found a bug in your app. Right now when I search for a stock quote that doesn't exist, the app crashes."

Xaio-lu says:
"When I opened this app for the first time without a network connection, it was a confusing blank screen. I would love a message that tells me why the screen is blank or whether my stock quotes are out of date."

---------

Based on the users' feedback, certain aspects of the application are enhanced/implemented:

<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-174136.png" width="250">

####Accessibility (A11y)
The most important aspect before publishing an app is to be aware of the wide range of potential users of the app. So it is crucial to support visually handicapped useres with Screen Reader properties. Google's TalkBack service provides spoken feedback for every interaction with the device. Stock Hawk does not yet have description for all buttons and other UI elements, so this missing information is added. 

####Homescreen Widgets
Widgets are an effective way to gain the attention of the user, without the need of opening the app. They can be placed on the Android homescreen, where they provide essential and compact information. In this project, two types of widgets are implemented. A 4x1 single-row widget shows the top most stock symbol, and a so called collection widget provides a resizable 4x3 container, which shows a scrollable list of the stored stock symbols. 

<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-174119.png" width="250">
<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-202659.png" width="250">


####Localization (L10n)
As important as A11y is, localization is also a very essential key for a successful app. Based on the feedback of ***the boss***, the release in Egypt is planned, so the app should be fine with Arabic translations and support for RTL (right to left) layouts. 

<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-204348.png" width="250">
<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-205215.png" width="250">

####Displaying data in a chart
One user requests a chart view, which shows the stock changes over time in a graph. For this purpose, the library [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) is used, which shows the stock changes, when the user selects a stock symbol on the main screen.

<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-174203.png" width="250">

####Error handling and misc changes
Stock Hawk could not handle stock searches for non-existing symbols, so it crashed. This scenario (and similar ones) are handled by showing a Toast (info text) to the user, with details what went wrong. Also, better user feedback is implemented, like showing a hint text, if no stock symbols are provided, instead of a blank screen. Also, several minor issues are addressed and fixes are provided.

<img style="position: center;" src="https://github.com/MostafaAnter/StockHawk/blob/master/device-2016-12-16-174257.png" width="250">
