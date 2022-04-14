# warehouse
Simple Data Warehouse

Stack:
- Java 11
- Spring
- Hibernate
- Querydsl
- openCSV

How to deploy (assuming using spring profile: dev):
1) create postgresql db & set your configuration in application-dev.properties
2) run gradle build
3) deploy jar with application(jar location: build/libs/warehouse-0.0.1-SNAPSHOT.jar) to choosen java application server

How to import data from CSV to database:
GET /statistics/import
(csv file location: src/main/resources/statistics.csv)

How to query statistics:
GET /statistics

You can choose from these parameters:
datasource.id
datasource.name(fixme: not all possible values are currently correctly treated - using some special characters will result in returning 400 Bad Request for example - AT | PLA)
campaign.id
campaign.name(fixme: same case as in datasource.name)
eventDate(it's possible to query beetwen dates if you provide two dates - example: eventDate=2019-01-01&eventDate=2019-01-31)
clicks
impressions

Sample query:
/statistics?datasource.name=Twitter Ads&eventDate=2019-01-01&eventDate=2019-01-02


How to query statistics with groupBy and aggregation function:
GET /statistics/report?groupingParamEnum=${groupingParamEnum}&aggregationFunctionEnum=${aggregationFunctionEnum}&aggregationParamEnum=${aggregationParamEnum}

Where possible values are:
- groupingParamEnum: DATASOURCE, CAMPAIGN, DATE
- aggregationFunctionEnum: SUM, COUNT, MAX, MIN, AVG
- aggregationParamEnum: CLICKS, IMPRESSIONS

Sample queries:
/statistics/report?aggregationFunctionEnum=COUNT&groupingParamEnum=DATASOURCE&aggregationParamEnum=IMPRESSIONS
/statistics/report?aggregationFunctionEnum=MAX&groupingParamEnum=CAMPAIGN&aggregationParamEnum=CLICKS
/statistics/report?aggregationFunctionEnum=SUM&groupingParamEnum=DATE&aggregationParamEnum=CLICKS

You can also use filter parameters in this endpoint:
/statistics/report?groupingParamEnum=DATASOURCE&aggregationFunctionEnum=COUNT&aggregationParamEnum=IMPRESSIONS&eventDate=2019-01-01&eventDate=2019-01-30

Possible improvements/further steps:
- implementing parameters other than equals(for example lower than, greater than where suitable)
- accepting list of possible values
- implementing sorting and pagination
- implementing validations
- adding swagger
