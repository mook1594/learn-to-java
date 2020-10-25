package effective.java.chapter2.item2;

import java.util.Objects;

public class Ex5NyPizza extends Ex4Pizza {
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class Builder extends Ex4Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }

        @Override
        Ex5NyPizza build() {
            return new Ex5NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Ex5NyPizza(Builder builder){
        super(builder);
        size = builder.size;
    }
}
