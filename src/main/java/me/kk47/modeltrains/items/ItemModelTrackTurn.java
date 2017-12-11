package me.kk47.modeltrains.items;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.client.model.ModelTrackTurn;
import me.kk47.ueri.UERIMod;
import me.kk47.ueri.UERITechne;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemModelTrackTurn extends ItemModelTrackBase {

	private static ModelTrackTurn MODEL = new ModelTrackTurn();
	private static ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/blocks/track/track-turn.png");

	private final String[] metadataNames = new String[]{"east", "north", "west", "south"};
	
	public ItemModelTrackTurn() {
		super("track-corner");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ModelTrains.creativeTab);
		if(UERIMod.isClientSided) {
			this.addRenderable(new UERITechne(MODEL, TEXTURE));
		}
	}
	
	@Override
	public int getMaxDamage() {
		return 4;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack){
		return super.getUnlocalizedName() + "-" + metadataNames[stack.getItemDamage()];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList list){
		if(this.isInCreativeTab(tab)) {
			for(int i = 0; i < metadataNames.length; i++){
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	public void registerVarients(){
		ResourceLocation[] varientNames = new ResourceLocation[metadataNames.length];
		for(int i = 0; i < varientNames.length; i++){
			varientNames[i] = new ResourceLocation(Data.MODID, getUnlocalizedName().substring(5) + "-" + metadataNames[i]);
		}
		ModelBakery.registerItemVariants(ModItems.trackStraight, varientNames);
	}
}
