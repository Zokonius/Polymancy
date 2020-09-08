package de.zpenguin.polymancy.compat.embers;

import static de.zpenguin.polymancy.main.PolyRecipes.recipes;
import static thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType.*;
import static thaumcraft.api.research.ResearchEntry.EnumResearchMeta.*;

import com.google.common.collect.Lists;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.compat.PolyCompat.IPolyCompat;
import de.zpenguin.polymancy.compat.embers.cards.CardBore;
import de.zpenguin.polymancy.items.PolyItems;
import de.zpenguin.polymancy.items.scribes.ItemEmberwell;
import de.zpenguin.polymancy.main.PolyRecipes;
import de.zpenguin.thaumicwands.util.research.ResearchHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import teamroots.embers.RegistryManager;
import teamroots.embers.api.alchemy.AspectList.AspectRangeList;
import teamroots.embers.recipe.AlchemyRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;

public class EmbersCompat implements IPolyCompat {

	public static Item itemEmberwell = new ItemEmberwell("item_emberwell");

    private static ResearchStage[] stages;
    private static String[] parents;
    private static ResearchAddendum[] addenda;

	@Override
	public void preInit() {

	}

	@Override
	public void initRecipes() {

        AspectList crystals;
        crystals = new AspectList().add(Aspect.AIR, 3).add(Aspect.FIRE, 3).add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3);
        PolyRecipes.addShapedArcaneRecipe("CAP_DAWNSTONE.1", "CAP_DAWNSTONE@1", new ItemStack(PolyItems.itemWandCap, 1, 5), 10, crystals, "NNN", "N N", 'N', "nuggetDawnstone");

        crystals = new AspectList().add(Aspect.AIR,3).add(Aspect.FIRE, 3).add(Aspect.ORDER,3);
        PolyRecipes.addShapelessArcaneRecipe("EMBERWELL.1", "EMBERWELL@1", new ItemStack(itemEmberwell), 10, crystals, PolyCompat.getItem("embers:ember_jar", 0), PolyCompat.getItem("embers:dust_ember", 0), "feather");

        AspectList aspects;
        aspects = new AspectList().add(Aspect.FIRE, 30).add(Aspect.ENERGY, 30).add(Aspect.EARTH, 30).add(Aspect.MECHANISM, 20).add(Aspect.DARKNESS, 15);
        PolyRecipes.addInfusionRecipe("ROD_ARCHAIC.1","ROD_ARCHAIC@1", new ItemStack(PolyItems.itemWandRod, 1, 6), 3, new ItemStack(RegistryManager.wildfire_core),  aspects, new ItemStack(RegistryManager.ancient_motive_core), new ItemStack(RegistryManager.archaic_brick), new ItemStack(RegistryManager.archaic_brick), new ItemStack(RegistryManager.archaic_brick), new ItemStack(RegistryManager.archaic_brick), new ItemStack(RegistryManager.brick_caminite), new ItemStack(RegistryManager.brick_caminite), new ItemStack(RegistryManager.crystal_ember), new ItemStack(RegistryManager.crystal_ember));

        RecipeRegistry.alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 24, 48).setRange("copper", 24, 48), Ingredient.fromStacks(new ItemStack(PolyItems.itemWandCap,1,5)), Lists.newArrayList(Ingredient.fromItem(RegistryManager.shard_ember),Ingredient.fromItem(RegistryManager.crystal_ember),Ingredient.fromItem(RegistryManager.shard_ember),Ingredient.fromItem(RegistryManager.shard_ember)), new ItemStack(PolyItems.itemWandCap,1,6)));
        RecipeRegistry.alchemyRecipes.add(new AlchemyRecipe(new AspectRangeList().setRange("dawnstone", 24, 48).setRange("copper", 24, 48), Ingredient.fromStacks(new ItemStack(PolyItems.itemWandRod,1,6)), Lists.newArrayList(Ingredient.fromItem(RegistryManager.crystal_ember),Ingredient.fromItem(RegistryManager.wildfire_core),Ingredient.fromItem(RegistryManager.crystal_ember),Ingredient.fromItem(RegistryManager.crystal_ember)), new ItemStack(PolyItems.itemWandRod,1,7)));

	}

	@Override
	public void initResearch() {

        // Embers
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.EMBERS.stage.0")
                .build()
        };
        parents = new String[] {"FIRSTSTEPS"};
        ResearchHelper.makeResearch("EMBERS", "POLYMANCY", "Discovering Ember", 3, -6, PolyCompat.getItem("embers:codex", 0), stages, parents, ROUND, AUTOUNLOCK);

        // Emberwell
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.EMBERWELL.stage.0")
                .setKnow(new Knowledge(OBSERVATION, PolyCompat.getCategory("BASICS"), 1), new Knowledge(OBSERVATION, PolyCompat.getCategory("POLYMANCY"), 1))
                .build(),
            new ResearchHelper.RSB()
                .setText("research.EMBERWELL.stage.1")
                .setRecipes(recipes.get("EMBERWELL.1"))
                .build()
        };
        parents = new String[] {"EMBERS"};
        ResearchHelper.makeResearch("EMBERWELL", "POLYMANCY", "Ember Scribing Tools", 4, -4, new ItemStack(itemEmberwell), stages, parents);


        // Archaic Rod
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.ROD_ARCHAIC.stage.0")
                .setRequiredCraft(PolyCompat.getItem("embers:wildfire_core", 0))
                .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("POLYMANCY"), 1), new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 1))
                .build(),
                new ResearchHelper.RSB()
                .setText("research.ROD_ARCHAIC.stage.1")
                .setRecipes(recipes.get("ROD_ARCHAIC.1"))
                .build()
        };
        parents = new String[] {"ROD_GREATWOOD", "EMBERS"};
        ResearchHelper.makeResearch("ROD_ARCHAIC", "POLYMANCY", "Archaic Rod", 5, -6, new ItemStack(PolyItems.itemWandRod, 1, 7), stages, parents);

        // Dawnstone Cap
        stages = new ResearchStage[] {
            new ResearchHelper.RSB()
                .setText("research.CAP_DAWNSTONE.stage.0")
                .setRequiredCraft(PolyCompat.getItem("embers:nugget_dawnstone", 0))
                .setKnow(new Knowledge(THEORY, PolyCompat.getCategory("THAUMATURGY"), 1), new Knowledge(OBSERVATION, PolyCompat.getCategory("POLYMANCY"), 1))
                .build(),
                new ResearchHelper.RSB()
                .setText("research.CAP_DAWNSTONE.stage.1")
                .setRecipes(recipes.get("CAP_DAWNSTONE.1"))
                .build()
        };
        parents = new String[] {"ROD_ARCHAIC", "CAP_BRASS", "CAP_COPPER"};
        ResearchHelper.makeResearch("CAP_DAWNSTONE", "POLYMANCY", "Dawnstone Caps", 6, -5, new ItemStack(PolyItems.itemWandCap, 1, 6), stages, parents);

        ResearchHelper.registerCards(CardBore.class);

        ResearchHelper.makeAid(RegistryManager.ember_bore, CardBore.class);


	}

}
