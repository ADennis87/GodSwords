package com.airborne.civilwar.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMucket extends TileEntity implements ISidedInventory{
	
	private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    
	protected ItemStack[] itemStack = new ItemStack[3];
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;

	@Override
	public int getSizeInventory() {
		return 64;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.itemStack[i];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.itemStack[par1] != null)
        {
            ItemStack itemstack;

            if (this.itemStack[par1].stackSize <= par2)
            {
                itemstack = this.itemStack[par1];
                this.itemStack[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.itemStack[par1].splitStack(par2);

                if (this.itemStack[par1].stackSize == 0)
                {
                    this.itemStack[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.itemStack[par1] != null)
        {
            ItemStack itemstack = this.itemStack[par1];
            this.itemStack[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.itemStack[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 2 ? false : (par1 == 1 ? TileEntityFurnace.isItemFuel(par2ItemStack) : true);
	}

	public void updateEntity()
    {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.burnTime > 0)
        {
            --this.burnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.burnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.burnTime = TileEntityFurnace.getItemBurnTime(this.itemStack[1]);
                if(this.worldObj.getBlockState(this.pos) == Blocks.fire && itemStack[1] == null){
                	this.currentItemBurnTime = this.burnTime = 1600;
                }

                if (this.burnTime > 0)
                {
                    flag1 = true;

                    if (this.itemStack[1] != null)
                    {
                        --this.itemStack[1].stackSize;

                        if (this.itemStack[1].stackSize == 0)
                        {
                            this.itemStack[1] = this.itemStack[1].getItem().getContainerItem(itemStack[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.cookTime;

                if (this.cookTime == 200)
                {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.cookTime = 0;
            }

            if (flag != this.burnTime > 0)
            {
                flag1 = true;            
               }
        }
    }
	private void smeltItem() {	
		if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.itemStack[0]);

            if (this.itemStack[2] == null)
            {
                this.itemStack[2] = itemstack.copy();
            }
            else if (this.itemStack[2].getItem() == itemstack.getItem())
            {
                this.itemStack[2].stackSize += itemstack.stackSize;
            }

            --this.itemStack[0].stackSize;

            if (this.itemStack[0].stackSize <= 0)
            {
                this.itemStack[0] = null;
            }
        }
	}

	public boolean isBurning() {
        return this.burnTime > 0;
	}

	protected boolean canSmelt(){
		if (this.itemStack[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.itemStack[0]);
            if(itemstack == null) return false;
            if (this.itemStack[2] == null) return true;
    		if(worldObj.getBlockState(this.pos) == Blocks.fire && this.itemStack[1] == null) return true;
    		if (!this.itemStack[2].isItemEqual(itemstack)) return false;
            int result = itemStack[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
	}
	@SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1)
    {
        return this.cookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.burnTime * par1 / this.currentItemBurnTime;
    }

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {		
	}

	@Override
	public int getField(int id) {
		switch (id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.cookTime;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
		switch (id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.cookTime = value;
        }
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.itemStack.length; ++i)
        {
            this.itemStack[i] = null;
        }
	}

	@Override
	public String getName() {
		return "container.mucket";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText("Display name???");
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		 if (direction == EnumFacing.DOWN && index == 1)
	        {
	            Item item = stack.getItem();

	            if (item != Items.water_bucket && item != Items.bucket)
	            {
	                return false;
	            }
	        }

	        return true;
	}

}
