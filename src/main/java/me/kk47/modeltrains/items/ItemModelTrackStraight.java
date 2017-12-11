package me.kk47.modeltrains.items;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.client.model.ModelTrackStraight;
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

public class ItemModelTrackStraight extends ItemModelTrackBase {

	private static ModelTrackStraight MODEL = new ModelTrackStraight();
	private static ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/blocks/track/track-straight.png");

	private final String[] metadataNames = new String[]{"north", "east"};

	public ItemModelTrackStraight() {
		super("track-straight");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ModelTrains.creativeTab);
		if(UERIMod.isClientSided) {
			this.addRenderable(new UERITechne(MODEL, TEXTURE));
		}
	}
	
	public int getMaxDamage() {
		return 1;
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
			varientNames[i] = new ResourceLocation(Data.MODID, this.getUnlocalizedName().substring(5) + "-" + metadataNames[i]);
		}
		ModelBakery.registerItemVariants(ModItems.trackStraight, varientNames);
	}
}
