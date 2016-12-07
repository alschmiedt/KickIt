CREATE TABLE Country(
   Id int PRIMARY KEY,
   Name varchar(20)
);

CREATE TABLE League(
   Id int PRIMARY KEY,
   CountryId int,
   Name varchar(30),
   FOREIGN KEY(CountryId) REFERENCES Country(Id)
);

CREATE TABLE Team(
   TeamId int PRIMARY KEY,
   TeamName varchar(50),
   TeamShort varchar(20)
);

CREATE TABLE Matches(
   Id int PRIMARY KEY AUTO_INCREMENT, 
   CountryId int, 
   LeagueId int, 
   Season char(16), 
   MatchDate Date, 
   HomeTeamId Int, 
   AwayTeamId Int, 
   HomeScore Int, 
   AwayScore Int, 
   FOREIGN KEY(HomeTeamId) REFERENCES Team(TeamId),
   FOREIGN KEY(AwayTeamId) REFERENCES Team(TeamId)
);