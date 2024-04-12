import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class QueueT {

    static class Item implements Delayed {

        private String name;

        private long time;


        public Item(String name, long delay, TimeUnit unit) {
            this.time = System.currentTimeMillis() + unit.toMillis(delay);
            this.name = name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Item item = (Item) o;
            long diff = this.time - item.time;
            if (diff <= 0) {
                return -1;
            } else {
                return 1;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        DelayQueue<Item> items = new DelayQueue<>();
        Item jim = new Item("jim", 3, TimeUnit.SECONDS);
        Item john = new Item("john", 20, TimeUnit.SECONDS);
        Item follen = new Item("follen", 30, TimeUnit.SECONDS);
        items.put(jim);
        items.put(john);
        items.put(follen);

        for (int i = 0; i < 3; i++) {
            Item item = items.take();
            System.out.println("we get item : " + item.getName());
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        }

    }


}
