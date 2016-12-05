Team Databses are Awesome

Abby Schmiedt alschmie@calpoly.edu
Alexis Fraga afraga@calpoly.edu
Jacob Rickman jrickman@calpoly.edu

Kick It!

We plan on creating a CRUD application that will allow anyone to modify the European Soccer
teams and matches played between 2008 and 2016. From this data the user will be 
able to view: 

 - Average points per match for a team
 - Percentage home games won vs away games won for each team
 - Which country has the most winning teams.

-- Databse Schema --
CREATE TABLE Country(
   Name varchar(20),
   Id int PRIMARY KEY
);

CREATE TABLE League(
   Name varchar(20),
   CountryId int,
   Id int PRIMARY KEY,
   FOREIGN KEY(CountryId) REFERENCES Country(Id)
);

CREATE TABLE Team(
   TeamId int PRIMARY KEY,
   TeamName varchar(50),
   TeamShort varchar(20)
);

CREATE TABLE Matches(
   Id int PRIMARY KEY,
   CountryId int,
   LeagueId int,
   Season char(10),
   MatchDate Date,
   HomeTeamId Int,
   AwayTeamId Int,
   HomeScore Int,
   AwayScore Int,
   FOREIGN KEY(HomeTeamId) REFERENCES Team(TeamId),
   FOREIGN KEY(AwayTeamId) REFERENCES Team(TeamId)
);

The dataset comes from the website Kaggle.com which contains a large library of open source
datasets.

