!produce.

+!produce : client(ClientName) <-
    .print("Booting up");
    lookupArtifact(ClientName, ClientId);
    joinWorkspace("production") [artifact_id(ClientId)];
    .print("Own workspace joined");
    joinSharedWorkspace [artifact_id(ClientId)];
    .print("Shared workspace joined");
    !loop(ClientId).

+!loop(ClientId) : true <-
    produce(Item) [artifact_id(ClientId)];
    .print("Item (", Item, ") has been received");
    enqueue(Item) [artifact_id(ClientId)];
    .print("Item (", Item, ") has been enqueued");
    !loop(ClientId).

{ include("$jacamoJar/templates/common-cartago.asl") }