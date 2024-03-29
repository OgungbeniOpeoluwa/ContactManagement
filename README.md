#  **ContactManagement**

## **Table of Content**
1. [Introduction](#Introduction)
2. [ Features](#Features)
3. [End-point](#END-POINTS)
4. [ Pre-requites](#Pre-requites)
5. [SetUp](#SetUp)


# Pre-Requites
1. JDK 21
2. MySql
3. Maven

# SetUp
1. Create an account with git.
2. From your terminal/command prompt clone the repository using this git command
   * git clone <https://github.com/OgungbeniOpeoluwa/ContactManagement.git>.
3. Ensure all dependencies in the project are well injected in your pom.xml.
   * To download and build the project you can run this command on the terminal: _mvn clean install_
4. setup mysql database to configure database connection.
5. To start the application from your IDE run the application main class.Alternatively you can run this command on the terminal
   * mvn spring-boot:run
6. install postman to test the application end-points by providing the necessary url and body requests if necessary.


# INTRODUCTION
_This contact app management is designed to save contacts for user.it helps to efficiently store,organise contact 
information for a user.This application is built on java Maven and uses my sql database to store data.
All request are received in Json Format_

# Features:

1. Register

2. Login

3. Reset Password

4. Reset Email

5. User Profile

6. View Profile

7. Edit User Profile

8. Delete Account

9. Add Contact

10. Edit Contact

11. Find All Contacts 

12. View Contact 

13. Delete Contact

14. Delete All Contacts

15. Block Contact

16. Unblock Contact

## **END-POINTS**

### **Register Request** 

#### **Description**

_This end point create a new user.it takes first name, last name,email,phone number and the password.
The phone number,e,ail and password must meet the following criteria._

1. **Password Validation:**
  * Valid Password: P@ssword123

  * Starts with an uppercase letter.

  * Followed by more than four letters.

  * Ends with at least one digit or special character.

2. **Email Validation:**
  * Valid Email: user@gmail.com

  * Username: user

  * Domain: gmail

  * Top-level domain: com

3. **Phone Number Validation**
     * Valid International Number: +123456789012
     * Starts with a plus sign.
     * Followed by a valid sequence of digits (6 to 12).
     * Valid Local Number: 07801234567
     * Starts with 0. 
     * Followed by a valid local number sequence.

### **Request:**
* Url: localhost:9050/api/user/users
* Method: Post
* Header: content-type:application/json
* Body:
```{
"fistName:"opeoluwa",
"lastName":"martins"
"phoneNumber":"+2347066221008"
"email":"martins123@gmail.com"
"password":"Martins123@#"
}
```

### **Response 1**
_This is a successful response. this response returns the user id ._

    Status code: Accepted 202
  * Body:
```
{
"data": {
"id":1,
"message":"Account has been created"
},
"successful": true
}
```

### **Response 2**
_This an unsuccessful response due to invalid email,phone number and password format._

      Status Code: Bad Request 400
* Body:
```
{
"data":"Wrong email format"
"successful": false
}
```


### **Response 3**

_This an unsuccessful response due to user with the same details already existing in the database._

   Status code: Bad request 400

   * Body:
   ```
   {
   "data" :"user already exist"
   "successful" : false
   }
```

## **Login Request** 

### **Description**

_This end point authenticate user before they can have access to the contact app management.it takes 
in the id and the user password._

### **Request**
* Url: localhost:9050/api/users/1
* parameter: userId: Long
* Method: Post
* Header: content-type:application/json
* Body:
```
{
"id" : 1,
"password" : "Martins123@
}
```

### **Fields**

* id(required,Long): the user identification number.

* password(required , String): the user password .

### **Response 1**
* successful request

      Status Code: Accepted 202
    * Body:
```
  {
"data": {
"message": "you have successfully login into your account"
},
"successful": true
}

```

### **Response 2**

_unsuccessful request due to invalid details_

      Status code : Bad request 400
  * Body:
```
{
"data": {
        "message": "Invalid details"
    },
    "successful": false
}
```

## **Reset Password Request**

### **Description**

_This end point allows user modify their password. it takes their old password and the new password._

_Request_

* Url: localhost:9050/api/user/password/1

* Parameter: userId: Long

* Method:Put

* Header: content-type:application/json


### **Response 1**

_successful response_

      Status code: Accepted 202
   * Body:
```
{
"data": {
"message": "password has been updated"
},
"successful": true
}
```

### **Response 2:**

_unsuccessful response._

_error: invalid identification number_

      Status Code: Not found
   * Body:
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```

### **Response 3:**

_unsuccessful response_

_Error: wrong password format_

      Status code: Bad request
   * Body
```
{
"data": {
"message": "wrong password format"
},
"successful": false
}
```

### **Response 4:**

_unsuccessful response_

_Error: old password is wrong_

      Status code: Bad request 400
   * Body:
```
{
"data": {
"message": "Invalid Details"
},
"successful": false
}
}
```

## **Reset Email Request**

### **Description**

_This end point allow user reset their old email.it takes in their identification number,old email and new email._

### **Request**
* Url: localhost:9050/api/user/email/1

* Method:Put

* parameter: userId:Long

* Header:Content_type:application/json

### **Response 1**

_Successful response._

      Status code: Accepted 202
   * Body
```
{
"data": {
"message": "Your email has been updated successfully"
},
"successful": true
}
```

### **Response 2**

_Unsuccessful response._

      Status code: Not Found

_Error Message: wrong Email_
   * Body:
```
{
"data": {
"message": "Incorrect Email"
},
"successful": false
}
```


## **View Profile Request**

### **Description**

_This end point allow user view their profile.it takes their identification number and return a view of their profile._

### **Request**
* Url: localhost:9050/api/user/profile/4

* Method:Get

* Header: content-type: application/json

* Parameter: user id:Long

### **Response 1**

_successful response_

      Status code: Ok 200
   * Body:
```
{
"data": {
"message": {
"firstName": "shola",
"lastName": "Martins",
"phoneNumber": "08152865402",
"email": "sholaojo34@gmail.com",
}
},
"successful": true
}
```

### **Response 2**

_Unsuccessful response_

      Status code: Not found
   * Body:
```
{ "data": {
"message": "Invalid details"
},
"successful": false
}
```

## **Edit Profile Request**

### **Description**

_This end-point allows users update/edit their profile, it accepts user identification number, first name,last name and also the phone number the user intend to modify i.however these fields could be left blank or empty as it has no effect on the initial data._

### **Request**
* url : localhost:9050/api/user/profile

* Method: Put

* Header: content-type:application/json

* Body:
      
     *  Request Example 1:
```
{
"id":1,
"lastName":"Martins",
"phoneNumber":"08152865402"
}
```
   * Request Example 2

```   
   {
   "id":1,
   "firstName" : "Tolu"
   "lastName":"Martins",
   "phoneNumber":"08152865402"
   }
```

### **Fields:**
* useId:(required , Long): user Identification Number

* First Name(required,String): User first name

* Last Name:(required,String): User last name

* phone number: (required, String): User phone number


### **Response 1**
_successful response_

      Status code:Ok 200
   * Body:
```   
   {
   "data": {
   "message": "profile updated"
   },
   "successful": true
   }
```

### **Response 2**

_unsuccessful response_

_error message: Invalid Details_

      Status Code: Not Found
   * Body
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```

### **Response 3**

__unsuccessful response__

      Status code: Not Found

  _Error cause: wrong detail format_
 * Body:
```
{
"data": {
"message": "Wrong phone number format"
},
"successful": false
}
```



## **Add Contact Request** 

### **Description**

_This end point allow user add contacts. it takes the contact name and phone number and save it to your list Contacts._

### **Request**
* Url: localhost:9050/api/user/contact

* Method: Post

* Header: content-type:application/json

* Body:
```
{
"id":2,
"name"shola martins
"phoneNumber": "07088906754
}
```

### **Fields:**
* id:(required,Long): user identification number

* name:(required,String):name of the person the user intended to save

* phoneNumber:(required, String):the phone number of the intended person.


### **Response 1**
* successful response.

* Status Code:Created

* Body:
```
{
data": {
"message": "Contact added"
},
"successful": true
}
```

### **Response 2**

_unsuccessful request due to wrong identification number_

      Status Code : Bad request 400
   * Body:
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```

### **Response 3:**

_unsuccessful request due to name already existing in the user contact list._

      Status code: Bad request 400
  * Body:
`
{
"data": {
"message": "Name already exist"
},
"successful": false
}`


### **Response 4**

_unsuccessful request due invalid phone number format_

      Status code: Bad Request 400
   * Body:
```
{
"data": {
"message": "Wrong phone number format"
},
"successful": false
}
```

### **Fields:**
   _message: response message_



## **Edit Contact Request**

### **Description**

_This end-point allows users update/edit their contact list, it accepts contact name,name the user intended to modify and also the phone number.however these fields could be left blank or empty as it has no effect on the initial data._

### **Request**
* Url: localhost:9050/api/user/contact

* Method: Put

* Header: content_type: application/json

* Body:
 
   * Request Example 1:
```
{
"userId":1,
"name":"tobi big",
"newPhoneNumber":"07066221008"
}
```
  *  Request Example 2:
   ```
   {
   "userId":1,
   "name":"tobi big",
   "newContactName":shole small,
   "newPhoneNumber":"+2347066221008"
   }
```

### **Fields:**
* id(required,Long):user identification number

* name:(require,String): name the user use the save the contact on his list

* newContactName:(required,String): name the user intend to change the former name to

* new ContactPhoneNumber(required,String): contact new phone number

### **Response 1**

_successful request_

      Status Code: Accepted 202
   * Body:
```      
      {
      "data": {
      "message": "contact has been updated"
      },
      "successful": true
      }
```

### **Response 2**

_unsuccessful request._

      Status code: Not Found
   * Body:
```
{
"data": {
"message": "contact doesn't exit"
},
"successful": false
}
```


### **Response 3**

_unsuccessful request_

      Status Code: bad request 400
   * Body:
```
{
{
"data": {
"message": "wrong phone number format"
},
"successful": false
}
}
```
#### **Fields:**

_message: response message_


## **View A Contact Request** 

### **Description**

_This end point allow user to view a particular contact in his contact list. it takes the name of the contact and returns the contact details to the user._

### Request
* Url: localhost:9050/api/user/contact/2

* Method : Get

* Header: content-type:application/json

* parameter: userId:Long

* Body:
 
       parameter: 
          * Key:ContactName , Value: Tobi

### **Response 1**
_successful response._

      Status Code: Ok 200
 * Body:
```
{
"data": {
"message": {
"id": 40,
"name": "tobi small yansh",
"phoneNumber": "07066221008",
"contactAppId": 64,
"blocked": false
}
},
"successful": true
}
```

### **Response 2**

_unsuccessful response._

      Status code: Not Found
 * Body
```
{
"data": {
"message": "contact doesn't exit"
},
"successful": false
}
```
### **Response 3**

_unsuccessful request_

      Status code:Not Found

_Error Message: wrong user identification number_

* Body:
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}

```

## **View All Contact Request**

### **Description**

_This end point views all contacts saved in the user contact list.it takes the user identification number as a parameter and return a list of of contacts saved._

### _Request_
* Url: localhost:9050/api/user/contacts/4

* Method: Get

* Parameter: userId : Long

* Header: content-type:application/json

### **Response 1**

*successful respon*se.

      Status Code: Accepted 202
 * Body:
````
{
"data": {
"message": [
{
"id": 40,
"name": "tobi small yansh",
"phoneNumber": "07066221008",
"contactAppId": 64,
"blocked": false
}
]
},
"successful": true
}
}
````

### **Response 2**

_unsuccessful request_

      Status code:Not Found

_Error Message: wrong user identification number_

* Body:
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```


## **Delete Contact Request** 

_This end point allow user delete a contact from their contact list.it takes in the user identification number and the contact name._

### **Request**
* Url:localhost:9050/api/user/contact/id

* Header: content-type:application/json

* Method:Delete

* Parameter: _userId: Long_

* Body:
   
       Parameter: 
          Key = contactName , Value = Tobi

### **Response 1**

_Successful response_

      Status Code :Accepted 201
   * Body
```
{
"data": {
"message": "Contact has been deleted"
},
"successful": true
}
```


### **Response 2**

_Unsuccessful request due to Invalid registration number._

      Status Code:Not Found
   * Body 
 ```  
{
data": {
"message": "Invalid details"
},
"successful": false
}
```


### **Response 3**

_Unsuccessful request_
      
      Status code: Not Found
   * Body:
   ```
   {
   data": {
   "message": "Cont doesn't exist"
   },
   "successful": false
   }
```

## **Delete All Contact** 


### **Description**

_This end point allow user clear all their contacts that was saved. it takes only the user identification number._

### **Request**
* Url: localhost:9050/api/user/contacts/2

* Parameter: userId: Long

* Method : Delete

* Header: content-type: application/json


### **Response 1**

_Successful response._

      Status Code: Accepted 201
   * Body
```
{
"data": {
"message": "All contacts Deleted successfully"
},
"successful": true
}
```

### **Response 2**

_Unsuccessful request_
         
         Status Code: Not found
   * Body
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```



## **Delete Account Request** 

### **Description**

_This end point allows user delete their account._

### **Request**
* Url: localhost:9050/api/user/user/1

* Parameter: userId: Long

* Method: Delete

* Header: content-type:application/json

* Body
```

{
"data": {
"message": "Account has been Deleted"
},
"successful": true
}
```

### **Response 2**

_Unsuccessful request_

      Status Code: Not Found
 * Body

   ```{
   "data": {
   "message": "Invalid details"
   },
   "successful": false
   }
   ```


## **Block Contact Request** 

### **Description**

_This end point allow user block a contact that was saved in their contact List._

### **Request**

* Url: localhost:9050/api/user/contact/1

* Parameter:userId:Long

* Method:Patch

* Header:content-type:application/json

* Parameter: 
  
         Key: ContactName

### **Response 1**

_Successful Response_

      Status Code: Accepted 201
* Body:
````
{
"data": {
"message": "contact is Blocked"
},
"successful": true
}
````

### **Response 2**

_unsuccessful request_
      
      Status Code: Bad Request 400
   * Body:
```   
   {
   "data": {
   "message": "Contact has been blocked already"
   },
   "successful": false
   }
```
### **Response 3**

_unsuccessful request_

      Status Code: Bad Request 400
   * Body:
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```


## **Unblock contact Request**

### **Description**

_This end point allow user unblock contact in their contact list.This delete request returns a message._

### **Request:**

* Url: localhost:9050/api/user/contact/id

  * Parameter:

        Path Variable: userId: Long,
        Request Param : Key:ContactName, Value: String

* Method: Post

* Header: content-type: application/json

### **Response 1:**

_Successful response._

      Status code: Accepted 201
   * Body

```
{
"data": {
"message": "Contact has been unblocked"
},
"successful": true
}
 ```

### **Response 2**

_unsuccessful request_
      
      Status Code: Bad request 400
   * Body

```{
"data": {
"message": "contact doesn't exit"
},
"successful": false
}
```

### **Response 3**

_unsuccessful Request_

      Status Code: Bad Request
* Body
```
{
"data": {
"message": "Invalid details"
},
"successful": false
}
```