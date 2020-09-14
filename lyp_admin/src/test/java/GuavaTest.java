import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaTest {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                RateLimiter rateLimiter = RateLimiter.create(5);
                while (true) {
                    System.out.println("run 0:"+rateLimiter.acquire());
                }
            });
            executorService.execute(() -> {
                RateLimiter rateLimiter = RateLimiter.create(5);
                while (true) {
                    System.out.println("run 1:"+rateLimiter.acquire());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
