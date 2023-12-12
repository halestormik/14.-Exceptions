import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ShopRepositoryTest {
    Product product1 = new Product(1, "хлеб", 40);
    Product product2 = new Product(2, "булка", 30);
    Product product3 = new Product(3, "картошка", 20);

    @Test
    public void shouldDisplayException() { // проверка выполения заданного Exception
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(4);
        });
    }

    @Test
    public void shouldDeleteProductFromRepository() { // удаление продукта из репозитория
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = {product1, product3};
        Product[] actual = repo.remove(2);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddProduct() { // добавление нового продукта с новым Id
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product product4 = new Product(4, "молоко", 60);


        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repo.add(product4);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMakeExceptionByAddingProductWithSameId() { // добавление нового продукта с повторяющимся Id
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product product4 = new Product(2, "молоко", 60);


        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }
}
