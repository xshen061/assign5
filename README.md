# assign5
Requirements and design:
1.	The software must have 4 different activities. Each of them is accessible by selecting a graphical icon from the main menu (Toolbar). you can add more activities to the menu and add new features.
2.	Each Activity uses: 
    1) a fragment
    2) a ListView to present items.
       a) When selecting an item from the ListView, the screen shows detailed information about the item selected.
       b) The items listed in the ListView are stored by the application, so:
          i) All entered items will appear the next time the application is launched. 
          ii) User can modify items (add, delete, update, etc.), which would also be stored.
    3)	an AsyncTask: open a Database, retrieve data from a server, save data, etc.
    4)	at least 1 progress bar
    5)	at least 1 button
    6)	at least 1 edit text input method
    7)	at least 1 Toast/Snackbar/dialog notification.
    8)	A help menu item that displays a dialog (e.g., author’s name, instructions)
    9)	1 other language – Mandarin

=================================================================================================================================================================================

User story:

Activity 1: Exercise Tracking
Choose the first icon from the toolbar

Story 1: add new items
         1) The user chooses from 5 physical exercise they did from drop-down box: Running, Walking, Biking, Swimming or Skating.
         2) enter the minutes they did the exercise.
         3) add comment, e.g., “easy”, or “My knee hurts after 10 minutes”. 

***Test cases: 
after entering the first activity page, click on the floating buttons. The "New Activity" page displays. Then, do the following steps:
a) choose exercise type: Running
b) Time will be filled by the system, no need to enter, but you can change the time when move mouse on it
c) enter the minutes in "Duration"
d) add "easy" to the "comment"
e) click "save" to save the entry. 
   OR, click "cancel", it will pop-up a box "Do you want to go back" and ask you to "cancel" (return to the add page) or "OK" to return the    list page
f) click "save", then the new record shows on the listview after progress bar complete

The database recorded all entered information.

Story 2: review/delete/update items
There is a fragment to show a ListView that displays the exercise entered. Users can click on the item, then delete or edit previous entries, for example, changing the activity type or time spent, or change comments.

***Test cases: 
click on the record just added, the "Update Activity" page display the "type", "time", "duration" and "comment" the user just entered. 
delete:
click on the "delete" button, it will pop-up a box "Do you want to delete activity" and ask you to "cancel" (return to the update page) or "OK" to return the list page. The record is removed after progress bar complete.
Update: 
change the type from "running" to "walking", clicks “update” button, it will pop-up a box "Do you want to update activity" and ask you to "cancel" (return to the update page) or "OK" to return the list page. The record updated and the pop-up toast message shows "update activity successfully" after progress bar complete

Story 3: show total and tracking statistics
The application also calculates how many minutes the user does per month, and show how much exercise the user did last month in ‘statistics’ (the three dots in upper right).

***Test cases: 
click on the three dots at upper right corner. there are two items: "Help" and "Statistics"
a) Help shows the author, version, how to use the App. It switches between the two languages when system language changes
b) statistics shows the total minutes per month and count how many minutes did for each exercise this month

-------------------------------------------------------------------------------------------------------------------------------------------
Activity 2: Food Nutrition information tracker
Choose the second icon from the toolbar

Story 1: add new items
user enters nutritional information about food that they have eaten, including normal items that are found on a nutrition label: Calories, Total Fat, Total Carbohydrate. 

***Test cases: 
Click on the second icon "food" and enter the food page, click on the "New Records" button.Then, do the following steps:
a) enter food type: meat
b) Time will be filled by the system, no need to enter, but you can change the time when move mouse on it
c) enter the numbers in "Calories", "TotalFat","Carbonhydrate"(e.g, 500 for all three)
d) click "save" to save the entry. 
   OR, click "cancel", it will pop-up a box "sure to cancel" and ask you to "no" (return to the add page) or "OK" to clear all entries but    stay on the page
e) click "save", then the pop-up toast message shows "food information saved" after progress bar complete
f) go back to the main page of food, click on "history records" to show all entries after progress bar complete

The database stores the time and day, together with the information user entered.

Story 2: review/delete/update items
There is a fragment to show a ListView that displays the items eaten. Users can click on the item, then delete or edit previous entries, for example, changing the number of calories or carbohydrates.

***Test cases: 
go back to the main page of food, click on "history records" to show all entries. click on the record just added.
delete:
click on the "delete" button, it will pop-up a box "Are you sure to delete the record" and ask you to "cancel" (return to the update page) or "OK" to return the list page. The record is removed and the pop-up toast message shows "food information deleted". 
Update: 
change the type from "meat" to "rice", clicks “update” button, it will pop-up a box "Do you want to update activity" and ask you to "cancel" (return to the update page) or "OK" to return the list page. The record updated in the list and the pop-up toast message shows "information is updated successfully" after progress bar complete


Story 3: show average and statistics
The application should calculate the average calories eaten per day, show how many calories were eaten in the last day.

***Test cases: 
a) click on the three dots at upper right corner. there is one item: "Help". It shows the author, version, how to use the App. It switches between the two languages when system language changes.
b) click on the "Static" button, the fragment shows average calories and total calories taken yesterday
c) click on the floating button, it display toast message "welcome"

-------------------------------------------------------------------------------------------------------------------------------------------
Activity 3: House Thermostat
Choose the third icon from the toolbar

Story 1: add new items
Users sets a schedule for a thermostat: enter the temperature, the day and time to set the temperature.  

***Test cases: 
after entering the third activity page, click on the floating buttons. The fragment pops from the bottom of screen. Then, 
a) choose the day from drop-down list.
b) scroll hours and minutes from the spinners.
c) enter the temperature.
Fill in using the following information:
  I) Monday 6:00 Temp -> 20
  II) Monday 9:00 Temp -> 16
  III) Wednesday 16:00 Temp -> 20
  IV) Wednesday 22:00 Temp -> 18
d)click "save", then the pop-up toast message shows "you save the rule successfully" after progress bar complete

The database stores the day & time and temperature user entered.

Story 2: review/delete/update items
There is a ListView that displays temperature user entered. When user clicks on an item from the list, the screen shows the details of the temperature rule in the fragment popped up from the bottom of the screen, and let the user edit the rule and save it. The user can edit the rule, and “save as new rule” instead of changing the selected rule. 

***Test case: 
step 1: click on “Monday 6:00 Temp -> 20”, the fragment pops from the bottom of screen shows the day, time, and temperature the user just entered. 
step 2a: change the day to Tuesday and clicks “update”, the rule now applies to Tuesday instead of Monday after progress bar complete. 
step 2b: change the day to Tuesday and clicks “Save as new rule”, a new rule “Tuesday 6:00 Temp -> 20” will be added, and the original “Monday 6:00 Temp -> 20” will still be in the list after progress bar complete.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Activity 4: Automobile
Choose the fourth icon from the toolbar:

Story 1: add new items
The user selects the number of litres, price, and kilometers of the gasoline they purchased. 

***Test cases: 
Click on the fourth icon "Gasoline" and enter the "Liters", "Price($/L)" and "kilometers". Press Save, the pop-up toast message shows "information saved successfully" 



The database stores the time and the information user entered.

Story 2: review/delete/update items
There is a ListView that displays the entries. The user can select items in the list, a fragment pop up from the bottom of the screen, which allows user to update them (click on "update") or click delete the record (click on "delete")


***Test cases: 
step 1: click on "history" to show all entries. click on the record just added. The fragment pops from the bottom of screen shows the date, price, liters,kilometers and ID that the user just entered. 
Step 2: change the price to 80, the liters to 20, click "update", the record is updated with the information.
Step 3: click "delete", the record is removed.


Story 3: show history and statistics
The application should provide information including the average price of gas for the last month, how much gasoline the user purchased last month, and how much gasoline the user purchases per month.


***Test cases: 
a) click on the three dots at upper right corner. there is one item: "Help". It shows the author, version, how to use the App. It switches between the two languages when system language changes.
b) click on the "Summary" button, the fragment shows how much gasoline the user purchased for each month in 2020 (default current year), also allow to change year (e.g., 2019) and click "inquire" button to show a different year.
c) click on the floating button, it display toast message "welcome to use Gasoline app"


=================================================================================================================================================================================
***Test cases:
switch languages: swap the two languages in system settings. The values are written in both languages, the APP will show language switches when system language changes.










