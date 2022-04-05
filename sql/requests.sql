create table requests
(
    userid   integer not null,
    friendid integer not null,
    FOREIGN KEY (userid) REFERENCES users (id),
    FOREIGN KEY (friendid) REFERENCES users (id)
)