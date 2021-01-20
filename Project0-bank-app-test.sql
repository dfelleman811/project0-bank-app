
/* TABLES */



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





/* FOREIGN KEYS */

-- create foreign keys

alter table bank_accounts
add constraint fk_bas_users
foreign key (user_id) references user_accounts(user_id) on delete cascade;


-- foreign keys to link transactions to users
alter table transactions
add constraint fk_transactions_users
foreign key (user_id) references user_accounts(user_id);

-- foreign keys to link transactions to bank accounts

alter table transactions
add constraint fk_transactions_baccounts
foreign key (account_number) references bank_accounts(account_number)on delete cascade;





/*  SEQUENCES  */

-- create a sequence to generate user ids
create sequence userid_seq
    start with 1
    increment by 1;


-- create a sequence to generate bank account ids
create sequence bankaccountnumber_seq
    start with 100001
    increment by 1;


-- create a sequence to generate transaction ids
create sequence transactionid_seq
    start with 200001
    increment by 1;
    


/*  PROCEDURES  */




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
-- procedure to add transaction to transactions table

create or replace procedure add_transaction(account_number number, transaction_type varchar2, transaction_amount number, account_balance number, user_id number)
is
begin
    insert into transactions values(transactionid_seq.nextval, systimestamp, account_number, transaction_type, transaction_amount, account_balance, user_id);
end;
/









-- failed trigger

create or replace trigger log_transaction
after update on bank_accounts
for each row
begin
    call add_transaction(:old.account_number, :new.transaction_type, :old.transaction_amount, :new.account_balance, :new.user_id);
end;



insert into transactions values(transactionid_seq.nextval, systimestamp, 100011, 'deposit', 100, 100, 5);

call add_transaction(100010, 'withdrawal', 500, 0, 10);

select * from transactions;
select * from user_accounts;
select * from bank_accounts;

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



select * from user_accounts;
select * from bank_accounts;
select * from transactions;













select * from user_accounts order by user_id;
select * from bank_accounts order by account_number;
select * from transactions;

-- Create a super user
call add_user_account('administrator', 'account', 'admin', 'admin');

update user_accounts set issuper = 1 where username = 'admin';
