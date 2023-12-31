# ContactManagement

INTRODUCTION
This contact app management is designed to save contacts for user.it helps to effiiciently store,oganise contact 
information for a user.This application is built on java Maven and uses my sql database to store data.

Feature:

User Authentication

Register

Login

Reset Password

Reset Email

User Profile

View Profile

Edit User Profile

Delete Account

Contact Management

Add Contact

Edit Contact

Find All Contacts

View Contact

Delete Contact

Delete All Contacts

Block Contact

Unblock Contact

Register Request End-Point

Description
This end point create a new user.it takes first name, last name,email,phone number and the password.
The phone number,e,ail and password must meet the following criteria.


Password Validation:

Valid Password: P@ssword123

Starts with an uppercase letter.

Followed by more than four letters.

Ends with at least one digit or special character.

Email Validation:

Valid Email: user@gmail.com

Username: user

Domain: gmail

Top-level domain: com

Phone Number Validation

Valid International Number: +123456789012

Starts with a plus sign.

Followed by a valid sequence of digits (6 to 12).

Valid Local Number: 07801234567

Starts with 0.

Followed by a valid local number sequence.


Request:

Url: localhost:9050/api/user/users

Method: Post

Header: content-type:application/json

Body:


JSON








{
"fistName:"opeoluwa",
"lastName":"martins"
"phoneNumber":"+2347066221008"
"email":"martins123@gmail.com"
"password":"Martins123@#"
}


Response 1

This is a successful response. this response returns the user id .

Status code: Accepted 202

Body:


JSON








{
"data": {
"id":1,
"message":"Account has been created"
},
"successful": true
}


Response 2

This an unsuccessful response due to invalid email,phone number and password format.

Status Code: Bad Request 400

Body:


JSON








{
"data":"Wrong email format"
"successful": false
}


Response 3
This an unsuccessful response due to user with the same details already existing in the database.
Status code: Bad request 400
Body:


JSON








{
"data" :"user already exist"
"successful" : false
}

Login Request End-Point

Description
This end point authenticate user before they can have access to the contact app management.it takes 
in the id and the user password.

Request

Url: localhost:9050/api/users/1

parameter: userId: Long

Method: Post

Header: content-type:application/json

Body:


JSON








{
"id" : 1,
"password" : "Martins123@
}


Fields
id(required,Long): the user identification number.

password(required , String): the user password .

Response 1

successful request

Status Code: Accepted 202


Body:


JSON








"data": {
"message": "you have successfully login into your account"
},
"successful": true


Fields:

Message: request message

Response 2

unsuccessful request due to invalid details

Status code : Bad request 400

Body:


JSON








    "data": {
        "message": "Invalid details"
    },
    "successful": false
}

Reset Password Request End-Point

Description

This end point allows user modify their pasword. it takes their old password and the new password.

Request

Url: localhost:9050/api/user/password/1

Parameter: userId: Long

Method:Put

Header: content-type:application/json

Response 1

successful response

Status code: Accepted 202

Body:


JSON








{
"data": {
"message": "password has been updated"
},
"successful": true
}


Response 2:

unsuccessful response.

error: invalid identification number

Status Code: Not found

Body:


JSON








{
"data": {
"message": "Invalid details"
},
"successful": false
}


Response 3:

unsuccessful response

Error: wrong password format

Status code: Bad request


JSON








{
"data": {
"message": "wrong password format"
},
"successful": false
}


Response 4:

unsuccessful response

Error: old password is wrong

Status code: Bad request 400

Body:


JSON








{
"data": {
"message": "Invalid Details"
},
"successful": false
}
}

Reset Email Request End-Point

Description

This end point allow user reset their old email.it takes in their identification number,old email and new email.

Request

Url: localhost:9050/api/user/email/1

Method:Put

parameter: userId:Long

Header:Content_type:application/json

Response 1

Successful response.

Status code: Accepted 202

Body


JSON








{
"data": {
"message": "Your email has been updated successfully"
},
"successful": true
}


Response 2

Unsuccessful response

Status code: Not Found

Error : wrong Email

Body:


JSON








{
"data": {
"message": "Incorrect Email"
},
"successful": false
}

View Profile Request End-Point

Description

This end point allow user view their profile.it takes their identification number and return a view of their profile.

Request

Url: localhost:9050/api/user/profile/4

Method:Get

Header: content-type: application/json

Parameter: user id:Long

Response 1

successful response

Status code: Ok 200

Body:


JSON








{
"data": {
"message": {
"id": 64,
"firstName": "shola",
"lastName": "Martins",
"phoneNumber": "08152865402",
"email": "sholaojo34@gmail.com",
"password": "FBDKGKAM925K4L1N0B3ISHR55O94PCMOocdayhXvlKZcaUPtRj5xXiGYFbRGzJrnXAhNaJzyQ=",
"locked": false
}
},
"successful": true
}


Response 2

Unsuccessful response

Status code: Not found

Body:


JSON








{ "data": {
"message": "Invalid details"
},
"successful": false
}

Edit Profile Request End-Point

Description
This end-point allows users update/edit their profile, it accepts user identification number, first name,last name and also the phone number the user intend to modify i.however these fields could be left blank or empty as it has no effect on the initial data.

Request

url : localhost:9050/api/user/profile

Method: Put

Header: content-type:application/json

Body:


JSON








{
"id":1,
"lastName":"Martins",
"phoneNumber":"08152865402"
}




JSON








{
"id":1,
"firstName" : "Tolu"
"lastName":"Martins",
"phoneNumber":"08152865402"
}


Fields:

useId:(required , Long): user Identification Number

First Name(required,String): User first name

Last Name:(required,String): User last name

phone number: (required, String): User phone number

Response 1

successful response

Status code:Ok 200

Body:


JSON








{
"data": {
"message": "profile updated"
},
"successful": true
}


Response 2

unsuccessful response

error cause: Invalid id number

Status Code: Not Found

Body


JSON








{
"data": {
"message": "Invalid details"
},
"successful": false
}


Response 3

unsuccessful response

Status code: Not Found

Error cause: wrong phone number format

Body:


JSON








{
"data": {
"message": "Wrong phone number format"
},
"successful": false
}


Fields:

Message: request message


Add Contact Request End-Point

Description

This end point allow user add contacts. it takes the contact name and phone number and save it to your list Contacts.

Request

Url: localhost:9050/api/user/contact

Method: Post

Header: content-type:application/json

Body:


JSON








{
"id":2,
"name"shola martins
"phoneNumber": "07088906754
}


Fields:

id:(required,Long): user identification number

name:(required,String):name of the person the user intended to save

phoneNumber:(required, String):the phone number of the intended person.


Response 1

successful response.

Status Code:Created

Body:


JSON








data": {
"message": "Contact added"
},
"successful": true


Response 2

unsuccessful request due to wrong identification number

Status Code : Bad request 400

Body:


JSON








{
"data": {
"message": "Invalid details"
},
"successful": false
}


Response 3:

unsuccessful request due to name already exisiting in the user contact list.

Status code: Bad request 400

Body:


JSON








{
"data": {
"message": "Name already exist"
},
"successful": false
}


Response 4

unsuccessful request due invalid phone number format

Status code: Bad Request 400

Body:


JSON








{
"data": {
"message": "Wrong phone number format"
},
"successful": false
}


Fields:

message: request message

Edit Contact Request

Description
This end-point allows users update/edit their contact list, it accepts contact name,name the user intended to modify and also the phone number.however these fields could be left blank or empty as it has no effect on the initial data.

Request

Url: localhost:9050/api/user/contact

Method: Put

Header: content_type: application/json

Body:


JSON








{
"userId":1,
"name":"tobi big",
"newPhoneNumber":"07066221008"
}




JSON








{
"userId":1,
"name":"tobi big",
"newContactName":shole small,
"newPhoneNumber":"+2347066221008"
}


Fields:

id(required,Long):user identification number

name:(require,String): name the user use the save the contact on his list

newContactName:(required,String): name the user intend to change the former name to

new ContactPhoneNumber(required,String): contact new phone number

Response 1

successful request

Status Code: Accepted 202

Body:


JSON








{
"data": {
"message": "contact has been updated"
},
"successful": true
}


Response 2

unsuccessful request.

Status code: Not Found

Body:


JSON








{
"data": {
"message": "contact doesn't exit"
},
"successful": false
}


Response 3

unsuccessful request

Status Code: bad request 400

Body:


JSON








{
{
"data": {
"message": "wrong phone number format"
},
"successful": false
}
}


Fields:

message: request message


View A Contact Request End-Point

Description

This end point allow user to view a particular contact in his contact list. it takes the name of the contact and returns the contact details to the user.

Request

Url: localhost:9050/api/user/contact/2
Method : Get

Header: content-type:application/json

parameter: userd:Long

Body:

parameter: Key:ContactName , Value: Tobi

Response 1

successful response.

Status Code: Ok 200

Body


JSON








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


Response 2

unsuccessful response.

Status code: Not Found

Body


JSON








{
"data": {
"message": "contact doesn't exit"
},
"successful": false
}

Response 3

unsuccessful request

Status code:Not Found

Error cause: wrong user identification number

Body:


JSON








{
"data": {
"message": "Invalid details"
},
"successful": false
}


View All Contact Request End-Point

Description

This end point views all contacts saved in the user contact list.it takes the user identification number as a parameter and return a list of of contacts saved.

Request

Url: localhost:9050/api/user/contacts/4

Method: Get

Parameter: userId:Long

Header: content-type:application/json

Response 1

successful response.

Status Code: Accepted 202

Body:


JSON








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


Response 2

unsuccessful request

Status code:Not Found

Error cause: wrong user identification number

Body:


JSON








{
"data": {
"message": "Invalid details"
},
"successful": false
}



Delete Contact Request End-Point

This end point allow user delete a contact from their contact list.it takes in the user identification number and the contact name.

Request

Url:localhost:9050/api/user/contact/id

Header: content-type:application/json

Method:Delete

Parameter: userId:Long

Body:
Parameter: Key = contactName , Value = Tobi

Response 1

Successful response

Status Code :Accepted 201

Body


JSON








{
"data": {
"message": "Contact has been deleted"
},
"successful": true
}


Response 2

Unsuccessful request due to Invalid registration number

Status Code:Not Found

Body


JSON








{
data": {
"message": "Invalid details"
},
"successful": false
}


Response 3

Unsuccessful request

Status code: Not Found

Body:


JSON








{
data": {
"message": "Cont doesn't exist"
},
"successful": false
}


Delete All Contact End-Point


Description

This end point allow user clear all their contacts that was saved. it takes only the user identification number.

Request

Url: localhost:9050/api/user/contacts/2

Parameter: userId: Long

Method : Delete

Header: content-type: application/json


Response 1

Successful response.

Status Code: Accepted 201

Body




JSON








{
"data": {
"message": "All contacts Deleted successfully"
},
"successful": true
}


Response 2

Unsuccessful request

Status Code: Not found

Body


JSON








{
"data": {
"message": "Invalid details"
},
"successful": false
}










Delete Account Request End-Point

Description

This end point allows user delete their account.

Request

Url: localhost:9050/api/user/user/1

Parameter: userId: Long

Method: Delete

Header: content-type:application/json

Body

json
{
"data": {
"message": "Account has been Deleted"
},
"successful": true
}


Response 2
Unsuccessful request

Status Code: Not Found

Body

json
{
"data": {
"message": "Invalid details"
},
"successful": false
}


Block Contact Request End-Point

Description

This end point allow user block a contact that was saved in their contact List.

Request
Url: localhost:9050/api/user/contact/1

Parameter:userId:Long

Method:Patch

Header:content-type:application/json

Parameter: Key: ContactName

Response 1

Successful Response

Status Code: Accepted 201

Body:

json
{
"data": {
"message": "contact is Blocked"
},
"successful": true
}

Response 2

unsuccessful request

Status Code: Bad Request 400

Body:

json
{
"data": {
"message": "Contact has been blocked already"
},
"successful": false
}

Response 3

unsuccessful request

Status Code: Bad Request 400

Body:

json
{
"data": {
"message": "Invalid details"
},
"successful": false
}



Unblock contact Request End-Point

Description

This end point allow user unblock contact in their contact list.This delete request returns a message.

Request:

Url: localhost:9050/api/user/contact/id

Parameter: userId: Long

Parameter: Key:ContactName, Value: String

Method: Post

Header: content-type: application/json

Response 1:

Successful response.

Status code: Accepted 201

Body

json
{
"data": {
"message": "Contact has been unblocked"
},
"successful": true
}


Response 2

unsuccessful request

Status Code: Bad request 400

Body

json
{
"data": {
"message": "contact doesn't exit"
},
"successful": false
}


Response 3

unsuccessful Request

Status Code: Bad Request

Body

json
{
"data": {
"message": "Invalid details"
},
"successful": false
}
