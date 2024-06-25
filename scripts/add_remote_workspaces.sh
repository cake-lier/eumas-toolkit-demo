curl -X PUT 'http://localhost:8082/workspaces/manufacturing#workspace' \
-H 'Content-Type: text/turtle' \
-H 'X-Agent-WebID: http://localhost:8080/workspaces/production/artifacts/alice/#agent' \
--data-binary '@scripts/manufacturing_workspace.ttl'
