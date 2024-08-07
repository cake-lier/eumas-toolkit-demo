package org.hyperagents.demo;

import cartago.OPERATION;
import cartago.OpFeedbackParam;
import ch.unisg.ics.interactions.wot.td.schemas.ArraySchema;
import ch.unisg.ics.interactions.wot.td.schemas.DataSchema;
import ch.unisg.ics.interactions.wot.td.schemas.IntegerSchema;

import org.hyperagents.yggdrasil.cartago.artifacts.HypermediaTDArtifact;

import java.util.Random;

public class Source extends HypermediaTDArtifact {
    private final Random random = new Random();

    @OPERATION
    public void produce(final OpFeedbackParam<Integer> item) {
        this.await_time((this.random.nextInt(5) + 1) * 1_000);
        final var producedItem = this.random.nextInt(10);
        this.log("Item (" + producedItem + ") has been produced!");
        item.set(producedItem);
    }

    @Override
    protected void registerInteractionAffordances() {
        this.registerActionAffordance(
            "https://example.org/Produce",
            "produce",
            "produce",
            null,
            new ArraySchema.Builder()
                .addItem(new IntegerSchema.Builder().build())
                .build()
        );
    }
}
