package effective.java.chapter2.item8;

import sun.misc.Cleaner;

public class Room implements AutoCloseable {
    private final Cleaner cleaner;

    // 청소가 필요한 자원. 절대 Room을 참조해서는 안된다.
    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles){
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }

    // 방 상태 cleanable과 공유
    private final State state;

    // 수거 대상이 되면 방을 청소한다.
//    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles){
        state = new State(numJunkPiles);
        cleaner = Cleaner.create(this, state);
    }

    @Override
    public void close() throws Exception {
        cleaner.clean();
    }
}
