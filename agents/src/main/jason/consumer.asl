!consume.

+!consume : client(ClientName) <-
    .print("Booting up");
    lookupArtifact(ClientName, ClientId);
    joinWorkspace("consumption") [artifact_id(ClientId)];
    .print("Own workspace joined");
    joinSharedWorkspace [artifact_id(ClientId)];
    .print("Shared workspace joined");
    !loop(ClientId).

+!loop(ClientId) : true <-
    poll(Item) [artifact_id(ClientId)];
    .print("Item (", Item, ") has been polled");
    consume(Item) [artifact_id(ClientId)];
    .print("Item (", Item, ") has been consumed");
    !loop(ClientId).

{ include("$jacamoJar/templates/common-cartago.asl") }