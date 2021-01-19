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

alter table user_accounts add constraint unique_username unique (username);

create table bank_accounts (
    account_number number(10) PRIMARY KEY,
    account_balance number(10),
    user_id number(10) -- to be foreign key
);


-- create foreign keys

alter table bank_accounts
add constraint fk_bas_users
foreign key (user_id) references user_accounts(user_id) on delete cascade;








-- can I create a table that generates a transaction id? each time something happens? 
-- i think i would use a sequence with a trigger? or maybe I can trigger through java?

-- this needs to have same values as account table, but yes you can.

create table transactions (
    transaction_id number(10) PRIMARY KEY,
    transaction_date TIMESTAMP WITH TIME ZONE, 
    account_number number(10), -- foreign key to bank_accounts
    transaction_type varchar2(20),
    transaction_amount number(10),
    account_balance number(10),
    user_id number(10),
    constraint t_type check (transaction_type in ('withdrawal', 'deposit', 'other'))
);

drop table transactions;


-- foreign keys to link transactions to users
alter table transactions
add constraint fk_transactions_users
foreign key (user_id) references user_accounts(user_id);

alter table transactions
add constraint fk_transactions_baccounts
foreign key (account_number) references bank_accounts(account_number);


select * from bank_accounts;
select * from transactions;

alter table transactions drop constraint t_type;
alter table transactions add constraint t_type check (transaction_type in ('withdrawal', 'deposit', 'other'));


/*
Not sure if I should be using after or before...after I think because I'm not deleting anything.
*/

create sequence transactionid_seq
    start with 200001
    increment by 1;
    
    
    


create or replace procedure add_transaction(account_num number, t_type varchar2, t_amount number, acct_balance number, u_id number)
is
begin
    insert into transactions values(transactionid_seq.nextval, systimestamp, account_num, t_type, t_amount, acct_balance, u_id);
end;
/


insert into transactions values(transactionid_seq.nextval, systimestamp, 100023, 'deposit', 100, 100, 1);

call add_transaction(100023, 'withdrawal', 500, 0, 1);

select * from transactions;

drop trigger log_transaction;



create or replace trigger log_transaction
for update of account_balance
on bank_accounts
compound trigger

    account_num number(10);
    t_type varchar2(20);
    t_amount number(10);
    acct_balance number(10);
    u_id number(10);
    
     
before statement is
begin
     select account_number, user_id into account_num, u_id from bank_accounts where account_number = account_number;
end before statement;

after each row is
begin
    if :new.account_balance > :old.account_balance then 
        t_type := 'deposit';
        t_amount := :new.account_balance - :old.account_balance;
        
    elsif :new.account_balance < :old.account_balance then 
        t_type := 'witdrawal';
        t_amount := :old.account_balance - :new.account_balance;
        
    elsif :new.account_balance = :old.account_balance then 
        t_type := 'other';
        t_amount := 0;
    end if;
   
    insert into transactions values(transactionid_seq.nextval, systimestamp, account_num, t_type, t_amount, :new.account_balance, u_id);
    
end after each row;

end;
/

drop trigger log_transaction;


select * from bank_accounts;
select * from transactions;


update bank_accounts set account_balance = 1000 where account_number = 100010;




-- create a sequence to generate user ids
create sequence userid_seq
    start with 1
    increment by 1;




-- create a sequence to generate bank account ids
create sequence bankaccountnumber_seq
    start with 100001
    increment by 1;






-- create a procedure to add a user account

create or replace procedure add_user_account(first_name varchar2, last_name varchar2, username varchar2, password varchar2)
is
begin

    insert into user_accounts values (userid_seq.nextval, first_name, last_name, username, password, 1, 0, 0);
    
end;
/







-- create a procedure to add a bank account

create or replace procedure add_bank_account(user_id number)
is
begin

    insert into bank_accounts values (bankaccountnumber_seq.nextval, 0, user_id);

end;
/



select * from user_accounts order by user_id;
select * from bank_accounts order by account_number;
select * from transactions;

-- Create a super user
call add_user_account('administrator', 'account', 'admin', 'admin');

update user_accounts set issuper = 1 where username = 'admin';
