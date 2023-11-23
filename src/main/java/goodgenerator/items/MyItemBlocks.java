package goodgenerator.items;

import static goodgenerator.loader.Loaders.essentiaCell;
import static goodgenerator.loader.Loaders.yottaFluidTankCell;
import static goodgenerator.util.CharExchanger.tierName;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goodgenerator.main.GoodGenerator;
import goodgenerator.util.CharExchanger;
import gregtech.api.util.GT_LanguageManager;

public class MyItemBlocks extends ItemBlock {

    private final String mNoMobsToolTip = GT_LanguageManager
            .addStringLocalization("gt.nomobspawnsonthisblock", "Mobs cannot Spawn on this Block");
    private final String mNoTileEntityToolTip = GT_LanguageManager
            .addStringLocalization("gt.notileentityinthisblock", "This is NOT a TileEntity!");

    public MyItemBlocks(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(GoodGenerator.GG);
    }

    @Override
    public int getMetadata(int aMeta) {
        return aMeta;
    }

    @Override
    public String getUnlocalizedName(ItemStack aStack) {
        return this.field_150939_a.getUnlocalizedName() + "." + this.getDamage(aStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        return this.field_150939_a.getIcon(0, stack.getItemDamage());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return this.getIcon(stack, renderPass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_) {
        return this.field_150939_a.getIcon(0, p_77618_2_);
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (stack == null) return;
        tooltip.add(mNoMobsToolTip);
        tooltip.add(mNoTileEntityToolTip);

        if (Block.getBlockFromItem(stack.getItem()).equals(yottaFluidTankCell)) {
            StringBuilder cap = new StringBuilder();
            cap.append(" 1000000");
            for (int i = 0; i < stack.getItemDamage(); i++) cap.append("00");
            cap.append(" L");
            tooltip.add(
                    StatCollector.translateToLocal("YOTTankCell.tooltip.0")
                            + CharExchanger.formatNumber(cap.toString()));
            tooltip.add(StatCollector.translateToLocal("YOTTankCell.tooltip.1"));
        }

        if (Block.getBlockFromItem(stack.getItem()).equals(essentiaCell)) {
            tooltip.add(
                    StatCollector.translateToLocal("hatchTier.tooltip.0") + " " + tierName[stack.getItemDamage() + 4]);
        }
    }
}
