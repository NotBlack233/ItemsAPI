# ItemsAPI

Todo: complete description

### API usage

Use class MainAPI

```java
import me.not_black.itemsapi.MainAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Example {
    private static ItemStack itemStack;

    public static void main(String[] args) {
        // Get an item
        itemStack = MainAPI.getItemStack("foo");

        // Import an item
        MainAPI.importItemStack("bar", new ItemStack(Material.DIAMOND));
    }
}
```
