package org.hyperagents.demo;

import cartago.OPERATION;
import cartago.OpFeedbackParam;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import org.apache.http.entity.ContentType;

public class ConsumerClient extends AbstractClient {
    @Override
    public void init(
        final String agentName,
        final String platformHost,
        final int platformPort,
        final String sharedHost,
        final int sharedPort
    ) {
        super.init(agentName, platformHost, platformPort, sharedHost, sharedPort);
    }

    @OPERATION
    public void consume(final int item) {
        this.doRequest(
            this.getClient()
                .post(
                    this.getPlatformPort(),
                    this.getPlatformHost(),
                    "/workspaces/consumption/artifacts/sink/consume"
                )
                .putHeader("X-Agent-WebID", this.getAgentId())
                .putHeader(HttpHeaders.CONTENT_TYPE.toString(), ContentType.APPLICATION_JSON.getMimeType())
                .sendJson(JsonArray.of(item))
        );
    }

    @OPERATION
    public void poll(final OpFeedbackParam<Integer> item) {
        item.set(
            Integer.parseInt(
                this.doRequest(
                        this.getClient()
                            .post(
                                this.getSharedPort(),
                                this.getSharedHost(),
                                "/workspaces/shared/artifacts/buffer/poll"
                            )
                            .putHeader("X-Agent-WebID", this.getAgentId())
                            .send()
                )
            )
        );
    }
}
