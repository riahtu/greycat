package org.mwg.memory.offheap;

import org.mwg.Callback;
import org.mwg.Graph;
import org.mwg.GraphBuilder;
import org.mwg.Type;
import org.mwg.internal.scheduler.HybridScheduler;
import org.mwg.task.TaskResult;

import static org.mwg.task.Tasks.cond;
import static org.mwg.task.Tasks.newTask;

@SuppressWarnings("Duplicates")
public class BenchmarkParTask {

    public static void main(String[] args) {

        Graph g = new GraphBuilder()
                .withMemorySize(1000000)
                .withPlugin(new OffHeapMemoryPlugin())
                .withScheduler(new HybridScheduler())
                .build();
        g.connect(result -> {
            final long previous = System.currentTimeMillis();
            final long previousCache = g.space().available();
            newTask().loopPar("0", "9999",
                    newTask()
                            .createNode()
                            .setAttribute("name", Type.STRING, "node_{{i}}")
                            .print("{{result}}")
                            .addToGlobalIndex("nodes", "name")
                            .loop("0", "999",
                                    newTask().travelInTime("{{i}}").setAttribute("val", Type.INT, "{{i}}").clearResult())
                            .ifThen(cond("i % 100 == 0"), newTask().save())
                            .clearResult()
            ).save().readGlobalIndex("nodes").execute(g, new Callback<TaskResult>() {
                @Override
                public void on(TaskResult result) {
                    System.out.println("indexSize=" + result.size());
                    result.free();
                    long after = System.currentTimeMillis();
                    long afterCache = g.space().available();
                    System.out.println(after - previous + "ms");
                    System.out.println(previousCache + "-" + afterCache);
                    g.disconnect(new Callback<Boolean>() {
                        @Override
                        public void on(Boolean result) {
                        }
                    });
                }
            });
        });
    }

}
