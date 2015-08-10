package com.airborne.civilwar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.airborne.civilwar.gui.ContainerCannon;
import com.airborne.civilwar.gui.ContainerHaversack;
import com.airborne.civilwar.gui.ContainerMucket;
import com.airborne.civilwar.gui.GuiCannon;
import com.airborne.civilwar.gui.GuiHaversack;
import com.airborne.civilwar.gui.GuiMucket;
import com.airborne.civilwar.tileentity.TileEntityCannon;
import com.airborne.civilwar.tileentity.TileEntityMucket;

public class CivilWarCommonProxy implements IGuiHandler{
	
	public void registerRenderThings(){
		
	}
	
	public int addArmor(String s){
		return 0;
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getTileEntity(new BlockPos(x, y, z));
		switch(ID){
		case 6: return new ContainerCannon(player.inventory, (TileEntityCannon) tile_entity);
		case 8: return new ContainerMucket(player.inventory, (TileEntityMucket) tile_entity);
		case 9: return new ContainerHaversack(player.inventory, (IInventory)tile_entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getTileEntity(new BlockPos(x, y, z));
		switch(ID){
		case 6: return new GuiCannon(player.inventory, (TileEntityCannon) tile_entity);
		case 8: return new GuiMucket(player.inventory, (TileEntityMucket) tile_entity);
		case 9: return new GuiHaversack(player.inventory, (IInventory)tile_entity);
		}
		return null;
	}
}