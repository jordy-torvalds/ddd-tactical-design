package kitchenpos;

import kitchenpos.common.infra.FakePurgomalum;
import kitchenpos.common.values.Name;
import kitchenpos.common.values.Price;
import kitchenpos.eatinorders.domain.*;
import kitchenpos.menus.domain.MenuGroup;
import kitchenpos.menus.domain.Menu;
import kitchenpos.menus.domain.MenuProduct;
import kitchenpos.products.domain.Product;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Fixtures {
    public static final UUID INVALID_ID = new UUID(0L, 0L);

    public static Menu menu() {
        return menu(19_000L, true, menuProduct());
    }

    public static Menu menu(final long price, final MenuProduct... menuProducts) {
        return menu(price, false, menuProducts);
    }

    public static Menu menu(final long price, final boolean displayed, final MenuProduct... menuProducts) {
        Name createdName = new Name("후라이드+후라이드", FakePurgomalum.create());
        Price createdPrice = new Price(price);
        return new Menu(
                createdName,
                createdPrice,
                menuGroup(),
                Arrays.asList(menuProducts),
                displayed
        );
    }

    public static MenuGroup menuGroup() {
        return menuGroup(new Name("후라이드+후라이드", FakePurgomalum.create()));
    }

    public static MenuGroup menuGroup(final Name name) {
        return new MenuGroup(name);
    }

    public static MenuProduct menuProduct() {
        return new MenuProduct(
                new Random().nextLong(),
                product().getId(),
                2L
        );
    }

    public static MenuProduct menuProduct(final Product product, final long quantity) {
        return new MenuProduct(
                new Random().nextLong(),
                product.getId(),
                quantity
        );
    }

    public static Order order(final OrderStatus status, final String deliveryAddress) {
        final Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setType(OrderType.DELIVERY);
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(orderLineItem()));
        order.setDeliveryAddress(deliveryAddress);
        return order;
    }

    public static Order order(final OrderStatus status) {
        final Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setType(OrderType.TAKEOUT);
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(orderLineItem()));
        return order;
    }

    public static Order order(final OrderStatus status, final OrderTable orderTable) {
        final Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setType(OrderType.EAT_IN);
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(orderLineItem()));
        order.setOrderTable(orderTable);
        return order;
    }

    public static OrderLineItem orderLineItem() {
        final OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenu(menu());
        return orderLineItem;
    }

    public static OrderTable orderTable() {
        return orderTable(false, 0);
    }

    public static OrderTable orderTable(final boolean occupied, final int numberOfGuests) {
        final OrderTable orderTable = new OrderTable();
        orderTable.setId(UUID.randomUUID());
        orderTable.setName("1번");
        orderTable.setNumberOfGuests(numberOfGuests);
        orderTable.setOccupied(occupied);
        return orderTable;
    }

    public static Product product() {
        return product("후라이드", 16_000L);
    }

    public static Product product(final String name, final long price) {
        Name createdName = new Name(name, FakePurgomalum.create());
        Price createdPrice = new Price(price);
        return new Product(createdName, createdPrice);
    }
    public static Product toBeProduct(final String name, final long price) {
        Name createdName = new Name(name, FakePurgomalum.create());
        Price createdPrice = new Price(price);
        return new Product(createdName, createdPrice);
    }
}
