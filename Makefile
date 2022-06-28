clean:
	mvn clean
	docker-compose down
	@docker rm -f $(docker ps -qa) |true
build:
	cd commons && mvn install
	cd cp && quarkus build -Dquarkus.container-image.build=true
	cd sp && quarkus build -Dquarkus.container-image.build=true
	cd co && quarkus build -Dquarkus.container-image.build=true
push:
	docker push nherbaut/cp:1.0.0-SNAPSHOT
	docker push nherbaut/co:1.0.0-SNAPSHOT
	docker push nherbaut/sp:1.0.0-SNAPSHOT
run:
	docker-compose up -d