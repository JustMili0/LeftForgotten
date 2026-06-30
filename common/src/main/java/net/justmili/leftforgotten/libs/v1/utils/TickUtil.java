package net.justmili.leftforgotten.libs.v1.utils;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.server.MinecraftServer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TickUtil {
    private static final ConcurrentLinkedQueue<WorkItem> queue = new ConcurrentLinkedQueue<>();

    public static void registerProcessQueue() {
        TickEvent.SERVER_POST.register(server -> queue.removeIf(item -> {
            if (--item.processTicks <= 0) { item.task.run(); return true; }
            return false;
        }));
    }
    public static void waitTicks(int ticks, Runnable action) {
        queue.add(new WorkItem(action, ticks));
    }

    public static void serverTickExecute(MinecraftServer server, Runnable task) {
        if (server != null) server.execute(task);
    }

    // Minimized helper class
    private static class WorkItem {
        Runnable task;
        int processTicks;

        WorkItem(Runnable task, int ticks) {
            this.task = task;
            this.processTicks = ticks;
        }
    }
}