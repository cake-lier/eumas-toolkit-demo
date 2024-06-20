package org.hyperagents.demo;

import cartago.OPERATION;
import ch.unisg.ics.interactions.wot.td.schemas.ArraySchema;
import ch.unisg.ics.interactions.wot.td.schemas.IntegerSchema;
import org.hyperagents.yggdrasil.cartago.artifacts.HypermediaArtifact;

import java.util.Random;

public class Sink extends HypermediaArtifact {
    private final Random random = new Random();

    @OPERATION
    public void consume(final int item) {
        this.await_time((this.random.nextInt(5) + 1) * 1_000);
        this.log("Item (" + item + ") has been consumed!");
    }

    @Override
    protected void registerInteractionAffordances() {
        this.registerActionAffordance(
            "https://example.org/Consume",
            "consume",
            "/consume",
            new ArraySchema.Builder()
                           .addItem(new IntegerSchema.Builder().build())
                           .build()
        );
    }
}
