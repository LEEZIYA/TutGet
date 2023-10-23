//1. run the discovery service and profile service
//2. open cmd in this folder and ran the below command to populate sample user

curl -d "@student.json" -H "Content-Type: application/json" -X POST http://localhost:8093/users
curl -d "@teacher.json" -H "Content-Type: application/json" -X POST http://localhost:8093/users