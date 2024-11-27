drop view if exists
    "AccountExampleView";

create view
    "AccountExampleView"
as
(
    select
        "accountId"
    from
        "Account"
);