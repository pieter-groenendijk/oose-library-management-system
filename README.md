# Library Management System

## Description

A library wants a new management system to use within the library. Users can borrow, return and reserve books and DVD's.

The current system is outdated. A lot of jobs are maintained and executed manually, e.g: 
- Reservations are maintained with an Excel spreadsheet;
- Users are to be informed by phone to inform them when reserved books/DVD's have become available to them;
- Returning books requires an employee at the counter  

The library would not only like a system to automate above jobs, but also to give more control to the users.

There are additional functionalities the library requires the system to have, namely:
- Give notices about the availability of a book/DVD;
- Give notices about fees for late returns


## Desired functionality

The library would like the following functionality for now.

### Administrative functionality

- **Management over several mediums**  
The ability to add, remove and update the catalog. The amount of available copies of a book are kept up to date. 
It's possible to generate a report of the catalog.  
The mediums include:
  - **books**
  - **DVD's**
- **Review of statistics**
  - Availability of catalog items

### User functionality

- **browsing (of the catalog)**  
Additionally, a user may:
  - Filter/Search based of:
    - title,
    - author,
    - availability
    - (medium type: book or DVD)
- **borrow**: A user may borrow a book/DVD that is currently available.
- **reserve**: A user may reserve a book/DVD that is currently not available. A user limit applies.
- **return**: A user is obligated to have returned a borrowed book/DVD on a specified date. A late fee may apply.
- **review borrow history**
- **receive notifications**
  - approaching due date
  - past due date, user will start building up their late fee
  - a reserved book/DVD has become available
