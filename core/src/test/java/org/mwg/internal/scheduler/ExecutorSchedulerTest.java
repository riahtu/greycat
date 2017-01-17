package org.mwg.internal.scheduler;

import org.mwg.Callback;
import org.mwg.DeferCounterSync;
import org.mwg.Graph;
import org.mwg.GraphBuilder;
import org.mwg.task.TaskResult;

import static org.mwg.internal.task.CoreActions.print;
import static org.mwg.task.Tasks.newTask;

/**
 * @ignore ts
 */
public class ExecutorSchedulerTest {

    //@Test
    public void test() {
        Graph g = new GraphBuilder().withScheduler(new ExecutorScheduler()).build();
        DeferCounterSync waiter = g.newSyncCounter(1);
        g.connect(new Callback<Boolean>() {
            @Override
            public void on(Boolean result) {
                newTask().loopPar("0", "99", newTask().then(print("{{result}}")))
                        .execute(g, new Callback<TaskResult>() {
                            @Override
                            public void on(TaskResult result) {
                                System.out.println("end");
                                g.disconnect(new Callback<Boolean>() {
                                    @Override
                                    public void on(Boolean result) {
                                        System.out.println("Disconnected");
                                        waiter.count();
                                    }
                                });
                            }
                        });
            }
        });
        waiter.waitResult();
        System.out.println("Result are here...");

/*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
    }

}
