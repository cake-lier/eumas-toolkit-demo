package org.hyperagents.demo;

import cartago.OPERATION;
import cartago.OpFeedbackParam;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import org.apache.http.entity.ContentType;

public class ProducerClient extends AbstractClient {
    @Override
    public void init(
        final String agentName,
        final String homeWorkspace,
        final String platformHost,
        final int platformPort,
        final String sharedHost,
        final int sharedPort
    ) {
        super.init(agentName, homeWorkspace, platformHost, platformPort, sharedHost, sharedPort);
    }

    @OPERATION
    public void produce(final OpFeedbackParam<Integer> item) {
        item.set(
            Integer.parseInt(
                this.doRequest(
                    this.getClient()
                        .post(
                            this.getPlatformPort(),
                            this.getPlatformHost(),
                            "/workspaces/production/artifacts/source/produce"
                        )
                        .putHeader("X-Agent-WebID", this.getAgentId())
                        .send()
                )
            )
        );
    }

    @OPERATION
    public void enqueue(final int item) {
        this.doRequest(
            this.getClient()
                .post(
                    this.getSharedPort(),
                    this.getSharedHost(),
                    "/workspaces/shared/artifacts/buffer/enqueue"
                )
                .putHeader("X-Agent-WebID", this.getAgentId())
                .putHeader(HttpHeaders.CONTENT_TYPE.toString(), ContentType.APPLICATION_JSON.getMimeType())
                .sendJson(JsonArray.of(item))
        );
    }
}
