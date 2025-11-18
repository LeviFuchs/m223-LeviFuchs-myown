CREATE TABLE user_table (
    UserId INT NOT NULL UNIQUE AUTO_INCREMENT,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);

INSERT INTO user_table VALUE ("Fuchs", "Levi", "Moosacker 25", "Zürich");