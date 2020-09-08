package de.zpenguin.polymancy.main;

import static de.zpenguin.polymancy.main.PolyRecipes.recipes;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.*;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.*;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.items.PolyItems;
import de.zpenguin.thaumicwands.util.research.ResearchHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;
import thaumcraft.common.lib.CommandThaumcraft;

@EventBusSubscriber
public class PolyResearch {

	public static ResearchCategory catPolymancy;
	public static final ResourceLocation iconPolymancy = new ResourceLocation(Polymancy.modID,"textures/research/icon_polymancy.png");
	public static final ResourceLocation backPolymancy = new ResourceLocation(Polymancy.modID,"textures/research/tab_polymancy.jpg");
	public static final ResourceLocation backOverlay = new ResourceLocation(Polymancy.modID,"textures/research/overlay_polymancy.png");
	public static final AspectList tabAspects = new AspectList().add(Aspect.MAGIC, 20)
																.add(Aspect.CRAFT, 15)
																.add(Aspect.AURA, 15)
																.add(Aspect.PLANT, 15)
																.add(Aspect.ENERGY, 10);

    private static ResearchStage[] stages;
    private static String[] parents;
    private static ResearchAddendum[] addenda;

	public static void init() {
		catPolymancy = ResearchCategories.registerCategory("POLYMANCY", "FIRSTSTEPS", tabAspects, iconPolymancy, backPolymancy, backOverlay);

        // Polymancy
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.POLYMANCY.stage.0")
                .build()
        };
        parents = new String[] {"FIRSTSTEPS"};
        ResearchHelper.makeResearch("POLYMANCY", "POLYMANCY", "Discovering Polymancy", 0, 0, iconPolymancy, stages, parents, ROUND, AUTOUNLOCK);

        // Infernal Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.ROD_INFERNAL.stage.0")
                .setConsumedItems(new ItemStack(Items.BLAZE_ROD))
                .setKnow(new Knowledge(THEORY, getCategory("THAUMATURGY"), 1),new Knowledge(OBSERVATION,getCategory("AUROMANCY"), 1), new Knowledge(OBSERVATION, catPolymancy, 1))
                .build(),
            new ResearchHelper.RSB()
                .setText("research.ROD_INFERNAL.stage.1")
                .setRecipes(recipes.get("ROD_INFERNAL.1"))
                .build()
        };
        parents = new String[] {"POLYMANCY", "ROD_SILVERWOOD"};
        ResearchHelper.makeResearch("ROD_INFERNAL", "POLYMANCY", "Infernal Rod", -1, -2, new ItemStack(PolyItems.itemWandRod), stages, parents);

        // Chorus Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.ROD_CHORUS.stage.0")
                .setConsumedItems(new ItemStack(Items.CHORUS_FRUIT_POPPED))
                .setKnow(new Knowledge(THEORY, getCategory("THAUMATURGY"), 1),new Knowledge(OBSERVATION,getCategory("AUROMANCY"), 1), new Knowledge(OBSERVATION, catPolymancy, 1))
                .build(),
            new ResearchHelper.RSB()
                .setText("research.ROD_CHORUS.stage.1")
                .setRecipes(recipes.get("ROD_CHORUS.1"))
                .build()
        };
        parents = new String[] {"POLYMANCY", "ROD_SILVERWOOD"};
        ResearchHelper.makeResearch("ROD_CHORUS", "POLYMANCY", "Chorus Rod", 1, -2, new ItemStack(PolyItems.itemWandRod,1, 1), stages, parents);

        // Primewell
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.PRIMEWELL.stage.0")
                .setKnow(new Knowledge(THEORY, getCategory("BASICS"), 1))
                .build(),
            new ResearchHelper.RSB()
                .setText("research.PRIMEWELL.stage.1")
                .setRecipes(recipes.get("PRIMEWELL.1"))
                .build()
        };
        parents = new String[] {"~POLYMANCY", "PRIMPEARL"};
        ResearchHelper.makeResearch("PRIMEWELL", "POLYMANCY", "Primal Inkwell", 0, 3, new ItemStack(PolyItems.itemPrimewell), stages, parents, HIDDEN);

        PolyCompat.initResearch();

	}

    public static ResearchCategory getCategory(String cat) {
        return ResearchCategories.getResearchCategory(cat);
    }


	@SubscribeEvent
	public static void commandEvent(CommandEvent ce) {
		if(ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload")) {
			new Thread(() -> {
				while(ResearchCategories.getResearchCategory("BASICS").research.containsKey("THAUMATURGY"))
				try {
						Thread.sleep(10L);
					}
				catch(InterruptedException e){
						e.printStackTrace();
					}

				init();
			}).start();
		}
	}

}
