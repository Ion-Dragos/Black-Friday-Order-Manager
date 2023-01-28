import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnableOrders implements Runnable {

    ExecutorService tpe1;
    ExecutorService tpe2;
    AtomicInteger inQueue1;
    AtomicInteger inQueue2;
    BufferedReader reader;

    public MyRunnableOrders(ExecutorService tpe1,
                            ExecutorService tpe2,
                            AtomicInteger inQueue1,
                            AtomicInteger inQueue2,
                            BufferedReader reader) {
        this.tpe1 = tpe1;
        this.tpe2 = tpe2;
        this.inQueue1 = inQueue1;
        this.inQueue2 = inQueue2;
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            String line = "";
            line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                int numProducts = Integer.parseInt(parts[1]);
                if (numProducts != 0) {
                    inQueue2.incrementAndGet();
                    tpe2.submit(new MyRunnableOrderProduct(tpe2, inQueue2, id, numProducts));
                    Tema2.writerOrders.write(id + ',' + numProducts + ",shipped\n");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int left = inQueue1.decrementAndGet();
        if (left == 0) {
            tpe1.shutdown();
            try {
                Tema2.writerOrders.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}