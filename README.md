# filestorage
Microservices to store data in files
Steps to run
1. Run files microservice (gradlew bootrun)
2. Run storage microservice
3. Run RabbitMQ locally (brew install rabbitmq, rabbitmq-server)
4. Make a Post call to localhost:8080/file
with data 
{
	"age": 24,
	"dob": "1997-11-08",
	"salary": 100,
	"name": "John"
} with header fileType CSV or XML
5. Make a get call to localhost:8080/file
to retrive all the records.
