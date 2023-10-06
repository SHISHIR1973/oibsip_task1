# ONLINE RESERVATION SYSTEM
&nbsp;&nbsp;&nbsp;&nbsp;This Online Reservation System is included with all the necessary fields which are required during online reservation system. This Online Reservation System is an easy to use and can be used by any person. The basic idea behind this project is to save data in a central database which can be accessed by any authorize person to get information and saves time and burden which are being faced by their customers.
***
### REQUIREMENTS
1. <a href="https://www.oracle.com/in/java/technologies/javase/javase8-archive-downloads.html" target="_blank">JDK 8</a>
2. <a href="https://dev.mysql.com/downloads/installer/" target="_blank">MySQL</a>
3. <a href="https://dev.mysql.com/downloads/connector/j/" target="_blank">MySQL JDBC Driver</a>
4. Any Code Editor ( <a href="https://code.visualstudio.com/download" target="_blank">VS Code</a> )
***
### MODULES
<b> &nbsp;&nbsp;&nbsp;&nbsp;1. Login Form </b> - To access this Online Reservation System, each user should have a valid login id and password. After providing the correct login id and password, users will able to access the main system.</br>
***
<b> &nbsp;&nbsp;&nbsp;&nbsp;2.Reservation System </b> - Under reservation form users will have to fill the necessary details such as their basic details, train number, train name will automatically come in the box, class type, date of journey, from (place) to destination and after that, users will have to press insert button.</br>
***
<b> &nbsp;&nbsp;&nbsp;&nbsp;3.Cancellation Form </b> - If passengers want to cancel their tickets then they have to provide their PNR number and after submitting it, this will display the entire information related to that particular PNR number. If users want to confirm their cancellation, in this case they have to press OK button.
***
### COMMANDS
<b>1. For compilation:</b>
~~~
javac <filename.java>
~~~
~~~
javac ReservationSystem.java
~~~
<b>2. For execution:</b>
~~~
java -cp[classpath] .;[jar file path] filename.class
~~~
~~~
java -cp .;mysql-connector-j-8.1.0.jar ReservationSystem
~~~
