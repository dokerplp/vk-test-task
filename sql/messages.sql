create table messages (
    id integer unique not null,
    userid   integer not null,
    friendid integer not null,
    text varchar(500),
    time timestamp with time zone not null,
    CONSTRAINT CHK_Text CHECK ( length(text) > 0 ),
    CONSTRAINT CHK_ID CHECK ( userid != friendid ),
    PRIMARY KEY (id)
)