# Expressions
## Account
|             |               |          |
|-------------|---------------|----------|
| Het account | **1**         | bestaat. |
|             | **2**         |          |
|             | **3**         |          |
|             | _ET: Account_ |          |
|             | _ID: id_      |          |

|              |            |          |
|--------------|------------|----------|
| De gebruiker | **1**      | bestaat. |
|              | **2**      |          |
|              | **3**      |          |
|              | **4**      |          |
|              | _ET: User_ |          |
|              | _ID: id_   |          |

|             |               |                                           |            |
|-------------|---------------|-------------------------------------------|------------|
| Het account | **1**         | representeert (onder andere) de gebruiker | **2**      |         
|             | **1**         |                                           | **1**      |
|             | **2**         |                                           | **3**      |
|             | **3**         |                                           | **4**      |
|             | _ET: Account_ |                                           | _ET: User_ |
|             | _MATCH_       |                                           | _MATCH_    |

_RT: UserRepresentsAccount: between User and Account_  
 

## Leningen

|           |               |         |
|-----------|---------------|---------|
| De lening | **1**         | bestaat |
|           | **2**         |         |
|           | **3**         |         |
|           | _ET: Lending_ |         |
|           | _ID: id_      |         |




|             |               |                                 |           |
|-------------|---------------|---------------------------------|-----------|
| Het account | **1**         | is verantwoordelijk voor lening | **2**     |
|             | **2**         |                                 | **3**     |
|             | **1**         |                                 | **1**     |
|             | _ET: Account_ |                                 | _Lending_ |
|             | _MATCH_       |                                 | _MATCH_   |

_RT: AccountIsResponsibleForLending: between Account and Lending_


## Notificatie gerelateerd

|                         |                        |          |
|-------------------------|------------------------|----------|
| De notificatie opdracht | **1**                  | bestaat. |
|                         | **2**                  |          |
|                         | **3**                  |          |
|                         | _ET: NotificationTask_ |          |
|                         | _ID: id_               |          |

|                         |                        |                 |                                 |
|-------------------------|------------------------|-----------------|---------------------------------|
| De notificatie opdracht | **1**                  | heeft de titel: | **A product is due!**           |
|                         | **2**                  |                 | **Outstanding fine of €10,00!** |
|                         | **3**                  |                 | **A product is due soon**       |
|                         | _ET: NotificationTask_ |                 | _ATT: title_                    |
|                         | _MATCH_                |                 |                                 |

|                         |                        |                   |                                                                                                     |
|-------------------------|------------------------|-------------------|-----------------------------------------------------------------------------------------------------|
| De notificatie opdracht | **1**                  | is beschreven als | **The product "Harry Potter" is due today! Avoid a fine by returning it.**                          |
|                         | **2**                  |                   | **Your debt has reached €10,00 today. You can pay it off here.**                                    |
|                         | **3**                  |                   | **The product "Harry Potter" is due on November 18 2024. Avoid a fine by returning it right away.** |
|                         | _ET: NotificationTask_ |                   | _ATT: message_                                                                                      |

|                          |                            |          |                                                                                                     |
|--------------------------|----------------------------|----------|-----------------------------------------------------------------------------------------------------|
| De notificatie strategie | **1**                      | bestaat. |                                                                                                     |
|                          | **2**                      |          |                                                                                                     |
|                          | **3**                      |          |                                                                                                     |
|                          | _ET: NotificationStrategy_ |          |                                                                                                     |
|                          | _ID: id_                   |          |                                                                                                     |

|                          |                            |      |              |
|--------------------------|----------------------------|------|--------------|
| De notificatie strategie | **1**                      | heet | **Alert**    |
|                          | **2**                      |      | **Warning**  |
|                          | **3**                      |      | **Reminder** |
|                          | _ET: NotificationStrategy_ |      | _ATT: name_  |
|                          | _MATCH_                    |      |              |


|                         |                        |                                   |                            |
|-------------------------|------------------------|-----------------------------------|----------------------------|
| De notificatie opdracht | **1**                  | zal gestuurd worden met strategie | **1**                      |
|                         | **2**                  |                                   | **1**                      |
|                         | **3**                  |                                   | **2**                      |
|                         | _ET: NotificationTask_ |                                   | _ET: NotificationStrategy_ |
|                         | _MATCH_                |                                   | _MATCH_                    |

_RT: NotificationTaskSentWithNotificationStrategy: between NotificationTask and NotificationStrategy_

|                         |                        |                 |                    |                    |
|-------------------------|------------------------|-----------------|--------------------|--------------------|
| De notificatie opdracht | **1**                  | zal ongeveer op | **11-18-2024**     | verzonden worden   |
|                         | **2**                  |                 | **11-18-2024**     |                    |
|                         | **3**                  |                 | **11-16-2024**     |                    |
|                         | _ET: NotificationTask_ |                 | _ATT: scheduledAt_ |                    |
|                         | _MATCH_                |                 |                    |                    |

