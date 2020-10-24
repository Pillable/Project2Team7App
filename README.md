# General Info

All code is in PillReminder/app/src/main/java/com/example/pillreminder/
It is well documented using kdoc style commenting and clearly displayed encapsulation and abstraction. 
Complex logic is abstracted away by classes such as the DataHandler class. 
In addition, all methods and instance variables that can be made private are made private.

# Specific info about each kotlin class

MainActivity is the class that creates the main screen and adds all the reminder widgets to that main screen. It gets which reminder widgets it should add from the DataHandler class

Reminder class is a data class that stores two strings. It has two constructors. One takes two strings (time and a list of days) while the other takes a boolean array and a string (A boolean array of which of the 7 days to include and a string representing the time).

Time class takes 24 hour time and formats

DataHandler saves all reminder classes to a shared preference in the form of a json array. It also has the methods to retrieve that list of reminders

AddReminder class creates the add reminder screen and if the user presses done, it uses DataHandler to add a new reminder to the database based on the user input in the form of what checkboxes they click (each box represents A day of the week) and the time selector widget. Reminder UI screen is shown in the second screenshot below. It should give you a better idea of what I am talking about

# UI 
All UI xml files are in PillReminder/app/src/main/res/

Here is a screenshot of the screen you see when in the main activity:










<img
src="addReminderScreen.png"
raw=true
alt="Subject Pronouns"
style="margin-right: 10px;"
/>

Here is a screenshot of the screen you see when you click on the plus button:






<img
src="MainActivity.jpg"
raw=true
width = 540
height = 1200
alt="Subject Pronouns"
style="margin-right: 10px;"
/>



# Functionality
As this app is meant to be a demo some functionality is missing. The app does not create notifications and UI may not scale properly since I only 
have one test device. 





