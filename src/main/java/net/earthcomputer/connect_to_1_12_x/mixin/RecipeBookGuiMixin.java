package net.earthcomputer.connect_to_1_12_x.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;

import net.earthcomputer.connect_to_1_12_x.PlaceRecipeC2SPacket;
import net.earthcomputer.connect_to_1_12_x.MultiConnectHelper;
import net.earthcomputer.connect_to_1_12_x.PacketLists;
import net.earthcomputer.connect_to_1_12_x.RecipeBookGuiAccessor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.RecipeCollection;
import net.minecraft.client.gui.recipebook.GhostRecipe;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.crafting.RecipeBook;
import net.minecraft.crafting.recipe.Recipe;
import net.minecraft.crafting.recipe.ShapedRecipe;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.ResultInventory;
import net.minecraft.inventory.menu.CraftingTableMenu;
import net.minecraft.inventory.menu.InventoryMenu;
import net.minecraft.inventory.menu.PlayerMenu;
import net.minecraft.inventory.slot.InventorySlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.StackedContents;

@Mixin(RecipeBookGui.class)
public class RecipeBookGuiMixin implements RecipeBookGuiAccessor {
    @Unique
	private static final Logger LOGGER = LogManager.getLogger();

    @Unique
    private InventoryMenu menu;

    @Shadow @Final
    private GhostRecipe ghostRecipe;

    @Shadow @Final
    private RecipeBookPage page;

    @Shadow
    private CraftingInventory inventory;

    @Shadow
    private Minecraft minecraft;

    @Shadow
    private RecipeBook recipeBook;

    @Shadow
    private StackedContents contents = new StackedContents();

    @Shadow
	public void setGhostRecipe(Recipe recipe, List<InventorySlot> slots) {
    }

    @Override
    public void connect_to_1_12_x$doSetGhostRecipe(Recipe recipe, List<InventorySlot> slots) {
        setGhostRecipe(recipe, slots);
    }

    @Shadow
    private boolean isOffset() {
        return false;
    }

    @Shadow
    private void setVisible(boolean visible) {
    }

    @Shadow
	public void toggleVisibility() {
    }

    @Inject(
    	method = "init",
    	at = @At(
    		value = "RETURN"
    	)
    )
    private void init(int width, int height, Minecraft minecraft, boolean offset, CraftingInventory inventory, CallbackInfo ci) {
        Screen screen = Minecraft.getInstance().screen;
        if (screen instanceof InventoryMenuScreen) {
            this.menu = ((InventoryMenuScreen) screen).menu;
        }
    }

    @Inject(
    	method = "mouseClicked",
    	at = @At(
    		value = "INVOKE",
    		target = "Lnet/minecraft/client/RecipeCollection;m_6180894(Lnet/minecraft/crafting/recipe/Recipe;)Z"
    	),
    	cancellable = true
    )
    private void redirectM_6180894(int x, int y, int button, CallbackInfoReturnable<Boolean> ci) {
        if (MultiConnectHelper.getProtocolVersion() <= PacketLists.PROTOCOL_1_12) {
            this.setContainerRecipe(this.page.getLastClickedRecipe(), Objects.requireNonNull(this.page.getLastClickedRecipeCollection()));
            if (!this.isOffset() && button == 0)
            {
                this.setVisible(false);
            }
            ci.setReturnValue(Boolean.TRUE);
        }
    }

    @Unique
    private void setContainerRecipe(Recipe recipe, RecipeCollection recipes) {
        boolean flag = recipes.m_6180894(recipe);
        ResultInventory inventorycraftresult = null;

        if (this.menu instanceof CraftingTableMenu) {
            inventorycraftresult = ((CraftingTableMenu) this.menu).resultInventory;
        } else if (this.menu instanceof PlayerMenu) {
            inventorycraftresult = ((PlayerMenu) this.menu).resultInventory;
        }

        if (inventorycraftresult != null) {
            if (!flag && this.ghostRecipe.getRecipe() == recipe) {
                return;
            }

            if (!this.testClearCraftingGrid() && !this.minecraft.player.isCreative()) {
                return;
            }

            if (flag) {
                this.handleRecipeClicked(recipe, this.menu.slots, this.menu.networkId, inventorycraftresult);
            } else {
                List<PlaceRecipeC2SPacket.ItemMove> list2 = this.clearCraftingGrid(inventorycraftresult);
                this.setGhostRecipe(recipe, this.menu.slots);

                if (!list2.isEmpty()) {
                    short short1 = this.minecraft.player.menu.getNextActionNetworkId(this.minecraft.player.inventory);
                    Objects.requireNonNull(this.minecraft.getNetworkHandler()).sendPacket(new PlaceRecipeC2SPacket(this.menu.networkId, list2, Lists.newArrayList(), short1));

                    if (this.recipeBook.isFilteringCraftable()) {
                        this.minecraft.player.inventory.markDirty();
                    }
                }
            }

            if (!this.isOffset()) {
                this.toggleVisibility();
            }
        }
    }

    @Unique
    private void handleRecipeClicked(Recipe p_193950_1_, List<InventorySlot> p_193950_2_, int p_193950_3_, ResultInventory p_193950_4_) {
        boolean flag = p_193950_1_.matches(this.inventory, this.minecraft.world);
        int i = this.contents.countCrafts(p_193950_1_, (IntList) null);

        if (flag) {
            boolean flag1 = true;

            for (int j = 0; j < this.inventory.getSize(); ++j) {
                ItemStack itemstack = this.inventory.getStack(j);

                if (!itemstack.isEmpty() && i > itemstack.getSize()) {
                    flag1 = false;
                }
            }

            if (flag1) {
                return;
            }
        }

        int i1 = this.getStackSize(i, flag);
        IntList intlist = new IntArrayList();

        if (this.contents.canCraft(p_193950_1_, intlist, i1)) {
            int j1 = i1;

            for (Integer integer : intlist) {
                int k = integer;
                int l = StackedContents.getStackFromId(k).getMaxSize();

                if (l < j1) {
                    j1 = l;
                }
            }

            if (this.contents.canCraft(p_193950_1_, intlist, j1)) {
                List<PlaceRecipeC2SPacket.ItemMove> list2 = this.clearCraftingGrid(p_193950_4_);
                List<PlaceRecipeC2SPacket.ItemMove> list3 = Lists.<PlaceRecipeC2SPacket.ItemMove>newArrayList();
                this.placeRecipe(p_193950_1_, p_193950_2_, j1, intlist, list3);
                short short1 = this.minecraft.player.menu.getNextActionNetworkId(this.minecraft.player.inventory);
                Objects.requireNonNull(this.minecraft.getNetworkHandler()).sendPacket(new PlaceRecipeC2SPacket(this.menu.networkId, list2, list3, short1));
                this.minecraft.player.inventory.markDirty();
            }
        }
    }

    @Unique
    private List<PlaceRecipeC2SPacket.ItemMove> clearCraftingGrid(ResultInventory p_193954_1_) {
        this.ghostRecipe.clear();
        PlayerInventory inventoryplayer = this.minecraft.player.inventory;
        List<PlaceRecipeC2SPacket.ItemMove> list2 = Lists.<PlaceRecipeC2SPacket.ItemMove>newArrayList();

        for (int i = 0; i < this.inventory.getSize(); ++i) {
            ItemStack itemstack = this.inventory.getStack(i);

            if (!itemstack.isEmpty()) {
                while (itemstack.getSize() > 0) {
                    int j = inventoryplayer.indexOfItemStack(itemstack);

                    if (j == -1) {
                        j = inventoryplayer.getEmptySlot();
                    }

                    ItemStack itemstack1 = itemstack.copy();
                    itemstack1.setSize(1);

                    if (inventoryplayer.insertStack(j, itemstack1)) {
                        itemstack1.increase(1);
                    } else {
                        LOGGER.error("Can't find any space for item in inventory");
                    }

                    this.inventory.removeStack(i, 1);
                    int k = i + 1;
                    list2.add(new PlaceRecipeC2SPacket.ItemMove(itemstack1.copy(), k, j));
                }
            }
        }

        this.inventory.clear();
        p_193954_1_.clear();
        return list2;
    }

    @Unique
    private int getStackSize(int p_193943_1_, boolean p_193943_2_) {
        int i = 1;

        if (Screen.isShiftDown()) {
            i = p_193943_1_;
        } else if (p_193943_2_) {
            i = 64;

            for (int j = 0; j < this.inventory.getSize(); ++j) {
                ItemStack itemstack = this.inventory.getStack(j);

                if (!itemstack.isEmpty() && i > itemstack.getSize()) {
                    i = itemstack.getSize();
                }
            }

            if (i < 64) {
                ++i;
            }
        }

        return i;
    }

    @Unique
    private void placeRecipe(Recipe p_193013_1_, List<InventorySlot> p_193013_2_, int p_193013_3_, IntList p_193013_4_, List<PlaceRecipeC2SPacket.ItemMove> p_193013_5_) {
        int i = this.inventory.getWidth();
        int j = this.inventory.getHeight();

        if (p_193013_1_ instanceof ShapedRecipe) {
            ShapedRecipe shapedrecipes = (ShapedRecipe) p_193013_1_;
            i = shapedrecipes.getWidth();
            j = shapedrecipes.getHeight();
        }

        int j1 = 1;
        Iterator<Integer> iterator = p_193013_4_.iterator();

        for (int k = 0; k < this.inventory.getWidth() && j != k; ++k) {
            for (int l = 0; l < this.inventory.getHeight(); ++l) {
                if (i == l || !iterator.hasNext()) {
                    j1 += this.inventory.getWidth() - l;
                    break;
                }

                InventorySlot slot = p_193013_2_.get(j1);
                ItemStack itemstack = StackedContents.getStackFromId(iterator.next());

                if (itemstack.isEmpty()) {
                    ++j1;
                } else {
                    for (int i1 = 0; i1 < p_193013_3_; ++i1) {
                        PlaceRecipeC2SPacket.ItemMove cpacketrecipeplacement$itemmove = this.findSpot(j1, slot, itemstack);

                        if (cpacketrecipeplacement$itemmove != null) {
                            p_193013_5_.add(cpacketrecipeplacement$itemmove);
                        }
                    }

                    ++j1;
                }
            }

            if (!iterator.hasNext()) {
                break;
            }
        }
    }

    @Unique
    private PlaceRecipeC2SPacket.ItemMove findSpot(int p_193946_1_, InventorySlot p_193946_2_, ItemStack p_193946_3_) {
        PlayerInventory inventoryplayer = this.minecraft.player.inventory;
        int i = inventoryplayer.indexOf(p_193946_3_);

        if (i == -1) {
            return null;
        } else {
            ItemStack itemstack = inventoryplayer.getStack(i).copy();

            if (itemstack.isEmpty()) {
                LOGGER.error("Matched: " + p_193946_3_.getHoverName() + " with empty item.");
                return null;
            } else {
                if (itemstack.getSize() > 1) {
                    inventoryplayer.removeStack(i, 1);
                } else {
                    inventoryplayer.removeStackQuietly(i);
                }

                itemstack.setSize(1);

                if (p_193946_2_.getStack().isEmpty()) {
                    p_193946_2_.setStack(itemstack);
                } else {
                    p_193946_2_.getStack().increase(1);
                }

                return new PlaceRecipeC2SPacket.ItemMove(itemstack, p_193946_1_, i);
            }
        }
    }

    @Unique
    private boolean testClearCraftingGrid() {
        PlayerInventory inventoryplayer = this.minecraft.player.inventory;

        for (int i = 0; i < this.inventory.getSize(); ++i) {
            ItemStack itemstack = this.inventory.getStack(i);

            if (!itemstack.isEmpty()) {
                int j = inventoryplayer.indexOfItemStack(itemstack);

                if (j == -1) {
                    j = inventoryplayer.getEmptySlot();
                }

                if (j == -1) {
                    return false;
                }
            }
        }

        return true;
    }
}
