# employeeserv

## Solution
### DB Details
I have used H2 in-memory DB.

### Api's
	Get Employee By id
	Url: http://localhost:8080/v1/bfs/employees/1
	Method: GET
	Response: {
		"id": 1,
		"first_name": "manish",
		"last_name": "pandey",
		"date_of_birth": "18-05-1986",
		"address": {
			"line1": "abcd",
			"city": "bangalore",
			"state": "karnataka",
			"country": "India",
			"zipcode": "560066"
		}
	}
---------------
	Create Employee
	Url: http://localhost:8080/v1/bfs/employees
	Method: POST
	Request: {
		"first_name": "manish",
		"last_name": "pandey",
		"date_of_birth":"18-5-1986",
		"address":{
			"line1":"abcd",
			"city":"bangalore",
			"state":"karnataka",
			"country":"India",
			"zipcode":"560066"
		}
	}
	Response: {
		"id": 1,
		"first_name": "manish1",
		"last_name": "pandey",
		"date_of_birth": "18-05-1986",
		"address": {
			"line1": "abcd",
			"city": "bangalore",
			"state": "karnataka",
			"country": "India",
			"zipcode": "560066"
		}
	}
### Test Case
All the tests are written in the  `employeeservFunctionalTests` .

### Idempotency logic
I'm considering employees are same if all the fields of the employees match.

### Validations & Response Codes
- For any validation failures returning `400 Bad request`. 
- For Internal Server Error returning `500 Internal Serever Error`
- For Employee Not Existing returning `404 Employee does not exists`
- For Querying Employee returning `200`
- `201` in case employee created successfully or employee object is returned successfully.


## Application Overview
employeeserv is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.

## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.

## Assignment
We would like you to enhance the existing project and see you complete the following requirements:

- `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
- Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
- Implement create and retrieve operations in `EmployeeResourceImpl.java`.
- Resource created using create endpoint should be retrieved using retrieve/get endpoint.
- Please add the unit tests to validate your implementation.
- Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
- Please make sure the validations are done for the requests.
- Response codes are as per rest guidelines.
- Error handling in case of failures.
- Idempotency logic is implemented to avoid duplicate resource creation.

## Assignment submission
Thank you very much for your time to take this test. Please upload this complete solution in Github and send us the link to `bfs-sor-interview@paypal.com`.


