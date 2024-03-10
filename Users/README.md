# **User Management Service**

---

A service that handles all the Rest API calls on users data.

---

### Tech Stack
1. Java
2. Spring-Boot
3. Spring-Data-Jpa
4. H2-InMemory-Database
5. Swagger 3

---

### Rest API Calls

> **Get Request="/users/all"**

Request to get all the users details from the table.

![get All Request](./pics/getAll.png)


> ** Get Request="/users/{id}" **

Request to get a specific user detail by Id from the table.

![get Id Request](./pics/getId.png)

> ** Post Request="/users/user" **

Request to create user in the table.

![post user Request](./pics/postUser.png)

> ** Put Request="/users/{id}" **

Request to update user in the table.

![put user request](./pics/putId.png)

> **Delete Request="/users/{id}" **

Request to delete a specific user in the table.

![delete user request](./pics/deleteId.png)
