
### Create an environment
```
docker-compose up -d
```

### Run migration scripts
```
docker cp ./eis-sentence-rest/migration eis_cassandra_1:/migration
docker exec -it eis_cassandra_1 sh -c "cqlsh < /migration/changeset-2020-08-08-1-create-keyspace.cql"
docker exec -it eis_cassandra_1 sh -c "cqlsh < /migration/changeset-2020-08-08-2-create-user-sentence.cql"
```

### Delete an environment
```
docker-compose down
```