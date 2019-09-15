This application forms the core part of the Alert Notification System. It contains all the functional rest end points.
The application can be tested using any Rest Client such as Postman. We have mentioned the the API specifications below for the same. Also you can import the collection file Bank.postman_collection and run it in your Postman.

**Application Flow:**

The customer makes the appointment using the API II. The branch head gets the notification about the customer visiting the branch if the customer happens to be a high net worth individual.
The branch head also gets the reminder mail on the day, the customer is supposed to visit the branch.
The staff can view the list of appointments assigned to him/her got the day using the API III. The staff can also change the appointment status to ATTENDED or ABSENT using the API IV.
The branch head can view the list of appointments in his/her branch for the day using the API V. Can also re-assign the staff for a particular appointment using the API VI.

**App Url**: http://bankappbackend-env.ehbhe9ppne.us-east-2.elasticbeanstalk.com

Note: AWS RDS service MySQL instance and ElasticBeanstalk Free Tier is being used for deployment.

**Steps to Test the Rest API's**
	
1.  Open Postman
2.  Import the Bank.postman_collection.json using the Import button 
3.  Go to the Settings icon on the top right. A dialog box : manage environment opens up.
4.  Add a new environment name: prod
5.  Add a new variable : url
6.  Set it's initial value as http://bankappbackend-env.ehbhe9ppne.us-east-2.elasticbeanstalk.com
7.  You're good to go now.

API I.  GET /customer/branches  -  List of branches displayed to the customer after selection of the name of the state and city
    Request Headers
    city : <city-name> Ex: BANGALORE
    state : <state-name> Ex: KARNATAKA
    No Authorization Required

API II.  GET /customer/appointments  -  Customer making an appointment prior to the branch visit
    Request Body
    Type: application/json
    {
      "phone":"9999209435",
      "name":"Dan",
      "branchName":"Chickpet",
      "purpose":"Digital Banking services",
      "subPurpose":"Internet Banking Activate",
      "date":"2019-09-22"
    }
    No Authorization Required

API III. GET /staff/appointments  - Staff viewing the appointments assigned to them
   Basic Authorization Required
   Username : staff1@gmail.com
   Password: 1234
   
API IV. PUT /staff/change_appintment_status  -  Change appointment Status
   Basic Authorization Required
   Username: staff1@gmail.com
   Password: 1234
   Request Body
   Type: application/json
   {
	    "id": "7",
	    "status" : "ATTENDED"
   }

API V. GET /branch_head/find_branch_appointments - Branch Head - Get All appointments for the day
   Basic Authorization Required
   Username: sanchitgarg2012@gmail.com
   Password: 1234
   
API VI. PUT /branch_head/reassign_staff  -  Branch Head - Reassign Staff
   Basic Authorization Required
   Username: sanchitgarg2012@gmail.com
   Password: 1234
   Request Body
   Type : application/json
   {
	    "id" : "7",
	    "username" : "staff2@gmail.com"
   }
   
   
Administrator API's

API VII. GET /users - Get list of all users (Branch heads and staffs)
   Basic Authorization Required
   Username: admin
   Password: 1234
   
API VIII. POST /users Add user (Branch Head or staff)
   Basic Authorization Required
   Username: admin
   Password: 1234
   Request Body
   Type : application/json
   {
	    "userName": "staff3@gmail.com",
	    "name": "Staff 3",
	    "phone": 9898767654,
	    "password": "1234",
	    "role": "STAFF",
	    "branch": null
   }

API IX GET /user/{username}	Get the details of a single user basis their username Ex: /users/staff1@gmail.com
Basic Authorization Required
   Username: admin
   Password: 1234

API X DELETE /user/{username}	Delete the details of a single user basis their username Ex: /users/staff1@gmail.com
Basic Authorization Required
   Username: admin
   Password: 1234

API XI. PUT /users Update any user details (Branch Head or staff)
   Basic Authorization Required
   Username: admin
   Password: 1234
   Request Body
   Type : application/json
   {
	    "userName": "staff3@gmail.com",
	    "name": "Staff 3",
	    "phone": 9898777444,
	    "password": "1234",
	    "role": "STAFF",
	    "branch": null
   }
   
Note : API's for customer do not need any authorization

If you want to run the application on the local system, then please ensure you meet the following software requirements.
1. Java 8 or higher must be installed
2. Clone the source code using the command: git clone https://github.com/maxsteel1996/BankAppBackend.git
2. Open command prompt -> Change the directory where the project has been cloned : **myapp-0.0.1-SNAPSHOT** is present.
3. Run the following command:  java -jar myapp-0.0.1-SNAPSHOT
