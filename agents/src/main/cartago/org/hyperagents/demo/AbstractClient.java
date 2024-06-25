package org.hyperagents.demo;

import cartago.Artifact;
import cartago.GUARD;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public abstract class AbstractClient extends Artifact {
    private WebClient client;
    private String platformHost;
    private int platformPort;
    private String sharedHost;
    private int sharedPort;
    private String agentName;
    private String homeWorkspace;
    private boolean requestComplete;

    @OPERATION
    public void joinWorkspace(final String workspaceName) {
        this.doRequest(
            this.getClient()
                .post(this.platformPort, this.platformHost, "/workspaces/" + workspaceName + "/join")
                .putHeader("X-Agent-WebID", this.getAgentId())
                .putHeader("X-Agent-LocalName", this.agentName)
                .send()
        );
    }

    @OPERATION
    public void joinSharedWorkspace() {
        this.doRequest(
            this.getClient()
                .post(this.sharedPort, this.sharedHost, "/workspaces/shared/join")
                .putHeader("X-Agent-WebID", this.getAgentId())
                .putHeader("X-Agent-LocalName", this.agentName)
                .send()
        );
    }

    protected void init(
        final String agentName,
        final String homeWorkspace,
        final String platformHost,
        final int platformPort,
        final String sharedHost,
        final int sharedPort
    ) {
        this.client = WebClient.create(Vertx.vertx());
        this.agentName = agentName;
        this.homeWorkspace = homeWorkspace;
        this.platformHost = platformHost;
        this.platformPort = platformPort;
        this.sharedHost = sharedHost;
        this.sharedPort = sharedPort;
    }

    protected final WebClient getClient() {
        return this.client;
    }

    protected final String getPlatformHost() {
        return this.platformHost;
    }

    protected final int getPlatformPort() {
        return this.platformPort;
    }

    protected final String getSharedHost() {
        return this.sharedHost;
    }

    protected final int getSharedPort() {
        return this.sharedPort;
    }

    protected final String getAgentId() {
        return "http://" + this.platformHost + ":" + this.platformPort + "/workspaces/" + this.homeWorkspace + "/agents/" + this.agentName + "#agent";
    }

    protected final String doRequest(final Future<HttpResponse<Buffer>> request) {
        this.requestComplete = false;
        request.onComplete(r -> this.execInternalOp("signalResponseReceived"));
        this.await("isRequestComplete");
        if (request.succeeded() && request.result().statusCode() <= 299) {
            return request.result().bodyAsString();
        } else if (!request.succeeded()) {
            this.failed(request.cause().getMessage());
        } else {
            this.failed(request.result().statusMessage());
        }
        throw new RuntimeException();
    }

    @INTERNAL_OPERATION
    private void signalResponseReceived() {
        this.requestComplete = true;
    }

    @GUARD
    private boolean isRequestComplete() {
        return this.requestComplete;
    }
}
