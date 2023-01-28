import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnableOrderProduct implements Runnable{

    ExecutorService tpe2;
    AtomicInteger inQueue2;
    String id;
    int numProducts;

    public MyRunnableOrderProduct(ExecutorService tpe2,
                                  AtomicInteger inQueue2,
                                  String id,
                                  int numProducts) {
        this.tpe2 = tpe2;
        this.inQueue2 = inQueue2;
        this.id = id;
        this.numProducts = numProducts;
    }


    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Tema2.orderProductsFile));
            String line = "";
            line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String idOrder = parts[0];
                String idProduct = parts[1];
                if (idOrder.equals(id)) {
                    Tema2.writerOrderProducts.write(id + ',' + idProduct + ",shipped\n");
                    numProducts--;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int left = inQueue2.decrementAndGet();
        if (left == 0) {
            tpe2.shutdown();
            try {
                Tema2.writerOrderProducts.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
