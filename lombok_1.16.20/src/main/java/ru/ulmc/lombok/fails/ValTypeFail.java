package ru.ulmc.lombok.fails;

import java.util.function.Function;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import lombok.val;

public class ValTypeFail {

    public void config() {
        val columnBool = createColumn(entity -> entity.getABoolean()); // this is working!
        val column = createColumn(Entity::getValue); // and here an error occurs
    }

    private <V> Column<Entity, V> createColumn(Function<Entity, V> func) {
        return new Column<>(func);
    }

    private static class Column<T, V> {
        public Column(Function<T, V> vp) {}
    }

    public static class Entity {
        @Delegate
        private MyDelegate innerDelegate;
    }

    @Getter
    @Setter
    public static class MyDelegate {
        private String value;
        private Boolean aBoolean;
    }
}
