# TempDb-without-hibernate

### Multiple remote sensor monitoring through JDBC backend &amp; Thymeleaf frontend


This was a group-project at Nackademin to learn more about System Integration.
We were four in the group and each one of us used either a Raspberry pi or an arduino
to setup a temperature sensor and send the data to a remote Mariadb-database.
The data from that database was then retrived and handled by a spring boot/Jdbc program
and presented with html/thymeleaf templates in a browser. We also made a version using
hibernate which can be found in my repo.

<p><br></p>

## The Setup

![setup](/arkitektur.png)
In a real world scenario the java application would have been implemented on a front end server
but since this was a group assignment we choose to write individual applications on our laptops.
<p><br></p>

#### model to tables in the database
```
@Data
@NoArgsConstructor
public class DbModel {
    int id;
    float temperature;
    Date date;
    Time time;
}
```
<p><br></p>



 
#### DbController class contains the following methods

```
public List<DbModel> getAllTempsFromUser(String name) {}
public DbModel getMinTempFromUser(String name) {}
public DbModel getMaxTempFromUser(String name) {}
```

<p><br></p>

### The frontend will display several buttons representing functions found in ThymeBasicController.java

![frontend](/Dashboard.png)

#### Functions included in ThymeBasicController
* Show Everyones Data
* Show individual Data
* Find/Show Highest Temperature
* Find/Show Lowest Temperature
* Find/Show temperatures from <name> higher than <value>
* Find/Show highest temperature from <name> 



## Thanks to the rest of the team: Jonas, Frej and Alexander
