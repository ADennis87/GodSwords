package com.airborne.civilwar.item;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.airborne.civilwar.CivilWar;
import com.airborne.civilwar.Registry;

public class ItemCanteen extends ItemFood{
	
	public ItemCanteen(int par2, boolean par4) {
		super(par2, par4);
		this.setCreativeTab(CivilWar.tabCivilWar);
		this.maxStackSize = 1;
		this.setAlwaysEdible();
		this.setHasSubtypes(true);
	}
	@Override
	public String getUnlocalizedName(ItemStack stack){
		String s = this.getDamage(stack) == 0 ? "empty" : "full";
		return "item." + s + "canteen";
	}
	
	 @Override
	public ItemStack onItemUseFinish(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
	    super.onItemUseFinish(par1ItemStack, par2World, par3EntityPlayer);
        if (!par2World.isRemote)
        {
            par3EntityPlayer.clearActivePotions();
            par3EntityPlayer.addChatMessage(new ChatComponentText("Healed!"));
        }
        return new ItemStack(Registry.canteen, 1, 0);
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.DRINK;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(this.getDamage(par1ItemStack) == 0){
    	MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (movingobjectposition == null)
        {
            return par1ItemStack;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.getBlockPos().getX();
                int j = movingobjectposition.getBlockPos().getY();
                int k = movingobjectposition.getBlockPos().getZ();

                if (!par3EntityPlayer.canPlayerEdit(new BlockPos(i, j, k), movingobjectposition.sideHit, par1ItemStack))
                {
                    return par1ItemStack;
                }

                if (par2World.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.water)
                {
                    --par1ItemStack.stackSize;

                    if (par1ItemStack.stackSize <= 0)
                    {
                        return new ItemStack(Registry.canteen, 1, 1);
                    }

                    if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Registry.canteen)))
                    {
                        par3EntityPlayer.dropItem(Registry.canteen, 1);
                    }
                }
            }
        }
    	}
    	else if(this.getDamage(par1ItemStack) == 1){
    		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
    	}
        return par1ItemStack;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }
}
