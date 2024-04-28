CREATE TABLE IF NOT EXISTS Trip(
    trip_id INT NOT NULL,
    pick_up varchar(15) NOT NULL,
    destination varchar(15) NOT NULL,
    departure timestamp NOT NULL,
    price FLOAT NOT NULL,
    n_class varchar(15) NOT NULL,
    PRIMARY KEY (trip_id)
);
