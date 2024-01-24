-- SETUP DOCKER

cp .env.docker.example .env.docker

# Up SQL DB Test
mvn clean package
# Down SQL DB Test
docker build .
docker compose up --force-recreate
# Go to SQL DB
mysql -h 127.0.0.1 -P 3306 -u root -p
CREATE DATATABLE db_name
docker compose up



---
Get projet version
mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec 2>/dev/null 