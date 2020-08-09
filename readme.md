### 1. Create an environment
```
docker-compose up -d
```

### 2. Run migration scripts

Once you have run the command above ensure Cassandra has started successfully by checking the logs `docker logs -f eis_cassandra_1`. Once started run the following commands to create the database.  

```
docker cp ./eis-sentence-rest/migration eis_cassandra_1:/migration
docker exec -it eis_cassandra_1 sh -c "cqlsh < /migration/changeset-2020-08-08-1-create-keyspace.cql"
docker exec -it eis_cassandra_1 sh -c "cqlsh < /migration/changeset-2020-08-08-2-create-user-sentence.cql"
```

### 3. Compile and run Engine
```
cd eis-sentence-engine/
mvn clean install
java -jar ./eis-sentence-engine-boot/target/eis-sentence-engine-boot.jar
```

Use url http://localhost:8081/eis/status to check the status of the service. You should get an `OK`

### 4. Compile and run Rest API
```
cd eis-sentence-rest/
mvn clean install
java -jar ./eis-sentence-rest-api/target/eis-sentence-rest-api.jar
```

Use url http://localhost:8080/eis/status to check the status of the service. You should get an `OK`

### 5. Run test script
```
test.sh 1
```

where `1` is the user id (can be any other)

Once script execution is completed, inspect the user related sentences by using the Rest API
```
curl -v http://localhost:8080/eis/users/1/sentences
```

or by inspecting the database:
```
docker exec -it eis_cassandra_1 cqlsh
select * from eis.user_sentences where user_id = '1';
```

### Delete an environment
```
docker-compose down
```