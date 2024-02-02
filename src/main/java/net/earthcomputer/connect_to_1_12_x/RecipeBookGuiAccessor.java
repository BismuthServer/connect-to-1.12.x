package net.earthcomputer.connect_to_1_12_x;

import java.util.List;
import net.minecraft.crafting.recipe.Recipe;
import net.minecraft.inventory.slot.InventorySlot;

public interface RecipeBookGuiAccessor {

	void doSetGhostRecipe(Recipe recipe, List<InventorySlot> slots);

}
