package com.airborne.civilwar.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.airborne.civilwar.Registry;

public class ItemMucket extends ItemDefault{
	public ItemMucket() {
		super();
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos pos, EnumFacing side, float par8, float par9, float par10)
    {
        if (side == EnumFacing.DOWN)
        {
            pos.down();
        }

        if (side == EnumFacing.UP)
        {
            pos.up();
        }

        if (side == EnumFacing.NORTH)
        {
            pos.south();
        }

        if (side == EnumFacing.SOUTH)
        {
            pos.north();
        }

        if (side == EnumFacing.EAST)
        {
            pos.west();
        }

        if (side == EnumFacing.WEST)
        {
            pos.east();
        }

        if (!par2EntityPlayer.canPlayerEdit(pos, side, par1ItemStack))
        {
            return false;
        }
        else
        {
            if (par3World.isAirBlock(pos))
            {
            	par3World.setBlockState(pos, Registry.mucket.getDefaultState());
            }

            --par1ItemStack.stackSize;
            return true;
        }
    }
}
