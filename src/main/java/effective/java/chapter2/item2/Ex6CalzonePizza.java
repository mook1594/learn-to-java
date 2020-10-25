package effective.java.chapter2.item2;

public class Ex6CalzonePizza extends Ex4Pizza {
    private final boolean sauceInside;

    public static class Builder extends Ex4Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside(){
            sauceInside = true;
            return this;
        }

        @Override
        Ex6CalzonePizza build() {
            return new Ex6CalzonePizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Ex6CalzonePizza(Builder builder){
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
