drop table user_accounts;
drop table bank_accounts;

-- let's start with a user account table

create table user_accounts (
    user_id number(10) PRIMARY KEY,
    first_name varchar2(50),
    last_name varchar2(50),
    username varchar2(50),
    password varchar2(50),
    registered number(1), -- I guess these could be yes no but numbers will be easier to deal with
    loggedIn number(1),
    isSuper number(1)
);


create table bank_accounts (
    account_number number(10) PRIMARY KEY,
    account_balance number(10),
    user_id number(10) -- to be foreign key
);


-- create foreign keys

alter table bank_accounts
add constraint fk_bas_users
foreign key (user_id) references user_accounts(user_id);








-- can I create a table that generates a transaction id? each time something happens? 
-- i think i would use a sequence with a trigger? or maybe I can trigger through java?

-- this needs to have same values as account table, but yes you can.

create table transactions (
    transaction_id number(10),
    account_number number(10),
    account_balance number(10),
    transaction_type varchar2(20),
    constraint t_type check (transaction_type in ('withdrawal', 'deposit', 'open account', 'close account', 'view balance'))
);

/*
Not sure if I should be using after or before...after I think because I'm not deleting anything.

create or replace trigger log_transcation
after update on

*/









