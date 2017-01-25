CREATE TABLE city (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30)
);

CREATE TABLE temperature (
  city_id INTEGER PRIMARY KEY,
  temp VARCHAR(30)
);