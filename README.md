


# mdt is a spring boot based graphql crud demo.
it has a frontend ui in a saperate repo: crudui
both fronend ui and backend api are deployed in azure cloud and api is also setup behind azure api management


deployment note
crudui is hosted at: https://dhweb.z13.web.core.windows.net/

its backend api is hosted in azure docker container at: http://demo-graphql-springboot-justin.eastus.azurecontainer.io:8080/api/v1/pod

an azure api management was configured for it, and its public access address is: https://demo-api-proxy.azure-api.net/demo-graphql-springboot-justin
