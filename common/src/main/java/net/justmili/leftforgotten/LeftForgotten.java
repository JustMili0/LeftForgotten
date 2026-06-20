package net.justmili.leftforgotten;

import dev.architectury.event.events.common.TickEvent;
import net.justmili.leftforgotten.content.entity.BoatImpactPacket;
import net.justmili.leftforgotten.registries.*;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class LeftForgotten {
    public static final Logger LOGGER = LoggerFactory.getLogger(LeftForgotten.class);
    public static final String MODID = "left_forgotten";

    public static void init() {
        TickEvent.SERVER_POST.register(server -> processQueue());
        LFBlocks.register();
        LFItems.register();
        LFTab.register();
        LFEntities.register();
        LFSounds.register();

        BoatImpactPacket.register();
        Events.register();
    }

    private static final ConcurrentLinkedQueue<WorkItem> workQueue = new ConcurrentLinkedQueue<>();
    private static class WorkItem {
        final Runnable task;
        int ticksRemaining;

        WorkItem(Runnable task, int delay) {
            this.task = task;
            this.ticksRemaining = delay;
        }
    }
    public static void processQueue() {
        for (Iterator<WorkItem> iterator = workQueue.iterator(); iterator.hasNext(); ) {
            WorkItem item = iterator.next();
            item.ticksRemaining--;
            if (item.ticksRemaining <= 0) {
                item.task.run();
                iterator.remove();
            }
        }
    }
    public static void wait(int tickDelay, Runnable action) {
        workQueue.add(new WorkItem(action, tickDelay));
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
