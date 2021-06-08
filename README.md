## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [API](#api)
* [Setup](#setup)
* [Screens](#screens)


## General info
ExchangeAPI give you possibility to check basic info about stock exchange results of given company by symbol and date. You can also send this result on your email after authorization using oauth2 (github)
	
## Technologies
Spring (Boot, Web, Security),
OAuth2 (github),
JavaMail

## API
|                 endpoint                | method |                                description                               |
|:---------------------------------------:|:------:|:------------------------------------------------------------------------:|
| /api/ExchangeAPI/date/companyName       | GET    | Get a stock exchange results by specified company symbol and given date. |
| /api/ExchangeAPI/date/companyName/email | GET    | Send a results by given company symbol, date and email.                  |


## Setup
To run this project:
```
git clone https://github.com/EmilFrankiewicz/ExchangeAPI
```
Get key from polygon.io and pass it to KEY field in StockController. If you want a working OAUTH2 and sending email with stock exchange results fill up application.properties file and enter your email to setFrom() method in MailService.
```
mvn clean package

java -jar target/ExchangeAPI-0.0.1-SNAPSHOT.jar
```

## Screens
![Postman](https://raw.githubusercontent.com/EmilFrankiewicz/ExchangeAPI/main/screens/postman.png)

![MailResults](https://raw.githubusercontent.com/EmilFrankiewicz/ExchangeAPI/main/screens/mail.png)

