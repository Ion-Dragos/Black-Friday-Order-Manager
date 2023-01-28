import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Tema2 {

    static String ordersFile;
    static String orderProductsFile;
    static int nrThreads;
    static BufferedWriter writerOrders;
    static BufferedWriter writerOrderProducts;
    static BufferedReader reader;

    public static void main(String[] args) throws IOException{

        ordersFile = args[0] + "/orders.txt";
        orderProductsFile = args[0] + "/order_products.txt";
        nrThreads = Integer.parseInt(args[1]);

        AtomicInteger inQueue1 = new AtomicInteger(0);
        AtomicInteger inQueue2 = new AtomicInteger(0);
        ExecutorService tpe1 = Executors.newFixedThreadPool(nrThreads);
        ExecutorService tpe2 = Executors.newFixedThreadPool(nrThreads);

        writerOrders = new BufferedWriter(new FileWriter("orders_out.txt"));
        writerOrderProducts = new BufferedWriter(new FileWriter("order_products_out.txt"));

        reader = new BufferedReader(new FileReader(Tema2.ordersFile));

        inQueue1.incrementAndGet();
        tpe1.submit(new MyRunnableOrders(tpe1, tpe2, inQueue1, inQueue2, reader));
    }
}

