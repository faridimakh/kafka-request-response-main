kafka-topics --bootstrap-server localhost:9092 --describe --topic wikichangesresponses

kafka-topics --bootstrap-server localhost:9092 --delete --topic wikichangesresponses

kafka-topics --bootstrap-server localhost:9092 --list

kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic wikichangesresponses --time -1
kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic wikichangesrequests --time -1

kafka-console-consumer --topic wikichangesresponses --from-beginning --bootstrap-server localhost:9092

kafka-configs --bootstrap-server localhost:9092 --alter --entity-type topics --entity-name wikichangesresponses --add-config max.message.bytes=10485880
